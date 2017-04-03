package week3;

/**
 * Created by u410 on 4/3/17.
 */
public class Interwoven {

    public static void main(String[] args) {
        String t = "caabcbbabccdw";
        String x = "abcaw";
        String y = "bbc";
        String z = "zxt";
        int xi = 0, yi = 0, zi = 0;

        for (int i = 0; i < t.length(); i++) {
            if (xi < x.length()) {
                if (x.charAt(xi) == t.charAt(i)) {
                    xi++;
                }
            }

            if (yi < y.length()) {
                if (y.charAt(yi) == t.charAt(i)) {
                    yi++;
                }
            }

            if (zi < z.length()) {
                if (z.charAt(zi) == t.charAt(i)) {
                    zi++;
                }
            }
        }

        if (xi == x.length() && yi == y.length() && zi == z.length()) {
            System.out.println("Interwoven");
        } else {
            System.out.println("Not Interwoven");
        }

    }
}
