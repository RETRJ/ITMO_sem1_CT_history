package expression.exceptions;

public class MyExceptions extends RuntimeException {
    protected String classCause;

    public MyExceptions(String msg) {
        super(msg);
    }

    public MyExceptions(String msg, String c) {
        super(msg);
        classCause = c;
    }

    @Override
    public String getMessage() {
        if(classCause!=null)
            return super.getMessage() + "\nHappened in class: " + classCause;
        return super.getMessage();
    }
}
