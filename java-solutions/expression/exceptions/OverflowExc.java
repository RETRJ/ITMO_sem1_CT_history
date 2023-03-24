package expression.exceptions;

public class OverflowExc extends MyExceptions{
    public OverflowExc(String msg) {
        super(msg);
    }

    public OverflowExc(String msg, String c) {
        super(msg, c);
    }
}
