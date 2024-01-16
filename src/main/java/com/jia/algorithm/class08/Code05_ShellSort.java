package com.jia.algorithm.class08;

/**
 * 希尔排序
 */
public class Code05_ShellSort {

    public static void shellSort(int[] arr) {
        if (null == arr || arr.length < 2) {
            return;
        }
        int[] step = {5, 2, 1};
        for (int s = 0; s < step.length; s++) {
            for (int i = step[s]; i < arr.length; i++) {
                for (int j = i - step[s]; j >= 0 && arr[j] > arr[j + step[s]]; j -= step[s]) {
                    swap(arr,j,j+step[s]);
                }
            }
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

}
