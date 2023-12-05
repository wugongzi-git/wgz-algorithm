package com.jia.algorithm.class03;

/**
 * @author wugongzi
 * @description 数组中出现了两个奇数
 * @date 2023-11-17 17:14
 */
public class Code02_TwoOddNumber {

    public static void twoOddNumber(int[] arr) {
        int eor = 0;
        for (int i = 0; i < arr.length; i++) {
            eor ^= arr[i];
        }
        // 找出 eor 最左侧的 1
        eor = eor & (-eor);
        int result1 = 0;
        int result2 = 0;
        for (int i = 0; i < arr.length; i++) {
            if ((eor & arr[i]) != 0) {
                result1 ^= arr[i];
            } else {
                result2 ^= arr[i];
            }
        }
        System.out.println("两个数分别是 " + result1 + " " + result2);
    }

    public static void main(String[] args) {
        int[] arr = {1, 1, 1, 2, 2, 5, 5, 5, 5 , 10, 10, 3, 3, 3, 3, 8};
        twoOddNumber(arr);
    }

}
