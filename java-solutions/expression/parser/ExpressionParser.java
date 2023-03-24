package expression.parser;

import expression.*;

import java.util.*;
import java.util.function.BinaryOperator;


public class ExpressionParser implements TripleParser {
    @Override
    public TripleExpression parse(String expression) {
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
        ADD(Add::new), SUBS(Subtract::new), MULTY(Multiply::new), DIV(Divide::new),
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
    private record  Lex(LexType type, String value) { }
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

    private static List<Lex> lexAnalyze(String expText) {
        List<Lex> lexs = new ArrayList<>();
        int psp = 0;
        int pos = 0;
        boolean flag = true;
        while (pos < expText.length()) {
            while (pos < expText.length() && Character.isWhitespace(expText.charAt(pos)))
                pos++;
            if(pos >= expText.length())
                break;

            int l = br.checkLexAt(expText, pos);
            String t = expText.substring(pos, pos + l);
            pos += l;
            switch (t) {
                case "(" -> {lexs.add(new Lex(LexType.LB, t)); flag = true; psp++;continue;}
                case ")" -> {lexs.add(new Lex(LexType.RB, t)); psp--;continue;}
                case "set" -> {lexs.add(new Lex(LexType.SET, t)); flag = true; continue;}
                case "clear" -> {lexs.add(new Lex(LexType.CLEAR, t)); flag = true; continue;}
                case "+" -> {lexs.add(new Lex(LexType.ADD, t)); flag = true; continue;}
                case "-" -> {
                    if (flag) {
                        int len = 0;
                        while(len+pos<expText.length() && Character.isDigit(expText.charAt(pos+len)))
                            len++;
                        if(len>0){
                            lexs.add(new Lex(LexType.CON, expText.substring(pos-1,pos+len)));
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
                    while(len+pos<expText.length() && Character.isDigit(expText.charAt(pos+len)))
                        len++;
                    if(len>0){
                        lexs.add(new Lex(LexType.CON, expText.substring(pos,pos+len)));
                        pos+=len;
                    }
                    //System.out.println(lexs.size());
                }
            }
            flag = false;
        }
        lexs.add(new Lex(LexType.EOF, "EOF"));
        if(psp!=0)
            throw new RuntimeException();
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


    private static MultiExpr switchedLayerRec(LexHolder lexs, int switcher) {
        int now = switcher % priorLayers.size();

        if (now == priorLayers.size()-1) {
                Lex tmp = lexs.get();
                MultiExpr res;
                return switch (tmp.type) {
                    case SUBS:
                        res = switchedLayerRec(lexs, switcher);
                        yield new Minus(res);
                    case CON:
                        yield new Const(Integer.parseInt(tmp.value));
                    case VAR:
                        yield new Variable(tmp.value);
                    case LB:
                        res = switchedLayerRec(lexs, switcher+1);
                        tmp = lexs.get();
                        if (tmp.type != LexType.RB) throw new RuntimeException("INVALID EXPRESSION -> ) not found at "+ lexs.getNowAt());

                        yield res;
                    default:
                        throw new RuntimeException("INVALID EXPRESSION ");
                };
            }else{
                MultiExpr res = switchedLayerRec(lexs, switcher+1);
                for(;;) {
                    Lex tmp = lexs.get();
                    if (priorLayers.get(now).contains(tmp.type)){
                        res = tmp.type.getBuilder().apply(res, switchedLayerRec(lexs, switcher+1));
                    }else if(tmp.type == LexType.CON || tmp.type == LexType.VAR){
                        throw new RuntimeException("INVALID EXPRESSION -> unexpected lex found at "+ lexs.getNowAt());
                    }else{
                        lexs.getPrev();
                        return res;
                    }
                }
            }
        }
}
