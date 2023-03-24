package RJutils;

import java.io.*;
import java.nio.charset.Charset;




public class FastScanner {
    private final Reader reader;
    private StringBuilder token = new StringBuilder(0);
    private char[] buffer = new char[8099];
    private int bL = 0;
    private int p = 0;



    private FastScanner(Reader in){
        reader = in;
    }
    public FastScanner(InputStream in){
        this(new InputStreamReader(in));
    }
    public FastScanner(InputStream in, Charset code){
        this(new InputStreamReader(in, code));
    }
    public FastScanner(String f, Charset code) throws IOException{ this(new InputStreamReader(new FileInputStream(f), code)); }
    public FastScanner(String str){
        this(new CharArrayReader(str.toCharArray()));
    }

    public void fillBuff()throws IOException{
        bL = reader.read(buffer);
        p = 0;
    }
    public boolean hasNextLine() throws IOException{
        //fillBuff();
        p++;
        return bL != -1;
    }

    /*public String readLine()throws IOException{
        token.setLength(0);
        while (bL != -1){
            for(;p<bL;p++){
                //if(Character.getType(buffer[p]) != Character.LINE_SEPARATOR && Character.getType(buffer[p]) != Character.CONTROL){
                if(buffer[p] != '\n'){
                    token.append(buffer[p]);
                }
                else{
                    p++;
                    return token.toString();
                }
            }
            fillBuff();
        }
        if(!token.isEmpty()){
            p++;
            return token.toString();
        }
        return null;
    }*/

    public boolean hasNextWord() throws IOException{
        while(bL != -1){
            for(;p < bL;p++){
                if(buffer[p] == '\r'){
                    p+=System.lineSeparator().length()-1;
                    return false;
                }
                else if(buffer[p] == '\n')
                    return false;
                else if(Character.isLetter(buffer[p]) || Character.getType(buffer[p]) == Character.DASH_PUNCTUATION || buffer[p] == '\'')
                    return true;
            }
            fillBuff();
        }
        return false;
    }

    public String nextWord() throws IOException{
        token.setLength(0);
        while(bL != -1){
            for(;p < bL;p++){
                if(Character.isLetter(buffer[p]) || Character.getType(buffer[p]) == Character.DASH_PUNCTUATION || buffer[p] == '\''){
                    token.append(buffer[p]);
                }else{
                    return token.toString();
                }
            }
            fillBuff();
        }
        return token.toString();
    }

    public String nextInt() throws IOException{
        token.setLength(0);

        while(bL != -1) {
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
        if(!token.isEmpty()){
            return token.toString();
        }
        else {
            return null;
        }
    }

    public void close()throws IOException{
        reader.close();
    }

}
