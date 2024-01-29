package com.jia.algorithm.class11;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 求二叉树最宽的层有多少个节点
 */
public class Code05_TreeMaxWidth {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    // 使用额外空间
    public static int maxWidthUseMap(Node head) {
        if (head == null) {
            return 0;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        HashMap<Node, Integer> levelMap = new HashMap<>(); // 当前 node 属于哪一层
        levelMap.put(head, 1); // head 肯定是第一层
        int curLevel = 1; // 当前你正在统计哪一层的宽度
        int curLevelNodes = 0; // 当前正在统计层的节点数是多少
        int max = 0; // 最大层对应的节点数
        while (!queue.isEmpty()) {
            Node poll = queue.poll();
            Integer level = levelMap.get(poll);
            if (poll.left != null) {
                queue.add(poll.left);
                levelMap.put(poll.left, level + 1);
            }
            if (poll.right != null) {
                queue.add(poll.right);
                levelMap.put(poll.right, level + 1);
            }
            if (curLevel == level) {
                curLevelNodes++;
            } else {
                max = Math.max(max, curLevelNodes);
                curLevel += 1;
                curLevelNodes = 1;
            }
        }
        max = Math.max(max, curLevelNodes);
        return max;
    }



    // 使用有限几个变量
    public static int maxWidthNoMap(Node head) {
        if (head == null) {
            return 0;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        Node curEnd = head; // 当前层最右节点是谁
        Node nextEnd = null; // 当前层下一个节点是谁
        int max = 0; // 最大节点数
        int curLevelNodes = 0; // 当前正在遍历的层，有多个节点
        while (!queue.isEmpty()) {
            Node poll = queue.poll();
            if (poll.left != null) {
                queue.add(poll.left);
                nextEnd = poll.left;
            }
            if (poll.right != null) {
                queue.add(poll.right);
                nextEnd = poll.right;
            }
            curLevelNodes++;
            if (poll == curEnd) {
                max = Math.max(max, curLevelNodes);
                curLevelNodes = 0;
                curEnd = nextEnd;
            }
        }
        max = Math.max(max, curLevelNodes);
        return max;
    }

    // for test
    public static Node generateRandomBST(int maxLevel, int maxValue) {
        return generate(1, maxLevel, maxValue);
    }

    // for test
    public static Node generate(int level, int maxLevel, int maxValue) {
        if (level > maxLevel || Math.random() < 0.5) {
            return null;
        }
        Node head = new Node((int) (Math.random() * maxValue));
        head.left = generate(level + 1, maxLevel, maxValue);
        head.right = generate(level + 1, maxLevel, maxValue);
        return head;
    }

    public static void main(String[] args) {
        int maxLevel = 10;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            if (maxWidthUseMap(head) != maxWidthNoMap(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");

    }


}
