package expression.exceptions;

public class ParserExc extends MyExceptions{
    public ParserExc(String msg) {
        super(msg);
    }

    public ParserExc(String msg, String c) {
        super(msg, c);
    }
}
