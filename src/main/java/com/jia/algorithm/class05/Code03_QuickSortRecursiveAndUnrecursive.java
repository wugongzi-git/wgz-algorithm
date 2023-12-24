package com.jia.algorithm.class05;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 快排的递归和非递归版本
 */
public class Code03_QuickSortRecursiveAndUnrecursive {

    // 荷兰国旗问题
    public static int[] netherlandsFlag(int[] arr, int L, int R) {
        if (L > R) {
            return new int[] { -1, -1 };
        }
        if (L == R) {
            return new int[] { L, R };
        }
        int less = L - 1;
        int more = R;
        int index = L;
        while (index < more) {
            if (arr[index] == arr[R]) {
                index++;
            } else if (arr[index] < arr[R]) {
                swap(arr, index++, ++less);
            } else {
                swap(arr, index, --more);
            }
        }
        swap(arr, more, R);
        return new int[] { less + 1, more };
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    // 快排递归版本
    public static void quickSort1(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process(arr, 0, arr.length - 1);
    }

    public static void process(int[] arr, int L, int R) {
        if (L >= R) {
            return;
        }
        swap(arr, L + (int) (Math.random() * (R - L + 1)), R);
        int[] equalArea = netherlandsFlag(arr, L, R);
        process(arr, L, equalArea[0] - 1);
        process(arr, equalArea[1] + 1, R);
    }

    private static class Op {
        public int L;
        public int R;

        public Op(int l, int r) {
            L = l;
            R = r;
        }
    }

    // 快排3.0 非递归版本 用栈来执行
    public static void quickSort2(int[] arr) {
        if (null == arr || arr.length < 2) {
            return;
        }
        int[] equalArea = netherlandsFlag(arr, 0, arr.length - 1);
        Stack<Op> stack = new Stack<>();
        stack.push(new Op(0, equalArea[0] - 1));
        stack.push(new Op(equalArea[1] + 1, arr.length - 1));
        while (!stack.isEmpty()) {
            Op op = stack.pop();
            if (op.L < op.R) {
                swap(arr, op.L + (int) (Math.random() * (op.R - op.L + 1)), op.R); // 随机交换数组
                // 在利用荷兰排序
                equalArea = netherlandsFlag(arr, op.L, op.R);
                // 产生新的位置，放入栈中继续排序
                stack.push(new Op(op.L, equalArea[0] - 1));
                stack.push(new Op(equalArea[1] + 1, op.R));
            }
        }
    }

    // 快排 3.0 非递归版本 队列实现
    public static void quickSort3(int[] arr) {
        if (null == arr || arr.length < 2) {
            return;
        }
        swap(arr, (int) (Math.random() * (arr.length)), arr.length - 1);
        int[] equalArea = netherlandsFlag(arr, 0, arr.length - 1);
        Queue<Op> queue = new LinkedList<>();
        queue.add(new Op(0, equalArea[0] - 1));
        queue.add(new Op(equalArea[1] + 1, arr.length - 1));
        while (!queue.isEmpty()) {
            Op op = queue.poll();
            if (op.L < op.R) {
                swap(arr, (int) (op.L + Math.random() * (op.R - op.L + 1)), op.R);
                equalArea = netherlandsFlag(arr, op.L, op.R);
                queue.add(new Op(op.L, equalArea[0] - 1));
                queue.add(new Op(equalArea[1] + 1, op.R));
            }
        }
    }

    // 生成随机数组（用于测试）
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }

    // 拷贝数组（用于测试）
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

    // 对比两个数组（用于测试）
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

    // 跑大样本随机测试（对数器）
    public static void main(String[] args) {
        int testTime = 50000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        System.out.println("test begin");
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            int[] arr3 = copyArray(arr1);
            quickSort1(arr1);
            quickSort2(arr2);
            quickSort3(arr3);
            if (!isEqual(arr1, arr2) || !isEqual(arr2,arr3)) {
                succeed = false;
                break;
            }
        }
        System.out.println("test end");
        System.out.println("测试" + testTime + "组是否全部通过：" + (succeed ? "是" : "否"));
    }




}
