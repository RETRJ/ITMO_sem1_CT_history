package expression;

public class New2 extends AbstractOperation {


    public New2(MultiExpr a, MultiExpr b) {
        super(a, b);
    }

    @Override
    protected String getSign() {
        return "max";
    }

    @Override
    protected int getPrior() {
        return 3;
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
        return Math.max(a, b);
    }


}