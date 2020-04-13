package com.learn.domain;

import com.learn.linkList.Node;

import java.util.Random;

/**
 * @author ZixiangHu
 * @create 2020-04-13  21:54
 * @description
 */
public class TestLinkList {
    public static void main(String[] args) throws Exception {
        Node<Integer> head = new Node<>();
        for (int i = 0; i < 10; i++) {
            head.listInsert(i + 1, new Random().nextInt(100));
        }
        head.show();
        head.listInsert(10, 66);
        head.show();
        head.listDelete(1);
        head.show();
        System.out.println(head.getElem(5));
    }
}
