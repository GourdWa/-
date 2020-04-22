package com.learn.domain;

import com.learn.BiTTree;
import com.learn.utils.BiTTraverseUtils;

import java.util.List;

/**
 * @author ZixiangHu
 * @create 2020-04-22  15:19
 * @description
 */
public class CreateTest {
    public static void main(String[] args) {
        int[] nums = {1,2,4,7,-1,-1,8,-1,-1,5,-1,-1,3,-1,6,-1,-1};
        BiTTree biTTree = new BiTTree();
        biTTree =  BiTTraverseUtils.createBitTree(biTTree,nums);
        List<Integer> list = BiTTraverseUtils.preOrderTraverse(biTTree);
        System.out.println(list);
    }
}
