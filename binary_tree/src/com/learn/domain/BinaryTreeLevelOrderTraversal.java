package com.learn.domain;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @author ZixiangHu
 * @create 2020-04-24  13:02
 * @description 二叉树的层序遍历，BFS的思想
 */


class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

public class BinaryTreeLevelOrderTraversal {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> lists = new ArrayList<>();
        Deque<TreeNode> deque = new LinkedList<>();
        if (root != null) {
            deque.add(root);
        }
        while (!deque.isEmpty()) {
            int size = deque.size();
            List<Integer> list = new ArrayList<>();
            while (size > 0) {
                size -= 1;
                TreeNode pollEle = deque.poll();
                if (pollEle.left != null)
                    deque.add(pollEle.left);
                if (pollEle.right != null)
                    deque.add(pollEle.right);
                list.add(pollEle.val);
            }
            lists.add(list);
        }
        return lists;
    }

    public static void main(String[] args) {

    }
}
