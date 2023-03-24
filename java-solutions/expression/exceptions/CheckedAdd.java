package expression.exceptions;

import expression.*;

public class CheckedAdd extends Add {
    public CheckedAdd(MultiExpr a, MultiExpr b) {
        super(a,b);
    }

    @Override
    protected int calc(int a, int b) {
        if((a<0 && b<0 && Integer.MIN_VALUE - a > b)||(a>0 && b>0 && Integer.MAX_VALUE - a < b)){
            throw new OverflowExc("overflow");
        }
        return a+b;
    }
}
