package game;

public abstract class BoardManager {
    protected Board board;
    protected int cellsUsed = 0;

    protected boolean tryMove(Move m) {
        if (m == null || m.getX() >= board.getM() || m.getX() < 0 || m.getY() >= board.getN() || m.getY() < 0 || board.getCellCode(m.getX(), m.getY()) != 0) {
            return false;
        } else {
            return true;
        }
    }

    public Board getBoard() {
        return new Board(board);
    }

    public void clear() {
        board = new Board(board.getM(), board.getN(), board.getK(), board.getType());
        cellsUsed = 0;
    }

    abstract public MoveResult turn(Move m, int cellCode);
}
