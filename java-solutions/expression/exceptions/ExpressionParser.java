package expression.exceptions;

import expression.*;

import java.util.*;
import java.util.function.BinaryOperator;


public class ExpressionParser implements TripleParser {
    @Override
    public TripleExpression parse(String expression) throws Exception{
        return switchedLayerRec(new LexHolder(lexAnalyze(expression)), 0);
    }
    private static class Bor{
        public Bor(String ... s){
            addListOfLex(s);
        }
        static class Node{
            private final Map<Character, Node> children = new HashMap<>();
            private boolean end = false;



            public boolean contains(char c){
                return children.containsKey(c);
            }

            public void add(char c){
                children.putIfAbsent(c, new Node());
            }

            public Node getNext(char c){
                return children.get(c);
            }

            public boolean isEmpty(){
                return children.isEmpty();
            }

            public boolean isEnd() {
                return end;
            }
        }
        private final Node root = new Node();

        private void addLex(String s){
            Node tmp = root;
            for(var t : s.toCharArray()){
                tmp.add(t);
                tmp = tmp.getNext(t);
            }
            tmp.end = true;
        }

        public void addListOfLex(String ...lexs){
            for (var t : lexs){
                addLex(t);
            }
        }

        public int checkLexAt(String str, int pos){
            Node tmp = root;
            int length = 0;
            while(!tmp.isEmpty() && pos+length < str.length()){
                char tmpC = str.charAt(pos + length);
                if(tmp.contains(tmpC)){
                    tmp = tmp.getNext(tmpC);
                }else{
                    if(tmp.isEnd())
                        return length;
                    else
                        return 0;
                }
                length++;
            }
            if(tmp.isEmpty())
                return length;
            else
                return 0;
        }

    }
    private static final Bor br = new Bor("(",")","-","+","*","/","x","y","z", "set", "clear");//////////////////////////

    public enum LexType {/////////////////////////////////////////////////
        LB, RB,
        GCD(Gcd::new), LCM(Lcm::new), NEW1(New1::new), NEW2(New2::new), SET(Set1::new), CLEAR(Clear::new),
        ADD(CheckedAdd::new), SUBS(CheckedSubtract::new), MULTY(CheckedMultiply::new), DIV(CheckedDivide::new),
        CON, VAR,
        EOF;

        private final BinaryOperator<MultiExpr> builder;
        LexType(BinaryOperator<MultiExpr> builder){
            this.builder = builder;
        }
        LexType(){
            this.builder = null;
        }

        public BinaryOperator<MultiExpr> getBuilder() {
            return builder;
        }
    }
    private record Lex(LexType type, String value) { }
    private static class LexHolder {
        private int nowAt = 0;
        public List<Lex> lexs;
        public LexHolder(List<Lex> lexemes) {
            this.lexs = lexemes;
        }
        public Lex get() {
            return lexs.get(nowAt++);
        }

        public int getNowAt() {
            return nowAt;
        }

        public void getPrev() {
            nowAt--;
        }
    }
    private  static List<Lex> lexAnalyze(String str) throws Exception{
        List<Lex> lexs = new ArrayList<>();
        int psp = 0;
        int pos = 0;
        boolean flag = true;
        while (pos < str.length()) {
            while (pos < str.length() && Character.isWhitespace(str.charAt(pos)))
                pos++;
            if(pos >= str.length())
                break;
            int l = br.checkLexAt(str, pos);
            String t = str.substring(pos, pos + l);
            pos += l;
            switch (t) {
                case "(" -> {lexs.add(new Lex(LexType.LB, t)); flag = true; psp++; continue;}
                case ")" -> {lexs.add(new Lex(LexType.RB, t)); psp--; continue;}
                case "set","clear" -> {
                    switch (t) {
                        case "set" -> lexs.add(new Lex(LexType.SET, t));
                        case "clear" -> lexs.add(new Lex(LexType.CLEAR, t));
                    }
                    flag = true;
                    if(pos<str.length())
                        if(str.charAt(pos)!='(' && str.charAt(pos)!='-'
                                && !Character.isWhitespace(str.charAt(pos)))
                            throw new ParserExc("INVALID EXPRESSION -> Bad Sym at "+pos);
                    continue;
                }
                case "+" -> {lexs.add(new Lex(LexType.ADD, t)); flag = true; continue;}
                case "-" -> {
                    if (flag) {
                        int len = 0;
                        while(len+pos<str.length() && Character.isDigit(str.charAt(pos+len)))
                            len++;
                        if(len>0){
                            lexs.add(new Lex(LexType.CON, str.substring(pos-1,pos+len)));
                            pos+=len;
                            break;
                        }
                    }
                    lexs.add(new Lex(LexType.SUBS, t));
                    flag = true;
                    continue;
                }
                case "*" -> {lexs.add(new Lex(LexType.MULTY, t)); flag = true; continue;}
                case "/" -> {lexs.add(new Lex(LexType.DIV, t)); flag = true; continue;}
                case "x", "y", "z" -> lexs.add(new Lex(LexType.VAR, t));
                default -> {
                    int len = 0;
                    while(len+pos<str.length() && Character.isDigit(str.charAt(pos+len)))
                        len++;
                    if(len>0){
                        lexs.add(new Lex(LexType.CON, str.substring(pos,pos+len)));
                        pos+=len;
                    }else {
                        throw new ParserExc("INVALID EXPRESSION -> not a num found at "+pos);
                    }
                    //System.out.println(lexs.size());
                }
            }
            flag = false;
        }
        lexs.add(new Lex(LexType.EOF, "EOF"));
        if(psp!=0)
            throw new ParserExc("INVALID EXPRESSION -> Bad PSP");
        return lexs;
    }


    private static final List<Set<LexType>> priorLayers = new ArrayList<>(
            List.of(
                    Set.of(LexType.GCD, LexType.LCM, LexType.NEW1, LexType.NEW2, LexType.SET, LexType.CLEAR),
                    Set.of(LexType.ADD, LexType.SUBS),
                    Set.of(LexType.MULTY, LexType.DIV),
                    Set.of()
            )
    );

    private static MultiExpr switchedLayerRec(LexHolder lexemes, int switcher) throws Exception {
        int now = switcher % priorLayers.size();
        if (now == priorLayers.size()-1) {
                Lex tmp = lexemes.get();
                MultiExpr res;
                return switch (tmp.type) {
                    case SUBS:
                        res = switchedLayerRec(lexemes, switcher);
                        yield new CheckedNegate(res);
                    case CON:
                        yield new Const(Integer.parseInt(tmp.value));
                    case VAR:
                        yield new Variable(tmp.value);
                    case LB:
                        res = switchedLayerRec(lexemes, switcher+1);
                        tmp = lexemes.get();
                        if (tmp.type != LexType.RB) throw new ParserExc("INVALID EXPRESSION -> ) not found at lexId: "+ lexemes.getNowAt());
                        yield res;
                    default:
                        throw new ParserExc("INVALID EXPRESSION -> unexpected lexeme (const or var expected) at lexId: "+ lexemes.getNowAt());
                };
            }else{
                MultiExpr res = switchedLayerRec(lexemes, switcher+1);
                for(;;) {
                    Lex lexeme = lexemes.get();
                    if (priorLayers.get(now).contains(lexeme.type)){
                        res = lexeme.type.getBuilder().apply(res, switchedLayerRec(lexemes, switcher+1));
                    }else if(lexeme.type == LexType.CON || lexeme.type == LexType.VAR){
                        throw new ParserExc("INVALID EXPRESSION -> unexpected lexeme (operation) at lexId: "+ lexemes.getNowAt());
                    }else {
                        lexemes.getPrev();
                        return res;
                    }
                }
            }
        }
}
