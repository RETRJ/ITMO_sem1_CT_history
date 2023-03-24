package expression;

public class Subtract extends AbstractOperation {

    public Subtract(MultiExpr a, MultiExpr b) {
        super(a, b);
    }
    @Override
    protected String getSign() {
        return "-";
    }
    @Override
    protected boolean getL() {
        return true;
    }
    @Override
    protected boolean getR() {
        return false;
    }

    @Override
    protected int calc(int a, int b) {
        return a-b;
    }


    @Override
    protected int getPrior() {
        return 5;
    }


}
