package com.jia.algorithm.class08;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 基数排序
 * 采用 10 个队列实现
 */
public class Code04_RadixSort {

    // only for no-negative value
    public static void radixSort(int[] arr) {
        if (null == arr || arr.length < 2) {
            return;
        }
        radixSort(arr, 0, arr.length - 1, maxBits(arr));
    }

    // 找到最高位的数
    public static int maxBits(int[] arr) {
        int result = 0;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
        }
        while (max != 0) {
            max = max / 10;
            result++;
        }
        return result;
    }

    // arr[L..R]排序  ,  最大值的十进制位数digit
    public static void radixSort(int[] arr, int L, int R, int digit) {
        // 准备 10 个桶
        Queue<Integer>[] queues = new Queue[10];
        for (int i = 0; i < queues.length; i++) {
            queues[i] = new LinkedList<>();
        }
        for (int i = 1; i <= digit; i++) {
            for (int j = L; j <= R; j++) {
                int rs = getDigit(arr[j], i);
                // 判断几号桶添加什么元素
                queues[rs].add(arr[j]);
            }
            // 将元素从桶中倒出
            int k = 0;
            for (int s = 0; s < queues.length; s++) {
                while (!queues[s].isEmpty()) {
                    arr[k++] = queues[s].poll();
                }
            }
        }
    }

    public static int getDigit(int value,int digit) {
        // 幂运算
        int pow = (int) Math.pow(10, digit - 1);
        return (value/ pow) % 10;
    }

    // for test
    public static void comparator(int[] arr) {
        Arrays.sort(arr);
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random());
        }
        return arr;
    }

    // for test
    public static int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    // for test
    public static boolean isEqual(int[] arr1, int[] arr2) {
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
            return false;
        }
        if (arr1 == null && arr2 == null) {
            return true;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    // for test
    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // for test
    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 10000;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            radixSort(arr1);
            comparator(arr2);
            if (!isEqual(arr1, arr2)) {
                succeed = false;
                printArray(arr1);
                printArray(arr2);
                System.out.println("=========");
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");

        int[] arr = generateRandomArray(maxSize, maxValue);
        printArray(arr);
        radixSort(arr);
        printArray(arr);
        /*int[] arr = {33, 31};
        radixSort(arr);
        printArray(arr);*/
    }
}
