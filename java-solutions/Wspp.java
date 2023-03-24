import RJutils.*;

import java.io.Closeable;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.TreeMap;
import java.util.LinkedHashMap;

public class Wspp {
    public static void main(String[] args) throws IOException {
        FastScanner rrj = new FastScanner(args[0], StandardCharsets.UTF_8);
        try {

            LinkedHashMap<String, IntArray> data = new LinkedHashMap<>();
            WriterRJ wrj = new WriterRJ(args[1], StandardCharsets.UTF_8);
            try {
                String temp;
                int wordInd = 0;
                while (rrj.hasNextLine()) {
                    while (rrj.hasNextWord()) {
                        temp = rrj.nextWord().toLowerCase();
                        wordInd++;
                        if (!data.containsKey(temp))
                            data.put(temp, new IntArray(8));
                        data.get(temp).Append(wordInd);
                    }
                }
                for (var t : data.keySet()) {
                    wrj.write(t + " " + data.get(t).GetLength());
                    for (int j = 0; j < data.get(t).GetLength(); j++) {
                        wrj.write(" " + data.get(t).Get(j));
                    }
                    wrj.writeln(null);
                }
            } finally {
                wrj.close();
            }
        } finally {
            rrj.close();
        }
    }
}
