package com.jia.algorithm.class01;

/**
 * @author wugongzi
 * @description 冒泡排序
 * @date 2023-11-16 14:53
 */
public class Code02_BubbleSort {


    /**
     * 1 和 2 比较，小的数往前，大的数往后
     * 2 和 3 比较，小的数往前，大的数往后
     * N-1 和 N 比较，小的数往前，大的数往后
     * 这样第一次遍历结束后，最大的值来到最后一位
     * 冒泡排序是大的上浮
     */
    public static void bubbleSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int i = arr.length-1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
            }
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {

    }


}
