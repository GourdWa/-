package com.learn.domain;

import com.learn.stack.SqDoubleStack;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * @author ZixiangHu
 * @create 2020-04-16  22:22
 * @description
 */
public class SqDoubleStackTest {

    SqDoubleStack<Integer> stack = new SqDoubleStack<>(Integer.class, 5);

    @Test
    public void push() throws Exception {
        stack.push(1, 1);
        stack.push(5, 2);
        stack.push(1, 1);
        stack.push(5, 2);
        stack.push(1, 1);
        stack.push(5, 2);
        System.out.println(Arrays.toString(stack.getData1()));
        System.out.println(Arrays.toString(stack.getData2()));
        System.out.println(stack.getTop1());
        System.out.println(stack.getTop2());
    }

    @Test
    public void pop() throws Exception {
        stack.push(1, 1);
        stack.push(5, 2);
        stack.push(1, 1);
        stack.push(5, 2);
        stack.push(1, 1);
        System.out.println(Arrays.toString(stack.getData1()));
        System.out.println(Arrays.toString(stack.getData2()));
        System.out.println(stack.getTop1());
        System.out.println(stack.getTop2());
        System.out.println(stack.pop(1));
        System.out.println(stack.pop(1));
        System.out.println(stack.pop(1));
        System.out.println(Arrays.toString(stack.getData2()));
        System.out.println(stack.getTop1());
        System.out.println(stack.getTop2());
    }
}