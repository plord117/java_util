package com.cxc.sort;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.function.BiFunction;

/**
 * @author : chi xuchong
 */
public abstract class AbstractSort {


    protected static final int TEST_TIME = 500_000;
    protected static final int MAX_SIZE = 100;
    protected static final int MAX_VALUE = 100;
    protected static final int SMALL_TEST_SIZE = 10;
    protected static final int SMALL_TEST_VALUE = 10;

    protected void swap(int[] arr, int i, int j) {
        if (i == j) {
            return;
        }
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public void test() {
        test('0');
    }

    public void test(char sign) {
        if (sign == '+') {
            test(AbstractSort::generatePositiveRandomArray);
        } else {
            test(AbstractSort::generateRandomArray);
        }
    }

    public void test(BiFunction<Integer, Integer, int[]> generateMethod) {
        boolean succeed = true;
        for (int i = 0; i < TEST_TIME; i++) {
            int[] arr1 = generateMethod.apply(MAX_SIZE, MAX_VALUE);
            int[] arr2 = copyArray(arr1);
            try {
                sort(arr1);
                comparator(arr2);
                if (isNotEqual(arr1, arr2)) {
                    succeed = false;
                    break;
                }
            } catch (Exception e) {
                System.out.println("Exception caught: " + e.getMessage());
                System.out.println("your method = " + Arrays.toString(arr1));
                System.out.println("comparator = " + Arrays.toString(arr2));
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");

        if (!succeed) {
            while (true) {
                int[] arr1 = generateMethod.apply(SMALL_TEST_SIZE, SMALL_TEST_VALUE);
                int[] arr2 = copyArray(arr1);
                int[] backup = copyArray(arr1);

                try {
                    sort(arr1);
                    comparator(arr2);
                    if (isNotEqual(arr1, arr2)) {
                        System.out.println("backup = " + Arrays.toString(backup));
                        System.out.println("your method = " + Arrays.toString(arr1));
                        System.out.println("comparator = " + Arrays.toString(arr2));
                        break;
                    }
                } catch (Exception e) {
                    System.out.println("Exception caught: " + e.getMessage());
                    System.out.println("backup = " + Arrays.toString(backup));
                    System.out.println("your method = " + Arrays.toString(arr1));
                    System.out.println("comparator = " + Arrays.toString(arr2));
                    break;
                }

            }
        }
    }


    private static int[] generatePositiveRandomArray(int maxSize, int maxValue) {
        SecureRandom random = new SecureRandom();
        int[] arr = new int[random.nextInt(maxSize + 1)]; // 长度随机
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(maxValue + 1);// 数据随机
        }
        return arr;
    }

    private static int[] generateRandomArray(int maxSize, int maxValue) {
        // Math.random() => [0, 1)所有小数 等概率返回一个
        // Math.random() * N [0, N)所有小数 等概率返回一个
        // (int) Math.random() * N [0, N-1]所有整数 等概率返回一个
        SecureRandom random = new SecureRandom();
        int[] arr = new int[random.nextInt(maxSize + 1)]; // 长度随机
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(maxValue + 1) - random.nextInt(maxValue);// 数据随机
        }
        return arr;
    }

    private static int[] copyArray(int[] arr) {
        if (arr == null) {
            return new int[0];
        }
        int[] res = new int[arr.length];
        System.arraycopy(arr, 0, res, 0, arr.length);
        return res;
    }

    public abstract void sort(int[] arr);

    protected static void comparator(int[] arr) {
        Arrays.sort(arr);
    }

    protected static boolean isNotEqual(int[] arr1, int[] arr2) {
        if (arr1 == null && arr2 == null) {
            return false;
        }
        if (arr1 == null || arr2 == null) {
            return true;
        }
        if (arr1.length != arr2.length) {
            return true;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return true;
            }
        }
        return false;
    }

}
