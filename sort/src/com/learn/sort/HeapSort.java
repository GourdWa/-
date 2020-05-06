package com.learn.sort;

import java.util.Arrays;
import java.util.Random;

/**
 * @author ZixiangHu
 * @create 2020-05-04  11:01
 * @description
 */
public class HeapSort {
    public static void main(String[] args) {
        int[] arr = new int[13];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = new Random().nextInt(50);
        }
//        int[] arr = new int[]{50, 10, 90, 30, 70, 40, 80, 60, 20};
        System.out.println("排序前：");
        System.out.println(Arrays.toString(arr));
        heapSort(arr);
        System.out.println("排序后：");
        System.out.println(Arrays.toString(arr));
    }

    private static void heapSort(int[] arr) {
        int len = arr.length;
        int temp;
        for (int i = len / 2 - 1; i >= 0; i--) {
            heapAdjust(arr, i, len);
        }
        //大顶堆
        for (int i = len - 1; i >= 0; i--) {
            temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            heapAdjust(arr, 0, i);
        }
    }

    private static void heapAdjust(int[] arr, int n, int len) {
        int temp = arr[n];
        for (int i = 2 * n + 1; i < len; i = 2 * i + 1) {
            //寻找子节点中最大的子节点
            if (i + 1 < len && arr[i + 1] > arr[i])
                i += 1;
            //如果temp大于最大的子节点，则跳出循环
            if (temp > arr[i])
                break;
            arr[n] = arr[i];
            n = i;
        }
        arr[n] = temp;
    }
}
