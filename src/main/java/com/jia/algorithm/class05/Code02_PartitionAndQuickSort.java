package com.jia.algorithm.class05;

/**
 * 1. 荷兰国旗问题
 * 2. 快排
 */
public class Code02_PartitionAndQuickSort {

    /**
     * 荷兰国旗问题，返回当前数组中 == arr[R-1] 的数组下标
     * 这里是以数组中最后一个数作为比较，比较完成后需要将数组最后一个数交换位置
     */
    public static int[] netherlandsFlag(int[] arr,int L,int R) {
        // 说明传入的范围不对
        if (L > R || R > arr.length - 1 || L < 0) {
            return new int[]{-1, -1};
        }
        if (L == R) {
            return new int[]{L, R};
        }
        int less = L - 1;
        int more = R;
        int current = L;
        // 当前数和右边界没有碰撞则继续排序
        while (current < more) {
            if (arr[current] < arr[R]) {
                // 如果 current < num，则和左边界下一个数交换，同时两个位置都 ++
                swap(arr, current, less + 1);
                current++;
                less++;
            } else if (arr[current] > arr[R]) {
                swap(arr, current, more - 1);
                more--;
            } else {
                current++;
            }
        }
        // 最后把 R 和右边界位置交换
        swap(arr, R, more);
        // 返回 == num 数的左右边界下标
        return new int[]{less + 1, more};
    }

    /**
     * 指定一个数组 arr，要求做到 <= num 的数放在左边，> num 的数放在右边
     * partition 排序，相当于一次只搞定一个数，也就是 == arr[R] 的数，而荷兰国旗则是每次搞定一堆数（所有 == arr[R] 的数字）
     * @return 返回 <= num 的数的右侧位置下标
     */
    public static int partition(int[] arr, int L, int R) {
        if (L > R) {
            return -1;
        }
        if (L == R) {
            return L;
        }
        int less = L-1;
        int current = L;
        while (current < R) {
            if (arr[current] <= arr[R]) {
                swap(arr, ++less, current++);
            } else {
                current++;
            }
        }
        swap(arr, ++less, R);
        return less;
    }


    public static void quickSort1(int[] arr) {
        if (null == arr || arr.length < 2) {
            return;
        }
        process1(arr, 0, arr.length - 1);
    }

    public static void process1(int[] arr, int L, int R) {
        if (L >= R) {
            return;
        }
        // L..R partition arr[R] [ <=arr[R] arr[R] >arr[R] ]
        // 一次搞定一个数
        int M = partition(arr, L, R);
        process1(arr, L, M - 1);
        process1(arr, M + 1, R);
    }

    /**
     * 快排也是递归的过程
     * 先是在整个范围上，找到 == num 的数字
     * 然后再递归去求左边界上 == num 的数字和 右边界上 ==num 的数字
     * 每次比较都是以左右边界最后的一个数为基准
     */
    public static void quickSort2(int[] arr) {
        if (null == arr || arr.length < 2) {
            return;
        }
        process2(arr, 0, arr.length - 1);
    }

    public static void process2(int[] arr,int L,int R) {
        if (L >= R) {
            return;
        }
        int[] equalArr = netherlandsFlag(arr, L, R);
        process2(arr, 0, equalArr[0] - 1);
        process2(arr, equalArr[1] + 1, R);
    }

    public static void quickSort3(int[] arr) {
        if (null == arr || arr.length < 2) {
            return;
        }
        process3(arr, 0, arr.length - 1);
    }

    /**
     * 第三种快排，之前是以数组最后一个数排序，不具备随机性
     * process3 就是加入了随机性
     */
    private static void process3(int[] arr, int L, int R) {
        if (L >= R) {
            return;
        }
        swap(arr, L + (int) (Math.random() * (R - L + 1)), R);
        int[] equalArr = netherlandsFlag(arr, L, R);
        process3(arr, L, equalArr[0] - 1);
        process3(arr, equalArr[1] + 1, R);
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
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


    // for test
    public static void main(String[] args) {
        int testTime = 500;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            int[] arr3 = copyArray(arr1);
            quickSort1(arr1);
            quickSort2(arr2);
            quickSort3(arr3);
            if (!isEqual(arr1, arr2) || !isEqual(arr2, arr3)) {
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Oops!");
    }

}
