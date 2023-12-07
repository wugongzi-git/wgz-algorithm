package com.jia.algorithm.class04;

import java.util.Arrays;

/**
 * @author wugongzi
 * @description 归并排序
 * @date 2023-12-07 9:31
 */
public class Code01_MergeSort {

    public static void process(int[] arr, int L, int R) {
        if (L == R) {
            return;
        }
        int middle = (L + R) / 2;
        process(arr, L, middle);
        process(arr, middle + 1, R);
        merge(arr, L, middle, R);
    }

    /**
     * 将数组在 L-R 范围上排序
     * merge 过程就是谁小拷贝谁的过程
     */
    public static void merge(int[] arr, int L, int M, int R) {
        int[] help = new int[R - L + 1];
        int i = 0;
        int p1 = L;
        int p2 = M + 1;
        while (p1 <= M && p2 <= R) {
            help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }
        // 要么p1越界了，要么p2越界了
        while (p1 <= M) {
            help[i++] = arr[p1++];
        }
        while (p2 <= R) {
            help[i++] = arr[p2++];
        }
        for (i = 0; i < help.length; i++) {
            arr[L + i] = help[i];
        }
    }

    public static void mergeSort1(int[] arr) {
        if (null == arr || arr.length < 2) {
            return;
        }
        process(arr, 0, arr.length - 1);
    }

    public static void mergeSort2(int[] arr) {
        if (null == arr || arr.length < 2) {
            return;
        }
        // 步长开始为 1
        int step = 1;
        int N = arr.length;
        while (step < N) {
            int L = 0;
            while (L < N) {
                // 步长超过数组长度返回
                if (step >= N - L) {
                    break;
                }
                int M = L + step - 1; // 左边数组位置
                int R = Math.min(M + step, N - 1); // 右边数组位置
                merge(arr, L, M, R);
                L = R + 1;
                // 防止溢出
                if (step > N / 2) {
                    break;
                }
            }
            // 步长乘 2
            step <<= 1;
        }
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
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

    public static void main(String[] args) {
        int count = 10000;
        System.out.println("测试开始");
        for (int i = 0; i < count; i++) {
            int[] arr1 = generateRandomArray(10, 10);
            int[] arr2 = copyArray(arr1);
            mergeSort1(arr1);
            mergeSort2(arr2);
            boolean equal = isEqual(arr1, arr2);
            if (!equal) {
                System.out.println("失败了");
            }
        }
        System.out.println("测试结束");
    }

}
