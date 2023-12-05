package com.jia.algorithm.class04;

/**
 * @author wugongzi
 * @description 单链表实现队列
 * @date 2023-11-28 14:03
 */
public class Code04_ListToQueue {

    public static class Node {
        public int value;
        public Node next;
        public Node(int value) {
            this.value = value;
        }
    }

    public static Node head;

    public static Node tail;

    public static void initQueue() {
        head = null;
        tail = null;
    }

    public static boolean isEmpty() {
        return head == null;
    }

    // 采用尾插法
    public static void inQueue(int value) {
        Node node = new Node(value);
        if (head == null) {
            head = node;
            tail = node;
        } else {
            tail.next = node;
            // 尾插入
            tail = node;
        }
    }

    public static Integer outQueue() {
        if (isEmpty()) {
            System.out.println("队列已经空了");
            return null;
        }
        int value = head.value;
        System.out.println("出队列的值：" + value);
        head = head.next;
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
