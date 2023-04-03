package com.cxc.sort;


/**
 * @author : chi xuchong
 */
public class RadixSort extends AbstractSort {

    public static final int RADIX = 10;

    public static void main(String[] args) {
        new RadixSort().test('+');
    }

    @Override
    public void sort(int[] arr) {
        // base case
        if (arr == null || arr.length < 2) {
            return;
        }
        // 1. 找最大数
        int max = findMax(arr);
        // 2.计算最大数的位数
        int digit = getDigitCount(max);

        sortBySwap(arr, 0, arr.length - 1, digit);
    }

    /**
     * 计算给定数字的位数
     *
     * @param num 正整数
     * @return 正整数的位数
     */
    public int getDigitCount(int num) {
        int digitCount = 0;
        while (num > 0) {
            num /= RADIX;
            digitCount++;
        }
        return digitCount;
    }

    private static int findMax(int[] arr) {
        int max = Integer.MIN_VALUE;
        for (int cur : arr) {
            if (cur > max) {
                max = cur;
            }
        }
        return max;
    }

    public void sort(int[] arr, int left, int right, int bits) {

        // 辅助数组
        int[] auxiliary = new int[right - left + 1];
        for (int d = 1; d <= bits; d++) {
            // 词频数组
            int[] count = new int[RADIX];
            for (int i = left; i <= right; i++) {
                int digit = getDigit(arr[i], d);
                // 计算词频数组
                count[digit]++;
            }
            // 计算前缀和数组
            for (int i = 1; i < RADIX; i++) {
                count[i] += count[i - 1];
            }

            // 从后往前遍历数组
            for (int i = right; i >= left; i--) {
                // 根据词频复制到辅助数组（按当前位排序）
                int curDigit = getDigit(arr[i], d);
                // 当前位的当前数字有多少个<=它
                count[curDigit]--;
                int index = count[curDigit];
                auxiliary[index] = arr[i];
            }

            // 将辅助空间的内容拷贝回原数组
            System.arraycopy(auxiliary, 0, arr, left, auxiliary.length);
        }
    }


    public void sortBySwap(int[] arr, int left, int right, int bits) {
        // 辅助数组
        int[] auxiliary = new int[right - left + 1];
        boolean swap = false;
        for (int d = 1; d <= bits; d++) {
            // 词频数组
            int[] count = new int[RADIX];
            for (int i = left; i <= right; i++) {
                int digit = getDigit(swap ? auxiliary[i - left] : arr[i], d);
                // 计算词频数组
                count[digit]++;
            }
            // 计算前缀和数组
            for (int i = 1; i < RADIX; i++) {
                count[i] += count[i - 1];
            }

            // 从后往前遍历数组
            for (int i = right; i >= left; i--) {
                // 根据词频复制到辅助数组（按当前位排序）
                int curDigit = getDigit(swap ? auxiliary[i - left] : arr[i], d);
                // 当前位的当前数字有多少个<=它
                count[curDigit]--;
                int index = count[curDigit];

                if (swap) {
                    arr[index + left] = auxiliary[i - left];
                } else {
                    auxiliary[index] = arr[i];
                }
            }
            swap = !swap;
        }
        if (swap) {
            System.arraycopy(auxiliary, 0, arr, left, right - left + 1);
        }
    }

    /**
     * 获取正整数第d位的数字
     *
     * @param num 正整数
     * @param d   从各位开始，个位是1 d>=1
     * @return 正整数第d位的数字
     */
    public int getDigit(int num, int d) {
        return (num / (int) (Math.pow(RADIX, (double) d - 1))) % RADIX;
    }
}
