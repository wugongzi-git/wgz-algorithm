package com.jia.algorithm.class05;


/**
 * 给你一个整数数组 nums 以及两个整数 lower 和 upper 。
 * 求数组中，值位于范围 [lower, upper] （包含 lower 和 upper）之内的 区间和的个数 。
 * 区间和 S(i, j) 表示在 nums 中，位置从 i 到 j 的元素之和，包含 i 和 j (i ≤ j)。
 * leecode 测评 https://leetcode.com/problems/count-of-range-sum/
 */
public class Code01_CountOfRangeSum {

    public static int countRangeSum(int[] nums, int lower, int upper) {
        if (null == nums || nums.length == 0) {
            return 0;
        }
        long[] sum = new long[nums.length];
        sum[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            sum[i] += sum[i - 1] + nums[i];
        }
        return process(sum, 0, sum.length - 1, lower, upper);
    }

    public static int process(long[] sum, int L, int R, int lower, int upper) {
        // L==R 说明只有一个数，判断这个数是否符合
        if (L == R) {
            return sum[L] >= lower && sum[R] <= upper ? 1 : 0;
        }
        int M = (L + R) / 2;
        // lower~upper 上的数 == 递归过程中左侧产生的符合范围的数+递归过程右侧的数+merge 的过程产生的数
        return process(sum, L, M, lower, upper) + process(sum, M + 1, R, lower, upper) + merge(sum, L, M, R, lower, upper);
    }

    public static int merge(long[] sum, int L, int M, int R, int lower, int upper) {
        int ans = 0;
        int windowL = L; // 左侧窗口
        int windowR = L; // 右侧窗口
        // [windowL, windowR)
        for (int i = M + 1; i <= R; i++) {
            long min = sum[i] - upper;
            long max = sum[i] - lower;
            while (windowR <= M && sum[windowR] <= max) {
                windowR++;
            }
            while (windowL <= M && sum[windowL] < min) {
                windowL++;
            }
            ans += windowR - windowL;
        }
        long[] help = new long[R - L + 1];
        int i = 0;
        int p1 = L;
        int p2 = M+1;
        while (p1 <= L && p2 <= R) {
            help[i++] = sum[p1] <= sum[p2] ? sum[p1++] : sum[p2++];
        }
        while (p1 <= M) {
            help[i++] = sum[p1++];
        }
        while (p2 <= R) {
            help[i++] = sum[p2++];
        }
        for (i = 0; i < help.length; i++) {
            sum[L + i] = help[i];
        }
        return ans;
    }



}
