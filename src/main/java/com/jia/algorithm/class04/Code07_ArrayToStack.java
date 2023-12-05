package com.jia.algorithm.class04;


/**
 * @author wugongzi
 * @description 数组实现栈
 * @date 2023-11-28 15:57
 */
public class Code07_ArrayToStack {

    public static int[] arr = new int[5];
    public static int index = 0;

    public static void push(int value) {
        if (index >= arr.length) {
            System.out.println("栈已经满了");
            return;
        }
        arr[index] = value;
        index++;
    }


    public static Integer pop() {
        if (index == 0) {
            System.out.println("栈已经空了");
            return null;
        }
        index--;
        System.out.println(arr[index]);
        return arr[index];
    }

    public static void main(String[] args) {
        push(1);
        pop();
        push(2);
        push(3);
        push(4);
        push(5);
        push(6);
        push(7);
        pop();
        pop();
        pop();
        push(10);
        pop();
    }

}
