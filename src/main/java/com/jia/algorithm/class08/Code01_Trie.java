package com.jia.algorithm.class08;

/**
 *   测试链接 : https:leetcode.cn/problems/implement-trie-ii-prefix-tree/
 * 	 提交Trie类可以直接通过
 * 	 原来代码是对的，但是既然找到了直接测试的链接，那就直接测吧
 * 	 这个链接上要求实现的功能和课上讲的完全一样
 * 	 该前缀树的路用数组实现
 */
public class Code01_Trie {

    class Trie {

        class Node {
            public int pass;
            public int end;

            // 是一个数组，因为一棵树下面有多个节点
            public Node[] nexts;

            public Node() {
                this.pass = 0;
                this.end = 0;
                this.nexts = new Node[26]; // 我们假设输入只有 26 个小写字母，因此最多只有 26
            }
        }

        private Node root;

        public Trie() {
            root = new Node();
        }

        public void insert(String word) {
            if (word == null) {
                return;
            }
            char[] charArray = word.toCharArray();
            Node node = root;
            int path = 0;
            // 遍历每个字符，判断是否有值
            for (char c : charArray) {
                path = c - 'a';
                if (node.nexts[path] == null) {
                    node.nexts[path] = new Node();
                }
                node = node.nexts[path];
                node.pass++;
            }
            node.end++;
        }

        // 从前缀树中删除字符
        public void erase(String word) {
            if (countWordsEqualTo(word) != 0) {
                Node node = root;
                int path = 0;
                node.pass--;
                char[] charArray = word.toCharArray();
                for (char c : charArray) {
                    path = c - 'a';
                    if (--node.nexts[path].pass == 0) {
                        node.nexts[path] = null;
                        return;
                    }
                    node = node.nexts[path];
                }
                node.end--;
            }
        }

        // 找出前缀树中有几个给定字符
        public int countWordsEqualTo(String word) {
            if (word == null) {
                return 0;
            }
            Node node = root;
            char[] charArray = word.toCharArray();
            int path = 0;
            for (char c : charArray) {
                path = c - 'a';
                if (node.nexts[path] == null) {
                    return 0;
                }
                node = node.nexts[path];
            }
            return node.end;
        }

        // 有多少个字符以 pre 为前缀
        public int countWordsStartingWith(String pre) {
            if (null == pre) {
                return 0;
            }
            Node node = root;
            int path = 0;
            char[] charArray = pre.toCharArray();
            for (char c : charArray) {
                path = c - 'a';
                if (node.nexts[path] == null) {
                    return 0;
                }
                node = node.nexts[path];
            }
            return node.pass;
        }
    }

}
