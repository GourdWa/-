package com.learn.sort;

import java.util.Arrays;
import java.util.Random;

/**
 * @author ZixiangHu
 * @create 2020-05-04  9:33
 * @description
 */
public class ShellSort {
    public static void main(String[] args) {
        int[] arr = new int[13];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = new Random().nextInt(50);
        }
        System.out.println("排序前：");
        System.out.println(Arrays.toString(arr));
        sellSort(arr);
        System.out.println("排序后：");
        System.out.println(Arrays.toString(arr));
    }

    private static void sellSort(int[] arr) {
        int len = arr.length;
        int increment = len / 3 + 1;
        while (increment != 0) {
            if (increment == 1)
                increment = 0;
            else
                increment = increment / 3 + 1;
            for (int i = increment; i < len; i++) {
                if (arr[i - increment] > arr[i]) {
                    int temp = arr[i];
                    int j = 0;
                    for (j = i - increment; j >= 0 && arr[j] > temp; j -= increment) {
                        arr[j + increment] = arr[j];
                    }
                    arr[j + increment] = temp;
                }
            }
        }
    }
}
