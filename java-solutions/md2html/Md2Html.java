package md2html;
import markupNew.*;
import RJutils.WriterRJ;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Md2Html {
    static private StringBuilder nowBuff = new StringBuilder();
    static private HashSet<String> tags = new HashSet<>();
    static private BufferedReader reader;
    static private char nowChar;
    public static String checkInCodeSym(char c){
        return switch (c) {
            case '<' -> "&lt;";
            case '>' -> "&gt;";
            case '&' -> "&amp;";
            default -> String.valueOf(c);
        };
    }
    static boolean isEnd() throws IOException{
        return switch (nowChar) {
            case (int)(char)-1 -> true;
            case (int) '\r' -> {
                char was = nowChar;
                reader.mark(10);
                int temp;
                temp = reader.read();
                nowChar = (char)temp;
                if(temp == '\r' || temp == -1)
                    yield true;

                if (temp == (int)'\n'){
                    temp = reader.read();
                    nowChar = (char)temp;
                    if (temp == (int)'\r' || temp == '\n' || temp == -1) {
                        yield true;
                    }
                    //System.err.println(":(");
                    reader.reset();
                    nowChar = was;
                    yield false;
                }

                reader.reset();
                nowChar = was;
                yield false;
            }
            case (int) '\n' -> {
                char was = nowChar;
                reader.mark(10);
                int temp;
                temp = reader.read();
                nowChar = (char)temp;
                if (temp == '\n'|| temp == -1) {
                    yield true;
                }else{
                    reader.reset();
                    nowChar = was;
                    yield false;
                }

            }
            default -> false;
        };
    }
    static boolean checkTag(String tag){
        return tags.contains(tag);
    }
    static String getTag() throws IOException{
        char need = nowChar;
        StringBuilder temp = new StringBuilder();
        reader.mark(10);
        while(nowChar == need) {
            temp.append(need);
            nowChar = (char)reader.read();
        }
        return temp.toString();
    }
    static int getHeaderLvl() throws IOException{
        if(nowChar=='#') {
            int lvl = 0;
            reader.mark(7);
            while (nowChar == '#') {
                lvl++;
                nowChar = (char) reader.read();
            }
            if (nowChar == ' ') {
                nowChar = (char)reader.read();
                return lvl;
            } else {
                reader.reset();
                nowChar = '#';
                return 0;
            }
        }else{
            return 0;
        }
    }
    static private void ignoreVoid() throws IOException{
        while(Character.getType(nowChar = (char)reader.read()) == Character.CONTROL){}
    }
    static private boolean isSpecSym(char c){
        return switch (c) {
            case '+' -> true;
            case '*' -> true;
            case '_' -> true;
            case '-' -> true;
            case '`' -> true;
            default -> false;
        };
    }
    static private Result getData(String endTag) throws IOException{
        List<Markupable> data = new ArrayList<>();
        String tempTag;
        boolean close = false;
        nowBuff.setLength(0);
        while(!isEnd()){
            if(isSpecSym(nowChar)){
                tempTag = getTag();
                if(checkTag(tempTag)){
                    if(!tempTag.equals(endTag)) {
                        data.add(new Text(nowBuff.toString()));
                        nowBuff.setLength(0);
                        switch (tempTag) {
                            case "++" -> data.add(new Underline(getData("++"), "++"));
                            case "*" -> data.add(new Emphasis(getData("*"), "*"));
                            case "_" -> data.add(new Emphasis(getData("_"), "_"));
                            case "**" -> data.add(new StrongEmphasis(getData("**"), "**"));
                            case "__" -> data.add(new StrongEmphasis(getData("__"), "__"));
                            case "--" -> data.add(new Strikeout(getData("--"), "--"));
                            case "`" -> data.add(new Code(getData("`"), "`"));

                            default -> throw new RuntimeException();
                        }
                        continue;
                    }else{
                        close = true;
                        data.add(new Text(nowBuff.toString()));
                        nowBuff.setLength(0);
                        break;
                    }
                }else{
                    nowBuff.append(tempTag);
                    continue;
                }
            }else{
                if(nowChar!='\\') {
                    nowBuff.append(checkInCodeSym(nowChar));
                }else{
                    nowBuff.append(nowChar = (char)reader.read());
                }
            }
            nowChar = (char)reader.read();
        }
        if(!nowBuff.isEmpty()){
            data.add(new Text(nowBuff.toString()));
            nowBuff.setLength(0);
        }
        return new Result(data, close);
    }
    public static void main(String[] args) throws IOException {
        ArrayList<Markupable> ans = new ArrayList<>();
        try{
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(args[0]), StandardCharsets.UTF_8));
           /* String str = "skd\n" +
                    "sdf\n" +
                    "\n" +
                    "fkfkfk";
            reader = new BufferedReader(new StringReader(str));*/
            tags.add("++");
            tags.add("**");
            tags.add("*");
            tags.add("__");
            tags.add("_");
            tags.add("--");
            tags.add("`");
            ignoreVoid();
            while (!isEnd()) {
                int level = getHeaderLvl();
                if (level == 0) {
                    ans.add(new Paragraph(getData(null)));
                } else {
                    ans.add(new Header(getData(null), level));
                }
                ignoreVoid();
            }
        }catch (IOException e) {
            System.err.println(e.getMessage());
        } finally {
            reader.close();
        }
        StringBuilder test = new StringBuilder();
        try (WriterRJ wrj = new WriterRJ(args[1], StandardCharsets.UTF_8)){
                ans.forEach(t -> {
                try {
                    test.setLength(0);
                    t.toHtml(test);
                    wrj.writeln(test.toString());
                }catch (IOException e) {
                    System.err.println(e.getMessage());
                }
            });
        }catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
