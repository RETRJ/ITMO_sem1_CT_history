package expression;

public class Divide extends AbstractOperation {


    public Divide(MultiExpr a, MultiExpr b) {
        super(a, b);
    }

    @Override
    protected String getSign() {
        return "/";
    }

    @Override
    protected int getPrior() {
        return 10;
    }

    @Override
    protected boolean getL() {
        return false;
    }
    @Override
    protected boolean getR() {
        return false;
    }

    @Override
    protected int calc(int a, int b) {
        return a/b;
    }


}
