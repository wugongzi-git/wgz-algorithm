package com.jia.algorithm.class04;

/**
 * @author wugongzi
 * @description 链表删除给定的数
 * @date 2023-11-18 16:04
 */
public class Code02_DeleteGivenValue {

    public static class Node {
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }
    }

    public static Node removeValue(Node head, int num) {
        // head 来到第一个不需要删除的位置
        while (head != null) {
            if (head.value != num) {
                break;
            }
            head = head.next;
        }
        Node pre = head; // 用来移动节点指针
        Node cur = head; // 当前节点指针，用来判断是否等于 num
        while (cur != null) {
            if (cur.value == num) {
                pre.next = cur.next; // 跳过该节点，指向当前节点的下一个节点，pre 指针不需要移动位置
            } else {
                pre = cur; // 如果不相等，那么 pre 来到 cur 的位置
            }
            cur = cur.next;
        }
        return head;
    }

}
