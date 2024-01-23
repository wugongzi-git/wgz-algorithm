package com.jia.algorithm.class11;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 二叉树序列化和反序列化
 */
public class Code02_SerializeAndReconstructTree {

    /*
     * 二叉树可以通过先序、后序或者按层遍历的方式序列化和反序列化，
     * 以下代码全部实现了。
     * 但是，二叉树无法通过中序遍历的方式实现序列化和反序列化
     * 因为不同的两棵树，可能得到同样的中序序列，即便补了空位置也可能一样。
     * 比如如下两棵树
     *         __2
     *        /
     *       1
     *       和
     *       1__
     *          \
     *           2
     * 补足空位置的中序遍历结果都是{ null, 1, null, 2, null}
     *
     * */
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    // 先序序列化
    public static Queue<String> preSerial(Node head) {
        Queue<String> queue = new LinkedList<>();
        pres(head, queue);
        return queue;
    }

    // 序列化递归过程
    public static void pres(Node head, Queue<String> ans) {
        if (head == null) {
            ans.add(null);
        } else {
            // 先将头节点放入队列
            ans.add(String.valueOf(head.value));
            pres(head.left, ans);
            pres(head.right, ans);
        }
    }

    // 先序反序列化
    public static Node buildByPreQueue(Queue<String> preList) {
        if (null == preList || preList.isEmpty()) {
            return null;
        }
        return preb(preList);
    }

    // 先序反序列化
    public static Node preb(Queue<String> preList) {
        String poll = preList.poll();
        if (null == poll) {
            return null;
        }
        Node head = new Node(Integer.parseInt(poll));
        head.left = preb(preList);
        head.right = preb(preList);
        return head;
    }


    // 后序序列化
    public static Queue<String> posSerial(Node head) {
        Queue<String> queue = new LinkedList<>();
        pos(head, queue);
        return queue;
    }

    // 后序序列化
    public static void pos(Node head, Queue<String> queue) {
        if (head == null) {
            queue.add(null);
        } else {
            pos(head.left, queue);
            pos(head.right, queue);
            queue.add(String.valueOf(head.value));
        }
    }


    // 后序反序列化
    public static Node buildByPosQueue(Queue<String> poslist) {
        if (poslist == null || poslist.size() == 0) {
            return null;
        }
        // 左右中  ->  stack(中右左)
        Stack<String> stack = new Stack<>();
        while (!poslist.isEmpty()) {
            stack.push(poslist.poll());
        }
        return posb(stack);
    }

    public static Node posb(Stack<String> posstack) {
        String value = posstack.pop();
        if (value == null) {
            return null;
        }
        Node head = new Node(Integer.valueOf(value));
        head.right = posb(posstack);
        head.left = posb(posstack);
        return head;
    }

    // 按层序列化
    public static Queue<String> levelSerial(Node head) {
        Queue<String> res = new LinkedList<>();
        if (head == null) {
            res.add(null);

        } else {
            Queue<Node> queue = new LinkedList<>();
            queue.add(head);
            res.add(String.valueOf(head.value));
            while (!queue.isEmpty()) {
                Node poll = queue.poll();
                if (poll.left != null) {
                    queue.add(poll.left);
                    res.add(String.valueOf(poll.left.value));
                } else {
                    res.add(null);
                }
                if (poll.right != null) {
                    queue.add(poll.right);
                    res.add(String.valueOf(poll.right.value));
                } else {
                    res.add(null);
                }
            }
        }
        return res;
    }


    // 按层反序列化
    // 反序列化是从最低的节点开始，从最小的子树开始挂，然后把最大的一棵树挂上
    public static Node buildByLevelQueue(Queue<String> levelList) {
        if (null == levelList || levelList.isEmpty()) {
            return null;
        }
        Node head = generateNode(levelList.poll());
        Queue<Node> queue = new LinkedList<>();
        if (head != null) {
            queue.add(head);
        }
        Node node = null;
        while (!queue.isEmpty()) {
            node = queue.poll();
            node.left = generateNode(levelList.poll());
            node.right = generateNode(levelList.poll());
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }
        return head;
    }


    // 生成一个 Node 节点
    public static Node generateNode(String val) {
        if (val == null) {
            return null;
        }
        return new Node(Integer.parseInt(val));
    }

    public static Node generate(int level, int maxLevel, int maxValue) {
        if (level > maxLevel || Math.random() < 0.5) {
            return null;
        }
        Node head = generateNode(String.valueOf((int) (Math.random() * maxValue)));
        head.left = generate(level + 1, maxLevel, maxValue);
        head.right = generate(level + 1, maxLevel, maxValue);
        return head;
    }


    // for test
    public static Node generateRandomBST(int maxLevel, int maxValue) {
        return generate(1, maxLevel, maxValue);
    }

    // for test
    public static boolean isSameValueStructure(Node head1, Node head2) {
        if (head1 == null && head2 != null) {
            return false;
        }
        if (head1 != null && head2 == null) {
            return false;
        }
        if (head1 == null && head2 == null) {
            return true;
        }
        if (head1.value != head2.value) {
            return false;
        }
        return isSameValueStructure(head1.left, head2.left) && isSameValueStructure(head1.right, head2.right);
    }

    // for test
    public static void printTree(Node head) {
        System.out.println("Binary Tree:");
        printInOrder(head, 0, "H", 17);
        System.out.println();
    }

    public static void printInOrder(Node head, int height, String to, int len) {
        if (head == null) {
            return;
        }
        printInOrder(head.right, height + 1, "v", len);
        String val = to + head.value + to;
        int lenM = val.length();
        int lenL = (len - lenM) / 2;
        int lenR = len - lenM - lenL;
        val = getSpace(lenL) + val + getSpace(lenR);
        System.out.println(getSpace(height * len) + val);
        printInOrder(head.left, height + 1, "^", len);
    }

    public static String getSpace(int num) {
        String space = " ";
        StringBuffer buf = new StringBuffer("");
        for (int i = 0; i < num; i++) {
            buf.append(space);
        }
        return buf.toString();
    }

    public static void main(String[] args) {
        int maxLevel = 5;
        int maxValue = 100;
        int testTimes = 1000000;
        System.out.println("test begin");
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            Queue<String> pre = preSerial(head);
            Queue<String> pos = posSerial(head);
            Queue<String> level = levelSerial(head);
            Node preBuild = buildByPreQueue(pre);
            Node posBuild = buildByPosQueue(pos);
            Node levelBuild = buildByLevelQueue(level);
            if (!isSameValueStructure(preBuild, posBuild) || !isSameValueStructure(posBuild, levelBuild)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("test finish!");

    }


}