package com.learn.sort;

import java.util.Arrays;
import java.util.Random;

/**
 * @author ZixiangHu
 * @create 2020-05-03  20:38
 * @description
 */
public class BubbleSorting {
    public static void bubbleSort1(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }

    public static void bubbleSort2(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    public static void bubbleSort(int arr[]) {
        boolean didSwap;
        for (int i = 0, len = arr.length; i < len - 1; i++) {
            didSwap = false;
            for (int j = 0; j < len - i - 1; j++) {
                if (arr[j + 1] < arr[j]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    didSwap = true;
                }
            }
            if (didSwap == false)
                return;
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[10];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = new Random().nextInt(50);
        }
        System.out.println("排序前：");
        System.out.println(Arrays.toString(arr));
        bubbleSort(arr);
        System.out.println("排序后：");
        System.out.println(Arrays.toString(arr));
    }
}
