package game;

public class MoveResult {
    public enum verdict {
        WIN,
        DRAW,
        WRONG_INPUT,
        EXTRA_TURN,
        NOTHING
    }

    private final verdict v;

    public MoveResult(verdict ver) {
        this.v = ver;
    }

    public verdict getV() {
        return v;
    }
}
