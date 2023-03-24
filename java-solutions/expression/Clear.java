package expression;

public class Clear extends AbstractOperation {


    public Clear(MultiExpr a, MultiExpr b) {
        super(a, b);
    }

    @Override
    protected String getSign() {
        return "clear";
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
        return false;
    }

    @Override
    protected int calc(int a, int b) {
        return a & ~(1<<b);
    }


}