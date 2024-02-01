package com.jia.algorithm.class12;

import java.util.LinkedList;

/**
 * 判断一棵树是否完全二叉树
 */
public class Code01_IsCBT {

    public static class Node {
        private int value;
        private Node left;
        private Node right;
        public Node(int value) {
            this.value = value;
        }
    }

    public static boolean isCBT1(Node head) {
        if (head == null) {
            return true;
        }
        LinkedList<Node> queue = new LinkedList<>();
        boolean leaf = false; // 是否遇到过两个孩子不双全的节点
        Node left = null; // 左孩子
        Node right = null; // 右孩子
        queue.add(head);
        while (!queue.isEmpty()) {
            Node poll = queue.poll();
            left = poll.left;
            right = poll.right;
            // 1. 右孩子有值，左孩子为 null，非完全二叉树
            // 2. 当遇到第一个左右孩子节点不全的节点后，后面所有遍历的节点都必须是叶子节点
            if ((leaf && (left != null || right != null)) || (right != null && left == null)) {
                return false;
            }
            if (left != null) {
                queue.add(left);
            }
            if (right != null) {
                queue.add(right);
            }
            if (left == null || right == null) {
                leaf = true;
            }
        }
        return true;
    }

    public static boolean isCBT2(Node head) {
        return true;
    }


















}
