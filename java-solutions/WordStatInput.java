import RJutils.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;


public class WordStatInput {
    public static void main(String[] args) throws IOException {
        ReaderRJ rrj = new ReaderRJ(args[0], StandardCharsets.UTF_8);
        try {
            ArrayList<pair<String,Integer>> data = new ArrayList<>(8);
            WriterRJ wrj = new WriterRJ(args[1], StandardCharsets.UTF_8);
            WriterRJ wrj2 = new WriterRJ("out.txt", StandardCharsets.UTF_8);
            try {
                String temp;
                while ((temp = rrj.nextWord()) != null) {
                        boolean found = false;
                        for (int i = 0; i < data.size(); i++) {
                            if(data.get(i).isEq(temp.toLowerCase())){
                                data.get(i).setSecond(data.get(i).getSecond()+1);
                                found = true;
                                break;
                            }
                        }
                        if(!found){
                            data.add(new pair<>(temp.toLowerCase(),1));
                        }
                }
                //data.sort(Collections.reverseOrder());
                for (int i = 0; i < data.size(); i++) {
                    wrj.writeln(data.get(i).getFirst() + " " + data.get(i).getSecond());
                    wrj2.writeln(data.get(i).getFirst() + " " + data.get(i).getSecond());
                }
            } finally {
                wrj.close();
                wrj2.close();
            }
        } finally {
            rrj.close();
        }

    }
}
