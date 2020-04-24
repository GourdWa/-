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
### 第六章
----
#### 二叉树
* **斜树**：所有节点都只有左子树的二叉树叫左斜树；所有节点都只有右子树的二叉树叫右斜树
* **满二叉树**：在一颗二叉树中，所有分支节点都存在左子树和右子树，且所有叶子节点都在同一层上，这样的二叉树叫做满二叉树
* **完全二叉树**：对一棵具有n个节点的二叉树按层序编号，如果编号i的节点与同样深度的满二叉树中编号为i的节点在二叉树中位置完全相同，则这棵二叉树称为完全二叉树 
### 二叉树的三种遍历方式（难点在于非递归遍历）
#### 二叉树的先序遍历递归和非递归实现
```
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