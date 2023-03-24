package game;

public class BoardManagerNMK extends BoardManager {

    public BoardManagerNMK(int m, int n, int k) {
        board = new Board(m, n, k, Board.TypeBoard.NMK);
    }

    private MoveResult checkWin(Move m, int cellCode) {

        int counter = 0;
        for (int i = m.getX(); i < board.getM() && board.getCellCode(i, m.getY()) == cellCode; i++)
            counter++;
        for (int i = m.getX() - 1; i >= 0 && board.getCellCode(i, m.getY()) == cellCode; i--)
            counter++;
        if (counter == board.getK()) {
            return new MoveResult(MoveResult.verdict.WIN);
        }

        counter = 0;
        for (int i = m.getY(); i < board.getN() && board.getCellCode(m.getX(), i) == cellCode; i++)
            counter++;
        for (int i = m.getY() - 1; i >= 0 && board.getCellCode(m.getX(), i) == cellCode; i--)
            counter++;
        if (counter == board.getK()) {
            return new MoveResult(MoveResult.verdict.WIN);
        }

        counter = 0;
        for (int i = m.getX(), j = m.getY(); i < board.getM() && j < board.getN() && board.getCellCode(i, j) == cellCode; i++, j++)
            counter++;
        for (int i = m.getX() - 1, j = m.getY() - 1; i >= 0 && j >= 0 && board.getCellCode(i, j) == cellCode; i--, j--)
            counter++;
        if (counter == board.getK()) {
            return new MoveResult(MoveResult.verdict.WIN);
        }

        counter = 0;
        for (int i = m.getX(), j = m.getY(); i < board.getM() && j >= 0 && board.getCellCode(i, j) == cellCode; i++, j--)
            counter++;
        for (int i = m.getX() - 1, j = m.getY() + 1; i >= 0 && j < board.getN() && board.getCellCode(i, j) == cellCode; i--, j++)
            counter++;
        if (counter == board.getK()) {
            return new MoveResult(MoveResult.verdict.WIN);
        }

        if (cellsUsed == board.getN() * board.getM())
            return new MoveResult(MoveResult.verdict.DRAW);

        return new MoveResult(MoveResult.verdict.NOTHING);
    }

    public MoveResult turn(Move m, int cellCode) {
        if (tryMove(m)) {
            board.setCell(m.getX(), m.getY(), cellCode);
            cellsUsed++;
            return checkWin(m, cellCode);
        } else {
            return new MoveResult(MoveResult.verdict.WRONG_INPUT);
        }
    }
}
