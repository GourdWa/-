package com.learn.domain;

import java.util.*;

/**
 * @author ZixiangHu
 * @create 2020-04-28  10:08
 * @description
 */
public class TopologySort {
    public static boolean canFinish(int numCourses, int[][] prerequisites) {
      /*    //用来存储各个节点
        Set<Integer> set = new HashSet<>();
        //存储节点的入度节点，例如节点2有1,3两个入度节点，则inNode.get(2)={1,3}
        //即要修1,3课程必须先修2课程
        Map<Integer, ArrayList<Integer>> inNode = new HashMap<>();
        //存储节点的出度，例如节点2的出度为3，那么outNum.get(2)=
        //即要修2课程，必须先修3课程
        Map<Integer, Integer> outNum = new HashMap<>();
        for (int[] prerequisite : prerequisites) {
            //要修a课程则必须先修b课程
            int a = prerequisite[0];
            int b = prerequisite[1];
            set.add(a);
            set.add(b);
            //课程a的依赖课程加一，也就是要修a课程之前还需要再修一门依赖课程
            if (!outNum.containsKey(a)) {
                outNum.put(a, 0);
            }
            if (!outNum.containsKey(b)) {
                outNum.put(b, 0);
            }
            outNum.put(a, outNum.get(a) + 1);

            if (!inNode.containsKey(b)) {
                inNode.put(b, new ArrayList<>());
            }
            inNode.get(b).add(a);
        }

        Queue<Integer> queue = new LinkedList<>();
        //先找出出度为0的课程，这些课程可以先修
        for (Integer course : set) {
            if (0 == outNum.get(course))
                queue.add(course);
        }

        while (!queue.isEmpty()) {
            Integer pollCourse = queue.poll();
            //找到需要先修pollCourse的课程，并将这些课程的出度减1
            List<Integer> list = inNode.getOrDefault(pollCourse, null);
            if (list != null) {
                for (Integer i : list) {
                    //如果i课程只依赖pollCourse课程，那么在pollCourse课程修完后就可以修i课程了
                    //因此将i课程放入队列
                    if (outNum.get(i) == 1) {
                        queue.add(i);
                    }
                    outNum.put(i, outNum.get(i) - 1);
                }
            }
        }
        for (Integer course : set) {
            if (outNum.get(course) != 0)
                return false;
        }
        return true;*/
        int[] flags = new int[numCourses];
        List<List<Integer>> list = new ArrayList<>();
        for (int i = 0; i < numCourses; i++)
            list.add(new ArrayList<>());
        for (int[] prerequisite : prerequisites)
            list.get(prerequisite[1]).add(prerequisite[0]);
        for (int i = 0; i < numCourses; i++)
            if (!dfs(list, flags, i))
                return false;
        return true;
    }

    public static boolean dfs(List<List<Integer>> list, int[] flags, int i) {
        if (flags[i] == 1)
            return false;
        if (flags[i] == -1)
            return true;
        flags[i] = 1;
        for (Integer j : list.get(i))
            if (!dfs(list, flags, j))
                return false;
        flags[i] = -1;
        return true;
    }


    public static void main(String[] args) {
//        int[][] course = {{1, 3}, {1, 4}, {2, 4}, {3, 5}, {3, 6}, {4, 6}};
        int[][] course = {{1, 0}, {0, 1}};
        System.out.println(canFinish(2, course));
        List<Integer> list = new ArrayList<>();

    }
}
