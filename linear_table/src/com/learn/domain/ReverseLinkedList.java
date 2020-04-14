package com.learn.domain;

/**
 * @author ZixiangHu
 * @create 2020-04-14  13:01
 * @description
 */
class Solution2 {
    public ListNode reverseList(ListNode head) {
        ListNode newHead = new ListNode(0);
        while (head != null) {
            ListNode s = new ListNode(head.val);
            s.next = null;
            if (newHead.next != null) {
                ListNode p = newHead.next;
                newHead.next = s;
                s.next = p;
            } else {
                newHead.next = s;
            }
            head = head.next;
        }
        return newHead.next;
    }
}

public class ReverseLinkedList {
    //方法二
    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr!=null){
            ListNode nextNode = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextNode;
        }
        return prev;
    }

    public static void main(String[] args) {

    }
}
