package game;


import java.nio.channels.ClosedByInterruptException;

public abstract class Player {
    protected Board gameVision;
    protected Skins skins;

    abstract public Move askToMove() throws ClosedByInterruptException;

    private void Draw() {
    }

    ;

    abstract public void getSkins(Skins s);

    public void getBoard(Board b) {
        gameVision = b;
    }

    public void getDiff(Move diff, int code) {
        gameVision.setCell(diff.getX(), diff.getY(), code);
        //this.Draw();
    }

    abstract public String askForSym() throws ClosedByInterruptException;
}
