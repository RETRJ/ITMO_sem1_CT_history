package game;

import java.util.Random;

public class Cheater extends Player {

    @Override
    public Move askToMove() {
        gameVision.setCell(Math.abs(new Random().nextInt()) % 50, Math.abs(new Random().nextInt()) % 50, 99);
        return new Move(Math.abs(new Random().nextInt()) % 50, Math.abs(new Random().nextInt()) % 50);
    }

    @Override
    public void getSkins(Skins s) {
        skins = s;
    }

    @Override
    public String askForSym() {
        return "L";
    }
}
