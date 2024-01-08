package com.jia.algorithm.class07;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 最大线段重合问题
 * 线段有开始位置和结束位置，共同组成一个二维数组
 */
public class Code01_CoverMax {

    public static int maxCover1(int[][] lines) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < lines.length; i++) {
            // 求出线段中起始位置最小值，结束位置最大值
            min = Math.min(min, lines[i][0]);
            max = Math.max(max, lines[i][1]);
        }
        int cover = 0;
        // 判断经过 0.5 位置有多少条线段
        for (double p = min + 0.5; p < max; p++) {
            int cur = 0;
            for (int j = 0; j < lines.length; j++) {
                if (lines[j][0] < p && lines[j][1] > p) {
                    cur++;
                }
            }
            cover = Math.max(cover, cur);
        }
        return cover;
    }

    /**
     * 利用小根堆来解决
     * @param m 线段数组
     * @return int
     */
    public static int maxCover2(int[][] m) {
        // 构造线段数组
        Line[] lines = new Line[m.length];
        for (int i = 0; i < m.length; i++) {
            lines[i] = new Line(m[i][0], m[i][1]);
        }
        // 排序
        Arrays.sort(lines, new StartComparator());
        // 使用小跟堆计算
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        int max = 0;
        for (Line line : lines) {
            while (!heap.isEmpty() && heap.peek() <= line.start) {
                heap.poll();
            }
            heap.add(line.end);
            max = Math.max(max, heap.size());
        }
        return max;
    }

    // 和maxCover2过程是一样的
    // 只是代码更短
    // 不使用类定义的写法
    public static int maxCover3(int[][] m) {
        // m是二维数组，可以认为m内部是一个一个的一维数组
        // 每一个一维数组就是一个对象，也就是线段
        // 如下的code，就是根据每一个线段的开始位置排序
        // 比如, m = { {5,7}, {1,4}, {2,6} } 跑完如下的code之后变成：{ {1,4}, {2,6}, {5,7} }
        Arrays.sort(m, (a, b) -> (a[0] - b[0]));
        // 使用小跟堆计算
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        int max = 0;
        for (int i = 0; i < m.length; i++) {
            while (!heap.isEmpty() && heap.peek() <= m[i][0]) {
                heap.poll();
            }
            heap.add(m[i][1]);
            max = Math.max(max, heap.size());
        }
        return max;
    }

    public static class StartComparator implements Comparator<Line> {
        @Override
        public int compare(Line o1, Line o2) {
            return o1.start - o2.start;
        }

    }

    public static class Line {
        public int start;
        public int end;

        public Line(int s, int e) {
            start = s;
            end = e;
        }
    }

    // for test
    public static int[][] generateLines(int N, int L, int R) {
        int size = (int) (Math.random() * N +1);
        int[][] ans =new int[size][2];
        for (int i = 0; i < size; i++) {
            int a = (int) (L + Math.random() * (R - L) + 1);
            int b = (int) (L + Math.random() * (R - L) + 1);
            if (a == b) {
                b = a + 1;
            }
            ans[i][0] = Math.min(a, b);
            ans[i][1] = Math.max(a, b);
        }
        return ans;
    }

    public static void printArr(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i][0] + "," + arr[i][1] + " ");
        }
    }

    public static void main(String[] args) {
        System.out.println("test begin");
        int N = 5;
        int L = 0;
        int R = 200;
        int testTimes = 20000;
        for (int i = 0; i < testTimes; i++) {
            int[][] lines = generateLines(N, L, R);
            int ans1 = maxCover1(lines);
            int ans2 = maxCover2(lines);
            int ans3 = maxCover3(lines);
            if (ans1 != ans2 || ans2 != ans3) {
                System.out.println("Oops!" + "ans1：" + ans1 + " ans2：" + ans2);
                printArr(lines);
                return;
            }
        }
        System.out.println("test end");
    }

}
