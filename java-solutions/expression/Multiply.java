package expression;

public class Multiply extends AbstractOperation {
    public Multiply(MultiExpr a, MultiExpr b) {
        super(a, b);
    }
    @Override
    protected String getSign() {
        return "*";
    }
    @Override
    protected int getPrior() {
        return 10;
    }
    @Override
    protected boolean getL() {
        return true;
    }
    @Override
    protected boolean getR() {
        return true;
    }

    @Override
    protected int calc(int a, int b) {
        return a*b;
    }
}
