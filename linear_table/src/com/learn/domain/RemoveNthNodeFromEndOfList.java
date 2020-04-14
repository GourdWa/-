package com.learn.domain;

/**
 * @author ZixiangHu
 * @create 2020-04-14  12:42
 * @description
 */

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}

class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode temp = new ListNode(0);
        temp.next = head;
        ListNode first = temp;
        ListNode second = temp;
        while (second != null && n > 0) {
            second = second.next;
            n--;
        }
        if (second == null)
            return null;
        else {
            while (second.next != null) {
                second = second.next;
                first = first.next;
            }
            //此时first就是待删除节点的前一个节点
            first.next = first.next.next;
        }
        return temp.next;
    }
}

public class RemoveNthNodeFromEndOfList {
    public static void main(String[] args) {

    }
}
