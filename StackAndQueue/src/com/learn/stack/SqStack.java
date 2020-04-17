package com.learn.stack;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * @author ZixiangHu
 * @create 2020-04-16  21:13
 * @description
 */
public class SqStack<T> {
    private int maxsize;
    private T[] data;
    private int top;

    public SqStack() {
    }

    /**
     * 栈空是top为-1；栈满时top=maxsize-1
     *
     * @param type
     * @param maxsize
     */
    @SuppressWarnings({ "unchecked", "hiding" })
    public SqStack(Class<T> type, int maxsize) {
        this.maxsize = maxsize;
        this.data = (T[]) Array.newInstance(type,maxsize);
        this.top = -1;
    }


    public int getMaxsize() {
        return maxsize;
    }


    public T[] getData() throws Exception {
        if (top==-1)
            throw new Exception("栈为空");
        return Arrays.copyOf(data,top+1);
    }

    public int getTop() {
        return top;
    }


    public boolean push(T ele) throws Exception {
        if (this.top == this.maxsize - 1) {
            throw new Exception("栈已满");
        }
        this.data[++this.top] = ele;
        return true;
    }

    public T pop() throws Exception {
        if (this.top == -1) {
            throw new Exception("栈为空");
        }
        return this.data[top--];
    }

}
