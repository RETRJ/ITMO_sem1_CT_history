//import RJutils.*;



import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;



class Rj_scanner2 {
    private final Reader reader;
    private final StringBuilder token = new StringBuilder(0);
    private final char[] buffer = new char[8];
    private int bL = 0;
    private int p = 0;
    private boolean txt = false;



    private Rj_scanner2(Reader in){
        reader = in;
    }

    public Rj_scanner2(InputStream in){
        this(new InputStreamReader(in));
    }
    public Rj_scanner2(InputStream in, Charset code){
        this(new InputStreamReader(in, code));
    }

    public Rj_scanner2(File f, Charset code) throws IOException{
        this(new InputStreamReader(new FileInputStream(f), code));
        txt = true;
    }

    public Rj_scanner2(String str){
        this(new CharArrayReader(str.toCharArray()));
    }

    public void fillBuff()throws IOException{
        bL = reader.read(buffer);
        p = 0;
    }
    public String readLine()throws IOException{
        token.setLength(0);
        while (bL != -1){
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
        if(!token.isEmpty()){
            if(!txt)
                p++;
            else
                p+=2;
            return token.toString();
        }
        return null;
    }


    public String nextWord() throws IOException{
        token.setLength(0);
        while(bL != -1){
            for(;p<bL;p++) {
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
        if(!token.isEmpty()){
            return token.toString();
        }
        else {
            return null;
        }
    }

    public String nextInt() throws IOException{
        token.setLength(0);

        while(bL != -1) {
            for (; p < bL; p++) {
                if (Character.isDigit(buffer[p]) || buffer[p] == '-') {
                    token.append(buffer[p]);
                } else if(Character.isLetter(buffer[p])){
                    token.append((char)((int)buffer[p]-49));
                }else{
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


class IntArray2 {
    
    private int[] arr;
    private int length = 0;
    private final double multi;

    public IntArray2(int cap){
        arr = new int[cap];
        multi = 2;
    }
    public IntArray2(int cap, double m){
        arr = new int[cap];
        multi = m;
    }
    public int GetCapacity(){
        return arr.length;
    }
    public int GetLength(){
        return length;
    }
    public int Get(int ind) throws IOException{
        if(ind>=length || ind<0){
            throw new IOException("Праизащел выход за границы массива APPEND :(");
        } else {
            return arr[ind];
        }
    }
    public void addToInd(int ind, int num) throws IOException{
        if(ind>=length || ind<0){
            throw new IOException("Праизащел выход за границы массива ADD :(");
        } else {
            arr[ind] += num;
        }
    }
    //    private void IncreaseArr(){
//        int[] temp = new int[(int)(arr.length* multi)+1];
//        System.arraycopy(arr,0,temp,0,arr.length);
//        arr = temp;
//    }
    private void IncreaseArr(){
        arr = Arrays.copyOf(arr,(int)(arr.length*multi)+1);
    }

    public void Append(int num){
        if(length >= arr.length){
            IncreaseArr();
        }
        arr[length++] = num;
    }




}



public class ReverseAbc {
    public static void main(String[] args) throws IOException {

        //LinkedList<IntArray> data = new LinkedList<>();
        ArrayList<IntArray2> data = new ArrayList<>();
        Rj_scanner2 rrj = new Rj_scanner2(System.in);
        try {

            Rj_scanner2 rrjLine;
            String temp;
            while ((temp = rrj.readLine()) != null) {
                rrjLine = new Rj_scanner2(temp);
                data.add(new IntArray2(32));

                String temp2;
                while ((temp2 = rrjLine.nextInt()) != null) {
                    data.get(data.size() - 1).Append(Integer.parseInt(temp2));
                }
            }
            String tmp;
            StringBuilder s = new StringBuilder();
            for (int i = data.size() - 1; i >= 0; i--) {
                for (int j = data.get(i).GetLength() - 1; j >= 0; j--) {
                    tmp = String.valueOf(data.get(i).Get(j));
                    s.setLength(0);
                    for(int k=0;k<tmp.length();k++){
                        if(Character.isDigit(tmp.charAt(k)))
                            s.append((char)((int)tmp.charAt(k)+49));
                        else
                            s.append(tmp.charAt(k));
                    }
                    System.out.print(s.toString()+" ");
                }
                System.out.println();
            }
        }catch (IOException e){
            System.out.println(e.getMessage());
        } finally {
            rrj.close();
        }
    }
}

