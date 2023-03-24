package expression.exceptions;

import expression.Divide;
import expression.MultiExpr;
import expression.TripleExpression;
import expression.Variable;

public class CheckedDivide extends Divide {
    public CheckedDivide(MultiExpr a, MultiExpr b) {
        super(a,b);
    }

    @Override
    protected int calc(int a, int b) {
        if(a == Integer.MIN_VALUE && b==-1)
            throw new OverflowExc("overflow");
        if(b==0)
            throw new DivByZeroExc("/ by zero");
        return a/b;
    }
}
