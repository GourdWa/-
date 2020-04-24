package com.learn.domain;

/**
 * @author ZixiangHu
 * @create 2020-04-24  17:20
 * @description LeetCode-110，平衡二叉树
 */
public class BalancedBinaryTree {
    public boolean isBalanced(TreeNode root) {
        if (root == null)
            return true;
        else if (Math.abs(getDepth(root.left) - getDepth(root.right)) > 1)
            return false;
        else
            return isBalanced(root.left) && isBalanced(root.right);
    }

    int getDepth(TreeNode node) {
        if (node != null) {
            int lDepth = getDepth(node.left);
            int rDepth = getDepth(node.right);
            return Math.min(lDepth,rDepth) + 1;
        }
        return 0;
    }

    public static void main(String[] args) {

    }
}
