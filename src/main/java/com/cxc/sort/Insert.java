package com.cxc.sort;


/**
 * 插入排序
 * 稳固区（拍好序的部分）在前面  => 外循环递增
 * 前面有一段是拍好的， 后面的向前比较，如果比前一个小交换
 * 内循环向前比较 => 内循环递减
 * @author cxcod
 */
public class Insert extends AbstractSort {


    public void sort2(int[] arr) {
        // base case
        if (arr == null || arr.length < 2) {
            return;
        }
        // 这种写法是 外层i代表新加入的值 j是从稳定区外新加入的值
        for (int i = 1; i < arr.length; i++) {
            // 判断 j > 0 还是 j >= 0 关键在于 swap里面会不会越界
            for (int j = i - 1; j >= 0; j--) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
            }
        }
    }

    @Override
    public void sort(int[] arr) {
        // base case
        if (arr == null || arr.length < 2) {
            return;
        }
        // 这种写法是 外层i代表稳定区最右侧 j是从稳定区外新加入的值
        for (int i = 0; i < arr.length - 1; i++) {
            // 判断 j > 0 还是 j >= 0 关键在于 swap里面会不会越界
            for (int j = i + 1; j > 0; j--) {
                if (arr[j] < arr[j - 1]) {
                    swap(arr, j, j - 1);
                }
            }
        }
    }

    public static void main(String[] args) {
        new Insert().test();
    }


}
