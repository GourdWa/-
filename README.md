# 大话数据结构
----
### 第二章
----
**时间复杂度**   
判断一个算法的效率时，函数中的常数和其他次项常常可以忽略，而应该关注最高项的阶数  
**推导大O阶**
1. 用常数1取代运行时间中的所有加法常数
2. 在修改后的运行次数函数中，只保留最高阶项
3. 如果最高阶项存在且不是1，则取出与这个项相乘的常数得到的结果就是大O阶     

对于分支结构而言，无论是真还是假，执行的次数都是恒定的，所以单纯的分支结构，其时间复杂度是O(1)   
**常见的时间复杂度**
* 常数阶O(1)
* 对数阶O(logn)
* 线性阶O(n)
* nlogn阶O(nlogn)
* 平方阶O(n²)
* 立方阶O(n³)
* 指数阶O(2ⁿ)  

在算法分析中，我们提到的运行时间一般都是指*最坏时间复杂度*
### 第三章
----
#### 顺序存储
线性表的**顺序存储**是指用一段地址连续的存储单元依次存储线性表的数据元素  
线性表的每个数据元素的类型都相同，可以用一维数组来实现顺序存储结构  
顺序存储结构需要三个属性
1. 存储空间的起始位置：数组data
2. 线性表的最大存储容量：数组的最大长度
3. 线性表的当前长度：length  
尤其值得注意的是线性表的第n个元素对应数组的n-1个下标，这在插入和删除中尤其需要注意

**创建顺序表**
```
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
...
}
```
**获取元素**
```
public static <T> T getElem(SqlList<T> sqlList, int i) throws Exception {
    if (sqlList.getLength() == 0 || i < 1 || i > sqlList.getLength()) {
        throw new Exception("获取的第" + i + "个元素序号不存在");
    }
    return sqlList.getData()[i - 1];
}
```
**插入元素**  
思路
* 首先判断插入的位置是否合理，比如表是否满，插入位置是否大于当前长度或小于1
* 从最后一个元素开始依次后移，直到将原本第i个位置上的元素后移
* 将待插入的元素填入i位置
* 表长加1
```
public static <T> boolean listInsert(SqlList<T> sqlList, int i, T e) throws Exception {
    if (sqlList.getLength() == sqlList.getMaxSize())
        throw new Exception("顺序链表已满");
    if (i < 1 || i > sqlList.getLength() + 1)
        throw new Exception("插入序号" + i + "不正确");
    if (i <= sqlList.getLength()) {
        for (int j = sqlList.getLength(); j >= i; j--) {
            sqlList.getData()[j] = sqlList.getData()[j - 1];
        }
    }
    sqlList.getData()[i - 1] = e;
    sqlList.setLength(sqlList.getLength() + 1);
    return true;
}
```
**删除元素**  
思路
* 如果删除元素位置不合理，例如小于1或者大于数组长度
* 取出要删除位置上的元素
* 待删除元素后面的元素依次前移
* 表长减一
```
public static <T> T listDelete(SqlList<T> sqlList, int i) throws Exception {
    if (sqlList.getLength() == 0)
        throw new Exception("有序列表为空");
    if (i < 1 || i > sqlList.getLength())
        throw new Exception("删除的索引" + i + "不正确");
    Object res = sqlList.getData()[i - 1];
    if (i < sqlList.getLength()) {
        for (int j = i; j < sqlList.getLength(); j++) {
            sqlList.getData()[j - 1] = sqlList.getData()[j];
        }
    }
    sqlList.setLength(sqlList.getLength() - 1);
    return (T) res;
}
```
#### 链式存储
线性表的链式存储结构的特点是用一组任意的存储单元存储线性表的数据元素，这组存储单元可以连续也可以不连续  
**创建单链表**    
单链表的每个结点由两部分组成：数据域和指针域。数据域存储着当前的数据元素信息；指针域存储着下一个节点的位置信息  
一般单链表有一个头结点，头结点的数据域可以不存放任何信息，其指针域存放着链表第一个元素的位置信息
```
public class Node<T> {
    private T date;
    private Node<T> next;

    public Node() {
    }

    public Node(T date, Node<T> next) {
        this.date = date;
        this.next = next;
    }
...
}
```
**单链表的读取**  
单链表读取元素都要从头结点开始查找  
思路  
* 声明一个临时结点temp指向单链表的第一个结点
* 移动temp依次指向下一个结点，直到移动i-1次（因为第一步已经移动了一次）
* 如果移动过程中temp为空，则说明位置i不存在
* 否则查找成功，返回temp指向结点的数据域中的值

```
public T getElem(int i) throws Exception {
    if (this.getNext() == null)
        throw new Exception("单链表为空");
    Node<T> temp = this.getNext();
    while (temp != null && --i > 0) {
        temp = temp.getNext();
    }
    if (temp == null)
        throw new Exception("第【" + i + "】个元素不存在");
    return temp.getDate();
}
```  
**单链表的插入**  
思路  
* 声明一个临时结点temp指向单链表的头结点（不是第一个结点）
* 定义变量j赋值为1，依次让j增，同时temp依次指向下一个结点
* 如果temp为null，说明插入位置不合理
* 如果j等于i（插入的位置），则执行插入操作，此时temp指向的是插入位置的前一个元素
```
public boolean listInsert(int i, T ele) throws Exception {
    Node<T> temp = this;
    int j = 1;
    while (temp != null && j != i) {
        temp = temp.getNext();
        j++;
    }
    if (temp == null)
        throw new Exception("插入的节点序号【" + i + "】不正确");
    //新节点
    Node<T> s = new Node<>(ele, null);
    s.setNext(temp.getNext());
    temp.setNext(s);
    return true;
}
```
**单链表的删除**  
思路
* 声明一个临时结点temp指向单链表的头结点（不是第一个结点）
* 定义变量j赋值为0，依次让j增，同时temp依次指向下一个结点。直到j==i-1或者temp为null
* 如果temp为null，则说明删除的位置不正确
* 否则执行删除操作，最终temp指向的是删除节点的前一个节点
```
public T listDelete(int i) throws Exception {
    Node<T> temp = this;
    int j = 0;
    while (temp != null && j != i - 1) {
        temp = temp.getNext();
        j++;
    }
    //最终temp是指向删除节点的前一个节点
    if (temp == null || temp.getNext() == null)
        throw new Exception("删除的节点序号【" + i + "】不正确");
    Node<T> delNode = temp.getNext();
    temp.setNext(temp.getNext().getNext());
    return delNode.getDate();
}
```
单链表与顺序表的对比  
1. 存储方式
	1. 顺序存储结构用一段连续的存储单元依次存储线性表的数据元素
	2.  单链表采用链式存储结构，用一组任意的存储单元存放线性表的元素
2. 时间性能
	1. 查找
		1. 顺序结构O(1)
		2. 单链表O(n)
	2. 插入和删除
		1. 顺序存储结构需要平均移动表长一半的元素，时间为O(n)
		2. 单链表在线找出某位置的指针后，插入和删除仅为O(1)
3. 空间性能
	1. 顺序存储结构需要预分配存储空间大小；分大了，浪费；分小了容易溢出
	2. 单链表不需要分配存储空间，只要有就可以分配，元素个数也不受限制

**练习**
1. 删除单链表的倒数第k个节点
2. 反转单链表
3. 合并两个有序链表