package game;

import java.io.IOException;
import java.nio.channels.ClosedByInterruptException;
import java.util.Random;
import java.util.Scanner;

public class HumanPl extends Player {

    private Scanner sc = new Scanner(System.in);

    public HumanPl() {
    }

    ;


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
    }

    @Override
    public Move askToMove() throws ClosedByInterruptException {
        this.Draw();
        while (true) {
            try {
                int x, y;
                System.out.print("Введите X и Y:");
                x = sc.nextInt();
                y = sc.nextInt();

                if (gameVision.getCellCode(y, x) != 0) {
                    System.out.println("Not empty! Try another one!");
                } else {
                    return new Move(x, y);
                }

            } catch (Exception e) {
                System.out.println("INVALID INPUT! You're banned!");
                sc.close();
                throw new ClosedByInterruptException();
            }
        }
    }

    @Override
    public void getSkins(Skins s) {
        skins = s;
    }

    @Override
    public String askForSym() throws ClosedByInterruptException {
        System.out.print("Enter your symbol: ");
        try {
            String tmp = sc.nextLine();
            if (tmp.length() <= 2)
                return tmp;
            else
            // :NOTE: не надо кидать и обрабатываться Exception
                throw new Exception();
        } catch (Exception e) {
            System.out.println("You caused an error that closed input! Banned!");
            throw new ClosedByInterruptException();
        }
    }
}
