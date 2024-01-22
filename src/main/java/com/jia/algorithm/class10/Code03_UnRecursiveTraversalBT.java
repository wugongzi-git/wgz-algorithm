package com.jia.algorithm.class10;

import java.util.Stack;

/**
 * 非递归遍历二叉树
 */
public class Code03_UnRecursiveTraversalBT {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int v) {
            value = v;
        }
    }

    // 先序：头 - 左 - 右
    // 压栈的时候，先压入头节点，然后压入右孩子，最后左孩子
    // 出栈的时候也是一个个出
    public static void pre(Node head) {
        System.out.println("pre-order：");
        if (head == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();
        stack.push(head);
        while (!stack.isEmpty()) {
            Node pop = stack.pop();
            System.out.print(pop.value + " ");
            if (pop.right != null) {
                stack.push(pop.right);
            }
            if (pop.left != null) {
                stack.push(pop.left);
            }
        }
    }

    // 中序遍历
    public static void in (Node cur) {
        System.out.println("in-order：");
        if (cur == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();
        stack.push(cur);
        while (!stack.isEmpty() && cur != null) {
            if (cur != null) {
                stack.push(cur.left);
                cur = cur.left;
            } else {
                cur = stack.pop();
                System.out.print(cur.value + " ");
                cur = cur.right;
            }
        }
    }

    // 后序遍历，用两个栈
    public static void pos1(Node head) {
        System.out.println("pos-order：");
        if (head == null) {
            return;
        }
        Stack<Node> in = new Stack<>();
        Stack<Node> out = new Stack<>();
        in.push(head);
        while (!in.isEmpty()) {
            Node pop = in.pop();
            out.push(pop);
            if (pop.left != null) {
                in.push(pop.left);
            }
            if (pop.right != null) {
                in.push(pop.right);
            }
        }

        while (!out.isEmpty()) {
            System.out.print(out.pop().value + " ");
        }
    }

    // 后序遍历，用一个栈
    public static void pos2(Node head) {

    }



    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.right.left = new Node(6);
        head.right.right = new Node(7);

        pre(head);
        System.out.println("========");
        in(head);
        System.out.println("========");
        pos1(head);
        System.out.println("========");
        pos2(head);
        System.out.println("========");
    }





}
