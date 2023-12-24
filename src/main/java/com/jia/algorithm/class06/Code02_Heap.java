package com.jia.algorithm.class06;

/**
 * 数组实现堆，有一个特点，就是对于数组任意一个位置 i：
 *
 * - 该数的左节点位置 = 2*i + 1
 * - 右节点 = 2*i + 2
 * - 父节点 = (i-1) / 2
 */
public class Code02_Heap {

    public static class MyMaxHeap {
        // 用数组实现堆
        private int[] heap;

        // 堆的大小
        private int limit;

        // 当前堆长度
        private int heapSize;

        public MyMaxHeap(int limit) {
            heap = new int[limit];
            this.limit = limit;
            heapSize = 0;
        }

        public boolean isEmpty() {
            return heapSize == 0;
        }

        public boolean isFull() {
            return heapSize == limit;
        }

        // 往堆中添加元素
        private void push(int value) {
            if (heapSize == limit) {
                System.out.println("堆已经满了");
                return;
            }
            // 放到数组最后一个位置，size++
            heap[heapSize++] = value;
            // 判断添加的数是否需要移动位置
            int index = heapSize - 1;
            // 如果当前位置大于父节点数字, 则和父节点交换位置
            while (heap[index] > heap[(index - 1) / 2]) {
                swap(heap, index, (index - 1) / 2);
                index = (index - 1) / 2;
            }
        }

        // 从堆中弹出元素，弹出最大值
        private Integer pop() {
            if (heapSize == 0) {
                return null;
            }
            // 保存最大位置的数
            int result = heap[0];
            // 将 0 位置数和最后一个位置交换，长度 -1
            swap(heap, 0, --heapSize);
            int index = 0;
            int left = index * 2 + 1;
            // 如果左子树存在，才需要交换
            while (left < heapSize) {
                // 左子树存在，右子树也可能存在，需要找出左右子树中的最大值对应的下标
                int largeIndex = left + 1 < heapSize && heap[left + 1] > heap[left] ? left + 1 : left;
                if (heap[index] > heap[largeIndex]) {
                    break;
                }
                swap(heap, index, largeIndex);
                index = largeIndex;
                left = index * 2 + 1;
            }
            return result;
        }

        private void swap(int[] arr, int i, int j) {
            int tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
        }

    }

    // for test
    public static class RightMaxHeap {
        private int[] arr;
        private final int limit;
        private int size;

        public RightMaxHeap(int limit) {
            arr = new int[limit];
            this.limit = limit;
            size = 0;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public boolean isFull() {
            return size == limit;
        }

        public void push(int value) {
            if (size == limit) {
                throw new RuntimeException("heap is full");
            }
            arr[size++] = value;
        }

        public int pop() {
            int maxIndex = 0;
            for (int i = 1; i < size; i++) {
                if (arr[i] > arr[maxIndex]) {
                    maxIndex = i;
                }
            }
            int ans = arr[maxIndex];
            arr[maxIndex] = arr[--size];
            return ans;
        }

    }

    public static void main(String[] args) {
        int value = 1000;
        int limit = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            int curLimit = (int) (Math.random() * limit) + 1;
            MyMaxHeap my = new MyMaxHeap(curLimit);
            RightMaxHeap test = new RightMaxHeap(curLimit);
            int curOpTimes = (int) (Math.random() * limit);
            for (int j = 0; j < curOpTimes; j++) {
                if (my.isEmpty() != test.isEmpty()) {
                    System.out.println("Oops!");
                }
                if (my.isFull() != test.isFull()) {
                    System.out.println("Oops!");
                }
                if (my.isEmpty()) {
                    int curValue = (int) (Math.random() * value);
                    my.push(curValue);
                    test.push(curValue);
                } else if (my.isFull()) {
                    if (my.pop() != test.pop()) {
                        System.out.println("Oops!");
                    }
                } else {
                    if (Math.random() < 0.5) {
                        int curValue = (int) (Math.random() * value);
                        my.push(curValue);
                        test.push(curValue);
                    } else {
                        if (my.pop() != test.pop()) {
                            System.out.println("Oops!");
                        }
                    }
                }
            }
        }
        System.out.println("finish!");
    }

}
