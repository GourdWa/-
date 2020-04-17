package com.learn.domain;

import com.learn.queue.SqQueue;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author ZixiangHu
 * @create 2020-04-17  20:30
 * @description
 */
public class SqQueueTest {
    SqQueue<Integer> queue = new SqQueue<>(Integer.class, 5);

    @Test
    public void enQueue() throws Exception {
        queue.enQueue(1);
        queue.enQueue(2);
        System.out.println(queue.queueLength());
        queue.enQueue(3);
        queue.enQueue(4);
        System.out.println(queue.queueLength());
    }

    @Test
    public void deQueue() throws Exception {
        queue.enQueue(1);
        queue.enQueue(2);
        queue.enQueue(3);
        queue.enQueue(4);
        System.out.println(queue.queueLength());
        System.out.println(queue.deQueue());
        System.out.println(queue.queueLength());
    }
}