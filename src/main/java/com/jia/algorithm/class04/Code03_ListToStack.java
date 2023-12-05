package com.jia.algorithm.class04;

/**
 * @author wugongzi
 * @description 单链表实现栈
 * @date 2023-11-18 16:41
 */
public class Code03_ListToStack {

    public static class Node {
        public int value;
        public Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    // 头节点
    public static Node top;

    // 判断是否为空
    public static boolean isEmpty() {
        return top == null;
    }

    // 入栈
    public static void push(int value)
    {
        Node node = new Node(value);
        node.next = top;
        top = node;
    }

    // 出栈
    public static Integer pop() {
        if (isEmpty()) {
            System.out.println("栈已经空了");
            return null;
        }
        int value = top.value;
        System.out.println("出栈的值：" + value);
        top = top.next;
        return value;
    }

    public static void main(String[] args) {
        push(1);
        push(2);
        push(3);
        push(4);
        push(5);
        push(6);
        pop();
        pop();
        pop();
        pop();
        pop();
        pop();
        pop();
    }


}
