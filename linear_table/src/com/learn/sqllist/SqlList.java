package com.learn.sqllist;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * @author ZixiangHu
 * @create 2020-04-13  14:04
 * @description
 */
public class SqlList<T> {
    private int maxSize;
    private T[] data;
    private int length;

    public SqlList() {
    }

    @SuppressWarnings({"unchecked", "hiding"})
    public SqlList(Class<T> tClass, int maxSize) {
        this.maxSize = maxSize;
        data = (T[]) Array.newInstance(tClass, maxSize);
        length = 0;
    }

    public int getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public T[] getData() {
        return data;
    }

}
