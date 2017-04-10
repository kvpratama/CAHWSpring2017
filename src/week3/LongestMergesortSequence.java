package week3;

import java.util.Arrays;

public class LongestMergesortSequence {

    public static void main(String[] args) {
        int[] a = new int[]{10, 20, 30, 1, 2};
        int[] b = new int[]{10, 20, 30};
        mergeSort(a);
        mergeSort(b);
        int[] result = longestMergesortSequence(a, b);
        System.out.println(result.length);
    }

    public static void mergeSort(int[] input) {
        int size = input.length;
        if (size < 2) return;

        int mid = size / 2;
        int[] left = new int[mid];
        int[] right = new int[size - mid];

        for (int i = 0; i < mid; i++) {
            left[i] = input[i];
        }
        for (int i = mid; i < size; i++) {
            right[i - mid] = input[i];
        }

        mergeSort(left);
        mergeSort(right);
        merge(left, right, input);
    }

    public static void merge(int[] left, int[] right, int[] arr) {
        int i = 0, j = 0, k = 0;

        while (i < left.length || j < right.length) {
            if (i < left.length && j < right.length) {
                    arr[k++] = left[i] < right[j] ? left[i++] : right[j++];
            } else if (i < left.length) {
                arr[k++] = left[i++];
            } else if (j < right.length) {
                arr[k++] = right[j++];
            }
        }
    }

    public static int[] longestMergesortSequence(int[] a, int[] b) {
        int[] c = new int[a.length + b.length];
        int i = 0, j = 0, k = 0, duplicateCount = 0;

        while (i < a.length || j < b.length) {
            if (i < a.length && j < b.length) {
                if (a[i] == b[j]) {
                    c[k++] = a[i++];
                    j++;
                    duplicateCount++;
                } else {
                    c[k++] = a[i] < b[j] ? a[i++] : b[j++];
                }
            } else if (i < a.length) {
                c[k++] = a[i++];
            } else if (j < a.length) {
                c[k++] = b[j++];
            }
        }
        return Arrays.copyOf(c, c.length - duplicateCount);
    }
}
