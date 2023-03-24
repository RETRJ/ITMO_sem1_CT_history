package RJutils;

public class RJfuncs {

    public static double smartSum(double... args) {
        double sum = 0;
        for (double t : args) sum += t;
        return sum;
    }


    public static boolean StrSimilarity(String a, String b, int percentage) {
        float sims = 0;
        for (int i = 0; i < (Math.min(a.length(), b.length())); i++) {
            if (a.charAt(i) == b.charAt(i)) {
                sims++;
            }
        }
        return (sims / Math.max(a.length(), b.length()) * 100) >= percentage;
    }
}
