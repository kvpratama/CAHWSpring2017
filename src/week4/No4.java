package week4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class No4 {

    public static void main(String[] args) {
        List<Integer> keysToPress = new ArrayList<Integer>();
        int totalPushes = 0;

        Scanner scanner = new Scanner(System.in);
        int numberOfKeys = scanner.nextInt();
        int numberOfKeysToPress = scanner.nextInt();

        for (int i = 0; i < numberOfKeysToPress; i++) {
            keysToPress.add(scanner.nextInt());
        }
        Collections.sort(keysToPress);

        for (int i : keysToPress) {System.out.print(i + " ");}

        System.out.println(pressKey(numberOfKeys, keysToPress, totalPushes, 1, numberOfKeys));
    }

    public static int pressKey(int numberOfKeys, List<Integer> keysToPress, int totalPushes, int leftIndex, int rightIndex){
        if (keysToPress.size() > 0){
            int targetKey = ((rightIndex-leftIndex)/2) + leftIndex;

            int closestMeanIndex = findClosestMeanIndex(keysToPress, targetKey);
            totalPushes += numberOfKeys - 1;

            int numberOfLeftKeys = keysToPress.get(closestMeanIndex) - leftIndex;//left
            int numberOfRightKeys = Math.abs(keysToPress.get(closestMeanIndex) - rightIndex);//right
            List<Integer> keysToPressLeft = keysToPress.subList(0, closestMeanIndex);//left
            List<Integer> keysToPressRight = keysToPress.subList(closestMeanIndex+1, keysToPress.size());//right
            totalPushes = pressKey(numberOfLeftKeys, keysToPressLeft, totalPushes, leftIndex, keysToPress.get(closestMeanIndex)-1);
            totalPushes = pressKey(numberOfRightKeys, keysToPressRight, totalPushes, keysToPress.get(closestMeanIndex)+1, rightIndex);
        }
        return totalPushes;
    }

    public static int findClosestMeanIndex(List<Integer> keysToPress, int targetKey) {

        if (keysToPress.size() == 1){
            return 0;
        }

        int left = -1, closestNumberIndex;
        int right = keysToPress.size();

        while (right > left + 1) {
            int middle = (left + right) / 2;
            if (keysToPress.get(middle) >= targetKey)
                right = middle;
            else
                left = middle;
        }

        if(left == -1){left = 0;}
        if (right == keysToPress.size()){right = keysToPress.size()-1;}

        if (right < keysToPress.size() && keysToPress.get(right) == targetKey) {
            closestNumberIndex = right;
        } else if(Math.abs(keysToPress.get(right) - targetKey) > Math.abs(keysToPress.get(left) - targetKey)) {
            closestNumberIndex = left;
        }else {
            closestNumberIndex = right;
        }
        return closestNumberIndex;
    }
}
