package week4;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class No7 {

    public static void main(String[] args) {
        Map<String, Integer> intimacies = new HashMap<>();
        int totalIntimacy = 0;

        Scanner scanner = new Scanner(System.in);
        int man = scanner.nextInt();
        int woman = scanner.nextInt();
        int numIntimacy = scanner.nextInt();
        for (int i = 0; i < numIntimacy; i++) {
            int manIndex = scanner.nextInt();
            int womanIndex = scanner.nextInt();
            int intimacy = scanner.nextInt();
            if (!intimacies.containsKey(manIndex + "" + womanIndex) ||
                    (intimacies.containsKey(manIndex + "" + womanIndex) &&
                            intimacy > intimacies.get(manIndex + "" + womanIndex))) {
                intimacies.put(manIndex + "" + womanIndex, intimacy);
            }
        }

        for (Map.Entry<String, Integer> i :
                intimacies.entrySet()) {
            System.out.println(i.getValue());
            totalIntimacy += i.getValue();
        }
        System.out.println(10000 * (man + woman) - totalIntimacy);
    }
}
