# 2023春季蓝山工作室第五次课

来一点八股文叭~~:stuck_out_tongue_winking_eye:



## Mysql索引

什么是索引捏：

> Mysql索引的建立对于Msyql的高效运行时很重要的，索引可以大大提高Mysql的检索速度。
>
> 打个比方如果合理的设计且使用索引的Mysql是一辆兰博基尼的话，那么没有设计和使用索引的Mysql就是一个人力三轮车
>
> 拿汉语字典的目录页(索引)打比方，我们可以按照拼音、笔画、偏旁部首等排序的目录(索引)快速查找到需要的字

总而言之：索引就是一种，高效获取数据的，帮我们**排好序的**，**数据结构**



在Mysql中，无论是InnoDB还是MyISAM，都使用了B+Tree作为索引数据结构



> 噢对了，说到这里了，给个抢答题：
>
> **索引是对于数据库还是数据库表来说的？**

### 索引的优缺点：

**优点：**

- 使用了索引可以大大加快 数据 的检索速度，这也是创建索引的最主要原因
- 通过创建唯一索引，可以保证数据表中吗，每一行数据的唯一性



**缺点：**

- 创建索引和维护索引需要耗费许多时间，当对表中的数据进行CRUD的时候，如果数据有修改，那么索引也需要动态的修稿，会降低SQL执行效率
- 索引需要使用物理文件存储，也会耗费一定空间

但是，**使用索引一定能提高查询性能嘛？**

大多数情况下，索引查询收拾比全表扫描要快的，但是数据库的 数据量不大。那么使用索引也不一定能够使查询速度得到很大的提升

#### 还是看看远方的那些没有成为Mysql索引的数据结构吧家人们~

我们可以想到的一些常见的数据结构：

![image-20230406170024687](https://raw.githubusercontent.com/YxYL6125/imgBad/main/img/image-20230406170024687.png)

- 链表

左图：如果我们要找id == 7 的数据，我们就直接要从上往下一直遍历，知道最后

- 二叉树

右图：如果数据量足够大，比如数据有200万条，

我们知道2^10 = 1024，而2^20 = 1,048,576，才接近100万，

所以着两百万条数据，我们需要这个索引树高为20左右，才能装得下，太高了，

- 红黑树(二叉平衡树)

再来看看红黑树(这里，推荐一个[数据结构可视化管理的网站](https://www.cs.usfca.edu/~galles/visualization/Algorithms.html))



插入1，2:

![image-20230406171221019](https://raw.githubusercontent.com/YxYL6125/imgBad/main/img/image-20230406171221019.png)

我们再插入3看看，

![image-20230406171255032](https://raw.githubusercontent.com/YxYL6125/imgBad/main/img/image-20230406171255032.png)

可以看到，根据红黑树的特性，它会调平

我们继续插入，先插100，再插入99

![image-20230406171525725](https://raw.githubusercontent.com/YxYL6125/imgBad/main/img/image-20230406171525725.png)

![image-20230406171538386](https://raw.githubusercontent.com/YxYL6125/imgBad/main/img/image-20230406171538386.png)

我们会发现，它倒了2次

像这样，每次插入不同顺序的数据的话，树的结构会”撕裂“

- Hash表

哈希表是键值对的集合，通过k可以快速的对应值，因此哈斯表可以快速检索数据(接近O(1))

这点是因为哈希算法(也叫散列算法)，我们可以快速找到k所对应的index，找到了index，我们也就找到了对应的value

![](https://raw.githubusercontent.com/YxYL6125/imgBad/main/img/mysql20210513092328171.png)

但是，Hash算法有个**Hash冲突**的问题，

> 当多个不同的k通过算法算出同样的index的时候这么办捏~
>
> 通常情况下，我们常用的解决方法是 **链表地址法** 
>
> 也就是将Hash冲突的数据存放在链表中
>
> (就比如说JDK1.8之前，HashMap就是通过链地址来解决Hash冲突的；但是在JDK1.8过后，HashMap为了减少链表过长的时候搜索时间过长就引入了红黑树，这些都是SE那边的八股啦:cry:)

除此之外，为什么不使用Hash索引作为默认的索引数据结构呢，明明这么快

主要是因为Hash索引不支持顺序和范围查询。加入我们要对表中的数据进行排序或者进行范围查询，那么Hash索引可就不行了。并且，每次IO都只能取一个

也就是这种情况：

```sql
SELECT * FROM Student WHERE id < 500;
```

在这种情况下，如果使用Hash索引，根据Hash算法，我们就要把1-499的数据，每个都进行一次hash运算，进行定位，还不考虑Hash冲突的情况

- B-Tree

B树也称B-树，全称是 多路平衡查找树，B+Tree是B-Tree的一种变体

目前大部分数据库系统机器文件系统都采用B-Tree或其变种B+tree作为索引结构

#### B-Tree和B+Tree

看看B-Tree

![image-20230406171917798](https://raw.githubusercontent.com/YxYL6125/imgBad/main/img/image-20230406171917798.png)

B-Tree特点：

- 叶节点具有相同的深度，叶节点的指针为空
- 所有索引元素不重复
- 节点中的数据索引从左到右递增排列



看看B+Tree

![image-20230406171932555](https://raw.githubusercontent.com/YxYL6125/imgBad/main/img/image-20230406171932555.png)

与上相比：

- 非叶子节点不存储data，只存储索引(冗余)，可以放更多的索引
- 叶子节点包含索引索引字段
- 叶子节点使用指针连接，提高区间访问的性能



#### 二级索引

二级索引又称之为**辅助索引**，是因为二级索引的椰子节点存储的数据是主键。也就是说，通过二级索引，可以定位到主键的位置。

1. **唯一索引**：唯一索引也是一种约束。唯一索引的属性列不能出现重复的数据，但是允许数据为NULL，一张表允许创建多个唯一索引。建立唯一索引的目的大部分时候都是为了该列的数据的唯一性(也就是为了符合业务需求)，而不是为了查询效率。
2. **普通索引**：普通索引的唯一作用就是为了加快查询数据的速度，一张表允许创建多个普通索引，并且允许数据重复和NULL
3. **前缀索引**：前缀索引只适用于字符串类型的数据。前缀索引是对文本的前几个字符创建索引，相比于普通索引建立的数据更小，因为只取前面几个字符。
4. **全文索引**：全文索引主要是为了检索大文本数据中的关键字的信息，是目前搜索引擎数据库使用的一种技术。Musql5.6之前只有MyISAM支持全文索引，5.6过后InnoDB也支持了全文索引。但是一般平常我们不用这个索引，比较积鸡肋，真的要用到搜索的话，我们会直接使用相关搜索引擎框架：ES(Elastic Search)





#### 索引最左前缀原理









## 存储引擎

### InnoDB

InnoDB是Mysql5.7过后默认的存储引擎，一般情况下，只有在需要**它不支持的**特性的时候，我们才考虑使用其他存储引擎

InnoDB默认实现的是四个标准的隔离级别

- 读未提交
- 读已提交
- 可重复读
- 可串行化

当中的**可重复读**

在可重复读隔离级别下，通过多版本并发控制(MVCC)+间隙锁(Next-Key Locking)防止幻读

主索引是聚蔟索引，在索引中保存了数据，从而避免直接读取磁盘，因此对查询性能有很大的提升

InnoDB的三大特性：

1. Buffer Pool(插入缓冲)
2. 自适应哈希索引
3. 两次写(double write)

 



### MyISAM

设计简单，数据以紧密格式存储。对于只读数据，或者表比较小，可以容忍修复操作，则依然可以使用它

他提供了大量的特性，包括压缩表、空间数据索引等

但是MyISAM相对于InnoDB最为不足的其中一点就是：不支持事务

当然如果我们对这块需求并不是那么严格的话，并且需要用到上述特性，可以使用MyISAM



比较一下以上两个存储引擎：

- 事务：InnoDB是事务型，可以使用Begin、Commit、Rollback等语句‘
- 并发：MyISAM只支持表级锁，InnoDB还支持行级锁
- 备份：InnoDB支持在线热备份
- 崩溃恢复：MyISAM崩溃后发生损坏的概率比InnoDB高很多，而且恢复的速度也更慢
- 其他特性：MyISAM支持压缩表和空间数据索引

### Memory

……







## 事务

### 是什么

事务就是一个最小的不可再分的工作单元，通常一个事务对应一个完成的业务(例如银行账户转业务，改业务就是一个最小的工作单元)



### 事务的ACID

事务是由一组SQL组成的逻辑处理单元。事务具有一下4个属性，通常简称为事务的ACID属性

- 原子性(Atomicity)：事务是一个原子**操作**单元。其对数据的修改，要么全部执行，要么全部不执行(即，所有的操作要么一起完成，要么一起失败)
- 一致性(Consistent)：在事务开始和完成时，**数据**都必须保持一致状态。这意味着所有相关的数据规则都必须应用于事务的修改，以保持数据的完整性
- 隔离性(Isolation)：数据库系统(MySql)提供一定的隔离机制，保证事务在不受外部并发操作影响的"独立"环境执行。这意味着事务处理过程中的中间状态对外部是不可见的，反之亦然(即，事务之间不能互相影响)
- 持久性(Durable)：事务完成之后，他对于数据的修改时永久性的，即使出现系统故障也能保持**并发事务处理带来的问题**



### 并发事务处理带来的问题

- **更新丢失(Lost Update)或脏写**
  当两个或多个事务选择同一行，然后基于最初选定的值更新该行时，由于每个事务都不知道其他事务的存在，就就会发生丢失更新问题——**最后的更新覆盖了其他事务所做的更新**
- **脏读(Dirty Reads)**
  事务A读到了事务B**已经修改但是还没有提交的数据(也叫脏数据)**，还在这个数据基础上做了操作。此时，如果事务B回滚，事务A读取的数据无效，不符合一致性原则
- **不可重读(Non-Repeatable Reads)**
  事务A内部的相同查询语句在不同时刻读出的结果不一致(也就是说，我在这个事务里面，第一次查询这个值是1，后面的每一次查询这个值，不能是其他值，只能是1)，不符合隔离性
- **幻读(Phantom Reads)**
  事务A读取到了事务B提交的新增数据，不符合隔离性 









## Mysql锁机制

> 锁是计算机协调多个**进程**或**线程**并发访问某个资源的机制

在数据库中，除了传统的计算资源（b比如CPU、RAM、I/O等）的竞争以外，数据也是一种供需要用户共享的资源。如何保证数据并发访问的一致性、有效性是所有数据库必须解决的一个问题，。



### 何为锁

锁是一种内存结构，在事务执行之前是不存在锁的，换句话就是，一开始并没有锁结构和记录相关联

只有当一个事务想要对某条记录进行操作的时候，首先需要查看内存中是否有搜结构和该记录相关联，如果没有，就在内存中生成一个锁结构与之关联(此时被称之为**加锁成功**)



### 锁分类

- 从性能上分为**乐观锁**(使用版本对比机制来实现)和**悲观锁**
- 从对数据库操作的类型分为**读锁和写锁**
  - 读锁：(共享锁，S锁(Shared))：针对同一份数据，多个读操作可以同时进行而不会互相影响
  - 写锁：(排他锁，X锁(eXclusive))：当前写操作没有完成之前，它会阻断其他**写锁和读锁**
- 从对数据操作的**粒度**，分为**行锁和表锁**



#### 表锁

> 每次操作锁住**整张表**

开销小，加锁快；不会出现死锁的情况；锁的粒度大，发生锁冲突的概率最高，并发度低；一般用在整表数据迁移的场景

**基本操作：**

```sql
‐‐建表SQL
 CREATE TABLE `mylock` (
 `id` INT (11) NOT NULL AUTO_INCREMENT,
 `NAME` VARCHAR (20) DEFAULT NULL,
 PRIMARY KEY (`id`)
 ) ENGINE = MyISAM DEFAULT CHARSET = utf8;

 ‐‐插入数据
 INSERT INTO`test`.`mylock` (`id`, `NAME`) VALUES ('1', 'a');
 INSERT INTO`test`.`mylock` (`id`, `NAME`) VALUES ('2', 'b');
 INSERT INTO`test`.`mylock` (`id`, `NAME`) VALUES ('3', 'c');
 INSERT INTO`test`.`mylock` (`id`, `NAME`) VALUES ('4', 'd');
```

- 手动增加表锁：
  `lock table 表名称 read(write),表名称2 read(write)……;`
- 查看表加上过的锁：
  `show open tables;`
- 删除表锁：
  `unlock tables;`







#### 行锁

> 每次操作锁住一行记录

**开销大，加锁慢**(为什么会相当于表锁开销大，加锁慢呢？)；会出现死锁；锁定粒度最小，发生所冲突的概率最低，并发度最高

InnoDB与MYISAM的最大不同上面说了就有两点：

- **InnoDB支持事务(TRANSACTION)**
- **InnoDB支持行级锁**



### 案例演示

```sql
CREATE TABLE `account` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`name` varchar(255) DEFAULT NULL,
`balance` int(11) DEFAULT NULL,
PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
INSERT INTO `demo`.`account` (`name`, `balance`) VALUES ('lilei', '450');
INSERT INTO `demo`.`account` (`name`, `balance`) VALUES ('hanmei', '16000');
INSERT INTO `demo`.`account` (`name`, `balance`) VALUES ('lucy', '2400');
```

#### 读未提交

先设置会话的隔离级别——**读未提交**(每一个会话可以有不同的隔离级别)

```sql
set session transaction isolation level read uncommitted;
```

事务A先查询所有数据：

![image-20230610143052364](https://raw.githubusercontent.com/YxYL6125/imgBad/main/img/image-20230610143052364.png)

事务B我更新一条数据并查询——**但是不commit！！！**：

![image-20230610143122286](https://raw.githubusercontent.com/YxYL6125/imgBad/main/img/image-20230610143122286.png)

这个时候我事务A在查询一下：

![image-20230610143147829](https://raw.githubusercontent.com/YxYL6125/imgBad/main/img/image-20230610143147829.png)

可以看到事务A查询到了事务B修改未提交的数据

现在我把事务B回滚掉：

![image-20230610143407924](https://raw.githubusercontent.com/YxYL6125/imgBad/main/img/image-20230610143407924.png)

这个时候在客户端执行更新语句：`update account set balance = balance -50 where id = 1;`

再查询：

![image-20230610143528126](https://raw.githubusercontent.com/YxYL6125/imgBad/main/img/image-20230610143528126.png)

发现：balance没有变成350，而是400



#### 读已提交

```sql
set session transaction isolation level read committed;
```

重复上述同上的操作看看不同……

看的出来：读已提交解决了脏读；但是没有解决可重复读的问题





#### 可重复读

```sql
set session transaction isolation level repeatable read;
```

重复上述同上的操作看看不同……

- 先开启两个事务
- 客户端B更新
- 客户端A查询
- 客户端B提交
- 客户端A查询
- 客户端A更新

![image-20230610144854367](https://raw.githubusercontent.com/YxYL6125/imgBad/main/img/image-20230610144854367.png)

看的出来：

- 解决了上述的 **可重复读** 的问题 (MVCC机制来保证：在select的时候不会更新换版本，是快照读(历史版本)；但是insert、update、和delete、会更新版本号，是当前读(当前版本))
  让我们在写代码的时候，你正常写就好了，因为数据库帮我们满足了隔离性的，并且 在正常更新的时候，更新的是最新的值
- 并且在更新的时候，是使用最新的值来更新





#### 总结

MyISAM在执行查询语句SELECT之前，会自动给涉及到的所有表加读锁，在执行update、insert、delete操作的时候会自动给涉及到的表加写锁

InnoDB在执行查询语句SELECT的时候(非 串行化 隔离级别)，不会加锁。但是update、insert、delete操作会加行锁

简而言之，就是读锁会堵塞写，但是不会阻塞读。而写锁就会把两个操作都阻塞了









### 其他锁

除了上述根据不同方面锁分裂的锁之外，还有一些锁，是面试，如果各位前面的知识掌握的比较牢固，面试官觉得拷打不了你了，说不定就会问问下面将要介绍到的锁

#### 间隙锁(Gap Lock)

间隙锁。锁的就是两个之间的空隙，Mysql默认隔离级别就是repeatable-read，那么有办法解决幻读问题嘛？间隙锁在某些情况下可以解决幻读问题







#### 临键锁(Next-key Locks)

Next-Key Locks是行锁与间隙锁的组合。像上面那个例子里的(3,20]这个区间可以键做临键锁

