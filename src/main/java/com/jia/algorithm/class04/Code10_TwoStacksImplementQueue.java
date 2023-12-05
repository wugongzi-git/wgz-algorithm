package com.jia.algorithm.class04;

import java.util.Stack;

/**
 * @author wugongzi
 * @description 两个栈实现队列
 * @date 2023-12-05 11:01
 */
public class Code10_TwoStacksImplementQueue {

    public static class TwoStacksQueue {
        private Stack<Integer> stackPush; // 入栈队列
        private Stack<Integer> stackPop; // 出栈队列

        public TwoStacksQueue() {
            this.stackPush = new Stack<>();
            this.stackPop = new Stack<>();
        }

        // push栈向pop栈倒入数据
        private void pushToPop() {
            if (stackPop.isEmpty()) {
                while (!stackPush.isEmpty()) {
                    stackPop.push(stackPush.pop());
                }
            }
        }

        private void add(int value) {
            stackPush.push(value);
            pushToPop();
        }

        private Integer pull() {
            if (stackPop.isEmpty() && stackPush.isEmpty()) {
                throw new RuntimeException("栈已经空了");
            }
            pushToPop();
            return stackPop.pop();
        }

        private Integer peek() {
            if (stackPop.isEmpty() && stackPush.isEmpty()) {
                throw new RuntimeException("栈已经空了");
            }
            pushToPop();
            return stackPop.peek();
        }
    }

    public static void main(String[] args) {
        TwoStacksQueue queue = new TwoStacksQueue();
        queue.add(1);
        queue.add(2);
        queue.add(3);
        queue.add(4);
        queue.add(5);
        System.out.println(queue.peek());
        System.out.println(queue.pull());
        System.out.println(queue.pull());
        queue.add(7);
        queue.add(8);
        System.out.println(queue.pull());
        System.out.println(queue.pull());
        System.out.println(queue.pull());
        System.out.println(queue.pull());
    }

}
