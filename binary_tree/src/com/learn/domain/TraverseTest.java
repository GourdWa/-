package com.learn.domain;

import com.learn.BiTTree;
import com.learn.utils.BiTTraverseUtils;

import java.util.LinkedList;
import java.util.List;

/**
 * @author ZixiangHu
 * @create 2020-04-21  22:39
 * @description
 */
public class TraverseTest {
    public static void main(String[] args) {
        BiTTree a = new BiTTree(1);
        BiTTree b = new BiTTree(2);
        BiTTree c = new BiTTree(3);
        BiTTree d = new BiTTree(4);
        BiTTree e = new BiTTree(5);
        BiTTree f = new BiTTree(6);
        BiTTree g = new BiTTree(7);
        BiTTree h = new BiTTree(8);
        BiTTree i = new BiTTree(9);
        BiTTree j = new BiTTree(10);
        BiTTree k = new BiTTree(11);
        h.setRchild(k);
        d.setLchild(h);
        b.setLchild(d);
        b.setRchild(e);
        a.setLchild(b);
        f.setLchild(i);
        g.setRchild(j);
        c.setLchild(f);
        c.setRchild(g);
        a.setRchild(c);
        List<Integer> preOrder = new LinkedList<>();
        List<Integer> inOrder = new LinkedList<>();
        List<Integer> postOrder = new LinkedList<>();
        BiTTraverseUtils.preOrderTraverseByRecursion(a,preOrder);
        BiTTraverseUtils.inOrderTraverseByRecursion(a,inOrder);
        BiTTraverseUtils.postOrderTraverseByRecursion(a,postOrder);
        System.out.println("递归遍历：先序遍历、中序遍历、后序遍历");
        System.out.println(preOrder);
        System.out.println(inOrder);
        System.out.println(postOrder);
        preOrder=  BiTTraverseUtils.preOrderTraverse(a);
        inOrder=  BiTTraverseUtils.inOrderTraverse(a);
        postOrder=  BiTTraverseUtils.postOrderTraverse(a);
        System.out.println("非递归遍历：先序遍历、中序遍历、后序遍历");
        System.out.println(preOrder);
        System.out.println(inOrder);
        System.out.println(postOrder);
    }
}
