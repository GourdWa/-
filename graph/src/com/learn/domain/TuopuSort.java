package com.learn.domain;

import java.util.*;

/**
 * @author ZixiangHu
 * @create 2020-04-27  23:56
 * @description
 */
public class TuopuSort {
    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        //保存每个节点的先修课个数，也就是出度
        HashMap<Integer, Integer> outNum = new HashMap<>();
        //保存以 key 为先修课的列表，也就是入度的节点
        HashMap<Integer, ArrayList<Integer>> inNodes = new HashMap<>();
        //保存所有节点
        HashSet<Integer> set = new HashSet<>();
        int rows = prerequisites.length;
        for (int i = 0; i < rows; i++) {
            int key = prerequisites[i][0];
            int value = prerequisites[i][1];
            set.add(key);
            set.add(value);
            if (!outNum.containsKey(key)) {
                outNum.put(key, 0);
            }
            if (!outNum.containsKey(value)) {
                outNum.put(value, 0);
            }
            //当前节点先修课个数加一
            int num = outNum.get(key);
            outNum.put(key, num + 1);

            if (!inNodes.containsKey(value)) {
                inNodes.put(value, new ArrayList<>());
            }
            //更新以 value 为先修课的列表
            ArrayList<Integer> list = inNodes.get(value);
            list.add(key);
        }

        //将当前先修课个数为 0 的课加入到队列中
        Queue<Integer> queue = new LinkedList<>();
        for (int k : set) {
            if (outNum.get(k) == 0) {
                queue.offer(k);
            }
        }
        while (!queue.isEmpty()) {
            //队列拿出来的课代表要删除的节点
            //要删除的节点的 list 中所有课的先修课个数减一
            int v = queue.poll();
            ArrayList<Integer> list = inNodes.getOrDefault(v, new ArrayList<>());

            for (int k : list) {
                int num = outNum.get(k);
                //当前课的先修课要变成 0, 加入队列
                if (num == 1) {
                    queue.offer(k);
                }
                //当前课的先修课个数减一
                outNum.put(k, num - 1);
            }
        }

        //判断所有课的先修课的个数是否为 0
        for (int k : set) {
            if (outNum.get(k) != 0) {
                return false;
            }
        }
        return true;
    }

//    [[1,3],[1,4],[2,4],[3,5],[3,6],[4,6]]
    public static void main(String[] args) {
        int[][] course = {{1,3},{1,4},{2,4},{3,5},{3,6},{4,6}};
        canFinish(6,course);
    }
}
