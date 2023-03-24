package game;

public class Board {
    public enum TypeBoard {
        NMK,
        GEX
    }

    TypeBoard type;
    private final int n;
    private final int m;
    private final int k;
    private final int[][] matrix;

    public Board(int m, int n, int k, TypeBoard t) {
        type = t;
        this.k = k;
        this.n = n;
        this.m = m;
        matrix = new int[m][n];
    }

    public Board(Board b) {
        this.type = b.getType();
        this.k = b.getK();
        this.n = b.getN();
        this.m = b.getM();
        matrix = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                matrix[i][j] = b.getMatrix()[i][j];
            }
        }

    }

    public TypeBoard getType() {
        return type;
    }

    public int getM() {
        return m;
    }

    public int getN() {
        return n;
    }

    public int getK() {
        return k;
    }

    public int getCellCode(int x, int y) {
        return matrix[y][x];
    }

    public int[][] getMatrix() {
        return matrix;
    }

    public void setCell(int x, int y, int id) {
        matrix[y][x] = id;
    }
}
