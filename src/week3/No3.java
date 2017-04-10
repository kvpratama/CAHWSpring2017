package week3;


public class No3 {

    public static void main(String[] args) {
        int candies = 4;
        double[] odds = new double[10000];
        double[] evens = new double[10000];
        evens[2] = 2;
        double odd, even;
        double possibility = 0;

        for (int i = 2; i <= candies; i+=2) {
            even = getEvenSets(evens, i);
            if (candies-i == 0){
                possibility += even;
            }else{
                odd = getPowFromSets(odds, candies-i);
                possibility += even * odd * ((candies-i)* i +1);
            }
        }

        odd = getPowFromSets(odds, candies);

        possibility += odd;
        System.out.println(possibility%65535);
    }

    public static double getPowFromSets(double[] sets, int power){
        double result;
        if (power == 0 ){
            return 0;
        }
        if (sets[power] == 0){
            result = Math.pow(2, power);
            sets[power] = result;
        }else {
            result = sets[power];
        }
        return result;
    }

    public static double getEvenSets(double[] evens, int index){
        double even;
        if (evens[index] == 0){
            if ((index / 2) %2 == 0){
                even = Math.pow(2, index/2) * 2;
                evens[index] = even;
            }else{
                even = evens[index-2] * 2;
                evens[index] = even;
            }
        }else {
            even = evens[index];
        }
        return even;
    }
}

