package expression;

public class Add extends AbstractOperation {


    public Add(MultiExpr a, MultiExpr b) {
        super(a, b);
    }

    @Override
    protected String getSign() {
        return "+";
    }

    @Override
    protected int getPrior() {
        return 5;
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
        return a+b;
    }


}
