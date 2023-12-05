package com.jia.algorithm.class03;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author wugongzi
 * @description 两个队列实现栈
 * @date 2023-12-05 11:29
 */
public class Code11_TwoQueueImplementStack {

    public static class TwoQueueStack{
        private Queue<Integer> queue;
        private Queue<Integer> help;

        public TwoQueueStack() {
            this.queue = new LinkedList<>();
            this.help = new LinkedList<>();
        }

        public void push(int value) {
            queue.offer(value);
        }

        public Integer poll() {
            while (queue.size() > 1) {
                help.offer(queue.poll());
            }
            Integer ans = queue.poll();
            Queue<Integer> tmp = queue;
            queue = help;
            help = tmp;
            return ans;
        }

    }
    public static void main(String[] args) {
        TwoQueueStack stack = new TwoQueueStack();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        System.out.println(stack.poll());
        System.out.println(stack.poll());
        System.out.println(stack.poll());
        stack.push(6);
        stack.push(7);
        stack.push(8);
        System.out.println(stack.poll());
        System.out.println(stack.poll());

    }

}
