import RJutils.*;

import javax.lang.model.element.Element;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;

class Pair {
    // :NOTE: модификаторы доступа .............DONE
    private int f;
    private int s;

    Pair(int a, int b) {
        f = a;
        s = b;
    }

    public int getF() {
        return f;
    }

    public int getS() {
        return s;
    }

    public void setF(int f) {
        this.f = f;
    }

    public void setS(int s) {
        this.s = s;
    }
}


public class WsppCountPosition {
    public static void main(String[] args) throws IOException {
        FastScanner rrj = new FastScanner(args[0], StandardCharsets.UTF_8);
        try {

            LinkedHashMap<String, ArrayList<Pair>> data = new LinkedHashMap<>();

            WriterRJ wrj = new WriterRJ(args[1], StandardCharsets.UTF_8);
            try {
                String temp;
                int wordInd = 0;
                int lineInd = 0;
                Rj_scanner wordR;
                while (rrj.hasNextLine()) {
                    wordInd = 0;
                    lineInd++;
                    String word;
                    while (rrj.hasNextWord()) {
                        word = rrj.nextWord().toLowerCase();
                        wordInd++;

                        data.putIfAbsent(word, new ArrayList<>());
                        data.get(word).add(new Pair(lineInd, wordInd));
/*
                        // :NOTE: много запросов в мап.............DONE
                        if (!data.containsKey(word)) {
                            data.put(word, new ArrayList<>());
                        }
 */
                    }
                }

                Iterator<Map.Entry<String, ArrayList<Pair>>> it;
                it = data.entrySet().stream().sorted(
                        Comparator.comparing(t -> t.getValue().size())
                ).iterator();

                // :NOTE: use foreach.............DONE
                it.forEachRemaining(t -> {
                    try {
                        wrj.write(t.getKey() + " " + t.getValue().size());
                        for (var value : t.getValue()) {
                            wrj.write(" " + value.getF() + ":" + value.getS());
                        }
                        wrj.writeln(null);
                    } catch (IOException e){
                        System.out.println(e.getMessage());
                    }
                });
/*
                while (it.hasNext()) {
                    Map.Entry<String, ArrayList<Pair>> t = it.next();
                    wrj.write(t.getKey() + " " + t.getValue().size());
                    // :NOTE: use foreach
                    for (int j = 0; j < t.getValue().size(); j++) {
                        wrj.write(" " + t.getValue().get(j).f + ":" + t.getValue().get(j).s);
                    }
                    wrj.writeln(null);
                }

                for (var t : it) {

                }
 */
            } finally {
                wrj.close();
            }
        } finally {
            rrj.close();
        }
    }
}
