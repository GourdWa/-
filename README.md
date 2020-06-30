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
### 第四章
----
#### 栈
仅限定在表尾进行插入和删除操作的线性表  
允许插入和删除的一端称为栈顶，另一端称为栈底。栈又称为后进先出的线性表  
**入栈和出栈思想**  
1. 定义一个变量top指向栈顶元素（栈为空时，top=-1，栈满时top=maxsize-1）
```
public class SqStack<T> {
    private int maxsize;
    private T[] data;
    private int top;

    public SqStack(Class<T> type, int maxsize) {
        this.maxsize = maxsize;
        this.data = (T[]) Array.newInstance(type,maxsize);
        this.top = -1;
    }
...
}
```
2. 入栈时，首先判断栈是否已满，如果未满top自增
```
public boolean push(T ele) throws Exception {
    if (this.top == this.maxsize - 1) {
        throw new Exception("栈已满");
    }
    this.data[++this.top] = ele;
    return true;
}
```
3. 出栈时，首先判断栈是否为空，如果非空top自减
```
public T pop() throws Exception {
    if (this.top == -1) {
        throw new Exception("栈为空");
    }
    return this.data[top--];
}
```
#### 两栈共享空间
**思想**：数组有两个端点，两个栈有两个栈底，让一个栈的栈底在数组的始端，即下标为0处，另一个栈为栈的末端，即数组长度n-1处。用top1代表第一个栈的栈顶位置，top2代表第二个栈的栈顶位置，当top1+1=top2时为栈满。注意，在初始化时，top1初始值为-1，top2的初始值为maxsize
**两栈共享空间实现**
1. 定义两个变量top1，指向第一个栈的栈顶，初始为-1；top2，指向第二个栈的栈顶，初始为maxsize
```
public class SqDoubleStack<T> {
    private int maxsize;
    private T[] data;
    private int top1;
    private int top2;
    private Class<T> type;

    public SqDoubleStack(Class<T> type, int maxsize) {
        this.type = type;
        this.maxsize = maxsize;
        data = (T[]) Array.newInstance(type, maxsize);
        top1 = -1;
        top2 = maxsize;
    }
...
}
```
2. 入栈时，先考察栈是否满（top1+1=top2），如果未满，栈1入栈时top1自加，栈2入栈时top2自减；
```
public boolean push(T ele, int stackNum) throws Exception {
    if (top1 + 1 == top2) {
        throw new Exception("栈空间已满");
    }
    if (stackNum != 1 && stackNum != 2) {
        throw new Exception("栈序号错误，只能是1或2");
    }
    if (stackNum == 1) {
        data[++top1] = ele;
    } else {
        data[--top2] = ele;
    }
    return true;
}
```
3. 出栈时，判断栈是否为空，栈1为空时top1=-1；栈2为空时top2=maxsize
```
public T pop(int stackNum) throws Exception {
    if (stackNum != 1 && stackNum != 2) {
        throw new Exception("栈序号错误，只能是1或2");
    }

    if (stackNum == 1) {
        if (top1 == -1) {
            throw new Exception("栈1为空");
        }
        return data[top1--];
    } else {
        if (top2 == maxsize) {
            throw new Exception("栈2为空");
        }
        return data[top2++];
    }
}
```

**练习**
1. 逆波兰表达式求解
2. 简易计算机（LeetCode）

#### 循环队列
队列是只允许在一端进行插入操作，而在另一端进行删除操作的线性表。队列是一种先进先出的线性表  
循环队列引入了两个指针，front指向对头元素，rear指向对尾元素的下一个位置（注意不是最后一个元素，是最后一个元素的以一个位置）。当front=rear时，循环队列为空；当 **(rear+1)%maxsize=front时**，队列满。队列长度的计算公式是：**(rear-front+maxsize)%maxsize**  
**实现**  
1. 定义循环队列，初始化front和rear为0
```
public class SqQueue<T> {
    private T[] data;
    private int front;
    private int rear;
    private int maxsize;

    public SqQueue(Class<T> type, int maxsize) {
        this.front = 0;
        this.rear = 0;
        data = (T[]) Array.newInstance(type, maxsize);
        this.maxsize = maxsize;
    }
...
}
```
2. 入队时，先判断队列是否已满（**(rear+1)%maxsize=front**），未满则入队，同时rear自加
```
public boolean enQueue(T ele) throws Exception {
    if ((rear + 1) % maxsize == front) {
        throw new Exception("队列已满");
    }
    data[rear] = ele;
    rear = (rear + 1) % maxsize;
    return true;
}
```
3. 出队时，先判断队列是否为空（front=rear），不为空时则出对，同时front自加
```
public T deQueue() throws Exception {
    if (rear == front) {
        throw new Exception("队列为空");
    }
    T res = data[front];
    front = (front + 1) % maxsize;
    return res;
}
```
### 第五章

---

**KMP算法**

给定主串S和待匹配的串P，找到S中P第一次出现的位置，如果不存在则返回-1；存在则返回S中相应的索引

暴力略

**KMP算法思想**

i表示S中的位置，j表示P中的位置。KMP算法相对于暴力求解的优势是不会回溯i，例如S='abcabcx',P='abcd'。如果使用暴力，当检查到S中第3位a与P中第3位d不相等的时候，i会回溯到1从新开始匹配；而KMP算法的i不会回溯，只会回溯j，也就是直接将j回溯到0，再与S中的第3位开始比较，避免了两次回溯

**KMP算法的关键是寻找next数组，next数组是由P串确定的，next[j]的值代表的是当P串中的第j位出现与S串中的第i为不等时，j应该回溯带next[j]位置**

next数组的确定

>P="abcabx"，如果P的第0位与S串的第i位不相等时，此时因为P已经在最左边了，应该移动S串，因此可以令next[0]=-1；如果P串的第1位与S串的第i位不等时，P只能回溯到第0位，因此next[1]=0
>
>确定next数组的第k位，需要看P串的第0到k-1位首位相等的数目
>
>例如next[2]，则P中0到1为ab，此时首位不相等，则next[2]=0;next[3],对应的是abc，也不相等，所以next[3]=0；next[4]，对应abca，可知首尾存在a一个相等，因此next[4]=1；next[5]，对应abcab，首尾的ab相等，因此next[5]=2；
>
>因此P="abcabx"，next={-1,0,0,0,1,2};
>
>同理P=“ababaaaba”，next={-1,0,0,1,2,3,1,1,2}

求解next数组的函数

```java
public int[] getNext(String ps) {
    int[] next = new int[ps.length()];
    next[0] = -1;
    int i = 0;
    int j = -1;
    while (i < ps.length() - 1) {
        if (j == -1 || ps.charAt(i) == ps.charAt(j)) {
            j += 1;
            i += 1;
            next[i] = j;
        } else {
            j = next[j];
        }
    }
    return next;
}
```

匹配函数

```java
public int strStr(String haystack, String needle) {
    if (needle == null || needle.length() == 0)
        return 0;

    char[] t = haystack.toCharArray();
    char[] p = needle.toCharArray();
    int i = 0; // 主串的位置
    int j = 0; // 模式串的位置
    int[] next = getNext(needle);
    while (i < t.length && j < p.length) {
        if (j == -1 || t[i] == p[j]) { // 当j为-1时，要移动的是i，当然j也要归0
            i++;
            j++;
        } else {
            j = next[j]; // j回到指定位置
        }
    }
    if (j == p.length) {
        return i - j;
    } else {
        return -1;
    }
}
```





### 第六章

----
#### 二叉树
* **斜树**：所有节点都只有左子树的二叉树叫左斜树；所有节点都只有右子树的二叉树叫右斜树
* **满二叉树**：在一颗二叉树中，所有分支节点都存在左子树和右子树，且所有叶子节点都在同一层上，这样的二叉树叫做满二叉树
* **完全二叉树**：对一棵具有n个节点的二叉树按层序编号，如果编号i的节点与同样深度的满二叉树中编号为i的节点在二叉树中位置完全相同，则这棵二叉树称为完全二叉树 
### 二叉树的三种遍历方式（难点在于非递归遍历）
#### 二叉树的先序遍历递归和非递归实现
```java
/**
 * 二叉树递归先序遍历
 *
 * @param tree
 * @param list 前序遍历的结果
 * @return
 */
public static void preOrderTraverseByRecursion(BiTTree tree, List<Integer> list) {
    if (tree != null) {
        list.add(tree.getData());
        preOrderTraverseByRecursion(tree.getLchild(), list);
        preOrderTraverseByRecursion(tree.getRchild(), list);
    }
}

/**
 * 二叉树的非递归先序遍历
 *
 * @param tree
 * @return 先序遍历的结果
 */
public static List<Integer> preOrderTraverse(BiTTree tree) {
    Deque<BiTTree> stack = new LinkedList<>();
    List<Integer> list = new ArrayList<>();
    BiTTree tNode = tree;
    while (tNode != null || !stack.isEmpty()) {
        while (tNode != null) {
            list.add(tNode.getData());
            stack.push(tNode);
            tNode = tNode.getLchild();
        }
        BiTTree popTree = stack.pop();
        tNode = popTree.getRchild();
    }
    return list;
}
```
#### 二叉树的中序遍历递归和非递归实现
```
/**
 * 二叉树递归中序遍历
 *
 * @param tree
 * @param list
 */
public static void inOrderTraverseByRecursion(BiTTree tree, List<Integer> list) {
    if (tree != null) {
        inOrderTraverseByRecursion(tree.getLchild(), list);
        list.add(tree.getData());
        inOrderTraverseByRecursion(tree.getRchild(), list);
    }
}

/**
 * 二叉树的非递归中序遍历
 *
 * @param tree
 * @return
 */
public static List<Integer> inOrderTraverse(BiTTree tree) {
    List<Integer> list = new ArrayList<>();
    Deque<BiTTree> stack = new LinkedList<>();
    BiTTree tNode = tree;
    while (tNode != null || !stack.isEmpty()) {
        while (tNode != null) {
            stack.push(tNode);
            tNode = tNode.getLchild();
        }
        BiTTree popTree = stack.pop();
        list.add(popTree.getData());
        tNode = popTree.getRchild();
    }
    return list;
}
```
#### 二叉树的后序遍历递归和非递归实现
```
/**
 * 二叉树递归后序遍历
 *
 * @param tree
 * @param list
 */
public static void postOrderTraverseByRecursion(BiTTree tree, List<Integer> list) {
    if (tree != null) {
        postOrderTraverseByRecursion(tree.getLchild(), list);
        postOrderTraverseByRecursion(tree.getRchild(), list);
        list.add(tree.getData());
    }
}

/**
 * 二叉树的非递归后序遍历
 *
 * @param tree
 * @return
 */
public static List<Integer> postOrderTraverse(BiTTree tree) {
    List<Integer> list = new ArrayList<>();
    Deque<BiTTree> stack = new LinkedList<>();
    BiTTree curNode = null;
    BiTTree preNode = null;
    stack.add(tree);
    while (!stack.isEmpty()) {
        curNode = stack.peek();
        if ((curNode.getRchild() == null && curNode.getLchild() == null) ||
                (preNode != null && (preNode == curNode.getLchild() || preNode == curNode.getRchild()))) {
            list.add(curNode.getData());
            stack.pop();
            preNode = curNode;
        } else {
            if (curNode.getRchild() != null)
                stack.push(curNode.getRchild());
            if (curNode.getLchild() != null)
                stack.push(curNode.getLchild());
        }
    }
    return list;
}
```
#### 二叉树的建立
基于**先序遍历的扩展二叉树**使用递归建立二叉树，传递先序的扩展二叉树数组，使用递归的方式建立二叉树  
扩展二叉树可以唯一的确定一个二叉树；先序遍历和中序遍历也能唯一确定一棵二叉树；中序遍历和后序遍历也能唯一确定一棵二叉树；但是先序遍历和后续遍历不能唯一确定一棵二叉树  
> 说明  
> 数组中的-1代表扩展出来的节点（也就是这个节点本身并不存在）  
> 例如一棵二叉树的先序遍历是：1 2 4 3 5；中序遍历是：2 4 1 5 3；那么这棵二叉树的扩展二叉树按先序遍历的结果是：1 2 -1 4 -1 -1 3 5 -1 -1 -1
```
public static BiTTree createBitTree(BiTTree node, int[] nums) {
    if (i < nums.length) {
        i += 1;
        if (nums[i] != -1) {
            node = new BiTTree();
            node.setData(nums[i]);
            node.setLchild(createBitTree(node.getLchild(), nums));
            node.setRchild(createBitTree(node.getRchild(), nums));
        } else {
            node = null;
        }

    }
    return node;
}
```
### 线索二叉树
如果一个节点的左子树为null，则将其左子树指向其前驱节点；如果一个节点的右子树为null，则将其右子树指向其后继节点。例如二叉树中序遍历：2 4 1 5 3（先序遍历1 2 4 3 5）。对于中序遍历线索二叉树，4节点的前驱节点是2，5节点的后继节点是3,5节点的前驱节点是1  
需要值得注意的是，线索化二叉树后，怎么知道一个节点的左节点是其前驱节点还是其左子树（右节点是后继节点还是其右子树）。为了解决这个问题，在线索化二叉树时，引入两个布尔量：lType,rType。默认都为false，代表其左右节点都是其子树，当设置为true时，代表其节点存储了响应的前驱或后继节点。  
通过上面的线索化二叉树可以充分利用二叉树的空间，同时，有利于二叉树的访问    
**中序线索化二叉树**  
```
/**
 * 线索化二叉树
 *
 * @param node
 */
public static void threadNodes(ThreadBiTree node) {
    if (node != null) {
        threadNodes(node.getLchild());
        //先处理当前节点的前驱节点（左指针）
        if (node.getLchild() == null) {
            //当前节点的左指针指向前驱节点
            node.setLchild(pre);
            node.setlType(true);
        }
        if (pre != null && pre.getRchild() == null) {
            //后继节点
            pre.setRchild(node);
            pre.setrType(true);
        }
        pre = node;
        threadNodes(node.getRchild());
    }
}
```
**中序线索化二叉树的遍历**  
```
    public static void inOrderTraverseThread(ThreadBiTree node) {
        while (node != null) {
            while (!node.getlType()) {
                node = node.getLchild();
            }
            System.out.print(node.getData() + " ");
            while (node.getrType()) {
				//此时，node节点的右节点其实存储的是该节点的后继节点
                node = node.getRchild();
                System.out.print(node.getData() + " ");
            }
			//当退出循环时，说明node节点的右子树存在
            node = node.getRchild();
        }
    }
```
**练习**
1. 利用递归求解二叉树的深度，DFS 
2. 层级遍历二叉树，主要使用BFS
3. 结合求二叉树的深度，判断一棵二叉树是否是平衡二叉树

### 第七章
---
### 定义一个图类
```
public class MGraph {
    //顶点数
    private int numVertexes;
    //边数
    private int numEdges;
    //顶点表
    private String[] vexs;
    //邻接矩阵
    private int[][] arc;
	...
}
```
#### 图的遍历
**深度优先遍历（DFS）**  
思想：从图中某个顶点v出发，访问此顶点，然后从v的未被访问的邻接点出发深度优先遍历图，直到图中所有和v有路径相同的顶点都被访问到
```
public void dfsTraverse(MGraph graph) {
    //visited数组用来记录那些点被访问过了
    // 如果点i被访问了，那么visited[i]为true，否则为false
    visited = new boolean[graph.getNumVertexes()];
    //graph.getNumVertexes()获得图的顶点数目
	//如果是连通图，可以不用加这个for循环
    for (int i = 0; i < graph.getNumVertexes(); i++) {
        if (!visited[i]) {
            dfs(graph, i);
        }
    }
}

private void dfs(MGraph graph, int i) {
    visited[i] = true;
    System.out.println(graph.getVexs()[i]);//输出顶点，也可以做其他操作
    //graph.getArc()[i][j] ，邻接矩阵i点到j点是否相通
    for (int j = 0; j < graph.getNumVertexes(); j++) {
        if (graph.getArc()[i][j] == 1 && !visited[j]) {
            dfs(graph, j);
        }
    }
}
```
**广度优先遍历（BFS）**  
思想：BFS算法一般都需要借助一个队列，当访问节点v时，将与节点v相连且未被访问过的节点加入队列（例如节点u），同时节点v出列；访问节点u，并将与节点u相连且未被访问的节点加入队列，u出列，直到所有节点都被访问
```
public void bfsTraverse(MGraph graph) {
    visited = new boolean[graph.getNumVertexes()];
    Queue<Integer> queue = new LinkedList<>();
	//如果是连通图，可以不用加这个for循环
    for (int i = 0; i < graph.getNumVertexes(); i++) {
        if (!visited[i]) {
            visited[i] = true;
            System.out.println(graph.getVexs()[i]);
            queue.add(i);
            while (!queue.isEmpty()) {
                Integer pollEle = queue.poll();
                for (int j = 0; j < graph.getNumVertexes(); j++) {
                    //将与
                    if (graph.getArc()[pollEle][j] == 1 && !visited[j]) {
		    	visited[j]=true;
                        System.out.println(graph.getVexs()[j]);
                        queue.add(j);
                    }
                }
            }
        }
    }
}
```
#### 最小生成树
在最小生成树中用的图用邻接矩阵表示如下
```
private static final int INFINITY = Integer.MAX_VALUE;
...
int[][] graph = {
            {0, 10, INFINITY, INFINITY, INFINITY, 11, INFINITY, INFINITY, INFINITY},
            {10, 0, 18, INFINITY, INFINITY, INFINITY, 16, INFINITY, 12},
            {INFINITY, INFINITY, 0, 22, INFINITY, INFINITY, INFINITY, INFINITY, 8},
            {INFINITY, INFINITY, 22, 0, 20, INFINITY, INFINITY, 16, 21},
            {INFINITY, INFINITY, INFINITY, 20, 0, 26, INFINITY, 7, INFINITY},
            {11, INFINITY, INFINITY, INFINITY, 26, 0, 17, INFINITY, INFINITY},
            {INFINITY, 16, INFINITY, INFINITY, INFINITY, 17, 0, 19, INFINITY},
            {INFINITY, INFINITY, INFINITY, 16, 7, INFINITY, 19, 0, INFINITY},
            {INFINITY, 12, 8, 21, INFINITY, INFINITY, INFINITY, INFINITY, 0}
    };
```
**普里姆算法（Prim）**  
MST（Minimum Spanning Tree，最小生成树）问题有两种通用的解法，Prim算法就是其中之一，它是从点的方面考虑构建一颗MST  
*思想*：设图G顶点集合为U，首先任意选择图G中的一点作为起始点a，将该点加入集合V，再从集合U-V中找到另一点b使得点b到V中任意一点的权值最小，此时将b点也加入集合V；以此类推，现在的集合V={a，b}，再从集合U-V中找到另一点c使得点c到V中任意一点的权值最小，此时将c点加入集合V，直至所有顶点全部被加入V，此时就构建出了一颗MST。因为有N个顶点，所以该MST就有N-1条边，每一次向集合V中加入一个点，就意味着找到一条MST的边。  
[Prime算法详细构建最小生成树的步骤](https://blog.csdn.net/yeruby/article/details/38615045)
```
public static void prim(int[][] graph, int[] lowcost, int[] adjvex) {
    int len = lowcost.length;
    for (int i = 1; i < len; i++) {
        int min = Integer.MAX_VALUE;
        int j = 1;
        int k = 0;
        while (j < len) {
            //如果节点j没有加入生成树
            if (lowcost[j] != 0 && min > lowcost[j]) {
                k = j;
                min = lowcost[j];
            }
            j++;
        }
        System.out.println(adjvex[k] + "==>" + k);
        lowcost[k] = 0;
        //将k节点加入生成树之后，更新距离
        for (j = 1; j < len; j++) {
            if (lowcost[j] != 0 && graph[k][j] < lowcost[j]) {
                lowcost[j] = graph[k][j];
                //代表从k到j的生成树，例如adjvex[3]=7,代表从节点7生成到节点3
                adjvex[j] = k;
            }
        }
    }
}
```
其中变量lowcost和adjvex的作用和初始化如下，这里假设都是从节点0开始建立最小生成树  
lowcost[i]=0时，代表顶点i已经加入了最小生成树  
adjvex[i]=j,代表从顶点j生成到顶点i，例如adjvex[3]=7,代表从节点7生成到节点3  
```
int[] adjvex = new int[graph.length];
int[] lowcost = new int[graph.length];
for (int i = 0; i < graph.length; i++) {
    lowcost[i] = graph[0][i];
}
```
**克鲁斯卡尔算法(kruskal)**
*思想*：克鲁斯卡尔算法是最小生成树的另外一种常用算法。其思想是在所有未选取的边中，找最小边，如果和已选取的边构成回路，则放弃，选取次小边
1. 将图的所有连接线去掉，只剩顶点
2. 从图的边集数组中找到权值最小的边，将边的两个顶点连接起来
3. 继续寻找权值最小的边，将两个顶点之间连接起来，如果选择的边使得最小生成树出现了环路，则放弃该边，选择权值次小的边
4. 直到所有的顶点都被连接在一起并且没有环路，最小生成树就生成了  

[克鲁斯卡尔算法步骤细节](https://blog.csdn.net/junya_zhang/article/details/83584592)  
因为Kruskal算法是以边为对象建立最小生成树，因此，需要构建一个**Edge类**

```
public class Edge {
	//边的起始顶点
    private int begin;
	//边的结束顶点
    private int end;
	//边的权值
    private int weight;
	...
}
```
当有了Edge类之后，由于Kruskal算法是每次从未选择的边中选择一个最小的边（如果没有构成环）来构建生成树，因此，在实现算法前将图的邻接矩阵转换为Edge数组，并按从小到大的顺序排序
```
List<Edge> edges = new ArrayList<>();
for (int i = 0; i < graph.length; i++) {
    for (int j = i + 1; j < graph[i].length; j++) {
		//如果graph[i][j]存在，则说明顶点i到顶点j构成一条边，起始顶点是i，结束顶点是j，且权值是graph[i][j]
        if (graph[i][j] != INFINITY) {
            edges.add(new Edge(i, j, graph[i][j]));
        }
    }
}
//排序
edges.sort(Comparator.comparingInt(Edge::getWeight));
int[] parent = new int[graph.length];
MGraphUtils.kruskal(edges,parent);
```
**parent参数说明**  
parent[i]=j，当j=0时，说明从顶点i出发没有能到达的点（但是不能说明没有到达顶点i的点）；如果j≠0，例如parent[4]=7，说明从顶点4出发能到达顶点7

```
public static void kruskal(List<Edge> edges, int[] parent) {
    for (int i = 0; i < edges.size(); i++) {
        int n = find(parent, edges.get(i).getBegin());//从该边的起点开始寻找parent数组中为0的元素的下标，
        int m = find(parent, edges.get(i).getEnd());//从该边的终点点开始寻找parent数组中为0的元素的下标
        if (n != m) {//如果m=n说明如果连接这条边，则从该边的起点和终点最终都会到达同一顶点，即有环
            parent[n] = m;
            System.out.println(edges.get(i).getBegin() + "==>" + edges.get(i).getEnd() + "  " + edges.get(i).getWeight());
        }
    }
}

private static int find(int[] parent, int i) {
    //说明i节点已经在生成树里了
    while (parent[i] > 0)
        i = parent[i];
    return i;
}
```
#### 最短路径问题
**迪杰斯特拉算法（Dijkstra）**   
**实现思想**
1. 通过Dijkstra计算图G中的最短路径时，需要指定起点s(即从顶点s开始计算)。
2. 此外，引进两个集合S和U。S的作用是记录已求出最短路径的顶点(以及相应的最短路径长度)，而U则是记录还未求出最短路径的顶点(以及该顶点到起点s的距离)。
3. 初始时，S中只有起点s；U中是除s之外的顶点，并且U中顶点的路径是”起点s到该顶点的路径”。然后，从U中找出路径最短的顶点，并将其加入到S中；接着，更新U中的顶点和顶点对应的路径。 然后，再从U中找出路径最短的顶点，并将其加入到S中；接着，更新U中的顶点和顶点对应的路径。 … 重复该操作，直到遍历完所有顶点。

两个集合可以用一个布尔型的数组来代替，数组的长度与节点数目一样，当第i个顶点在U集合中时对应的数组第i为false；否则为true  
[通俗易懂的Dijkstra算法原理](https://zhuanlan.zhihu.com/p/40338107)  
实现代码如下，关键需要理解的是distance数组和visited数组。其中distance数组代表的是每个顶点到起始点的距离，例如distance[k]为第k个顶点到起始点的距离；而visited数组代表的就是集合U和集合S中的顶点。只要理解了这两点整个算法是很简单的
```
private static int[] dijksra(int[][] graph, int start) {
    //distance数组中存储了从起始顶点出发到每个顶点的距离
    int[] distance = new int[graph.length];
    //当到达i顶点时，visited[i]设置为true
    boolean[] visited = new boolean[graph.length];
    visited[start] = true;
    int[] trace = new int[graph.length];
    for (int i = 0; i < distance.length; i++) {
        distance[i] = graph[start][i];
    }

    int cnt = 0;
    while (cnt < distance.length - 1) {
        int k = 0;
        int min = Integer.MAX_VALUE;
        //找到当前未到达的节点中距离最近的节点
        for (int i = 0; i < distance.length; i++) {
            if (!visited[i] && distance[i] < min) {
                k = i;
                min = distance[i];
            }
        }
        cnt++;
        visited[k] = true;
        for (int i = 0; i < distance.length; i++) {
            //如果顶点i未被访问过，从k顶点能够到达i顶点
            // 并且从起始点到顶点i的距离大于懂起始点经顶点k再到i的距离，更新最短距离
            if (!visited[i] && graph[k][i] != Integer.MAX_VALUE && distance[i] > distance[k] + graph[k][i]) {
                trace[i] = k;
                distance[i] = distance[k] + graph[k][i];
            }
        }
    }

    System.out.println(Arrays.toString(trace));
    return distance;
}
```
**弗洛伊德算法（Floyd）**  
![Floyd图1](https://img-blog.csdn.net/20130501182147975)
![Floyd公式](https://img-blog.csdn.net/20130501183035414)
![Floyd图2](https://img-blog.csdn.net/20130501183719888)  
看图不说话，重要的思想就是引入中转节点，重点实现推导是上面的公式，还不理解就看书，简洁明了
代码，其中distance变量的初始值等价于graph邻接矩阵，trace的值如下初始化，核心思想就是引入一个中间节点i，当求节点j到k的距离时，先判断j到i的距离加i到k的距离和j到k的距离的大小（也就是上面的公式）。那个小就用谁更新节点

```
private static void floyd(int[][] distance, int[][] trace) {
    for (int i = 0; i < distance.length; i++) {
        for (int j = 0; j < distance.length; j++) {
            trace[i][j] = j;
        }
    }

    /**
     * 中转节点为i；起点为j；终点为k
     */
    for (int i = 0; i < distance.length; i++) {
        for (int j = 0; j < distance.length; j++) {
            for (int k = 0; k < distance.length; k++) {
                if (distance[j][i] != Integer.MAX_VALUE && distance[i][k] != Integer.MAX_VALUE &&
                        distance[j][k] > distance[j][i] + distance[i][k]) {
                    distance[j][k] = distance[j][i] + distance[i][k];
                    trace[j][k] = trace[j][i];
                }
            }
        }
    }
}
```
#### 拓扑排序
在一个有向图中，对所有的节点进行排序，要求没有一个节点指向它前面的节点,先统计所有节点的入度，对于入度为0的节点就可以分离出来，然后把这个节点指向的节点的入度减一,一直做改操作，直到所有的节点都被分离出来。如果最后不存在入度为0的节点，那就说明有环，不存在拓扑排序，也就是很多题目的无解的情况。    

![拓扑排序过程](https://img-blog.csdn.net/20180625175824103)  
典型例题，LeetCode207课程表问题，分为BFS解决和DFS两种思路，但是不管哪种思路都需要使用到邻接链表的思路，也就是找到每个顶点的入度节点，例如上图中e的入度节点是c,f,d  
在拓扑排序中，其问题的本质可以归结为是否有环，如果图有环，那么是不可能完成拓扑排序的

```
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
```

## 第八章 查找
---
### 顺序查找
顺序查找是最基本的查找方法，俗称暴力查找，也就是从第一个元素开始逐个与待查找的元素进行匹配。若匹配成功则说明查找成功，如果查到最后一个元素都没有匹配成功即说明表中没有查找的元素，时间复杂度是O(n)  
### 二分查找
二分查找也叫折半查找。这种查找实现的前提是线性表是有序的。每次用表中的中间元素与待查元素比较，如果待查元素大于中间元素，则继续在右半区间继续查找，否则在左半区间进行查找，直到找到待查元素。时间复杂度是O(logn)
```
public int binarySearch(int[] nums, int target) {
    int start = 0;
    int end = nums.length - 1;
    int mid = 0;
    while(end >= start)
    {
      mid = start + (end - start) / 2;
        if(nums[mid] > target)
            end = mid - 1;
        else if(nums[mid] < target)
            start = mid + 1;
        else
            return mid;
    }
    return -1;
}
```
### 插值查找
二分查找的关键迭代代码是
> mid = start + (end - start)/2  


插值查找的关键迭代代码是
> mid = start + (end - start)*(target - nums[start])/(nums[end] - nums[start])

通过对比可以发现，实质就是将二分查找中的系数1/2用(target - nums[start])/(nums[end] - nums[start])代替了  
从时间复杂度来看，二分查找和插值查找的时间复杂度都是O(logn)。但是对于表长较大，且数据分布均匀的查找表来说，插值查找的平均性能要比二分查找好  


### 二叉排序树
二叉排序树又称二叉查找树，它要么是一棵空树，或者是具有以下性质的二叉树
* 若它的左子树不为空，则左子树上所有节点的值均小于它的根节点的值
* 若它的右子树不为空，则右子树上所有节点的值均大于它的根节点的值
* 二叉排序树的左、右子树也分别为二叉排序树

**二叉排序树的查找操作**
```
public class SearchBST {
    //p始终指向查找节点的父节点
    private static BitNode p = null;

    /**
     * 返回要查找的节点，如果存在则返回，不存在则返回null
     * @param node
     * @param val
     * @return
     */
    public static BitNode searchBst(BitNode node, int val) {
        if (node == null)
            return null;
        if (node.getData() == val)
            return node;
        if (node.getData() > val) {
            p = node;
            return searchBst(node.getLeft(), val);
        } else {
            p = node;
            return searchBst(node.getRight(), val);
        }
    }
	...
}
```
比较简单的递归查找，值得注意的是在这里我引进了一个节点p，这个节点在树不为空时，始终指向带查找节点的父节点。p节点在插入和删除节点时有大用处  
**二叉排序树的插入操作**  
思路  
1. 先在二叉排序树中查找要插入的元素是否已经存在，如果存在则插入失败
2. 如果待插入的元素在二叉排序树中不存在，则将待插入的元素插入到二叉排序树的叶节点

```
public static boolean insertBST(BitNode root, int val) {
    //如果原有的二叉搜索树中不存在val值节点
    BitNode bitNode = searchBst(root, val);
    if (searchBst(root, val) == null) {
        //如果p等于null说明原本传入的二叉树就是空树
        if (p == null)
            p = new BitNode(val);
        else if (p.getData() > val)
            p.setLeft(new BitNode(val));
        else
            p.setRight(new BitNode(val));
        return true;
    }
    return false;
}
```
可以断言，如果带插入的元素在二叉排序树中不存在，p节点至多有一个孩子节点  
**二叉排序树的删除操作**  
思路
1. 要删除二叉排序树中的某一个节点的前提是该节点存在，因此第一步是查找到要删除的节点
2. 当查找到要删除的节点的位置时，需要判断该节点是叶节点还是只有一个孩子或者还是只有两个孩子
	1. 如果待删除的节点是叶节点，那么删除这个节点对整个二叉排序树的结构并无影响，因此可以直接删除
	2. 如果待删除的节点只有一个左孩子或者右孩子，当删除这个节点时只需要用这个节点的孩子节点覆盖要删除的节点即可
	3. 如果待删除的节点既有左孩子又有右孩子，此时需要用待删除节点的前驱节点（中序遍历）或后继节点（中序遍历）来覆盖待删除的节点，之后再删除该节点的前驱节点或者后继节点（使用那个节点覆盖就删除那个节点）

代码中的p节点依然是指向将要删除节点的父节点，这里始终假设待删除的二叉排序树不为空

```
public static boolean deleteBST(BitNode node, int val) {
    if (node == null)
        return false;
    if (node.getData() == val) {
        return delete(node);
    } else if (val < node.getData()) {
        p = node;
        return deleteBST(node.getLeft(), val);
    } else {
        p = node;
        return deleteBST(node.getRight(), val);
    }
}

private static boolean delete(BitNode delNode) {
    if (delNode.getLeft() == null) {
        //如果待删除的节点的左节点为空
        if (p == null) {
            //如果删除的是根节点
            delNode.setData(delNode.getRight().getData());
            delNode.setLeft(delNode.getRight().getLeft());
            delNode.setRight(delNode.getRight().getRight());
        } else if (p.getRight() == delNode) {
            p.setRight(delNode.getRight());
        } else {
            p.setLeft(delNode.getRight());
        }
    } else if (delNode.getRight() == null) {
        //如果待删除的节点的右节点为空
        if (p == null) {
            delNode.setData(delNode.getLeft().getData());
            delNode.setLeft(delNode.getLeft().getLeft());
            delNode.setRight(delNode.getLeft().getRight());
        } else if (p.getRight() == delNode) {
            p.setRight(delNode.getLeft());
        } else {
            p.setLeft(delNode.getLeft());
        }
    } else {
        //如果待删除的节点的左右节点都存在
        BitNode q = delNode;
        BitNode s = delNode.getLeft();
        while (s.getRight() != null) {
            q = s;
            s = s.getRight();
        }
        delNode.setData(s.getData());
        if (q != delNode) {
            q.setRight(s.getLeft());
        } else {
            q.setLeft(s.getLeft());
        }
    }
    return true;
}

```
**求二叉树的高度**  
很简单的实现，看一下
```
public static int getHeight(BitNode node) {
    if (node != null) {
        int l = getHeight(node.getLeft());
        int r = getHeight(node.getRight());
        return Math.max(l, r) + 1;
    } else return 0;
}
```
### 平衡二叉树
平衡二叉树是一种二叉排序树，其中每一个节点的左子树和右子树的高度差至多为1   
**平衡因子的定义**  
二叉树上节点的左子树深度减去右子树深度的值称为平衡因子BF  
平衡二叉树的实现及思想见书本，这里跳过  

### 多路查找树（B树、B+树）
多路查找树，其每一个节点的孩子数可以多余两个，且每一个节点处可以存储多个元素
#### 2-3树
2-3树是特殊的B树，其中的每一个节点都具有两个孩子（称为2节点）或者三个孩子（称为3节点）  
一个2节点包含一个元素和两个孩子（或者没有孩子），且孩子节点左小右大  
一个3节点包含两个元素和三个孩子（或者没有孩子），且孩子节点左小右大
#### 2-3-4树
2-3-4树是2-3树的概念扩展，包括了4个节点的使用。一个4节点包含小中大三个元素和四个孩子（或者没有孩子）
#### B树和B+树
B树是一种平衡的多路查找树，2--3树和2-3-4树都是B树的特例。节点最大的孩子数目称为B树的阶，因此2-3树是3阶B树，2-3-4树是4阶B树    
**m阶B树具有的性质**  
* 如果根节点不是叶节点，则其至少有两颗子树
* 每一个非根的分支节点至少有Ceil(m/2)-1个元素，至多有m-1个元素
* 每一个非根的分支节点至少有Ceil(m/2)个子节点，至多有m个子节点
* 所有叶子节点都位于同一层次

B+树和B树的形式基本一致，不过其性质和实现不同  
**m阶B+树具有的性质**  
* B+树包含2种类型的结点：内部结点（也称索引结点）和叶子结点。根结点本身即可以是内部结点，也可以是叶子结点。根结点的关键字个数最少可以只有1个
* B+树与B树最大的不同是内部结点不保存数据，只用于索引，所有数据（或者说记录）都保存在叶子结点中
* 阶B+树表示了内部结点最多有m-1个关键字（或者说内部结点最多有m个子树），阶数m同时限制了叶子结点最多存储m-1个记录
* 内部结点中的key都按照从小到大的顺序排列，对于内部结点中的一个key，左树中的所有key都小于它，右子树中的key都大于等于它。叶子结点中的记录也按照key的大小排列
* 每个叶子结点都存有相邻叶子结点的指针，叶子结点本身依关键字的大小自小而大顺序链接

**重点掌握B树和B+树的插入和删除思想**  
[图解B树和B+树的插入与删除操作](https://www.cnblogs.com/nullzx/p/8729425.html)

### 散列表查找概述
散列技术是在记录的存储位置和它的关键字之间建立一个确定的对应关系f，使得每个关键字key对应一个存储位置f(key)。查找时，根据这个确定的对应关系找到给定值key的映射f(key)，若查找集合中存在这个记录，则必定在f(key)的位置上  
上面所陈述的f称为散列函数，又称哈希函数。采用散列技术将记录存储在一块连续的存储空间中，这块连续存储空间称为散列表或哈希表
#### 散列函数的构造方法
**直接定址法**  
可以取关键字的某个线性函数值为散列地址
> f(key)=a*key+b

直接定址法的优点就是简单、均匀，也不会产生冲突，但问题是这需要实现知道关键字的分布情况，适合查找表较小且连续的情况  
**数字分析法**  
例如关键字的位数较多时，比如电话号码，那么此时可以考虑用后四位作为散列地址  
**平方取中法**  
例如关键字是1234，那么它的平方就是1522756，再抽去中间3位就是227  
**折叠法**  
折叠法是将关键字从左到右分割成位数相等的几部分（若最后一部分位数不够，最后一部分可以短些），然后将这几部分叠加求和，并按散列表表长取后几位作为散列地址  
**除留余数法**  
这是最常用的构造散列函数方法。对于散列表长为m的散列函数公式为
> f(key)=key mod p

**随机数法**  
如其名，去关键字的随机函数作为它的散列地址
#### 处理散列冲突的方法
**开放地址法**  
所谓开放地址法就是一旦发生了冲突，就去寻找一下个空的散列地址，只要散列表足够大。空的散列地址总能找到并将记录存入
> f(key)=(f(key)+d) mod m

比如表长为12，已结存入了25，f(25)=25%12=1；此时如果再存入37就会造成冲突，于是应用上面的公式f(37)=(f(37)+1)%12=2  
上面这种解决冲突的开放地址法称为线性探测法  
**二次探测法**  
> f(key)=(f(key)+d) mod m  (d=1²，-1²，2²，-2²...，q²，-q²，q≤m/2) 

增加平方运算的目的是为了不让关键字都聚集在某一块区域。还有一种叫**随机探测法**，也就是每次的d都是随机生成的  
下面将利用开放地址法来实现散列表的查找算法  
#### 散列表查找实现
初始化散列表，需要传入散列表的大小，以便初始化。并且将散列表的存储数据全部初始化为Integer.MIN_VALUE
```
public class MyHashTable {
    private int[] elem;
    private int count;

    /**
     * 初始化散列表
     *
     * @param count
     */
    public MyHashTable(int count) {
        this.elem = new int[count];
        this.count = count;
        for (int i = 0; i < count; i++) {
            elem[i] = Integer.MIN_VALUE;
        }
    }
...
}
```
散列函数定义，使用的是除留余数法构建散列函数
```
/**
 * 散列函数，除留余数法
 *
 * @param key
 * @return
 */
public int hash(int key) {
    return key % count;
}
```
向散列表中插入元素，首先需要判断插入的位置是否产生了冲突，如果有冲突则使用线性探测重新进行寻址。除此之外还需要判断散列表是否已满
```
/**
 * 向散列表中插入元素
 *
 * @param key
 */
public void insertHash(int key) {
    int addr = hash(key);
    //说明该地址已经有其他的元素存在
    while (elem[addr] != Integer.MIN_VALUE) {
        addr = hash(addr + 1);
        if (addr == hash(key)) {
            System.out.println("散列表已满...元素" + key + "插入不成功");
            return;
        }
    }
    elem[addr] = key;
    System.out.println("元素" + key + "插入成功");
}
```
查找散列表中是否存在元素，为了陷入死循环，查找的过程也需要判断是否查找完成，当所有的元素都检查之后仍然没有找到，则说明查找失败
```
/**
 * 散列表查找元素
 *
 * @param key
 * @return
 */
public int searchHash(int key) {
    int addr = hash(key);
    while (elem[addr] != key) {
        addr = hash(addr + 1);
        if (elem[addr] == Integer.MIN_VALUE || addr == hash(key)) {
            return -1;
        }
    }
    return addr;
}
```

**散列表的装填因子**  
散列表的装填因子α=填入表中的记录个数/散列表长度。α标志着散列表的装满的程度。当填入表中的记录越多，产生冲突的可能性就越大

## 第九章
---
![八大排序算法比较](https://upload-images.jianshu.io/upload_images/1156494-62f859c2ac6f95ff.png?imageMogr2/auto-orient/strip|imageView2/2/w/630/format/webp)

其中归并排序的辅助空间为O(n)，需要一个临时数组  
**排序算法的稳定性定义**  
能保证两个相等的数，经过排序之后，其在序列的前后位置顺序不变。（A1=A2，排序前A1在A2前面，排序后A1还在A2前面）  
**稳定的排序算法**：冒泡排序，插入排序、归并排序、基数排序  
**不稳定的排序算法**：选择排序、快速排序、希尔排序、堆排序


### 交换排序
交换排序可以分为冒泡排序和快速排序
#### 冒泡排序
简单明了，最容易想到的排序算法
**传统的冒泡排序算法**
```
public static void bubbleSort2(int[] arr) {
    for (int i = 0; i < arr.length; i++) {
        for (int j = 0; j < arr.length - i - 1; j++) {
            if (arr[j] > arr[j + 1]) {
                int temp = arr[j];
                arr[j] = arr[j + 1];
                arr[j + 1] = temp;
            }
        }
    }
}
```
**优化的冒泡排序算法**
```
public static void bubbleSort(int arr[]) {
    boolean didSwap;
    for (int i = 0, len = arr.length; i < len - 1; i++) {
        didSwap = false;
        for (int j = 0; j < len - i - 1; j++) {
            if (arr[j + 1] < arr[j]) {
                int temp = arr[j];
                arr[j] = arr[j + 1];
                arr[j + 1] = temp;
                didSwap = true;
            }
        }
        if (didSwap == false)
            return;
    }
}
```
总结：使用优化后的冒泡排序算法的时间复杂度平均情况是O(n²)；最好的情况是O(n)（也就是此时元素是排好序的）；最坏的情况是O(n²)（逆序）。空间复杂度为O（1）

#### 快速排序
快速排序的关键在于基准值的选择（也就是代码中的t），每次将数组中比基准值小的移动到左边，比基准值大的移动到右边。值得注意的是一定要从右边开始，也就是先找比基准值大的，不然程序不能正确排序，这个bug很容易复现
```
private static void quickSort(int[] arr, int left, int right) {
    if (left > right)
        return;
    int start = left;
    int end = right;
    int t;
    while (left < right) {
        while (left < right && arr[right] >= arr[start])
            right--;
        while (left < right && arr[left] <= arr[start])
            left++;

        if (left < right) {
            t = arr[left];
            arr[left] = arr[right];
            arr[right] = t;
        }
    }
    //说明left等于right
    t = arr[left];
    arr[left] = arr[start];
    arr[start] = t;
    quickSort(arr, start, left - 1);
    quickSort(arr, left + 1, end);

}
```
总结：快速排序的时间复杂度
1.	最好的情况就是每次的基准值都能将数组均分，这种情况下的时间复杂度为O(nlogn)，此时空间复杂度为O(logn)
2.	平均情况也是O(nlogn)
3.	最糟糕的情况是待排序列已经是正序或者逆序，每次每次划分只能将序列分为一个元素与其他元素两部分，这时的快速排序退化为冒泡排序，如果用数画出来，得到的将会是一棵单斜树，也就是说所有所有的节点只有左（右）节点的树，这种情况下时间复杂度为O(n²)；此时空间复杂度为O(n)
4.	快速排序不是一个稳定的排序算法。比如序列为 “5,3(1),4,3(2),8,11,9”， 现在中枢元素5和3(2)交换就会把元素3的稳定性打乱。不稳定发生在中枢元素和a[j]交换的时刻

### 选择排序
选择排序排序分为直接选择排序和堆排序
#### 简单选择排序
正如其名，简单明了
```
private static void selectSort(int[] arr) {
    for (int i = 0; i < arr.length; i++) {
        int min = i;
        for (int j = i + 1; j < arr.length; j++)
            if (arr[min] > arr[j])
                min = j;
        if (i != min) {
            int temp = arr[min];
            arr[min] = arr[i];
            arr[i] = temp;
        }
    }
}
```
总结：快速排序的时间复杂度无论最坏情况还是最好情况都是O(n²)，空间复杂度为O(1)。且选择排序不是一个稳定的排序算法，例如序列“5(1),8,5(2),2,9”，5(1)会和2交换，破坏稳定性。

#### 堆排序
堆排序的关键在于构建堆，堆又分为大顶堆和小顶堆，这里构建的大顶堆（小顶堆的构建是同样的思想）。大顶堆中根元素一定是最大的值，因此根据这个性质可以对一个无序序列进行排序  
**思想**：将待排序的序列构成一个大顶堆。此时，整个序列的最大值就是堆顶的根节点。将根节点与堆数组的末尾元素交换，此时末尾元素就是最大值，然后再讲剩余的n-1个序列重新构成一个堆，这样就会得到n个元素中的次小值。如此往复直到得到一个有序序列
```
private static void heapSort(int[] arr) {
    int len = arr.length;
    int temp;
    for (int i = len / 2 - 1; i >= 0; i--) {
        heapAdjust(arr, i, len);
    }
    //大顶堆
    for (int i = len - 1; i >= 0; i--) {
        temp = arr[0];
        arr[0] = arr[i];
        arr[i] = temp;
        heapAdjust(arr, 0, i);
    }
}

private static void heapAdjust(int[] arr, int n, int len) {
    int temp = arr[n];
    for (int i = 2 * n + 1; i < len; i = 2 * i + 1) {
        //寻找子节点中最大的子节点
        if (i + 1 < len && arr[i + 1] > arr[i])
            i += 1;
        //如果temp大于最大的子节点，则跳出循环
        if (temp > arr[i])
            break;
        arr[n] = arr[i];
        n = i;
    }
    arr[n] = temp;
}
```
总结：堆排序的时间复杂度无论最好最坏都是O(nlogn)，空间复杂度是O(1)。且堆排序是不稳定的排序算法，例如序列50,90（1）,40,30,60,90（2）,80.在构建大顶堆时90（1）会排在序列的最后


### 插入排序
插入排序可以分为直接插入排序和希尔排序
#### 直接插入排序
直接插入排序的基本操作是将一个记录插入到排好序的有序表中，从而得到一个新的、记录数增1的有序表
```
private static void insertionSort(int[] arr) {
    for (int i = 1; i < arr.length; i++) {
        if (arr[i] < arr[i - 1]) {
            int temp = arr[i];
            int j = 0;
            for (j = i - 1; j >= 0 && arr[j] > temp; j--) {
                arr[j + 1] = arr[j];
            }
            arr[j + 1] = temp;
        }
    }
}
```
总结：直接插入排序最好的情况是待排序的序列是有序的，这样时间复杂度是O(n)；而最好的情况就是待排序的序列是逆序的情况，这样时间复杂度是O(n²)；平均情况也是O(n²)。空间复杂度是O(1)。经过分析可以知道，直接插入排序是稳定的排序算法
#### 希尔排序
希尔排序的思想与直接插入排序基本一致，只是直接插入排序的跳跃间隔是1，而希尔排序的间隔会逐渐减小，最后一步一定是1
```
private static void sellSort(int[] arr) {
    int len = arr.length;
    int increment = len / 3 + 1;
    while (increment != 0) {
        if (increment == 1)
            increment = 0;
        else
            increment = increment / 3 + 1;
        for (int i = increment; i < len; i++) {
            if (arr[i - increment] > arr[i]) {
                int temp = arr[i];
                int j = 0;
                for (j = i - increment; j >= 0 && arr[j] > temp; j -= increment) {
                    arr[j + increment] = arr[j];
                }
                arr[j + increment] = temp;
            }
        }
    }
}
```
总结：希尔排序的最好情况，时间复杂度是O(n^1.3)，也就是有序的情况，最坏情况是O(n²)，空间复杂度是O(1)。由于希尔排序步长的原因，交换是跳跃式的，所以希尔排序不是一个稳定的排序算法

### 归并排序
归并排序的原理是假设初始序列含有n个记录，则可以看成是n个有序的子序列，每个子序列的长度为1，然后两两归并，得到ceil(n/2)个长度为2或1的有序子序列，再两两合并，...，直到得到一个长度为N的有序序列   
在归并排序中，关键之一在于合并两个有序的数组，使这两个有序的数组合并后仍然有序，下面给出代码  
**合并两个有序的序列**
```
/**
 * 待排序的数组分为两个有序的子序列，将这两个有序的子序列合并成一个有序的序列
 * 例如5 10 20 6 9 12，子序列5 10 20和子序列6 9 12分别是有序的，将这两个有序的序列合并成 6 9 12 5 10 20
 *
 * @param arr
 * @param left
 * @param mid
 * @param right
 * @param temp temp数组与arr数组一样的长度
 */

private static void merge(int[] arr, int left, int mid, int right, int[] temp) {
    int p1 = left;
    int p2 = mid + 1;
    int point = left;
    while (p1 <= mid && p2 <= right) {
        if (arr[p1] < arr[p2])
            temp[point++] = arr[p1++];
        else
            temp[point++] = arr[p2++];
    }
    while (p1 <= mid)
        temp[point++] = arr[p1++];
    while (p2 <= right)
        temp[point++] = arr[p2++];
    for (int i = left; i <= right; i++) {
        arr[i] = temp[i];
    }
}
```
**递归实现**  
递归实现归并排序是最容易理解的，其原理就是简单的应用归并排序的原理，首先将无序序列拆分成一个个的单独序列，然后两两合并成有序序列
```
private static void mergeSort(int[] arr, int left, int right, int[] temp) {
    if (left < right) {
        int mid = (left + right) / 2;
        mergeSort(arr, left, mid, temp);
        mergeSort(arr, mid + 1, right, temp);
        merge(arr, left, mid, right, temp);
    }
}
```
**迭代实现**  
重点掌握迭代实现归并排序，使用迭代实现的归并排序空间复杂度更低
```
private static void mergeSort(int[] arr) {
    int k = 1;
    int len = arr.length - 1;
    int[] temp = new int[len + 1];
    while (k <= len) {
        mergeMethod(arr, k, len, temp);
        k = 2 * k;
    }
}

private static void mergeMethod(int[] arr, int k, int len, int[] temp) {
    int i;
    //为什么是i + 2 * k - 1和i+k-1呢
    //因为比如每个序列的长度为2（也就是k为2），那么第一组的两个序列应该是arr[0~1]，
    // arr[2~3];第二组的两个序列应该是arr[4~5]和arr[6~7]，
    // 那么根据merge函数的参数定义i+k-1起始就是对应着mid参数；i+2*k-1就是对应着high参数
    for (i = 0; i <= len; i += 2 * k) {
        if (i + 2 * k - 1 <= len)
            merge(arr, i, i + k - 1, i + 2 * k - 1, temp);
        else
            break;
    }
    if (i + k - 1 < len)
        merge(arr, i, i + k - 1, len, temp);
}
```
总结：归并排序的时间复杂度无论好坏都是O(nlogn)，其需要O(n)的辅助空间(使用迭代实现)，且归并排序是稳定的排序算法

