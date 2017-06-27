package week6;

import java.util.Scanner;

public class No1 {

    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);
        String input = s.nextLine();

        int noMileage = Integer.parseInt(input);

        input = s.nextLine();
        int mileagePoint[] = splitInputString(input);

        input = s.nextLine();
        int noPayment = Integer.parseInt(input);

        input = s.nextLine();
        int payment[] = splitInputString(input);

        int mileageUsage[][] = new int[noMileage][];

        for (int i = 0; i < noMileage; i++) {
            input = s.nextLine();
            mileageUsage[i] = splitInputString(input);
        }

        int mileageOrder[] = getMileageUsageOrder(mileageUsage);

        for (int i = 0; i < mileageOrder.length; i++) {
            int order = mileageOrder[i];
            for (int j = 0; j < mileageUsage[order].length; j++) {
                if (payment[mileageUsage[order][j]] >= mileagePoint[order]) {
                    payment[mileageUsage[order][j]] -= mileagePoint[order];
                    mileagePoint[order] = 0;
                } else {
                    mileagePoint[order] -= payment[mileageUsage[order][j]];
                    payment[mileageUsage[order][j]] = 0;
                }
            }
        }

        int minPayment = 0;
        for (int i = 0; i < payment.length; i++) {
            minPayment += payment[i];
        }
        System.out.println("total Payment: " + minPayment);
    }

    public static int[] splitInputString(String input) {
        String splitInput[] = input.split(" ");
        int splitInt[] = new int[splitInput.length];

        for (int i = 0; i < splitInput.length; i++) {
            splitInt[i] = Integer.parseInt(splitInput[i]);
        }

        return splitInt;
    }

    public static int[] getMileageUsageOrder(int[][] mileage) {
        int size[] = new int[mileage.length];
        for (int i = 0; i < mileage.length; i++) {
            size[i] = mileage[i].length;
        }

        int smallestIndex[] = new int[size.length];
        for (int i = 0; i < size.length; i++) {
            int index = 0;
            int smallestSize = Integer.MAX_VALUE;
            for (int j = 0; j < size.length; j++) {
                if (size[j] <= smallestSize) {
                    smallestSize = size[j];
                    index = j;
                }
            }
            smallestIndex[i] = index;
            size[index] = Integer.MAX_VALUE;
        }
        return smallestIndex;
    }
}
