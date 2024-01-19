package com.jia.algorithm.class09;

import java.util.HashMap;
import java.util.Map;

/**
 * 链表有 next 节点和 random 节点，要求你复制链表
 * // 测试链接 : https://leetcode.com/problems/copy-list-with-random-pointer/
 *
 */
public class Code04_CopyListWithRandom {

    public static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    // 使用额外空间
    public static Node copyRandomList1(Node head) {
        if (head == null) {
            return null;
        }
        Map<Node, Node> newMap = new HashMap<>();
        Node cur = head;
        // 创建新的节点，并加入 map 中
        while (cur != null) {
            newMap.put(cur, new Node(cur.val));
            cur = cur.next;
        }
        // 设置新节点的 next 和 random 指针
        cur = head;
        while (cur != null) {
            newMap.get(cur).next = newMap.get(cur.next);
            newMap.get(cur).random = newMap.get(cur.random);
            cur = cur.next;
        }
        return newMap.get(head);
    }


    public static Node copyRandomList2(Node head) {
        if (head == null) {
            return null;
        }
        Node cur = head;
        Node next;
        // 原来链表：1 -> 2 -> 3 -> null
        // 现在链表：1 -> 1' -> 2 -> 2' -> 3 -> 3' -> null
        while (cur != null) {
            next = cur.next; // 保存当前指针的 next 节点
            cur.next = new Node(cur.val); // 创建一个 new' 节点，让 old.next 指向 new 的节点
            cur.next.next = next; // 新节点指向老节点
            cur = next;
        }
        // 更新 new' 的 random 指针
        cur = head;
        Node copy;
        while (cur != null) {
            next = cur.next.next;
            copy = cur.next;
            copy.random = cur.random != null ? cur.random.next : null; // 当前的 random.next 节点指向了 new'，new' 指向了 old
            cur = next;
        }
        // 更新 new' 的 next 指针，把原链表 next 指针复原即可
        Node res = head.next;
        cur = head;
        while (cur != null) {
            next = cur.next.next;
            copy = cur.next;
            cur.next = next; // 更新老节点的 next 节点
            copy.next = next != null ? next.next : null; // 更新复制后节点的 next 节点
            cur = next;
        }
        return res;
    }

}
