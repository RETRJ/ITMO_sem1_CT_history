package game;

public class ResultOfGame {
    private final String res;
    private final int IDofWinner;

    ResultOfGame(String s, int ID) {
        res = s;
        IDofWinner = ID;
    }

    public int getIDofWinner() {
        return IDofWinner;
    }

    public String getRes() {
        return res;
    }
}
