package com.learn.domain;

import java.util.Arrays;

/**
 * @author ZixiangHu
 * @create 2020-04-28  13:30
 * @description
 */
public class LeetCode56 {

    public static int[] singleNumbers(int[] nums) {
        int sum = 0;
        int[] res = new int[2];
        for (int num : nums) {
            sum ^= num;
        }
        int bit = 1;
        while ((bit & sum) == 0) {
            bit <<= 1;
        }
        for (int num : nums) {
            if ((num & bit) == 0) {
                res[0] ^= num;
            } else {
                res[1] ^= num;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] numbers = singleNumbers(new int[]{1, 2, 10, 4, 1, 4, 3, 3});
        System.out.println(Arrays.toString(numbers));
    }
}
