package com.learn.domain;

import com.learn.sqllist.SqlList;
import com.learn.sqllist.SqlListUtils;


/**
 * @author ZixiangHu
 * @create 2020-04-13  14:38
 * @description
 */
public class TestSqlList {
    public static void main(String[] args) throws Exception {
        SqlList sqlList = new SqlList(Integer.class,10);
        System.out.println("初始化后SqlList的长度："+sqlList.getLength());
        SqlListUtils.listInsert(sqlList,1,20);
        System.out.println("插入一个元素后SqlList的长度："+sqlList.getLength());
        SqlListUtils.show(sqlList);
        SqlListUtils.listInsert(sqlList,2,"20");

    }
}
