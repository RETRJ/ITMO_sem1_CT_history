public class Sum {
    public static void main(String args[]){
        int sum=0;
        String now;
        String temp="";
        for(int i = 0; i < args.length; i++) {
            now = " "+args[i]+" ";
            for (int j = 1; j < now.length(); j++) {
                if (Character.isDigit(now.charAt(j)) && now.charAt(j - 1) == '-') {
                    temp += "-" + now.charAt(j);
                } else if (Character.isDigit(now.charAt(j))) {
                    temp += now.charAt(j);
                } else {
                    if (!temp.isEmpty()) {
                        sum += Integer.parseInt(temp);
                    }
                    temp = "";
                }
            }
        }
        System.out.println(sum);
    }
}
