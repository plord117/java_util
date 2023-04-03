package com.cxc.sort;

/**
 * @author : chi xuchong
 */
public class Merge extends AbstractSort {

    private int[] auxiliary;

    @Override
    public void sort(int[] arr) {
        // base case
        if (arr == null || arr.length < 2) {
            return;
        }
        auxiliary = new int[arr.length];
        sort(arr, 0, arr.length - 1);
    }

    private void sort(int[] arr, int lo, int hi) {
        // base case
        if (hi == lo) {
            return;
        }
        int mid = lo + ((hi - lo) >> 2);
        sort(arr, lo, mid);
        sort(arr, mid + 1, hi);
        merge(arr, lo, mid, hi);
    }

    private void merge(int[] arr, int lo, int mid, int hi) {
        int i = lo;
        int j = mid + 1;
        int[] auxiliary = getAuxiliary(arr.length);
        // 等价于 k 从lo到<= hi aux[k] = arr[k]
        if (hi + 1 - lo >= 0) {
            System.arraycopy(arr, lo, auxiliary, lo, hi + 1 - lo);
        }
        for (int k = lo; k <= hi; k++) {
            if (i > mid) {
                arr[k] = auxiliary[j];
                j++;
            } else if (j > hi) {
                arr[k] = auxiliary[i];
                i++;
            } else if (auxiliary[j] < auxiliary[i]) {
                arr[k] = auxiliary[j];
                j++;
            } else {
                arr[k] = auxiliary[i];
                i++;
            }
        }

    }

    private int[] getAuxiliary(int length) {
        if (auxiliary == null || auxiliary.length < length) {
            auxiliary = new int[length];
        }
        return auxiliary;
    }

    public static void main(String[] args) {
        new Merge().test();
    }
}
