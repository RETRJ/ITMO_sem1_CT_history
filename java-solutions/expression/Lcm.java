package expression;

public class Lcm extends AbstractOperation {


    public Lcm(MultiExpr a, MultiExpr b) {
        super(a, b);
    }
    private int gcd(int a, int b) { return b==0 ? a : gcd(b, a%b); }

    @Override
    protected String getSign() {
        return "lcm";
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
        return (a*b) / gcd(a, b);
    }


}