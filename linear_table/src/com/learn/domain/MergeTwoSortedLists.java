package com.learn.domain;

/**
 * @author ZixiangHu
 * @create 2020-04-14  13:53
 * @description
 */
public class MergeTwoSortedLists {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0);
        ListNode t = head;
        while (l1 != null && l2 != null) {
            if (l1.val > l2.val) {
                t.next = new ListNode(l2.val);
                l2 = l2.next;
            } else if (l1.val < l2.val) {
                t.next = new ListNode(l1.val);
                l2 = l2.next;
            } else{
                t.next = new ListNode(l1.val);
                t = t.next;
                t.next = new ListNode(l2.val);
            }
            t = t.next;
        }
        if (l1!=null){
            t.next = l1;
        }else {
            t.next = l2;
        }
        return head.next;
    }

    public static void main(String[] args) {

    }
}
