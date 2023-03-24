import game.*;

import java.util.List;

public class Main {


    public static void main(String[] args) {

        ResultOfGame res = new Tournament(List.of(new HumanPl(), new NULLPlayer())).playMatch(new BoardManagerNMKextra(8, 8, 8), 3);
        if (res.getIDofWinner() == -2) {
            System.out.println("CANCELED!");
        } else {
            System.out.println(res.getRes());
        }
    }
}
