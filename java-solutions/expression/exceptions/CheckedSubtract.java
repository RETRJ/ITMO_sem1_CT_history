package expression.exceptions;

import expression.*;

public class CheckedSubtract extends Subtract {
    public CheckedSubtract(MultiExpr a, MultiExpr b) {
        super(a,b);
    }

    @Override
    protected int calc(int a, int b) {
        if((a<=0 && b>=0 && Integer.MIN_VALUE + b > a) || (b<=0 && a>=0 && Integer.MAX_VALUE + b < a)){
            throw new OverflowExc("overflow");
        }
        return a-b;
    }
}
