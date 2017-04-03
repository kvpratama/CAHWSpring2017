package week2;

/**
 * Created by u410 on 3/21/17.
 */
public class No4 {
    public static void main(String[] args) {
//        double a = 1, i=3, temp=0;
//
//        while(i<20){
//            temp = a + Math.pow(2, i-3);
//            a= a + temp;
//            i++;
//        }
//        System.out.println(a);
//
//        double probability = a / Math.pow(2, 20);
//        System.out.println(probability);

        double[] gn = new double[21];
        double[] bn = new double[21];

        gn[0]= 0;gn[1]= 0;gn[2]= 0;gn[3]= 1;
        bn[0]= 1;bn[1]= 2;bn[2]= 4;bn[3]= 7;

        for (int n = 3; n < 20; n++) {
            gn[n+1] = 2 * gn[n] + bn[n-3];
            bn[n+1] = Math.pow(2,n+1) - gn[n+1];
        }
        System.out.println(1 - gn[20] / Math.pow(2, 20));

    }

}
