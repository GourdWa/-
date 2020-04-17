package com.learn.stack;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * @author ZixiangHu
 * @create 2020-04-16  21:45
 * @description
 */
public class SqDoubleStack<T> {
    private int maxsize;
    private T[] data;
    private int top1;
    private int top2;
    private Class<T> type;

    public SqDoubleStack(Class<T> type, int maxsize) {
        this.type = type;
        this.maxsize = maxsize;
        data = (T[]) Array.newInstance(type, maxsize);
        top1 = -1;
        top2 = maxsize;
    }

    public int getMaxsize() {
        return maxsize;
    }

    public T[] getData1() throws Exception {
        if (top1 == -1) {
            throw new Exception("栈1为空");
        }
        return Arrays.copyOf(data, top1 + 1);
    }

    public T[] getData2() throws Exception {
        if (top2 == maxsize) {
            throw new Exception("栈2为空");
        }
        T[] res = (T[]) Array.newInstance(type, maxsize - top2);
        for (int i = top2; i < maxsize; i++) {
            res[i - top2] = data[i];
        }
        return res;
    }

    public int getTop1() {
        return top1;
    }

    public int getTop2() {
        return top2;
    }

    public boolean push(T ele, int stackNum) throws Exception {
        if (top1 + 1 == top2) {
            throw new Exception("栈空间已满");
        }
        if (stackNum != 1 && stackNum != 2) {
            throw new Exception("栈序号错误，只能是1或2");
        }
        if (stackNum == 1) {
            data[++top1] = ele;
        } else {
            data[--top2] = ele;
        }
        return true;
    }

    public T pop(int stackNum) throws Exception {
        if (stackNum != 1 && stackNum != 2) {
            throw new Exception("栈序号错误，只能是1或2");
        }

        if (stackNum == 1) {
            if (top1 == -1) {
                throw new Exception("栈1为空");
            }
            return data[top1--];
        } else {
            if (top2 == maxsize) {
                throw new Exception("栈2为空");
            }
            return data[top2++];
        }
    }
}
