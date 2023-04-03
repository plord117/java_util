package com.cxc.bitOp.xor;

/**
 * @author : chi xuchong
 */
public class EvenTimesOddTimes {

    /**
     * 问题：一个int数组 里面只有一个数有奇数个，其余为偶数个求 奇数个数的值
     *
     * @param arr int数组
     */
    public static void printOddTimesNum1(int[] arr) {
        if (arr.length == 1) {
            System.out.println(arr[0]);
        }
        // eor 是Exclusive Or（异或）的缩写 写成xor更常见
        int eor = 0;
        for (int cur : arr) {
            eor ^= cur;
        }
        System.out.println(eor);
    }

    /**
     * 问题：一个int数组 里面只有两个数有奇数个，其余为偶数个求 奇数个数的值
     *
     * @param arr int数组
     */
    public static void printOddTimesNum2(int[] arr) {
        int xor = 0;
        for (int cur : arr) {
            xor ^= cur;
        }
        // 设两个奇数分别为 a,b 且 a != b
        // xor = a ^ b != 0
        // xor至少有一位为1
        // 异或后的结果为1 说明 a b的对应位不相等
        // 获取xor 最右侧的1 形如：00001000
        int rightOne = xor & (~xor + 1);
        // 再次遍历 只异或这一位为1（或为0）的数值
        // 最终会得到 a or b
        int onlyOne = 0;
        for (int cur : arr) {
            if ((cur & rightOne) == 1) {
                onlyOne ^= cur;
            }
        }
        System.out.println(onlyOne + " " + (onlyOne ^ xor));
    }
}
