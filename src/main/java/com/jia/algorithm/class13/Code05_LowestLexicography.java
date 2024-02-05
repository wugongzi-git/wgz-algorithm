package com.jia.algorithm.class13;

import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeSet;

public class Code05_LowestLexicography {


    public static String lowestString1(String[] strArr) {
        if (strArr == null || strArr.length == 0) {
            return "";
        }
        TreeSet<String> ans = process(strArr);
        return ans.isEmpty() ? "" : ans.first();
    }

    // strs中所有字符串全排列，返回所有可能的结果
    public static TreeSet<String> process(String[] strArr) {
        TreeSet<String> ans = new TreeSet<>();
        if (strArr.length == 0) {
            ans.add("");
            return ans;
        }
        for (int i = 0; i < strArr.length; i++) {
            String first = strArr[i];
            String[] next = removeIndexString(strArr, i);
            TreeSet<String> process = process(next);
            for (String cur : process) {
                ans.add(first + cur);
            }
        }
        return ans;
    }

    // 移除字符串数组中指定位置的数
    // {"abc", "cks", "bct"}
    // removeIndexString(arr , 1) -> {"abc", "bct"}
    public static String[] removeIndexString(String[] arr, int index) {
        int arrLength = arr.length;
        String[] ans = new String[arrLength - 1];
        int ansIndex = 0;
        for (int i = 0; i < arrLength; i++) {
            if (i != index) {
                ans[ansIndex++] = arr[i];
            }
        }
        return ans;
    }

    public static class MyComparator implements Comparator<String> {
        @Override
        public int compare(String a, String b) {
            return (a + b).compareTo(b + a);
        }
    }

    // 贪心解法
    public static String lowestString2(String[] strArr) {
        if (null == strArr || strArr.length == 0) {
            return "";
        }
        Arrays.sort(strArr, new MyComparator());
        StringBuilder sb = new StringBuilder();
        for (String s : strArr) {
            sb.append(s);
        }
        return sb.toString();
    }

    // fot test 随机生成字符串
    public static String generateRandomString(int strLen) {
        // 生成长度为 1 - strLen 长度的字符串
        char[] ans = new char[(int) (Math.random() * strLen + 1)];
        // 填充数字
        for (int i = 0; i < ans.length; i++) {
            int value = (int) (Math.random() * 25);
            ans[i] = (char) (Math.random() >= 0.5 ? 65 + value : 97 + value);
        }
        return String.valueOf(ans);
    }

    // for test
    public static String[] generateRandomStringArray(int arrLen, int strLen) {
        String[] ans = new String[(int) (Math.random() * arrLen + 1)];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = generateRandomString(strLen);
        }
        return ans;
    }

    // for test
    public static String[] copyStringArray(String[] arr) {
        String[] ans = new String[arr.length];
        for (int i = 0; i < arr.length; i++) {
            ans[i] = arr[i];
        }
        return ans;
    }


    public static void main(String[] args) {
        int arrLen = 6;
        int strLen = 5;
        int testTimes = 10000;
        System.out.println("test begin");
        for (int i = 0; i < testTimes; i++) {
            String[] arr1 = generateRandomStringArray(arrLen, strLen);
            String[] arr2 = copyStringArray(arr1);
            if (!lowestString1(arr1).equals(lowestString2(arr2))) {
                for (String str : arr1) {
                    System.out.print(str + ",");
                }
                System.out.println();
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }

}
