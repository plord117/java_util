package com.cxc.sort;

/**
 * 计数排序：数数
 *
 * @author : chi xuchong
 */
public class Counting extends AbstractSort {


    public static void main(String[] args) {
        new Counting().test('+');
    }

    @Override
    public void sort(int[] arr) {
        // base case
        if (arr == null || arr.length < 2) {
            return;
        }
        // 找数组最大值和最小值
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
        // 计算偏差值并初始化数组
        int offset = min;
        int[] countArray = new int[max - min + 1];

        // 计算词频
        for (int cur : arr) {
            countArray[cur - offset]++;
        }
        // 还原数组
        int index = 0;
        for (int i = 0; i < countArray.length; i++) {
            for (int j = 0; j < countArray[i]; j++) {
                arr[index] = i + offset;
                index++;
            }
        }
    }
}
