package expression.exceptions;

import expression.MultiExpr;
import expression.Multiply;

public class CheckedMultiply extends Multiply {
    public CheckedMultiply(MultiExpr a, MultiExpr b) {
        super(a, b);
    }

    @Override
    protected int calc(int a, int b) {

        if (a != 0 && b != 0 && (
                (b > 0 && a > 0 && Integer.MAX_VALUE / b < a) ||
                    (a < 0 && b < 0 && Integer.MAX_VALUE / b > a) ||
                    (b < -1 && a > 0 && Integer.MIN_VALUE / b < a) ||
                    (a < -1 && b > 0 && Integer.MIN_VALUE / a < b)
            )) {
            throw new OverflowExc("overflow");
        }
        return a * b;
    }

}
