package com.cxc.sort;

/**
 * 冒泡排序
 * 稳固区（拍好序的部分）在最后  => 外循环递减
 * 从前往后 相邻的两个数字之间比较交换，遍历到最后能确定最后一位 (内循环从前往后）
 * 所以外循环是从后往前逐步缩写
 * @author : chi xuchong
 */
public class Bubble extends AbstractSort {

    @Override
    public void sort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int i = arr.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
            }
        }
    }


    // for test
    public static void main(String[] args) {
        new Bubble().test();
    }

}
