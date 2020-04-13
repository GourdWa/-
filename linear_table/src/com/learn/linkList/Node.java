package com.learn.linkList;

import java.util.Random;

/**
 * @author ZixiangHu
 * @create 2020-04-13  20:52
 * @description
 */
public class Node<T> {
    private T date;
    private Node<T> next;

    public Node() {
    }

    public Node(T date, Node<T> next) {
        this.date = date;
        this.next = next;
    }

    private T getDate() {
        return date;
    }

    private void setDate(T date) {
        this.date = date;
    }

    private Node<T> getNext() {
        return next;
    }

    private void setNext(Node<T> next) {
        this.next = next;
    }

    /**
     * 读取单链表中的第i个元素的值
     *
     * @param i
     * @return
     */
    public T getElem(int i) throws Exception {
        if (this.getNext() == null)
            throw new Exception("单链表为空");
        Node<T> temp = this.getNext();
        while (temp != null && --i > 0) {
            temp = temp.getNext();
        }
        if (temp == null)
            throw new Exception("第【" + i + "】个元素不存在");
        return temp.getDate();
    }

    /**
     * 在单链表的第i个位置插入元素ele
     *
     * @param i
     * @param ele
     * @return
     */
    public boolean listInsert(int i, T ele) throws Exception {
        Node<T> temp = this;
        int j = 1;
        while (temp != null && j != i) {
            temp = temp.getNext();
            j++;
        }
        if (temp == null)
            throw new Exception("插入的节点序号【" + i + "】不正确");
        //新节点
        Node<T> s = new Node<>(ele, null);
        s.setNext(temp.getNext());
        temp.setNext(s);
        return true;
    }

    /**
     * 删除第i个节点
     *
     * @param i
     * @return
     */
    public T listDelete(int i) throws Exception {
        Node<T> temp = this;
        int j = 0;
        while (temp != null && j != i - 1) {
            temp = temp.getNext();
            j++;
        }
        //最终temp是指向删除节点的前一个节点
        if (temp == null || temp.getNext() == null)
            throw new Exception("删除的节点序号【" + i + "】不正确");
        Node<T> delNode = temp.getNext();
        temp.setNext(temp.getNext().getNext());
        return delNode.getDate();
    }

    /**
     * 展示全部节点数据
     */
    public void show() {
        Node<T> temp = this.getNext();
        while (temp != null) {
            System.out.print(temp.getDate() + " ");
            temp = temp.getNext();
        }
        System.out.println();
    }

}
