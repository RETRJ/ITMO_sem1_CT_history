package game;

import java.util.Random;

public class NULLPlayer extends Player {

    @Override
    public Move askToMove() {
        return null;
    }

    @Override
    public void getSkins(Skins s) {
        //skins for client
    }

    @Override
    public String askForSym() {
        return String.valueOf(new Random().nextInt() % 10);
    }
}
