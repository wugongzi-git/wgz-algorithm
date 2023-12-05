package com.jia.algorithm.class04;

/**
 * @author wugongzi
 * @description 链表反转
 * @date 2023-11-18 15:17
 */
public class Code01_ReverseList {

    // 单链表结构
    public static class Node {
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }
    }

    // 双链表
    public static class DoubleNode {
        public int value;
        public DoubleNode pre;
        public DoubleNode next;

        public DoubleNode(int value) {
            this.value = value;
        }
    }


    //  反转单链表
    //   a    ->   b    ->  c  ->  null
    //   c    ->   b    ->  a  ->  null
    public static Node reverseLinkedList(Node head) {
        Node pre = null;
        Node next;
        while (head != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

    // 反转双链表
    public static DoubleNode reverseDoubleList(DoubleNode head) {
        DoubleNode pre = null;
        DoubleNode next = null;
        while (head != null) {
            next = head.next;
            head.next = pre;
            head.pre = next;
            pre = head;
            head = next;
        }
        return pre;

    }



    public static void main(String[] args) {
        Node a = new Node(1);
        Node b = new Node(2);
        Node c = new Node(3);
        a.next = b;
        b.next = c;
        Node node = reverseLinkedList(a);
        System.out.println(node.value);
        System.out.println(node.next.value);
        System.out.println(node.next.next.value);
    }

}
