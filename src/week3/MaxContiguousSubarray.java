package week3;

import java.util.Arrays;
import java.util.HashMap;

public class MaxContiguousSubarray {

    public static int maxContiguousSubarray(int[] array) {
        int newsum = array[0];
        int maxsum = array[0];

        for (int i = 1; i < array.length; i++) {
            newsum = Math.max(newsum + array[i], array[i]);
            maxsum = Math.max(maxsum, newsum);
        }
        return maxsum;
    }

    public static HashMap<String, Integer> maxContiguousSubarrayAndIndex(int[] array) {
        int newsum = array[0], maxsum = Integer.MIN_VALUE, tempMaxsum = array[0],
                tempStart = 0, tempEnd = 0, start = 0, end = 0;

        for (int i = 1; i < array.length; i++) {
            if (newsum + array[i] > array[i] && newsum + array[i] > tempMaxsum) {
                tempEnd = i;
                newsum = newsum + array[i];
                tempMaxsum = newsum;
            } else if (newsum + array[i] > array[i]) {
                newsum = newsum + array[i];
            } else if (array[i] > newsum + array[i]) {
                if (tempMaxsum > maxsum) {
                    maxsum = tempMaxsum;
                    start = tempStart;
                    end = tempEnd;
                }
                tempMaxsum = array[i];
                newsum = array[i];
                tempStart = i;
                tempEnd = i;
            }
        }

        if (tempMaxsum > maxsum) {
            maxsum = tempMaxsum;
            start = tempStart;
            end = tempEnd;
        }
        HashMap<String, Integer> result = new HashMap<String, Integer>();
        result.put("start", start);
        result.put("end", end);
        result.put("maxsum", maxsum);

        System.out.println("(" + result.get("start") + "," + result.get("end") + ") : " + result.get("maxsum"));
        return result;
    }

    public static HashMap<String, Integer> maxContiguousSubarrayAndIndex(int[][] array) {
        HashMap<String, Integer> result = new HashMap<String, Integer>();
        result.put("i", 0);
        result.put("start", 0);
        result.put("end", 0);
        result.put("maxsum", Integer.MIN_VALUE);
        for (int i = 0; i < array.length; i++) {
            HashMap<String, Integer> tempResult = maxContiguousSubarrayAndIndex(
                    Arrays.copyOfRange(array[i], 0, array[i].length - 1));
            if (tempResult.get("maxsum") > result.get("maxsum")){
                result = tempResult;
                result.put("i", i);
            }
        }

        System.out.println("(" + result.get("i") + "," + result.get("start") + ") - (" +
        result.get("i") + "," + result.get("end") + ") : " + result.get("maxsum"));
        return result;
    }
}
