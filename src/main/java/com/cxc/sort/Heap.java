package com.cxc.sort;

/**
 * @author : chi xuchong
 */
public class Heap extends AbstractSort {
    @Override
    public void sort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int heapSize = arr.length;

        for (int i = (heapSize - 1) / 2; i >= 0; i--) {
            heapify(arr, i, heapSize);
        }

        while (heapSize > 1) {
            heapSize--;
            swap(arr, 0, heapSize);
            heapify(arr, 0, heapSize);
        }
    }

    /**
     * 堆化（下沉）函数
     *
     * @param arr      用数组构建堆
     * @param index    从哪个节点开始
     * @param heapSize 控制堆元素个数
     */
    public void heapify(int[] arr, int index, int heapSize) {
        // 堆化父节点和两个子节点比较 如果父比两个节点中最大的还大 交换 直到结尾

        // 左孩子下标
        int leftIndex = 2 * index + 1;
        int largestIndex;

        while (leftIndex < heapSize) {
            // 右孩子下标
            int rightIndex = leftIndex + 1;

            largestIndex = leftIndex;
            if (rightIndex < heapSize && arr[rightIndex] > arr[leftIndex]) {
                largestIndex = rightIndex;
            }

            if (arr[largestIndex] > arr[index]) {
                swap(arr, index, largestIndex);
            } else {
                break;
            }
            index = largestIndex;
            leftIndex = 2 * index + 1;
        }
    }

    public static void main(String[] args) {
        new Heap().test();
    }
}
