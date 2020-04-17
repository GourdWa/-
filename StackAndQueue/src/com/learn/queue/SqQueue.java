package com.learn.queue;

import java.lang.reflect.Array;

/**
 * @author ZixiangHu
 * @create 2020-04-17  19:54
 * @description
 */
public class SqQueue<T> {
    private T[] data;
    private int front;
    private int rear;
    private int maxsize;

    public SqQueue(Class<T> type, int maxsize) {
        this.front = 0;
        this.rear = 0;
        data = (T[]) Array.newInstance(type, maxsize);
        this.maxsize = maxsize;
    }

    public T[] getData() {
        return data;
    }

    /**
     * 获取循环队列的长度
     *
     * @return
     */
    public int queueLength() {
        return (rear - front + maxsize) % maxsize;
    }

    /**
     * 向循环队列中入队
     *
     * @param ele
     * @return
     * @throws Exception
     */
    public boolean enQueue(T ele) throws Exception {
        if ((rear + 1) % maxsize == front) {
            throw new Exception("队列已满");
        }
        data[rear] = ele;
        rear = (rear + 1) % maxsize;
        return true;
    }

    public T deQueue() throws Exception {
        if (rear == front) {
            throw new Exception("队列为空");
        }
        T res = data[front];
        front = (front + 1) % maxsize;
        return res;
    }
}
