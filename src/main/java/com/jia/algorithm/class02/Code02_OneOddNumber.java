package com.jia.algorithm.class02;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wugongzi
 * @description 数组中一个奇数
 * @date 2023-11-17 16:18
 */
public class Code02_OneOddNumber {

    /**
     * 随机生成一个数组
     * 有一种数出现了奇数次
     * 有一种数出现了偶数次
     */
    public static int[] randomArr() {
        // 随机生成一个数组长度，长度为奇数，长度必须 >=3
        int length = (int) (Math.random() * 100) + 3;
        if (length % 2 == 0) {
            length += 1;
        }
        int[] arr = new int[length];
        int a = (int) (Math.random() * 10) +1;
        int b = (int) (Math.random() * 10) + 1;
        if (a == b) {
            a++;
        }
        // 生成一个奇数次的数
        int odd = (int) (Math.random() * length);
        if (odd % 2 == 0) {
            odd++;
        }
        int index = 0;
        for (; index < odd; index++) {
            arr[index] = a;
        }
        for (; index < arr.length; index++) {
            arr[index] = b;
        }
        return arr;
    }

    // 对数器
    public static int test(int[] arr) {
        if (arr == null || arr.length < 4) {
            return -1;
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i : arr) {
            if (map.containsKey(i)) {
                map.put(i, map.get(i) + 1);
            } else {
                map.put(i, 1);
            }
        }
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() % 2 != 0) {
                return entry.getKey();
            }
        }
        return -1;
    }

    public static int searchOddNumber(int[] arr) {
        if (arr == null || arr.length < 4) {
            return -1;
        }
        int result = 0;
        for (int i : arr) {
            result ^= i;
        }
        return result;
    }

    public static void printArr(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    public static void main(String[] args) {
        int time = 100000;
        System.out.println("测试开始");
        for (int i = 0; i < time; i++) {
            int[] arr = randomArr();
            int i1 = searchOddNumber(arr);
            int i2 = test(arr);
            if (i1 != i2) {
                System.out.println("出错了");
                return;
            }
        }
        System.out.println("测试成功");
    }

}
