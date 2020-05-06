package com.learn.sort;

import java.util.Arrays;
import java.util.Random;

/**
 * @author ZixiangHu
 * @create 2020-05-03  20:52
 * @description
 */
public class SelectSort {
    public static void main(String[] args) {
        int[] arr = new int[10];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = new Random().nextInt(50);
        }
        System.out.println("排序前：");
        System.out.println(Arrays.toString(arr));
        selectSort(arr);
        System.out.println("排序后：");
        System.out.println(Arrays.toString(arr));
    }

    private static void selectSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int min = i;
            for (int j = i + 1; j < arr.length; j++)
                if (arr[min] > arr[j])
                    min = j;
            if (i != min) {
                int temp = arr[min];
                arr[min] = arr[i];
                arr[i] = temp;
            }
        }
    }
}
