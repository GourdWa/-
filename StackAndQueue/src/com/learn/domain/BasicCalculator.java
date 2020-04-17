package com.learn.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author ZixiangHu
 * @create 2020-04-17  10:39
 * @description
 */
public class BasicCalculator {
    public int evalRPN(List<String> tokens) {

        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < tokens.size(); i++) {
            if (tokens.get(i).equals("+")) {
                stack.push(stack.pop() + stack.pop());
            } else if (tokens.get(i).equals("-")) {
                int a = stack.pop();
                int b = stack.pop();
                stack.push(b - a);
            } else if (tokens.get(i).equals("*")) {
                stack.push(stack.pop() * stack.pop());
            } else if (tokens.get(i).equals("/")) {
                int a = stack.pop();
                int b = stack.pop();
                stack.push(b / a);
            } else {
                stack.push(Integer.parseInt(tokens.get(i)));
            }
        }
        return stack.pop();
    }

    public int calculate(String s) {

        Stack<Character> stack = new Stack<>();
        //得到逆波兰表达式
        List<String> list = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ')
                continue;
            if (s.charAt(i) == '(') {
                stack.push('(');
            } else if (s.charAt(i) == ')') {
                char ch = stack.pop();
                while (ch != '(') {
                    list.add(ch + "");
                    ch = stack.pop();
                }
            } else if (s.charAt(i) == '+') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    char ch = stack.pop();
                    list.add(ch + "");
                }
                stack.push('+');
            } else if (s.charAt(i) == '-') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    char ch = stack.pop();
                    list.add(ch + "");
                }
                stack.push('-');
            } else {
                int t = 0;
                int j = i;
                while (j < s.length() && (s.charAt(j) >= '0' && s.charAt(j) <= '9')) {
                    t = t * 10 + Integer.parseInt(s.charAt(j) + "");
                    j++;
                }
                i = j - 1;
                list.add(t + "");
            }
        }
        while (!stack.isEmpty()) {
            char ch = stack.pop();
            list.add(ch + "");
        }
        return evalRPN(list);
    }


    public static void main(String[] args) {
        String s = "(1+(4+5+2)-3)+(6+8)";
        System.out.println(new BasicCalculator().calculate(s));
    }
}
