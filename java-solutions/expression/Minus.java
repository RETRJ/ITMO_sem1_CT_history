package expression;

public class Minus implements MultiExpr {

    protected MultiExpr a;

    public Minus(MultiExpr a) {
        this.a = a;
    }

    @Override
    public int evaluate(int x) {
        return -1 * a.evaluate(x);
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return -1 * a.evaluate(x, y, z);
    }

    @Override
    public String toString(){
        return "-("+String.valueOf(a.toString())+")";
    }

    @Override
    public String toMiniString(){
        if(a instanceof AbstractOperation){
            return "-("+a.toMiniString()+")";
        }else {
            return "- "+a.toMiniString();
        }
    }
}
