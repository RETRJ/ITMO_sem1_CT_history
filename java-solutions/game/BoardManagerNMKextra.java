package game;

public class BoardManagerNMKextra extends BoardManager {

    public BoardManagerNMKextra(int m, int n, int k) {
        board = new Board(m, n, k, Board.TypeBoard.NMK);
    }


    private MoveResult checkWin(Move m, int cellCode) {

        int res = -1;
        int counter = 0;
        int[][] check = {{1, 0}, {0, 1}, {1, 1}, {1, -1}};
        for (var t : check) {
            for (int i = 0; i < 2; i++) {
                int tmpX = m.getX();
                int tmpY = m.getY();
                while (tmpX < board.getN() && tmpY < board.getM() && tmpX >= 0 && tmpY >= 0 && board.getCellCode(tmpX, tmpY) == cellCode) {
                    tmpX += t[0];
                    tmpY += t[1];
                    counter++;
                }
                for (var rev : t)
                    rev *= -1;
                tmpY += t[1];
                tmpX += t[0];
                res = Math.max(res, counter);
                counter = 0;
            }
        }
        if (res >= board.getK()) {
            return new MoveResult(MoveResult.verdict.WIN);
        } else if (cellsUsed == board.getN() * board.getM()) {
            return new MoveResult(MoveResult.verdict.DRAW);
        } else if (res >= 4) {
            return new MoveResult(MoveResult.verdict.EXTRA_TURN);
        }
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
