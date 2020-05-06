package com.learn.sort;

import java.util.Arrays;

/**
 * @author ZixiangHu
 * @create 2020-05-05  16:45
 * @description
 */
public class MergingSort {
    public static void main(String[] args) {
        int[] arr = new int[]{50, 10, 90, 30, 70, 40, 80, 60, 20, 20};
        System.out.println("排序前：");
        System.out.println(Arrays.toString(arr));
        int[] temp = arr.clone();
        mergeSort(arr, 0, arr.length - 1, temp);
        System.out.println("排序后：");
        System.out.println(Arrays.toString(arr));
    }

    private static void mergeSort(int[] arr, int left, int right, int[] temp) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(arr, left, mid, temp);
            mergeSort(arr, mid + 1, right, temp);
            merge(arr, left, mid, right, temp);
        }
    }

    /**
     * 待排序的数组分为两个有序的子序列，将这两个有序的子序列合并成一个有序的序列
     * 例如5 10 20 6 9 12，子序列5 10 20和子序列6 9 12分别是有序的，将这两个有序的序列合并成 6 9 12 5 10 20
     *
     * @param arr
     * @param left
     * @param mid
     * @param right
     * @param temp
     */

    private static void merge(int[] arr, int left, int mid, int right, int[] temp) {
        int p1 = left;
        int p2 = mid + 1;
        int point = left;
        while (p1 <= mid && p2 <= right) {
            if (arr[p1] < arr[p2])
                temp[point++] = arr[p1++];
            else
                temp[point++] = arr[p2++];
        }
        while (p1 <= mid)
            temp[point++] = arr[p1++];
        while (p2 <= right)
            temp[point++] = arr[p2++];
        for (int i = left; i <= right; i++) {
            arr[i] = temp[i];
        }
    }
}
