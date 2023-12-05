package com.jia.algorithm.class04;

/**
 * @author wugongzi
 * @description 递归获取最大值
 * @date 2023-12-05 12:00
 */
public class Code12_RecursionGetMax {
    public static void main(String[] args) {
        int[] arr = {2, 3, 423, 3, 12, 23, 343};
        System.out.println(process(arr, 0, arr.length - 1));
    }

    public static Integer process(int[] arr,int L,int R) {
        if (L == R) {
            return arr[L];
        }
        int mid = (L+R)/2;
        int leftMax = process(arr, L, mid);
        int rightMax = process(arr, mid + 1, R);
        return Math.max(leftMax, rightMax);
    }
}
