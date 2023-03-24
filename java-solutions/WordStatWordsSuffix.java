import RJutils.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;


public class WordStatWordsSuffix {
    public static void main(String[] args) throws IOException {
        ReaderRJ rrj = new ReaderRJ(args[0], StandardCharsets.UTF_8);
        try {
            ArrayList<pair<String,Integer>> data = new ArrayList<>(8);
            WriterRJ wrj = new WriterRJ(args[1], StandardCharsets.UTF_8);
            try {
                String temp;
                while ((temp = rrj.nextWord()) != null) {
                        boolean found = false;
                        if(temp.length()>3) {
                            for (int i = 0; i < data.size(); i++) {
                                if (data.get(i).isEq(temp.substring(temp.length()-3, temp.length()).toLowerCase())) {
                                    data.get(i).setSecond(data.get(i).getSecond() + 1);
                                    found = true;
                                    break;
                                }
                            }
                            if (!found) {
                                data.add(new pair<>(temp.substring(temp.length()-3,temp.length()).toLowerCase(), 1));
                            }
                        }else{
                            for (int i = 0; i < data.size(); i++) {
                                if (data.get(i).isEq(temp.toLowerCase())) {
                                    data.get(i).setSecond(data.get(i).getSecond() + 1);
                                    found = true;
                                    break;
                                }
                            }
                            if (!found) {
                                data.add(new pair<>(temp.toLowerCase(), 1));
                            }
                        }
                }

                data.sort(null);

                for (int i = 0; i < data.size(); i++) {
                    wrj.writeln(data.get(i).toString());
                }
            } catch (IOException e){
                System.out.println(e.getMessage());
                e.printStackTrace();
            } finally {
                wrj.close();
            }
        }catch (IOException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }finally {
            rrj.close();
        }

    }
}
