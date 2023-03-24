package RJutils;

import java.io.*;
import java.nio.charset.Charset;


public class Rj_scanner {
    private final Reader reader;
    private StringBuilder token = new StringBuilder(0);
    private char[] buffer = new char[8];
    private int bL = 0;
    private int p = 0;
    private boolean txt = false;


    private Rj_scanner(Reader in) {
        reader = in;
    }

    public Rj_scanner(InputStream in) {
        this(new InputStreamReader(in));
    }

    public Rj_scanner(InputStream in, Charset code) {
        this(new InputStreamReader(in, code));
    }

    public Rj_scanner(String f, Charset code) throws IOException {
        this(new InputStreamReader(new FileInputStream(f), code));
        txt = true;
    }

    public Rj_scanner(String str) {
        this(new CharArrayReader(str.toCharArray()));
    }

    public void fillBuff() throws IOException {
        bL = reader.read(buffer);
        //for(int i = 0;i<bL;i++)System.out.println(Character.getType(buffer[i]));
        //System.out.println(bL);
        p = 0;
    }

//    public void mark()throws IOException{
//        reader.mark(1);
//    }
//    public void reset()throws IOException{
//        reader.reset();
//    }

    public String readLine() throws IOException {
        token.setLength(0);
        while (bL != -1) {
            for (; p < bL; p++) {
                //if(Character.getType(buffer[p]) != Character.LINE_SEPARATOR && Character.getType(buffer[p]) != Character.CONTROL){
                if(buffer[p] == '\r'){
                    p+=System.lineSeparator().length();
                    return token.toString();
                }
                else if (buffer[p] == '\n') {
                    p++;
                    return token.toString();
                } else {
                    token.append(buffer[p]);
                }
            }
            int tt = p % 8;
            fillBuff();
            p = tt;
        }
        if (!token.isEmpty()) {
            p++;
            return token.toString();
        }
        return null;
    }


    public String nextWord() throws IOException {
        token.setLength(0);
        while (bL != -1) {
            for (; p < bL; p++) {
                if (Character.isLetter(buffer[p]) || Character.getType(buffer[p]) == Character.DASH_PUNCTUATION || buffer[p] == '\'') {
                    token.append(buffer[p]);
                } else {
                    if (!token.isEmpty()) {
                        return token.toString();
                    }
                }
            }
            fillBuff();
        }
        if (!token.isEmpty()) {
            return token.toString();
        } else {
            return null;
        }
    }

    public String nextInt() throws IOException {
        token.setLength(0);

        while (bL != -1) {
            for (; p < bL; p++) {
                if (Character.isDigit(buffer[p]) || buffer[p] == '-') {
                    token.append(buffer[p]);
                } else {
                    if (!token.isEmpty() && !token.toString().equals("-")) {
                        return token.toString();
                    }
                }
            }
            fillBuff();
        }
        if (!token.isEmpty()) {
            return token.toString();
        } else {
            return null;
        }
    }

    public void close() throws IOException {
        reader.close();
    }

}
