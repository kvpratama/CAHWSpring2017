package week2;

import java.util.Scanner;

public class No2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        int a = 1, count = 1;
        while(a % n != 0){
                a = (a%n) * 10 +1;
                count++;
        }
        System.out.println(count);
    }
}
