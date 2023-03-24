package expression;

public class Const implements MultiExpr{
    private final int constanta;
    public Const(int constanta){
        this.constanta = constanta;
    }

    public double getConstanta() {
        return constanta;
    }

    @Override
    public int evaluate(int x) {
        return (int)constanta;
    }
    @Override
    public String toString(){
        return String.valueOf(constanta);
    }
    @Override
    public int hashCode() {
        return Double.hashCode(constanta);
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || this.getClass() != obj.getClass())
            return false;
        Const that = (Const) obj;
        return this.getConstanta() == that.getConstanta();
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return (int)constanta;
    }
}
