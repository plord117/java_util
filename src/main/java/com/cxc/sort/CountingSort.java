package com.cxc.sort;

/**
 * @author : chi xuchong
 */
public class CountingSort extends AbstractSort {
    @Override
    public void sort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

        int[] countArray = new int[findRange(arr)];
        computeCountArray(arr, countArray);
        restoreArray(arr, countArray);
    }

    private static int findRange(int[] arr) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int cur : arr) {
            if (cur < min) {
                min = cur;
            }
            if (cur > max) {
                max = cur;
            }
        }
        return max - min + 1;
    }

    private static void computeCountArray(int[] arr, int[] countArray) {
        int offset = findOffset(arr);
        for (int cur : arr) {
            countArray[cur - offset]++;
        }
    }

    private static void restoreArray(int[] arr, int[] countArray) {
        int index = 0;
        for (int i = 0; i < countArray.length; i++) {
            for (int j = 0; j < countArray[i]; j++) {
                arr[index] = i + findOffset(arr);
                index++;
            }
        }
    }

    private static int findOffset(int[] arr) {
        int min = Integer.MAX_VALUE;
        for (int cur : arr) {
            if (cur < min) {
                min = cur;
            }
        }
        return min;
    }

    public static void main(String[] args) {
        new CountingSort().test('+');
    }
}
