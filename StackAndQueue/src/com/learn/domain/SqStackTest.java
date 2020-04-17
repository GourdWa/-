package com.learn.domain;

import com.learn.stack.SqStack;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * @author ZixiangHu
 * @create 2020-04-16  21:27
 * @description
 */
public class SqStackTest {
    SqStack<String> sqStack = new SqStack<>(String.class, 5);



    @org.junit.Test
    public void push() throws Exception {
        sqStack.push("5");
        sqStack.push("1");
        sqStack.push("2");
        sqStack.push("3");
        sqStack.push("4");
        System.out.println(Arrays.toString(sqStack.getData()));
    }

    @org.junit.Test
    public void pop() throws Exception {
        sqStack.push("5");
        sqStack.push("1");
        sqStack.push("2");
        sqStack.push("3");
        sqStack.push("4");
        System.out.println(Arrays.toString(sqStack.getData()));
        System.out.println(sqStack.pop());
        System.out.println(sqStack.pop());
        System.out.println(sqStack.pop());
        System.out.println(sqStack.pop());
        System.out.println(Arrays.toString(sqStack.getData()));
        System.out.println(sqStack.pop());
        System.out.println(sqStack.pop());
    }
}