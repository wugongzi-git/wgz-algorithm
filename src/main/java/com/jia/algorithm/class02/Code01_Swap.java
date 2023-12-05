package com.jia.algorithm.class02;

/**
 * @author wugongzi
 * @description
 * @date 2023-11-17 16:14
 */
public class Code01_Swap {

    public static void main(String[] args) {
        int a = 10;
        int b = 8;
        a = a ^ b;
        b = a ^ b;
        a = a ^ b;
        System.out.println("a " + a);
        System.out.println("b " + b);
    }
}
