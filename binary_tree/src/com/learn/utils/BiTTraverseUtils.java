package com.learn.utils;

import com.learn.BiTTree;

import java.util.*;

/**
 * @author ZixiangHu
 * @create 2020-04-21  20:56
 * @description
 */
public class BiTTraverseUtils {
    private static int i = -1;

    /**
     * 二叉树递归前序遍历
     *
     * @param tree
     * @param list 前序遍历的结果
     * @return
     */
    public static void preOrderTraverseByRecursion(BiTTree tree, List<Integer> list) {
        if (tree != null) {
            list.add(tree.getData());
            preOrderTraverseByRecursion(tree.getLchild(), list);
            preOrderTraverseByRecursion(tree.getRchild(), list);
        }
    }

    /**
     * 二叉树的非递归前序遍历
     *
     * @param tree
     * @return 前序遍历的结果
     */
    public static List<Integer> preOrderTraverse(BiTTree tree) {
        Deque<BiTTree> stack = new LinkedList<>();
        List<Integer> list = new ArrayList<>();
        BiTTree tNode = tree;
        while (tNode != null || !stack.isEmpty()) {
            while (tNode != null) {
                list.add(tNode.getData());
                stack.push(tNode);
                tNode = tNode.getLchild();
            }
            BiTTree popTree = stack.pop();
            tNode = popTree.getRchild();
        }
        return list;
    }

    /**
     * 二叉树递归中序遍历
     *
     * @param tree
     * @param list
     */
    public static void inOrderTraverseByRecursion(BiTTree tree, List<Integer> list) {
        if (tree != null) {
            inOrderTraverseByRecursion(tree.getLchild(), list);
            list.add(tree.getData());
            inOrderTraverseByRecursion(tree.getRchild(), list);
        }
    }

    /**
     * 二叉树的非递归中序遍历
     *
     * @param tree
     * @return
     */
    public static List<Integer> inOrderTraverse(BiTTree tree) {
        List<Integer> list = new ArrayList<>();
        Deque<BiTTree> stack = new LinkedList<>();
        BiTTree tNode = tree;
        while (tNode != null || !stack.isEmpty()) {
            while (tNode != null) {
                stack.push(tNode);
                tNode = tNode.getLchild();
            }
            BiTTree popTree = stack.pop();
            list.add(popTree.getData());
            tNode = popTree.getRchild();
        }
        return list;
    }

    /**
     * 二叉树递归后序遍历
     *
     * @param tree
     * @param list
     */
    public static void postOrderTraverseByRecursion(BiTTree tree, List<Integer> list) {
        if (tree != null) {
            postOrderTraverseByRecursion(tree.getLchild(), list);
            postOrderTraverseByRecursion(tree.getRchild(), list);
            list.add(tree.getData());
        }
    }

    /**
     * 二叉树的非递归后序遍历
     *
     * @param tree
     * @return
     */
    public static List<Integer> postOrderTraverse(BiTTree tree) {
        List<Integer> list = new ArrayList<>();
        Deque<BiTTree> stack = new LinkedList<>();
        BiTTree curNode = null;
        BiTTree preNode = null;
        stack.add(tree);
        while (!stack.isEmpty()) {
            curNode = stack.peek();
            if ((curNode.getRchild() == null && curNode.getLchild() == null) ||
                    (preNode != null && (preNode == curNode.getLchild() || preNode == curNode.getRchild()))) {
                list.add(curNode.getData());
                stack.pop();
                preNode = curNode;
            } else {
                if (curNode.getRchild() != null)
                    stack.push(curNode.getRchild());
                if (curNode.getLchild() != null)
                    stack.push(curNode.getLchild());
            }
        }
        return list;
    }

    public static BiTTree preCreat(BiTTree btnode) {
        Scanner in = new Scanner(System.in);
        System.out.println("输入结点的值");
        int value = in.nextInt();
        if (value != 0) {
            btnode = new BiTTree();
            btnode.setData(value);
            //以下两行是核心代码
            btnode.setLchild(preCreat(btnode.getLchild()));
            btnode.setRchild(preCreat(btnode.getRchild()));
        } else
            //这个是一定要有的，确定最终的结束结点
            btnode = null;
        return btnode;
    }


    public static BiTTree createBitTree(BiTTree node, int[] nums) {
        if (i < nums.length) {
            i += 1;
            if (nums[i] != -1) {
                node = new BiTTree();
                node.setData(nums[i]);
                node.setLchild(createBitTree(node.getLchild(), nums));
                node.setRchild(createBitTree(node.getRchild(), nums));
            } else {
                node = null;
            }

        }
        return node;
    }

}
