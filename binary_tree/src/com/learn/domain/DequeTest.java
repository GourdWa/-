package com.learn.domain;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author ZixiangHu
 * @create 2020-04-21  23:15
 * @description
 */
public class DequeTest {
    public static void main(String[] args) {
        Deque<Integer> deque = new LinkedList<>();
        deque.push(1);
        deque.push(2);
        deque.push(3);
        deque.push(4);
        System.out.println(deque.peek());
        System.out.println(deque.peekLast());
        System.out.println(deque.pop());
        System.out.println(deque.poll());

    }
}
