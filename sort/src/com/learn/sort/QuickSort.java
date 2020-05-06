package com.learn.sort;

import java.util.Arrays;
import java.util.Random;

/**
 * @author ZixiangHu
 * @create 2020-05-05  21:34
 * @description
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] arr = new int[12];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = new Random().nextInt(50);
        }
        arr = new int[]{50, 10, 90, 30, 70, 40, 80, 60, 20};
        arr = new int[]{9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
        System.out.println("排序前：");
        System.out.println(Arrays.toString(arr));
        int[] temp = arr.clone();
        quickSort(arr, 0, arr.length - 1);
        System.out.println("排序后：");
        System.out.println(Arrays.toString(arr));
    }

    private static void quickSort(int[] arr, int left, int right) {
        if (left > right)
            return;
        int start = left;
        int end = right;
        int t;
        while (left < right) {
            while (left < right && arr[right] >= arr[start])
                right--;
            while (left < right && arr[left] <= arr[start])
                left++;

            if (left < right) {
                t = arr[left];
                arr[left] = arr[right];
                arr[right] = t;
            }
        }
        //说明left等于right
        t = arr[left];
        arr[left] = arr[start];
        arr[start] = t;
        quickSort(arr, start, left - 1);
        quickSort(arr, left + 1, end);

    }
}
