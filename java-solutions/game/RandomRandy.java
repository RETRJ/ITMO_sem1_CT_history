package game;

import java.util.Random;

public class RandomRandy extends Player {
    private void Draw() {
        System.out.print("  ");
        for (int j = 0; j < gameVision.getN(); j++) {
            System.out.printf("%2d", j);
        }
        System.out.println();
        for (int i = 0; i < gameVision.getM(); i++) {
            ///////////////////////////////////////////////////////GEX draw
            if (gameVision.getType() == Board.TypeBoard.GEX) {
                for (int j = 0; j < i; j++)
                    System.out.print(" ");
            }
            ///////////////////////////////////////////////////////
            System.out.printf("%2d", i);


            for (int j = 0; j < gameVision.getN(); j++) {
                System.out.printf("%2s", skins.getSkin(gameVision.getCellCode(i, j)));
            }
            System.out.println();
        }
        System.out.println();
    }//DEBUG FEATURE

    @Override
    public Move askToMove() {
        Draw();
        while (true) {
            int x, y;
            x = Math.abs(new Random().nextInt()) % gameVision.getN();
            y = Math.abs(new Random().nextInt()) % gameVision.getM();


            if (gameVision.getCellCode(y, x) == 0) {
                return new Move(x, y);
            }
        }
    }

    @Override
    public void getSkins(Skins s) {
        skins = s;
    }

    @Override
    public String askForSym() {

        return String.valueOf(Math.abs(new Random().nextInt()) % 10);
    }
}
