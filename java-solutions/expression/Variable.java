package expression;

public class Variable implements MultiExpr{
    private final String var;

    public Variable(String var){
        this.var = var;
    }

    public String getVar() {
        return var;
    }

    @Override
    public int evaluate(int x) {
        return x;
    }
    @Override
    public String toString(){
        return var;
    }
    @Override
    public int hashCode() {
        return var.hashCode();
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || this.getClass() != obj.getClass())
            return false;
        Variable that = (Variable) obj;
        return this.getVar().equals(that.getVar());
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return switch (var){
            case "x" -> x;
            case "y" -> y;
            case "z" -> z;
            default -> throw new RuntimeException();
        };
    }
}
