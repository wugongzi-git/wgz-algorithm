package com.jia.algorithm.class03;

/**
 * @author wugongzi
 * @description 双链表实现队列
 * @date 2023-11-28 15:19
 */
public class Code06_DoubleListToQueue {

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

    public static void inQueue(int value) {
        DoubleNode node = new DoubleNode(value);
        if (isEmpty()) {
            head = node;
            tail = node;
        } else {
            tail.next = node;
            node.pre = tail;
            tail = node;
        }
    }

    public static Integer outQueue() {
        if (isEmpty()) {
            System.out.println("队列已经空了");
            return null;
        }
        int value = head.value;
        System.out.println("出队的值：" + value);
        head = head.next;
        if (head != null) {
            head.pre = null;
        } else {
            tail = null;
        }
        return value;
    }

    public static void main(String[] args) {
        inQueue(1);
        inQueue(2);
        inQueue(3);
        inQueue(4);
        inQueue(5);
        outQueue();
        outQueue();
        outQueue();
        outQueue();
        outQueue();
        outQueue();
    }


}
