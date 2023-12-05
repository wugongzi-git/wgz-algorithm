package com.jia.algorithm.class04;

/**
 * @author wugongzi
 * @description 双链表实现栈
 * @date 2023-11-28 14:39
 */
public class Code05_DoubleListToStack {

    public static class DoubleNode {
        public int value;
        public DoubleNode pre;
        public DoubleNode next;

        public DoubleNode(int value) {
            this.value = value;
        }
    }

    public static DoubleNode head;
    public static DoubleNode tail;

    public static boolean isEmpty() {
        return head == null;
    }

    public static void push(int value) {
        DoubleNode node = new DoubleNode(value);
        if (head == null) {
            head = node;
        } else {
            // 头插法
            node.next = head;
            head.pre = node;
            head = node;
        }
    }

    public static Integer pop() {
        if (isEmpty()) {
            System.out.println("栈空了");
            return null;
        }
        int value = head.value;
        head = head.next;
        if (head != null) {
            head.pre = null;
        }
        System.out.println(value);
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
