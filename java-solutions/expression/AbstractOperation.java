package expression;

public abstract class AbstractOperation implements MultiExpr {
    protected MultiExpr a;
    protected MultiExpr b;

    abstract protected String getSign();

    abstract protected boolean getL();
    abstract protected boolean getR();

    abstract protected int calc(int a, int b);
    public int evaluate(int x, int y, int z){
        return calc(a.evaluate(x,y, z), b.evaluate(x, y, z));
    };

    public int evaluate(int x){
        return calc(a.evaluate(x,0, 0), b.evaluate(x, 0, 0));
    };

    public AbstractOperation(MultiExpr a, MultiExpr b) {
        this.a = a;
        this.b = b;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || this.getClass() != obj.getClass()) return false;
        AbstractOperation that = (AbstractOperation) obj;
        return this.a.equals(that.a) && this.b.equals(that.b);
    }

    @Override
    public int hashCode() {
        return this.getClass().hashCode() + ((a.hashCode() * 13) + b.hashCode() * 17);
    }

    @Override
    public String toString() {
        return "(" + a.toString() + " " + this.getSign() + " " + b.toString() + ")";

    }

    abstract protected int getPrior();

    @Override
    public String toMiniString() {
        StringBuilder tmp = new StringBuilder();
        if (a instanceof AbstractOperation that) {
            if (that.getPrior() < this.getPrior()) {
                tmp.append("(").append(that.toMiniString()).append(")");
            } else {
                tmp.append(that.toMiniString());
            }
        } else {
            tmp.append(a.toMiniString());
        }
        tmp.append(" ").append(this.getSign()).append(" ");
        if (b instanceof AbstractOperation that) {
                if ((that.getPrior() < this.getPrior() || (that.getPrior() == this.getPrior() && !this.getR())) || (that.getPrior() <= this.getPrior()&&that.getR()==that.getL() && !that.getL())) {
                    tmp.append("(").append(that.toMiniString()).append(")");
                } else {
                    tmp.append(that.toMiniString());
                }
        } else {
            tmp.append(b.toMiniString());
        }
        return tmp.toString();
    }
}
