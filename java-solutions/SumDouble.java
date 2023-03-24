public class SumDouble {
    public static void main(String[] args){
        double sumDouble = 0;
        StringBuffer now = new StringBuffer();
        //String now ="";
        boolean nowDouble = false;
        for (int i = 0; i < args.length; i++) {
            for (int j = 0; j < args[i].length(); j++){
                if (!Character.isWhitespace(args[i].charAt(j)) || args[i].charAt(j) == '-' || args[i].charAt(j) == '.'){
                    if(now.isEmpty() && args[i].charAt(j)=='-')
                        //now += args[i].charAt(j);
                        now.append(args[i].charAt(j));
                    else if (now.length()>=2 && args[i].charAt(j)=='.' && !nowDouble) {
                        //now += args[i].charAt(j);
                        now.append(args[i].charAt(j));
                        nowDouble = true;
                    }
                    else
                        //now += args[i].charAt(j);
                        now.append(args[i].charAt(j));
                }else{
                    if(!now.isEmpty()) {
                        sumDouble += Double.parseDouble(now.toString());
                    }
                    now = new StringBuffer();
                }
            }
            if(!now.isEmpty()) {
                sumDouble += Double.parseDouble(now.toString());
            }
            now = new StringBuffer();
        }
            System.out.println(sumDouble);
    }
}
