package com.learn.domain;

import com.learn.hash.MyHashTable;

/**
 * @author ZixiangHu
 * @create 2020-05-01  13:56
 * @description
 */
public class MyHashTableTest {
    public static void main(String[] args) {
        MyHashTable myHashTable = new MyHashTable(5);
        myHashTable.insertHash(5);
        myHashTable.insertHash(4);
        myHashTable.insertHash(3);
        myHashTable.insertHash(2);
        myHashTable.insertHash(1);
        myHashTable.insertHash(0);
        System.out.println(myHashTable.searchHash(1));
    }
}
