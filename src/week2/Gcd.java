package week2;

/**
 * Created by u410 on 3/20/17.
 */
public class Gcd {

    public int gcd(int a, int b) {
        boolean even = false;
        if (a % 2 == 0 && b % 2 == 0) {
            a /= 2;
            b /= 2;
            even = true;
        } else if (a % 2 != 0 && b % 2 == 0) {
            b /= 2;
        } else if (a % 2 != 0 && b % 2 != 0) {
            a = (a - b) / 2;
        }

        int c = b / 2;

        while (c > 0) {
            if (b % c == 0) {
                if (a % c == 0) {
                    if (even) {
                        return c * 2;
                    } else {
                        return c;
                    }
                }
            }
            c--;
        }
        return 0;
    }

    public static void main(String[] args) {
        int a = 891;//57;//192;
        int b = 567;//23;//270;

        Gcd g = new Gcd();
        System.out.println(g.gcd(a, b));
    }

}
