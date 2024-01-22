package com.jia.algorithm.class09;

/**
 * 给定两个可能有环也可能无环的单链表，头节点head1和head2。请实现一个函数，如果两个链表相交，请返回相交的第一个节点。如果不相交，返回null。
 * 【要求】
 * 如果两个链表长度之和为 N，时间复杂度请达到O(N)，额外空间复杂度请达到0(1)。
 */
public class Code05_FindFirstIntersectNode {

    public static class Node {
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }
    }

    private static Node getIntersectNode(Node head1, Node head2) {
        if (head1 == null || head2 == null) {
            return null;
        }
        Node loop1 = getLoopNode(head1);
        Node loop2 = getLoopNode(head2);
        if (loop1 == null && loop2 == null) {
            return noLoop(head1, head2);
        } else if (loop1 != null && loop2 != null) {
            return bothLoop(head1, loop1, head2, loop2);
        }
        return null;
    }

    // 找到链表第一个入环节点，如果无环，返回null
    public static Node getLoopNode(Node head) {
        // 如果节点数量小于 3，肯定无环，一个环至少三个节点
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }
        // 利用快慢指针判断是否有环
        Node slow = head.next;
        Node fast = head.next.next;
        // 如果快慢指针没有相遇，就一直往前走，直到两者相遇或者 fast 为 null 了
        while (slow != fast) {
            // fast 为 null，说明快指针走到头了，两个还没有相遇，则返回 null
            if (fast == null || fast.next == null) {
                return null;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        // 到这一步，说明 fast 和 slow 相遇了，一定有环
        // 在有环的情况下，找到第一次相遇的节点
        fast = head; // fast 来到 head 的位置
        while (fast != slow) {
            slow = slow.next;
            fast = fast.next;
        }
        // 这次相遇后，就是第一个入环节点
        return slow;
    }


    /**
     * 如果两个链表都无环，返回第一个相交节点，如果不相交，返回 null
     * 两个无环链表相交，相交之后的长度一定相同
     * 那么只需要求出相交之前的链表长度，然后让两个链表变成一样长，再一起往前走，这样当他们相遇的时候，就是第一个相交点
     * @param head1 链表1 头节点
     * @param head2 链表2 头节点
     * @return Node
     */
    public static Node noLoop(Node head1, Node head2) {
        if (head1 == null || head2 == null) {
            return null;
        }
        Node cur1 = head1;
        Node cur2 = head2;
        // n  :  链表 1 长度减去链表 2 长度的值
        int n = 0;
        while (cur1 != null) {
            n++;
            cur1 = cur1.next;
        }
        while (cur2 != null) {
            n--;
            cur2 = cur2.next;
        }
        // 如果遍历完链表后，cur1 != cur2 的话，说明两个链表没有重合，因为如果重合的话最后一定是同一个节点
        if (cur1 != cur2) {
            return null;
        }
        cur1 = n > 0 ? head1 : head2; // 让 cur1 指向长的节点
        cur2 = n > 0 ? head2 : head1; // 让 cur2 指向短的节点
        n = Math.abs(n); // 求出 n 的绝对值
        while (n != 0) {
            cur1 = cur1.next;
            n--;
        }
        while (cur1 != cur2) {
            cur1 = cur1.next;
            cur2 = cur2.next;
        }
        return cur1;
    }

    /**
     * 两个有环链表，返回第一个相交节点，如果不相交返回 null
     * @param head1 链表 1 头节点
     * @param loop1 链表 1 入环的第一个节点
     * @param head2 链表 2 头节点
     * @param loop2 链表 2 入环的第一个节点
     * @return Node
     */
    public static Node bothLoop(Node head1, Node loop1, Node head2, Node loop2) {
        Node cur1;
        Node cur2;
        // 如果两个入环节点相同
        if (loop1 == loop2) {
            cur1 = head1;
            cur2 = head2;
            int n = 0;
            while (cur1 != loop1) {
                n++;
                cur1 = cur1.next;
            }
            while (cur2 != loop2) {
                n--;
                cur2 = cur2.next;
            }
            cur1 = n > 0 ? head1 : head2;
            cur2 = n > 0 ? head2 : head1;
            n = Math.abs(n);
            while (n > 0) {
                n--;
                cur1 = cur1.next;
            }
            while (cur1 != cur2) {
                cur1 = cur1.next;
                cur2 = cur2.next;
            }
            return cur1;
        } else {
            // 如果两个入环节点不同
            cur1 = loop1.next;
            while (cur1 != loop1) {
                // 当 cur1 和 loop2 重合了，说明两个有交集，返回
                if (cur1 == loop2) {
                    return loop1;
                }
                cur1 = cur1.next;
            }
            return null;
        }
    }

    public static void main(String[] args) {
        // 1->2->3->4->5->6->7->null
        Node head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(6);
        head1.next.next.next.next.next.next = new Node(7);

        // 0->9->8->6->7->null
        Node head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next.next.next.next.next; // 8->6
        System.out.println(getIntersectNode(head1, head2).value);

        // 1->2->3->4->5->6->7->4...
        head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(6);
        head1.next.next.next.next.next.next = new Node(7);
        head1.next.next.next.next.next.next = head1.next.next.next; // 7->4

        // 0->9->8->2...
        head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next; // 8->2
        System.out.println(getIntersectNode(head1, head2).value);

        // 0->9->8->6->4->5->6..
        head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next.next.next.next.next; // 8->6
        System.out.println(getIntersectNode(head1, head2).value);

    }




}
