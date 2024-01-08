package com.jia.algorithm.class07;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

/**
 * 手写堆
 */
public class HeapGreater<T> {

    private ArrayList<T> heap; // 存放堆数据
    private HashMap<T,Integer> indexMap; // 存储堆中元素的位置
    private int heapSize; // 堆的大小，数组长度就是堆的大小
    private Comparator<? super T> comp; // T 类型比较器

    public HeapGreater(Comparator<? super T> c) {
        heap = new ArrayList<>();
        indexMap = new HashMap<>();
        heapSize = 0;
        comp = c;
    }

    public boolean isEmpty() {
        return heapSize == 0;
    }

    public int size() {
        return heapSize;
    }

    public boolean contains(T obj) {
        return indexMap.containsKey(obj);
    }

    public T peek() {
        return heap.get(0);
    }

    // 添加元素
    public void push(T obj) {
        heap.add(obj);
        // 修改 map 对应位置
        indexMap.put(obj, heapSize);
        // 调整大根堆
        heapInsert(heapSize++);
    }

    public T pop() {
        T t = heap.get(0);
        swap(0, heapSize - 1);
        indexMap.remove(t);
        heapify(0);
        heapSize--;
        return t;
    }

    public void remove(T obj) {
        T replace = heap.get(heapSize - 1);
        Integer index = indexMap.get(obj);
        indexMap.remove(obj);
        heap.remove(--heapSize);
        while (replace != obj) {
            heap.set(index, replace);
            indexMap.put(replace, index);
            resign(replace);
        }
    }

    // 调整元素位置
    public void resign(T obj) {
        heapInsert(indexMap.get(obj));
        heapify(indexMap.get(obj));
    }

    public List<T> getAllElements() {
        List<T> ans = new ArrayList<>();
        for (T c : heap) {
            ans.add(c);
        }
        return ans;
    }

    // 从最后一位加，往上调整的过程
    private void heapInsert(int index) {
        while (comp.compare(heap.get(index), heap.get((index - 1) / 2)) < 0) {
            swap(index, (index - 1) / 2);
            index = (index - 1) / 2;
        }

    }

    // 删除元素的时候，往下调整的过程
    private void heapify(int index) {
        int left = index * 2 + 1; // 找出节点左孩子
        // 当左孩子小于堆的长度
        while (left < heapSize) {
            // 找出左右孩子中较大的那个，这里为什么必须要 <0，> 0 为什么不可以
            int best = left + 1 < heapSize && comp.compare(heap.get(left), heap.get(left + 1)) < 0 ? left + 1 : left;
            // 比较 index 和 best 位置上面的数，哪个大
            best = comp.compare(heap.get(best), heap.get(index)) < 0 ? best : index;
            if (best == index) {
                break;
            }
            // 交换位置
            swap(index, best);
            index = best;
            left = index * 2 + 1;
        }
    }


    private void swap(int i, int j) {
        T obj1 = heap.get(i);
        T obj2 = heap.get(j);
        heap.set(i, obj2);
        heap.set(j, obj1);
        indexMap.put(obj1, j);
        indexMap.put(obj2, i);
    }


}
