package expression.exceptions;


public class Main {


    public static void main(String[] args) {
        String test= "(0 set 0)";



    try {
        System.out.println(new ExpressionParser().parse(test).toMiniString());
    }catch (Exception e){
        System.out.println(e.getMessage());
    }
    }
}
