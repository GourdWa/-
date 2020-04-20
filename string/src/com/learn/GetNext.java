package com.learn;

import java.util.Arrays;

/**
 * @author ZixiangHu
 * @create 2020-04-20  16:12
 * @description
 */
public class GetNext {
    public int[] getNext(String t) {
        int len = t.length();
        int[] next = new int[len + 1];
        next[1] = 0;
        int i = 1, j = 0;
        while (i < len) {
            if (j == 0 || t.charAt(i - 1) == t.charAt(j - 1)) {
                ++i;
                ++j;
                next[i] = j;
            } else
                j = next[j];
        }
        return next;
    }

    public int indexKMP(String s, String t) {
        int i = 0;
        int j = 0;
        int[] next = getNext(t);
        while (i < s.length() && j < t.length()) {
            if (j == 0 || s.charAt(i) == t.charAt(j)) {
                j++;
                i++;
            } else {
                j = next[j];
            }
        }
        if (j >= t.length()) {
            return i - t.length();
        } else
            return -1;
    }

    public static void main(String[] args) {
        int i = new GetNext().indexKMP("a2def6abcabx", "c");
        System.out.println(i);
    }
}
