package com.learn.sort;

import java.util.Arrays;
import java.util.Random;

/**
 * @author ZixiangHu
 * @create 2020-05-05  20:30
 * @description
 */
public class MergingSort2 {
    public static void main(String[] args) {
/*        int[] arr = new int[12];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = new Random().nextInt(50);
        }*/
        int[] arr = new int[]{50, 10, 90, 30, 70, 40, 80, 60, 20, 20,10};
        System.out.println("排序前：");
        System.out.println(Arrays.toString(arr));
        mergeSort(arr);
        System.out.println("排序后：");
        System.out.println(Arrays.toString(arr));
    }

    private static void mergeSort(int[] arr) {
        int k = 1;
        int len = arr.length - 1;
        int[] temp = new int[len + 1];
        while (k <= len) {
            mergeMethod(arr, k, len, temp);
            k = 2 * k;
        }
    }

    private static void mergeMethod(int[] arr, int k, int len, int[] temp) {
        int i;
        //为什么是i + 2 * k - 1和i+k-1呢
        //因为比如每个序列的长度为2（也就是k为2），那么第一组的两个序列应该是arr[0~1]，
        // arr[2~3];第二组的两个序列应该是arr[4~5]和arr[6~7]，
        // 那么根据merge函数的参数定义i+k-1起始就是对应着mid参数；i+2*k-1就是对应着high参数
        for (i = 0; i <= len; i += 2 * k) {
            if (i + 2 * k - 1 <= len)
                merge(arr, i, i + k - 1, i + 2 * k - 1, temp);
            else
                break;
        }
        if (i + k - 1 < len)
            merge(arr, i, i + k - 1, len, temp);
    }

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

























  /*  public static void mergeSort(int[] arr) {
        int len = arr.length - 1;
        int[] temp = new int[len + 1];
        int k = 1;
        while (k <= len) {
            mergePass(arr, k, len, temp);
            k *= 2;
        }
    }

    *//**
     * @param arr  待排序的数组
     * @param k 步长，比如第一波步长应该为1，即两个长度为1的序列合并成有序序列
     * @param len
     * @param temp
     *//*
    private static void mergePass(int[] arr, int k, int len, int[] temp) {
        int i = 0;
        //为什么是i + 2 * k - 1和i+k-1呢
        //因为比如每个序列的长度为2（也就是k为2），那么第一组的两个序列应该是arr[0~1]，
        // arr[2~3];第二组的两个序列应该是arr[4~5]和arr[6~7]，
        // 那么根据merge函数的参数定义i+k-1起始就是对应着mid参数；i+2*k-1就是对应着high参数
        while (i + 2 * k - 1 <= len) {
            merge(arr, i, i + k - 1, i + 2 * k - 1, temp);
            i = i + 2 * k;
        }
        if (i + k - 1 < len)
            merge(arr, i, i + k - 1, len, temp);
    }

    *//**
     * @param arr  待排序的数组
     * @param low  第一个序列的起始位置
     * @param mid  两个序列的分界位置，也就是第一个序列的终点位置，第二个序列的起始位置
     * @param high 第二个序列的终止位置
     * @param temp 临时数组，只要和待排序数组一样大即可
     *//*

    private static void merge(int[] arr, int low, int mid, int high, int[] temp) {
        //第一个序列的起始指针
        int left = low;
        //第二个序列的起始指针
        int right = mid + 1;
        //临时数组的起始指针
        int point = low;

        while (left <= mid && right <= high) {
            if (arr[left] < arr[right])
                temp[point++] = arr[left++];
            else
                temp[point++] = arr[right++];
        }
        while (left <= mid)
            temp[point++] = arr[left++];
        while (right <= high)
            temp[point++] = arr[right++];
        for (int i = low; i <= high; i++) {
            arr[i] = temp[i];
        }

    }*/
}
