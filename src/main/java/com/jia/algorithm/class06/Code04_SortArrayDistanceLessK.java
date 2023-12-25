package com.jia.algorithm.class06;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 已知一个几乎有序的数组
 * 几乎有序是指，如果把数组排好序的话，每个元素移动的距离一定不超过 K，并且 K 相对数组长度来说是比较小的。
 * 请选择一个合适的排序策略，对这个数组进行排序
 */
public class Code04_SortArrayDistanceLessK {


    public static void sortedArrDistanceLessK(int[] arr, int k) {
        if (k == 0) {
            return;
        }
        // 默认最小堆
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        int index = 0;
        // K 可能超过数组长度，所以取二者最小值
        for (; index <= Math.min(arr.length - 1, k - 1); index++) {
            // 依次放入堆中
            heap.add(arr[index]);
        }
        // 从堆中弹出数据，每次弹出最小值，依次放入数组，然后将新的数加入到 heap 中，保持 heap 中始终有 K 个数，直到数组全部放完
        int i = 0;
        for (; index < arr.length; i++, index++) {
            heap.add(arr[index]);
            arr[i] = heap.poll();
        }
        while (!heap.isEmpty()) {
            arr[i++] = heap.poll();
        }
    }


    // for test
    public static void comparator(int[] arr, int k) {
        Arrays.sort(arr);
    }

    // for test
    public static int[] randomArrayNoMoveMoreK(int maxSize, int maxValue, int K) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        // 生成随机数组
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * (maxValue + 1));
        }
        // 给数组排序
        Arrays.sort(arr);
        // swap[i] == true 表示 i 位置已经交换过了，不需要再交换
        boolean[] swap = new boolean[arr.length];
        // 然后将数组打乱，规则是保证数组排好序后，每个数字移动的位置不能超过 k
        for (int i = 0; i < arr.length; i++) {
            // 随机生成一个不超过 K 位置的数，和 i 交换
            int j = Math.min(i + (int) (Math.random() * (K + 1)), arr.length - 1);
            if (!swap[i] && !swap[j]) {
                swap[i] = true;
                swap[j] = true;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
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
        System.out.println("test begin");
        int testTime = 50000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int k = (int) (Math.random() * maxSize) + 1;
            int[] arr = randomArrayNoMoveMoreK(maxSize, maxValue, k);
            int[] arr1 = copyArray(arr);
            int[] arr2 = copyArray(arr);
            sortedArrDistanceLessK(arr1, k);
            comparator(arr2, k);
            if (!isEqual(arr1, arr2)) {
                succeed = false;
                System.out.println("K : " + k);
                printArray(arr);
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");
    }














}
