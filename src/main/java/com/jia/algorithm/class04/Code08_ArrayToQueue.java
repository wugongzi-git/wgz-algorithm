package com.jia.algorithm.class04;

/**
 * @author wugongzi
 * @description 数组实现队列
 * @date 2023-11-28 16:17
 */
public class Code08_ArrayToQueue {

    public static class MyQueue {
        private int[] arr;
        private int pushIndex = 0; // 加入元素的位置
        private int popIndex = 0; // 弹出元素的位置

        private int size = 0; // 数组中的数据

        private final int limit;

        public MyQueue(int limit) {
            arr = new int[limit];
            this.limit = limit;
        }

        public void push(int value) {
            if (size >= limit) {
                throw new RuntimeException("队列已经满了");
            }
            size++;
            arr[pushIndex] = value;
            pushIndex = nextIndex(pushIndex);
        }

        public int pop() {
            if (size == 0) {
                throw new RuntimeException("队列已经空了");
            }
            size--;
            int value = arr[popIndex];
            popIndex = nextIndex(popIndex);
            System.out.println("出栈的值：" + value);
            return value;
        }

        private int nextIndex(int i) {
            return i < limit - 1 ? i + 1 : 0;
        }
    }

    public static void main(String[] args) {
        MyQueue queue = new MyQueue(5);
        queue.push(1);
        queue.push(2);
        queue.push(3);
        queue.pop();
        queue.pop();
        queue.push(4);
        queue.push(5);
        queue.push(6);
        queue.pop();
        queue.pop();
        queue.push(7);
        queue.push(8);
        queue.push(9);
        queue.push(10);
    }

}
