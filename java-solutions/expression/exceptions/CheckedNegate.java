package expression.exceptions;

import expression.Minus;
import expression.MultiExpr;
import expression.TripleExpression;
import expression.Variable;

public class CheckedNegate extends Minus {
    public CheckedNegate(MultiExpr a) {
        super(a);
    }
    @Override
    public int evaluate(int x) {
        int tmp = a.evaluate(x);
        if(tmp == Integer.MIN_VALUE)
            throw new OverflowExc("overflow");
        return -tmp;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        int tmp = a.evaluate(x,y,z);
        if(tmp == Integer.MIN_VALUE)
            throw new OverflowExc("overflow");
        return -tmp;
    }

}
