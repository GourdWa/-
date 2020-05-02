package com.learn.hash;

/**
 * @author ZixiangHu
 * @create 2020-05-01  13:31
 * @description
 */
public class MyHashTable {
    private int[] elem;
    private int count;

/*    public MyHashTable() {
    }*/

    /**
     * 初始化散列表
     *
     * @param count
     */
    public MyHashTable(int count) {
        this.elem = new int[count];
        this.count = count;
        for (int i = 0; i < count; i++) {
            elem[i] = Integer.MIN_VALUE;
        }
    }

    /**
     * 散列函数，除留余数法
     *
     * @param key
     * @return
     */
    public int hash(int key) {
        return key % count;
    }

    /**
     * 向散列表中插入元素
     *
     * @param key
     */
    public void insertHash(int key) {
        int addr = hash(key);
        //说明该地址已经有其他的元素存在
        while (elem[addr] != Integer.MIN_VALUE) {
            addr = hash(addr + 1);
            if (addr == hash(key)) {
                System.out.println("散列表已满...元素" + key + "插入不成功");
                return;
            }
        }
        elem[addr] = key;
        System.out.println("元素" + key + "插入成功");
    }

    /**
     * 散列表查找元素
     *
     * @param key
     * @return
     */
    public int searchHash(int key) {
        int addr = hash(key);
        while (elem[addr] != key) {
            addr = hash(addr + 1);
            if (elem[addr] == Integer.MIN_VALUE || addr == hash(key)) {
                return -1;
            }
        }
        return addr;
    }
}
