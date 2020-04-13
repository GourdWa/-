package com.learn.sqllist;

/**
 * @author ZixiangHu
 * @create 2020-04-13  14:28
 * @description
 */
public class SqlListUtils {

    /**
     * 获取第i个位置上的元素
     *
     * @param sqlList
     * @param i
     * @return
     * @throws Exception
     */
    public static <T> T getElem(SqlList sqlList, int i) throws Exception {
        if (sqlList.getLength() == 0 || i < 1 || i > sqlList.getLength()) {
            throw new Exception("获取的第" + i + "个元素序号不存在");
        }
        return (T) sqlList.getData()[i];
    }

    /**
     * 在第i个位置上插入元素e
     *
     * @param sqlList
     * @param i
     * @param e
     * @return
     */
    public static <T> boolean listInsert(SqlList sqlList, int i, T e) throws Exception {
        if (sqlList.getLength() == sqlList.getMaxSize())
            throw new Exception("顺序链表已满");
        if (i < 1 || i > sqlList.getLength() + 1)
            throw new Exception("插入序号" + i + "不正确");
        if (i <= sqlList.getLength()) {
            for (int j = sqlList.getLength(); j >= i - 1; j--) {
                sqlList.getData()[j] = sqlList.getData()[j - 1];
            }
        }
        sqlList.getData()[i - 1] = e;
        sqlList.setLength(sqlList.getLength() + 1);
        return true;
    }

    /***
     * 删除第i个元素
     * @param sqlList
     * @param i
     * @return
     * @throws Exception
     */
    public static <T> T listDelete(SqlList sqlList, int i) throws Exception {
        if (sqlList.getLength() == 0)
            throw new Exception("有序列表为空");
        if (i < 1 || i > sqlList.getLength())
            throw new Exception("删除的索引" + i + "不正确");
        Object res = null;
        if (i < sqlList.getLength()) {
            res = sqlList.getData()[i - 1];
            for (int j = i; j < sqlList.getLength(); j++) {
                sqlList.getData()[j - 1] = sqlList.getData()[j];
            }
        }
        sqlList.setLength(sqlList.getLength() - 1);
        return (T) res;
    }

    /**
     * 展示全部元素
     *
     * @param sqlList
     */
    public static void show(SqlList sqlList) {
        for (int i = 0; i < sqlList.getLength(); i++) {
            System.out.print(sqlList.getData()[i] + " ");
        }
        System.out.println();
    }
}
