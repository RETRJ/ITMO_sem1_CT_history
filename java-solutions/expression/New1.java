package expression;

import expression.AbstractOperation;
import expression.MultiExpr;

public class New1 extends AbstractOperation {


    public New1(MultiExpr a, MultiExpr b) {
        super(a, b);
    }

    @Override
    protected String getSign() {
        return "min";
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
        return Math.min(a, b);
    }


}