package com.cxc.sort;

/**
 * @author : chi xuchong
 */
public class Fast extends AbstractSort {

    @Override
    public void sort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        sort(arr, 0, arr.length - 1);
    }

    private void sort(int[] arr, int left, int right) {
        if (right <= left) {
            return;
        }
        swap(arr, left, left + (int) (Math.random() * (right - left + 1)));
        int[] p = partition(arr, left, right);
        sort(arr, left, p[0] - 1);
        sort(arr, p[1] + 1, right);
    }

    public static void main(String[] args) {
        new Fast().test();
    }


    public int[] partition(int[] arr, int left, int right) {
        int target = arr[left];
        // 指针
        int i = left + 1;
        // <区边界，<区最后一位
        int less = left;
        // >区边界，>区第一位
        int more = right + 1;
        // 指针碰到>区时停止
        while (i < more) {
            // 当前数属于小于区
            if (arr[i] < target) {
                // 小于区先扩张
                less++;
                swap(arr, less, i);
                // 指针移动
                i++;
            } else if (arr[i] > target) {
                // 当前区域属于大于区
                // 扩张大于区
                more--;
                swap(arr, more, i);
                // 指针不变
            } else {
                // 相等时只移动指针
                i++;
            }
        }
        // 和标记位置交换 标记位置在最左就和less交换
        swap(arr, left, less);
        return new int[]{less, more - 1};
    }

    private static boolean isRight(int[] arr, int[] partition, int target) {
        if (partition == null || partition.length != 2) {
            return false;
        }
        for (int i = 0; i < arr.length; i++) {
            if (i < partition[0]) {
                if (arr[i] >= target) {
                    return false;
                }
            } else if (i <= partition[1]) {
                if (arr[i] != target) {
                    return false;
                }
            } else if (i > partition[1]) {
                if (arr[i] < target) {
                    return false;
                }
            }
        }
        return true;
    }


}
