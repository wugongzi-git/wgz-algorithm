package com.jia.algorithm.class12;


import java.util.ArrayList;

public class Code05_MaxSubBSTSize {

    // 提交时不要提交这个类
    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int value) {
            val = value;
        }
    }

    public static int largestBSTSubtree(TreeNode head) {
        if (null == head) {
            return 0;
        }
        return process(head).maxBSTSubtreeSize;
    }

    public static class Info {
        // 最大的二叉搜索子树的 size
        public int maxBSTSubtreeSize;
        // 当前二叉树有多少个节点
        public int allSize;
        // 二叉树中的最大值
        public int max;
        // 二叉树中的最小值
        public int min;

        public Info(int maxBSTSubtreeSize, int allSize, int max, int min) {
            this.maxBSTSubtreeSize = maxBSTSubtreeSize;
            this.allSize = allSize;
            this.max = max;
            this.min = min;
        }
    }

    public static Info process(TreeNode head) {
        if (null == head) {
            return null;
        }
        int allSize = 1;
        int max = head.val;
        int min = head.val;
        Info leftInfo = process(head.left);
        Info rightInfo = process(head.right);
        if (null != leftInfo) {
            allSize += leftInfo.allSize;
            max = Math.max(max, leftInfo.max);
            min = Math.min(min, leftInfo.min);
        }
        if (null != rightInfo) {
            allSize += rightInfo.allSize;
            max = Math.max(max, rightInfo.max);
            min = Math.min(min, rightInfo.min);
        }
        // 左子树的最大二叉搜索树的 size
        int p1 = -1;
        if (leftInfo != null) {
            p1 = leftInfo.maxBSTSubtreeSize;
        }
        // 右子树的最大二叉搜索子树的 size
        int p2 = -1;
        if (rightInfo != null) {
            p2 = rightInfo.maxBSTSubtreeSize;
        }
        // 以 X 为节点的二叉搜索子树
        int p3 = -1;
        boolean leftIsBST = leftInfo == null ? true : leftInfo.maxBSTSubtreeSize == leftInfo.allSize;
        boolean rightIsBST = rightInfo == null ? true : rightInfo.maxBSTSubtreeSize == rightInfo.allSize;
        // 只有当左右子树都是二叉搜索树的情况下，才有可能是以 X 为头节点的二叉搜索树
        if (leftIsBST && rightIsBST) {
            boolean leftMaxLessX = leftInfo == null ? true : (leftInfo.max < head.val);
            boolean rightMinMoreX = rightInfo == null ? true : (rightInfo.min > head.val);
            if (leftMaxLessX && rightMinMoreX) {
                int lSize = leftInfo == null ? 0 : leftInfo.allSize;
                int rSize = rightInfo == null ? 0 : rightInfo.allSize;
                p3 = lSize + rSize + 1;
            }
        }
        return new Info(Math.max(p1, Math.max(p2, p3)), allSize, max, min);
    }


    // 为了验证
    // 对数器方法
    public static int right(TreeNode head) {
        if (head == null) {
            return 0;
        }
        int h = getBSTSize(head);
        if (h != 0) {
            return h;
        }
        return Math.max(right(head.left), right(head.right));
    }

    // 为了验证
    // 对数器方法
    public static int getBSTSize(TreeNode head) {
        if (head == null) {
            return 0;
        }
        ArrayList<TreeNode> arr = new ArrayList<>();
        in(head, arr);
        for (int i = 1; i < arr.size(); i++) {
            if (arr.get(i).val <= arr.get(i - 1).val) {
                return 0;
            }
        }
        return arr.size();
    }

    // 为了验证
    // 对数器方法
    public static void in(TreeNode head, ArrayList<TreeNode> arr) {
        if (head == null) {
            return;
        }
        in(head.left, arr);
        arr.add(head);
        in(head.right, arr);
    }

    // 为了验证
    // 对数器方法
    public static TreeNode generateRandomBST(int maxLevel, int maxValue) {
        return generate(1, maxLevel, maxValue);
    }

    // 为了验证
    // 对数器方法
    public static TreeNode generate(int level, int maxLevel, int maxValue) {
        if (level > maxLevel || Math.random() < 0.5) {
            return null;
        }
        TreeNode head = new TreeNode((int) (Math.random() * maxValue));
        head.left = generate(level + 1, maxLevel, maxValue);
        head.right = generate(level + 1, maxLevel, maxValue);
        return head;
    }

    // 为了验证
    // 对数器方法
    public static void main(String[] args) {
        int maxLevel = 4;
        int maxValue = 100;
        int testTimes = 1000000;
        System.out.println("测试开始");
        for (int i = 0; i < testTimes; i++) {
            TreeNode head = generateRandomBST(maxLevel, maxValue);
            if (largestBSTSubtree(head) != right(head)) {
                System.out.println("出错了！");
            }
        }
        System.out.println("测试结束");
    }



}
