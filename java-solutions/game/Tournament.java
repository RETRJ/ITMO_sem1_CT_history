package game;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.LinkedHashMap;

public class Tournament {
    // :NOTE: модификаторы доступа
    Map<Integer, Integer> score = new LinkedHashMap<>();
    List<Player> players = new ArrayList<>();

    public Tournament(List<Player> list) {
        players = list;
        int IDs = 1;
        for (var t : list) {
            score.put(IDs, 0);
            IDs++;
        }
    }

    public ResultOfGame playMatch(BoardManager b, int scr) {
        int matchID = 0;
        while (true) {

            System.out.println("MATCH NUM: " + (matchID + 1));
            List<Player> shuffle = new ArrayList<>();
            for (int i = 0; i < players.size(); i++) {
                shuffle.add(players.get((i + matchID) % players.size()));
            }
            b.clear();
            ResultOfGame res;

            try {
                res = new GameServer(shuffle).play(b);
            } catch (Exception e) {
                return new ResultOfGame("Input was Closed! Game canceled! (match)", -2);
            }

            if (res.getIDofWinner() > 0) {
                score.put(res.getIDofWinner(), score.get(res.getIDofWinner()) + 1);
            }
            for (var t : score.keySet()) {
                if (score.get(t) >= scr) {
                    return new ResultOfGame("HORAY!!! Player ID: " + t + " WON!!!!!", t);
                }
            }
            matchID++;
        }
    }


}
