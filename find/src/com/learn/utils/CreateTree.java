package com.learn.utils;

import com.learn.tree.BitNode;

/**
 * @author ZixiangHu
 * @create 2020-04-29  16:21
 * @description
 */
public class CreateTree {
    private static int i = 0;

    public static BitNode create(BitNode node, int[] nums) {
        if (nums[i] != -1) {
            node = new BitNode(nums[i]);
            i += 1;
            node.setLeft(create(node.getLeft(), nums));
            node.setRight(create(node.getRight(), nums));
            return node;
        } else {
            i += 1;
            return null;
        }
    }
}
