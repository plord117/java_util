package com.cxc.sort;

/**
 * 选择排序
 * 外循环从前往后遍历，从前往后确定
 * 内循环为 确定的部分后面 选择最小值和前面的交换
 * @author : chi xuchong
 */
public class Selection extends AbstractSort {
    @Override
    public void sort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        // 选择排序即选择最小的和前面交换
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            // 交换 arr[i] 和 arr[minIndex]
            swap(arr, i, minIndex);
        }
    }

    public static void main(String[] args) {
        new Selection().test();
    }

}
