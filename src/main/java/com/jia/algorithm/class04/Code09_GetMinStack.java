package com.jia.algorithm.class04;

import java.util.Stack;

/**
 * @author wugongzi
 * @description 最小栈功能
 * @date 2023-11-28 17:24
 */
public class Code09_GetMinStack {

    private static Stack<Integer> dataStack = new Stack<>(); // 数据栈，存储我们插入的数据
    private static Stack<Integer> minStack = new Stack<>(); // 最小栈，存储对应位置最小的值

    private static void push(int value) {
        // 插入数据
        dataStack.push(value);
        boolean empty = minStack.empty();
        if (empty) {
            minStack.push(value);
        } else {
            int min = value > minStack.peek() ? minStack.peek() : value;
            minStack.push(min);
        }
    }

    private static Integer pop() {
        int value = dataStack.pop();
        minStack.pop();
        System.out.println("出栈的值：" + value);
        return value;
    }

    private static Integer getMin() {
        System.out.println("最小栈：" + minStack.peek());
        return minStack.peek();
    }


    public static void main(String[] args) {
        push(10);
        push(5);
        push(2);
        push(20);
        push(23);
        push(1);
        push(34);
        getMin();
        pop();
        pop();
        getMin();
    }

}
