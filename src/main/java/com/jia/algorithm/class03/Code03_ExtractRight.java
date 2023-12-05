package com.jia.algorithm.class03;

/**
 * @author wugongzi
 * @description 提取最右侧的 1
 * @date 2023-11-17 16:49
 */
public class Code03_ExtractRight {

    public static void main(String[] args) {
        int a = 10; // 1010
        System.out.println(extractRightOne(a));
    }


    // 提取整形数的最右侧的 1
    public static int extractRightOne(int num) {
        return num & (-num);
    }
}
