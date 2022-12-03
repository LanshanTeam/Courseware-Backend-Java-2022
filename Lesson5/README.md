# Mysql

## 数据库

1. #### **什么是数据库**

> 数据库（Database）：是按照数据结构来组织、存储和管理数据的仓库

##### **什么是（SQL）关系型数据库：**

>  关系型数据库指的是使用关系模型（二维表格模型）来组织数据的数据库。

##### **什么是（NOSQL）非关系型数据库：**

>  非关系型数据库又被称为 NoSQL（Not Only SQL )，意为不仅仅是 SQL。通常指数据以对象的形式存储在数据库中，而对象之间的关系通过每个对象自身的属性来决定，常用于存储非结构化的数据。 

| 大类  | 类别        | 常见                                                       | 说明                                                         |
| ----- | ----------- | ---------------------------------------------------------- | ------------------------------------------------------------ |
| SQL   | 关系数据库  | Oracle、MySQL/MariaDB、SQL Server、PostgrcSQL、 DB2        | 遵循“表一记录”模型。按行存储在文件中(先第 1 行，然后第 2 行……) |
| NoSQL | 时序数据库  | InfluxDB、RRDtool、Graphite、OpcnTSDB、Kdb+                | 存储时间序列数据，每条记录都带有时间戳。如存储从感应器采集到的数据 |
| NoSQL | 键/值数据库 | Redis、Memcached、Riak KV、Hazelcast、Ehcache              | 遵循“键——值”模型，是最简单的数据库管理系统                   |
| NoSQL | 文档数据库  | MongoDB、Couchbase、Amazon DynamoDB、CouchDB、MarkLogic    | 无固定结构，不同的记录允许有不同的列数和列类型。列允许包含多值，记录允许嵌套 |
| NoSQL | 图数椐库    | Neo4j、OrientDB、Titan、Virtuoso、ArangoDB                 | 以“点——边”组成的网络（图结构）来存储数据                     |
| NoSQL | 搜索引擎    | Elasticsearch、Solr、Splunk、MarkLogic、Sphinx             | 存储的目的是为了搜索，主要功能是搜索                         |
| NoSQL | 对象数据库  | Caché、db4o、Versant Object Database、ObjcctStore、Matisse | 受面向对象编程语言的启发，把数据定义为对象并存储在数据库中，包括对象之问的关系，如继承 |
| NoSQL | 宽列数据库  | Cassandra、 HBase、Accumulo                                | 按照列（由“键——值”对组成的列表）在数据文件中记录数据，以获得更好的请求及遍历效率。一行中的列数允许动态变化，且列的数目可达数百万，每条记录的关键码不同，支持多值列。 |

2. #### **数据库是如何存放数据的？**

数据库有很多种类，这里我们重点学习使用最广泛的关系数据库。

关系数据库是由多个表组成的。

**关系数据库=多张表+各表之间的关系**

1. **表的结构**

   > 每个表由一个名字标识**主键**。表包含带有列名的**列**，和记录数据的**行**。
   >
   > **主键**是关系数据库中重要的概念，用来标识数据的唯一性。

   下面图片里的表名是：学生表，记录了每个学生的信息。

   ![img](https://pic2.zhimg.com/v2-34182ea6a2382ca1eb8efcabcf08cb19_r.jpg)

   2. **各表之间的关系**

      关系数据库是由多张表组成的

      ![img](https://pic3.zhimg.com/80/v2-84e91a1f5769a5c9ed8245af0179eb4e_720w.webp)

      **关系就是数据能够对应的匹配，在关系数据库中正式名称叫联结，对应的英文名称叫做join**。

      **联结**是关系型数据库中的核心概念，**务必记住这个概念，后面会在多表查询中具体学到。**

   3. **什么是关系数据库管理系统？**

      > 实现数据库原理的“施工团队”就是，用来管理数据库的计算机软件叫做数据库管理系统。
      > 常用的关系数据库管理系统有mysql，orcale，sql server

   4. **什么是SQL?**

      **SQL(Structured Query Language结构化查询语言)**是用来操作数据库里数据的工具。

      1. DQL(Data Query Language)数据查询语言

         数据查询语言基本结构是由SELECT子句，FROM子句，WHERE子句组成的查询块

      2. DML(Data Manipulation Language)数据操纵语言

         数据操纵语言DML主要有三种形式：

         1. 插入：INSERT
         2. 更新：UPDATE
         3. 删除：DELETE

      3. DDL(Data Definition Language)数据定义语言

         数据定义语言用来创建数据库中的各种对象-----表、视图、索引、同义词、聚簇等

      4. DCL(Data Control Language)数据控制语言

         数据控制语言用来授予或回收访问数据库的某种特权，并控制数据库操纵事务发生的时间及效果，对数据库实行监视等。

      

## 常用数据库

**Oracle**、**Sybase**、**SQL Server**、**MySQL**

MySQL是一个小型关系型数据库管理系统，开发者为瑞典MySQL AB公司。在2008年1月16号被Sun公司收购。目前MySQL被广泛地应用在Internet上的中小型网站中。由于其体积小、速度快、总体拥有成本低，尤其是开放源码这一特点，许多中小型网站为了降低网站总体拥有成本而选择了MySQL作为网站数据库。

1. 可以同时处理几乎不限数量的用户；

2. 处理多达50,000,000以上的记录；

3. 命令执行速度快，也许是现今最快的；

4. 简单有效的用户特权系统。

**主要还是免费😍😍！！！**

## 所以我们开始进入MySQL的学习！

### MySQL版本

1. **版本**

   分为四种：Alpha版、Beta版、RC版(Release Candidate)、GA版(Generally Available)。

   1. **Alpha版**

      Alpha版软件，这是软件工程对软件开发过程软件版本定义使用的版本说明。Alpha是内部测试版,一般不向外部发布,会有很多Bug.除非你也是测试人员,否则不建议使用.是希腊字母的第一位,表示最初级的版本，alpha 就是α。

   2. **Beta版**

      Beta版软件，这也是软件工程中对软件开发测试版本控制的版本说明。Beta一般是Alpha后面的版本。该版本相对于α版已有了很大的改进，消除了严重的错误，但还是存在着一缺陷，需要经过多次测试来进一步消除。这个阶段的版本会一直加入新的功能。beta 就是β。

   3. **RC版**

      RC版，RC即Release Candidate的简写。这是Beta后面的版本，一般RC版并没有新增功能，而是修复了一些反馈的Beta中存在的BUG。所以RC版更接近最终发行版即稳定版(GA版)

   4. **GA版**

      GA版，GA即Generally Available的简写。这就是软件最终的发行版。这个版本一般BUG相对较少。这个发行版也可以叫稳定版。

   5. **Release版**

      Release版，在有些软件存在，在MySQL中一般没有这个版本。该版本意味“最终版本”，在前面版本的一系列测试版之后，终归会有一个正式版本，是最终交付用户使用的一个版本。该版本有时也称为标准版。一般情况下，Release不会以单词形式出现在软件封面上，取而代之的是符号(R)。	

2. **分支**

   不同公司对数据库的需求不同，如功能，性能等，因此诞生了不同的MySQL分支。

   市面上流行的分支：Oracle公司官方版MySQL、**Percona Server、Maria DB**

   简单来说大家需要知道的是：

   **Percona公司更偏向于运维工具；Maria DB更偏向于功能的完善；Oracle MySQL它介于两者之间**。

3. **版本号**

   推荐主流版本号：**5.7**和**8.0**这两个大版本的数据库

   求稳定建议5.7系列，如果想尝鲜直接上车8.0

### MySQL下载安装

略（自行搜索教程）

### MySQL图形化界面管理工具

1. **MySQL Workbench、**

2. **phpMyAdmin、**

3. **Navicat、**

4. **MySQL Dumper、**

5. **SQLyog、**

6. **MySQL ODBC Connector、**

7. **Data grip**
8. .......

### CRUD工程师？ 

![image-20221129114056474](C:\Users\v\AppData\Roaming\Typora\typora-user-images\image-20221129114056474.png)

> "工作一年到头，除了写了些BUG，就整天做一些CRUD"

 CRUD是指在做计算处理时的增加(Create)、读取查询(Retrieve)、更新(Update)和删除(Delete)几个单词的首字母简写。主要被用在描述软件系统中Data Base或者持久层的基本操作功能。



### MySQL常用语法

1. 登录mysql服务端

​		**mysql -u root -p；**

2. 查看当前所有数据库

​		**show databases;**

3. 打开指定的库

​		**use** 库名；

4. 查看当前库的所有表

​		**show tables；**

5. 查看其他库的所有表

​		**show tables from** 数据库名；

6. 创建表

​		**create table** 表名**（**

​			列名 列类型，

​			列名 列类型，

​			........

​			**);**

7. 查看当前库的表

​		**select * from** 表名；

8. 向表中插入数据

​		**insert into** 表名(列名1,列名2) **values** (数据1,数据2);

9. 更改表中数据

​		**update** 表名 **set** 列名**=**新数据 **where** 列名**=**数据;

10. 删除表中数据

​		 **delete from** 表名 **where** 列名=数据;

11. 查看表的结构

​		**desc** 表名；

12. 查看服务器版本

​	1.登录到mysql服务端 ：select version（）；

​	2.没有登录到mysql服务端：mysql--v；

13. 查看字符集

    - **show** variables like 'character_%';
    - **show** character set;
    - **select** * **from** information_schema.character_sets;

14. 查看字符集的校对规则

    - **show** collation;

    - **show** collation like 'utf8';

    - **select** * from information_schema.collations where collation_name like 'utf8%';

15. 查询数据(12种)

    1. 简单查询
       select * from 表名称 ——查询表中所有数据 *代表所有列
       select code,name from 表名称——查询指定列数据
       select code as'代号',name as'姓名' from 表名称——给列指定别名

    2. 条件查询
       select * from 表名 where code=' '   查这一行
       select * from 表名 where sex='true' and nation=' '   表示并列，--多条件并的关系
       select * from 表名 where sex='true' or nation=' ' --多条件或的关系

    3. 范围查询
       select * from 表名 where 列名>40 and 列名<50
       select * from 表名 where 列名 between 40 and 50  --专用于范围查询

    4. 离散查询
       select * from 表名 where 列名 in (' ',' ',' ')
       select * from 表名 where 列名 not in (' ',' ',' ')  反选，不在里面的

    5. 模糊查询
       select * from 表名 where 列名 like '**%**A**%**'——查包含A的
       select * from 表名 where 列名 like 'A**%**'——查以A开头的
       select * from 表名 where 列名 like '**%**A'——查以A结尾的
       select * from 表名 where 列名 like 'A'——查等于A的
       select * from 表名 where 列名 like '__E'——查第三个是E的
       % 代表是任意多个字符

       下划线 代表是一个字符

    6. 排序查询
       select * from 表名 order by 列名——默认升序排序
       select * from 表名 order by 列名 desc——降序排列
       select * from 表名 order by 列名 desc, 列名 asc——多个条件排序   ， 前面是主条件 后面是次要条件
       desc 降序  ，asc 升序， order by  排序  根据哪一列排序

    7. 分页查询
       select  * from 表名 limit x(起始索引),y(记录条数);

       select  * from 表名 limit x(数据条数);//起始索引默认为0

    8. 去重查询（去掉重复的）
       select distinct 列名 from

    9. 分组查询

       select 字段1，字段2 from 表名 group by 字段1，字段2...    // 没有group by 默认分成一组

       having //针对于group by 使用过滤 在where无法完成时才使用 用法和where一样

    10. 聚合函数（统计查询）
        select count (*) from 表名——查询所有数据条数（每一列的）
        select count (列名主键) from 表名——查询这列的所有数据条数（执行快）
        select sum (列名) from 表名——求和
        select avg  (列名) from 表名——求平均值
        select max (列名) from 表名——求最大值
        select min (列名) from 表名——求最小值

    11. 子查询

        1. where子句中的子查询（where中不能使用分组函数如min（））
           找出比最低工资高的员工姓名及工资

           1. 查询最低工资
              select min（salary）from employee；

           2. 找出大于最低工资

              ```sql
              select name，salary 
              from employee 
              where salary>(select min（salary）from employee);
              ```

              

        2. from子句中的子查询（可以将子查询的查询结果当做一张临时表）
           查询工作岗位的平均  工资，以及所在等级

           1. 查询工作岗位及平均工资
              **select** job，avg（sal）**as** avgsal **from** employee **group by** job；==>t表

           2. 查询等级对应条件

              ```sql
              select t.*.s.grade(等级) 
              from t 
              join salgrade s 
              on t.avgsal between s.losal and s.hisal;
              ```

              

           3. 综合

              ```sql
              select t.*,s.grade(等级)
              from （select job，avg（sal）as avgsal from employee group by job）t 
              join salgrade s
              on t.avgsal between s.losal and s.hisal;
              ```

              

        3. select后面出现的子查询
           注意:对于select后面的子查询来说，这个子查询只能一次返回1条结果，多于1条,就报错了!

           ```sql
           SELECT 
             d.*,
             (SELECT 
               COUNT(*) 
             FROM
               `employees` e
               WHERE e.`department_id`=d.`department_id`)  个数
           FROM
             `departments` d;
           ```

    12. 多表查询

    角度1：等值连接 versus 非等值连接

    - 非等值连接例子：

      查询工资的等级 通过判断工资所在范围判断

      ```sql
      select e.last_neme,e.salary,j.grade_level
      
      from employees e,job_grades j
      
      where e.`salary` >= j.`lowest_sal` and `salary` >= j.`highest_sal` ;
      
      //where e.`salary` between j.`lowest_sal` and j.`highest_sal` ;
      ```

    角度2：自连接 versus 非自连接

    - 自连接例子

      查询员工ID，员工姓名及其管理者的ID和姓名

      ```sql
      select  emp.employees_id, emp.last_name,mgr.employees_id,mgr.last_name
      
      from employees emp,employees mgr
      
      where  emp.manager_id=mgr.employees_id;
      ```

    角度3：内连接 versus 外连接

    - 内连接：合并具有同一列的两个以上的表的行，结果集中不包含一个表于另一个表不匹配的行

    - 外连接：合并具有同一列的两个以上的表的行，结果集中除了包含一个表于另一个表匹配的行之外，还查询到了左表 或 右表中不匹配的行

      - ​	外连接分为：左外连接，右外连接，满外连接

        - 左外连接：两个表在连接过程中除了返回满足连接条件的行以外还返回左表中不满足系件的行，这种连接称为左外连接

        - 右外连接：两个表在连接过程中除了返回满足连接条件的行以外还返回右表中不满足系件的行，这种连接称为右外连接

        - 一旦出现所有的，并且来自不同的表，一定是外连接

### MySQL语法规范

1. 不区分大小写，建议关键字大写，表名，列名小写

2. 每条命令最好用分号结尾

3. 每条命令根据需要，可以进行缩进或换行

4. 注释：单行注释 #注释文字 /-- 注释文字    多行注释 /*      */

### 字符集(Character Set)

一、MySQL字符集简介

**字符集（character set）**规定了字符在数据库中的存储格式，比如占多少空间，支持哪些字符等等。不同的字符集有不同的编码规则，在有些情况下，甚至还有**校对规则（collation）**的存在。在运维和使用mysql数据库中，选取合适的字符集非常重要，如果选择不恰当，轻则影响数据库性能，严重的可能导致数据存储乱码。
常见的mysql字符集主要有以下四种：

| 字符集  | 长度 | 说明                                    |
| ------- | ---- | --------------------------------------- |
| GBK     | 2    | 支持中文，但是不是国际通用字符集        |
| UTF-8   | 3    | 支持中英文混合场景，是国际通用字符集    |
| latin1  | 1    | MySQL默认字符集                         |
| utf8mb4 | 4    | 完全兼容UTF-8，用四个字节存储更多的字符 |

mysql数据库在开发运维中，字符集选用规则如下：
1、如果系统开发面向国外业务，需要处理不同国家、不同语言，则应该选择utf-8或者utf8mb4。
2、如果只需要支持中文，没有国外业务，则为了性能考虑，可以采用GBK。

Unicode（Universal Code）是一种在计算机上使用的字符编码。Unicode  是为了解决传统的字符编码方案的局限而产生的，它为每种语言中的每个字符设定了统一并且唯一的二进制编码，以满足跨语言、跨平台进行文本转换、处理的要求。Unicode存在不同的编码方案，包括Utf-8，Utf-16和Utf-32。Utf表示Unicode Transformation Format。

### 存储引擎（Storage Engine）

MySQL中的数据**用各种不同的技术存储**在文件（或者内存）中。这些技术中的每一种技术都使用不同的**存储机制**、**索引技巧**、**锁定水平**并且最终提供广泛的不同的功能和能力。通过选择不同的技术，你能够获得额外的速度或者功能，从而改善你的应用的整体功能。

就跟计算机如何将数据保存到磁盘中一样，在数据库中，存储引擎的意思就是通过何种引擎将数据存储在磁盘中
 	

| 功能                   | MyISAM | Memory | Archive | InnoDB | NDB   |
| ---------------------- | ------ | ------ | ------- | ------ | ----- |
| B树索引                | 是√    | 是√    | 是√     | 否×    | 否×   |
| 备份/时间点恢复        | 是√    | 是√    | 是√     | 是√    | 是√   |
| 集群数据库支持         | 否×    | 否×    | 否×     | 否×    | 是√   |
| 聚集索引               | 否×    | 否×    | 是√     | 否×    | 否×   |
| 压缩数据               | 是√    | 否×    | 是√     | 是√    | 否×   |
| 资料快取               | 否×    | 否×    | 是√     | 否×    | 是√   |
| 加密数据               | 是√    | 是√    | 是√     | 是√    | 是√   |
| 外键支持               | 否×    | 否×    | 是√     | 否×    | 是√   |
| 全文搜索索引           | 是√    | 否×    | 是√     | 否×    | 否×   |
| 地理空间数据类型支持   | 是√    | 否×    | 是√     | 是√    | 是√   |
| 地理空间索引支持       | 是√    | 否×    | 是√     | 否×    | 否×   |
| 哈希索引               | 否×    | 是√    | 否×     | 否×    | 是√   |
| 索引缓存               | 是√    | 否×    | 是√     | 否×    | 是√   |
| MVCC                   | 否×    | 否×    | 是√     | 否×    | 否×   |
| 复制支持               | 是√    | 限量   | 是√     | 是√    | 是√   |
| 储存限制               | 256TB  | 内存   | 64TB    | 否×    | 384EB |
| T树索引                | 否×    | 否×    | 否×     | 否×    | 是√   |
| 交易次数               | 否×    | 否×    | 是√     | 否×    | 是√   |
| 更新数据字典的统计信息 | 是√    | 是√    | 是√     | 是√    | 是√   |

### 约束（Constraint）

- constraint
  列级约束符加在列的字段的后面
  表级约束符在最后声明

- 非空约束：not null
  非空约束not null约束的字段不能为NULL.
  列级约束 
  create table t vip (
  id int ,
  name varchar (255) not null
  );

- 唯一性约束：unique
  不能重复 但是可以为null （直接添加到列的后面为列级约束   ）
  联合唯一（表级约束）：

  ```sql
  create table t vip (
  id int,
  name varchar (255),
  email varchar (255) ,
  unique ( name，email)
  );
  ```

- 主键约束：primary key（pk）
  主键值不能是null，同时也不能重复
  所以加上unique 和not null约束的自动变为主键字段

  ```sql
  create table t vip(
  id int,
  name varchar (255)，
  primary key(id) // 表级约束
  );
  ```

  称为复合主键（少用）
  一张表只有一个主键
  自然主键：主键值是一个自然数，和业务没关系

  业务主键：主键值和业务紧密联系，例如拿银行卡账号做主键值。这就是业务主键
  primary key 后加上auto_increment;//表示自增

- 外键约束：foreign key （fk）

  ```sql
  create table t_student (
  no int primary key auto increment ,
  name varchar (255)，
  cno int,
  foreign key (cno) references t_ class (classno)
  );
  ```

  被引用的字段不一定是主键，但是至少是有唯一性
  外键值可以为空

- 检查约束：check（mysql不支持，Oracle支持）

### 索引（Index）

> MySQL索引的建立对于MySQL的高效运行是很重要的，索引可以大大提高MySQL的检索速度。

索引分单列索引和组合索引。

创建索引时，你需要确保该索引是应用在	SQL 查询语句的条件(一般作为 WHERE 子句的条件)。

### 事务（Transaction）

> 在 MySQL 中只有使用了 InnoDB 数据库引擎的数据库或表才支持事务。

> 事务处理可以用来维护数据库的完整性，保证成批的 SQL 语句要么全部执行，要么全部不执行。

> 事务用来管理 insert,update,delete 语句

一般来说，事务是必须满足4个条件（ACID）：原子性（**A**tomicity，或称不可分割性）、一致性（**C**onsistency）、隔离性（**I**solation，又称独立性）、持久性（**D**urability）。

- **原子性：**说明事务是最小的工作单位，不可再分

- **一致性：**所有事务要求，在同一个事务当中，所有操作必须同时成功，或者同时失败，以保证数据的一致性

- **隔离性：**数据库允许多个并发事务同时对其数据进行读写和修改的能力，隔离性可以防止多个事务并发执行时由于交叉执行而导致数据的不一致。事务隔离分为不同级别，包括读未提交（Read uncommitted）、读提交（read committed）、可重复读（repeatable  read）和串行化（Serializable）。

  隔离级别 

  - **读未提交**：read uncommitted （最低隔离级别） 事务A可以读取到事务B未提交的数据。 这种隔离级别存在的问题就是: 脏读现象 (Dirty Read) 我们称读到了脏数据。 这种隔离级别一般都是理论上的，大多数的数据库隔离级别都是二档起步 
  - **读已提交**：read committed 事务A只能读取到事务B提交之后的数据。 这种隔离级别解决了什么问题? 解决了脏读的现象。 这种隔离级别存在什么问题? 不可重复读取数据。 在事务开启后，第一读到3条，当前事务还没有结束，可能第二次再读取的时候，读到的数据是4条，3<>4，称为不可重复读取 
  - **可重复读**：repeatable read 事务A开启之后，不管是多久，每一次在事务A中读取到的数据都是一致的。即使事务B将数据已经修改，并且提交了，事务A读取到的数据还是没有发生改变，这就是可重复读。 可重复读存在的问题是什么? 可以会出现幻影读。 每一次读取到的数据都是幻象。不够真实! mysql默认的事务隔离级别就是这个！！  
  - **序列化**：serializable 这是最高隔离级别，效率最低。解决所有问题 这种隔离级别表示事务排队，不能并发！ synchronized，线程同步（事务同步） 每一次读取到的数据都是真实的，并且效率是最低的。

- **持久性：**事务最终结束的一个保障。事务提交，就相当于将没有保存到硬盘的数据保存到硬盘上！

### 视图（View）

**create** **view** table_view **as** select *from table;

删除视图对象:

**drop** **view** table_view ;

注意:只 有DQL语句才能以view的形式创建。

**create** **view** view_name as

这里的语句必须是DQL语句;







# Redis

## Redis是什么？

Redis（=Remote Dictionary Server )，即远程字典服务
Redis是一个**key-value** 存储系统
Redis 通常被称为数据结构服务器，因为值（value）可以是字符串(String)、哈希(Hash)、列表(list)、集合(sets)和有序集合(sorted sets)等类型。

## Redis的诞生

数据库诸如MySQL等在一些特定的时间节点以及场合下会面临大量的客户的访问，但其实在绝大部分时间里客户端对数据库发送的请求是读操作，而且经常都是反复查询一个东西，会浪费大量的时间进行磁盘的I/O,于是我们就考虑到应该给数据库中频繁访问的数据存储到电脑内存中做缓存，提高查询效率。于是乎Redis诞生了。而且随着人们的需求不断增加，Redis的功能也在不断丰富，加强....

## Redis能干什么？

1. 内存存储、持久化，内存中是断电即失、所以说持久化很重要(（ rdb、aof )
2. 效率高，可以用于高速缓存
3. 发布订阅系统
4. 地图信息分析
5. 计时器、计数器（浏览量)
6. .….

## Redis使用

### redis的下载安装使用

略

### 基本介绍

#### Redis和Memcache的比较

串行vs多线程+锁( memcached ) vs单线程+多路Io复用(Redis).
 (与Memcache三点不同:支持多数据类型，支持持久化，单线程+多路IO复用)

#### 数据库

默认16个数据库，类似数组下标从0开始，初始默认使用0库,
使用命令select来切换数据库。如: select 8
所有库的密码相同

#### key操作

1.  keys *查看当前库所有key
2.  exists key判断某个key是否存在
3.  type key查看你的 key是什么类型
4.  del key删除指定的key数据·unlink key根据value选择非阻塞删除仅将keys 从 keyspace元数据中删除，真正的删除会在后续异步操作。
5.  expire key 10 10秒钟:为给定的 key设置过期时间
6.  ttl key查看还有多少秒过期，-1表示永不过期，-2表示已过期。

#### 其他命令

dbsize查看当前数据库的key的数量
flushdb清空当前库
flushall通杀全部

#### 数据类型

##### 字符串（String）

**简介**

String是 Redis最基本的类型，你可以理解成与Memcached一模一样的类型，一个key对应一个value。
String类型是二进制安全的。意味着Redis的string可以包含任何数据。比如jpg图片或者序列化的对象。
String类型是Redis最基本的数据类型，一个Redis中字符串value最多可以是512M

**常用命令**

1.  set ：set <key> <value>设置相同的key将覆盖先前的值
2.  get ：get <key>
3.  append <key> <value> 将给定的value追加到原值的末尾。
4.  strlen <key> 获得值的长度
5.  setnx <key> <value>只有在key 不存在时才设置key的值,存在时不设置
6.  incr <key>将key中储存的数字值增1 只能对数字值操作，如果为空，新增值为1
7.  decr <key> 将key 中储存的数字值减1 只能对数字值操作，如果为空，新增值为-1
8.  incrby / decrby key 步长 将key中储存的数字值增减。自定义步长。
9.  mset <key1><value1><key2><value2>.... 同时设置一个或多个key-value对r
10.  mget <key1><key2><key3>..... 同时获取一个或多个value
11.  msetnx  <key1> <value1> <key2> <value2>....  同时设置一个或多个key-value对，当且仅当所有给定key都不存在
12.  getrange <key><起始位置><结束位置> 获得值的范围，类似java中的substring,前包，后包
13.  setrange <key><起始位置><value>  用<value>覆写<key>所储存的字符串值，从<起始位置>开始（索引从0开始）。
14.  setex <key> <expired time> <value> 设置键值的同时，设置过期时间，单位秒。

##### 列表（List）

**简介**

Redis列表是简单的字符串列表，按照插入顺序排序。你可以添加一个元素到列表的头部（左边）或者尾部（右边）。
它的底层实际是个双向链表，对两端的操作性能很高，通过索引下标的操作中间的节点性能会较差。

**数据结构**

List为快速链表quickList。
首先在列表元素较少的情况下会使用一块连续的内存存储，这个结构是ziplist,也即是压缩列表。
它将所有的元素紧挨着一起存储，分配的是一块连续的内存。
当数据量比较多的时候才会改成quicklist。
因为普通的链表需要的附加指针空间太大，会比较浪费空间。比如这个列表里存的只
是int类型的数据，结构上还需要两个额外的指针prev和next。

![image-20221201091310961](C:\Users\v\AppData\Roaming\Typora\typora-user-images\image-20221201091310961.png)

**常用命令**

lpush/rpush <key><value1> <value2> <value3>…从左边/右边插入一个或多个值。
lpop/rpop <key> 从左边/右边吐出一个值。值在键在，值光键亡。
rpop/lpush <key1> <key2>  从<key1>列表右边吐出一个值，插到<key2>列表左边。
Irange <key> <start> <stop>按照索引下标获得元素（从左到右）
Irange <key> 0 -1     0左边第一个，-1右边第一个   (0 -1表示获取所有)
lindex <key> <index> 按照索引下标获得元素（从左到右）
len<key> 获得列表长度

##### 集合(Set)

**简介**
Redis set对外提供的功能与list类似是一个列表的功能，特殊之处在于set是可以自动排重的，当你需要存储一个列表数据，又不希望出现重复数据时，set是一个很好的选择，并且set提供了判断某个成员是否在一个set集合内的重要接口，这个也是list所不能提供的。
Redis的set是string类型的无序集合。它底层其实是一个value为null的hash表，所以添加，删除，查找的复杂度都是O(1)。
一个算法，随着数据的增加，执行时间的长短，如果是O(1)，数据增加，查找数据的时间不变。

**数据结构**
 Set数据结构是dict字典，字典是用哈希表实现的。
 Java中HashSet的内部实现使用的是 HashMap ，只不过所有的value都指向同一个对象Redis的set结构也是一样，它的内部也使用hash结构，所有的value都指向同一个内部值

**常用命令**

1. sadd <key><value1><value2>.... 将一个或多个member元素加入到集合key中，已经存在的member元素将被忽略
2. smembers<ky>取出该集合的所有值。
3. sismember<key><value> 判断集合<key>是否为含有该<value>值，有1，没有0，
4. scard<key> 返回该集合的元素个数。
5. srem <key> <value1> <value2>..删除集合中的某个元素。
6. spop <key> 随机从该集合中吐出一个值。
7. srandmember<key> <n>随机从该集合中取出n个值。不会从集合中删除
8. smove<source><destination>value把集合中一个值从一个集合移动到另一个集合
9. sinter<key1><key2>返回两个集合的交集元素。
10. sunion<key1><key2>返回两个集合的并集元素。
11. sdif<key1><key2>返回两个集合的差集元素(key1中的，不包含key2中的)

##### 哈希(Hash)

**简介**

Redis hash是一个键值对集合。·
Redis hash是一个string类型的field和value的映射表，hash特别适合用于存储对象。类似Java里面的Map<String,Object>
主要有以下2种存储方式:



**数据结构**

Hash类型对应的数据结构是两种：ziplist(压缩列表)，hashtable(哈希表)。当field-value长度较短且个数较少时，使用ziplist,否则使用hashtable。

**常用命令**

1. hset <key> <field> <value>  给<key>集合中的<field>键赋值<value>
2. hget<key1> <field>从<key1>集合<field>取出value
3. hmset<key1> <field1> <valuel> <field2> <value2>...  批量设置hash的值
4. hexists<key1><field>查看哈希表key中，给定域field是否存在。
5. hkeys <key>列出该hash集合的所有field
6. hvals <key>列出该hash集合的所有value
7. hincrby <key> <field> <increment>.为哈希表key中的域feld的值加上增量1 -1
8. hsetnx <key> <field> <value>.将哈希表key中的域field的值设置为value,、当且仅当域field不存在

##### 有序集合(Zset)

Redis有序集合zset与普通集合set非常相似，是一个没有重复元素的字符串集合。不同之处是有序集合的每个成员都关联了一个**评分(score)** ,这个**评分( score)**被用来按照从最低分到最高分的方式排序集合中的成员。集合的成员是唯一的，但是评分可以是重复了。因为元素是有序的,所以你也可以很快的根据评分( score )或者次序( position )来获取一个范围的元素。访问有序集合的中间元素也是非常快的,因此你能够使用有序集合作为一个没有重复成员的智能列表

1. zadd <key> <score1> <value1> <score2> <value2>.... 将一个或多个member元素及其score值加入到有序集key当中。
2. zrange <key> <start> <stop> [withscores] 返回有序集key中，下标在<start> <stop>之间的元素带WITHSCORES,可以让分数一起和值返回到结果集。
3. zrangebyscore key minmax [withscores] [limit offset count] 返回有序集key中，所有score值介于min和max之间（包括等于min或max)的成员。有序集成员按score值递增（从小到大）次序排列。
4. zrevrangebyscore key maxmin [withscores] [limit offset count]

**数据结构**

SortedSet(zset)是Redis提供的一个非常特别的数据结构，一方面它等价于Java的数据结构Map<String,Double>,可以给每一个元素value赋予一个权重score,另一方面它又类似于TreeSe:,内部的元素会按照权重score进行排序，可以得到每个元素的名次，还可以通过score范围来获取元素的列表。zset底层使用了两个数据结构
(1)hash,hash的作用就是关联元素value和权重score,保障元素value的唯
一性，可以通过元素value找到相应的score值。
(2)跳跃表，跳跃表的目的在于给元素value排序，根据score的范围获取元素列表

##### Bitmaps

现代计算机用二进制（位）作为信息的基础单位，1个字节等于8位，例如“abc”字符串是由3个字节组成，但实际在计算机存储时将其用二进制表示，"abc”分别对应的ASCII码分别是97、98、99，对应的二进制分别是01100001、01100010和01100011，如下图
合理地使用操作位能够有效地提高内存使用率和开发效率。
Redis提供了Bitmaps这个"数据类型”可以实现对位的操作：

1. Bitmaps本身不是一种数据类型，实际上它就是字符串(key-value),但是它可以对字符串的位进行操作。

2. Bitmaps单独提供了一套命令，所以在Redis中使用tldp3和使箅学符串的方法不太相同。可以把Bitmaps想象成一个以位为单位的数组，数组的每个单元只能存储0和1，数组的不标在Bitmaps中叫做偏移量。

   ![image-20221201144000813](C:\Users\v\AppData\Roaming\Typora\typora-user-images\image-20221201144000813.png)

3. 格式

   1. setbit <key> <offset> <value> 设置Bitmaps中某个偏移量的值(0或1) //offset偏移量从0开始

      实例
      每个独立用户是否访问过网站存放在Bitmaps中，将访问的用户记做1，没有访问的用户记做0，用偏移量作为用户的id。
      设置键的第offset个位的值（从0算起），假设现在有20个用户，userid=1,6,11,15,19的用户对网站进行了访问，那么当前Bitmaps初始化结果如图

   ![image-20221201144248670](C:\Users\v\AppData\Roaming\Typora\typora-user-images\image-20221201144248670.png)

   2. getbit <key><offset>获取Bitmaps中某个偏移量的值
   3. 统计字符串被设置为1的bit数。一般情况下，给定的整个字符串都会被进行计数，通过指定额外的stat或end参数，可以让计数只在特定的位上进行。start和end参数的设置，都可以使用负数值：比如1表示最后一个位，而-2表示倒数第二个位，start、end是指bit组的字节的下标数，二者皆包含。
   4. bitcount <key> [start] [end]统计字符串从stat字节到end字节比特值为1的数量
   5. bitop and(or/not/xor)<destkey>[key...]

##### HyperLogLog

在工作当中，我们经常会遇到与统计相关的功能需求，比如统计网站PV(PageView页面访问量)，可以使用Redis的incr、incrby轻松实现。但像UV(UniqueVisitor,独立访客)、独立IP数、搜索记录数等需要去重和计数的问题如何解决？这种求集合中不重复元素个数的问题称为基数问题。解决基数问题有很多种方案：
(1)数据存储在MySQL表中，使用distinct count计算不重复个数
(2)使用Redis提供的hash、set、bitmaps等数据结构来处理
以上的方案结果精确，但随着数据不断增加，导致占用空间越来越大，对于非常大的数据集是不切实际的。
能否能够降低一定的精度来平衡存储空间？Redis推出了HyperLogLog
Redis HyperLogLog是用来做基数统计的算法，HyperLogLog的优点是在输入元素的数量或者体积非常非常大时，计算基数所需的空间总是固定的、并且是很小的。
在Redis里面，每个HyperLogLog键只需要花费12KB内存，就可以计算接近2^64个不同元素的基数。这和计算基数时，元素越多耗费内存就越多的集合形成鲜明对比。
但是，因为HyperLogLog只会根据输入元素来计算基数，而不会储存输入元素本身，所以HyperLogLog不能像集合那样，返回输入的各个元素。
什么是基数？
比如数据集{1,3,5,7,5,7,8}，那么这个数据集的基数集为{1,3,5,7,8}，基数（不重复元素）为5。基数估计就是在误差可接受的范围内，快速计算基数。

##### Geospatial

Redis3.2中增加了对GE0类型的支持。GE0,Geographic,地理信息的缩写。该类型，就是元素的2维坐标，在地图上就是经纬度。redis基于该类型，提供了经纬度设置，查询，范围查询，距离查询，经纬度Hash等常见操作。



## Redis的发布和订阅

什么是发布和订阅
 Redis发布订阅(pub/sub) 是一种消息通信模式 :发送者(pub) 发送消息,订阅者(sub)接收消息。Redis客户端可以订阅任意数量的频道。

![image-20221201100050832](C:\Users\v\AppData\Roaming\Typora\typora-user-images\image-20221201100050832.png)

## 事务

Redis事务是一个单独的隔离操作∶事务中的所有命令都会序列化、按顺序地执行。事务在执行的过程中，不会被其他客户端发送来的命令请求所打断。
Redis事务的主要作用就是串联多个命令防止别的命令插队。

Multi、Exec、discard
从输入Mutⅰ命令开始，输入的命令都会依次进入命令队列中，但不会执行，直到输入
Exec后，Redis会将之前的命令队列中的命令依次执行。
组队的过程中可以通过discard来放弃组队。

![image-20221201214417010](C:\Users\v\AppData\Roaming\Typora\typora-user-images\image-20221201214417010.png)

#### 事务冲突问题

##### 悲观锁

顾名思义，就是很悲观，每次去拿数据的时候都认为别人会修改，所以每次在拿数据的时候都会上锁。这样别人想拿这个数据就会block直到它拿到锁。传统的关系型数据库里边就用到了很多这种锁机制，比如行锁，表锁等，读锁，写锁等，都是在做操作之前先上锁。

##### 乐观锁

顾名思义，就是很乐观，每次去拿数据的时候都认为别人不会修改，所以不会上锁，但是在更新的时候会判断一下在此期间别人有没有去更新这个数据，可以使用版本号等机制。乐观锁适用于多读的应用类型，这样可以提高吞吐量。Redis 就是利用这种check-and-set机制实现事务的。

## 持久化

在指定的时间间隔内将内存中的数据集快照写入磁盘，也就是行话讲的 Snapshot 快照，它恢复时是将快照文件直接卖到内存里

##### RDB

RDB持久化是将数据记录定时dump到磁盘上进行持久化，恢复数据通过读取原始数据加载到内存恢复数据，数据恢复快，效率高，但是数据容易丢失（数据同步有时间间隔）

##### AOF

AOF持久化是将操作日志追加形式写入文件，恢复数据通过命令恢复数据，缓存一致性更高，牺牲性能

## 主从复制

主机数据更新后根据配置和策略，自动同步到备机的master/slaver机制，Master以写为主，slave以读为主

## 集群

Redis集群通过**分区**(partition)来提供一定程度的可用性(availability):即使集群中有一部分节点失效或者无法进行通讯，集群也可以继续处理命令请求。

## 缓存穿透

一个一定不存在缓存及查询不到的数据，由于缓存是不命中时被动写的，并且出于容错考虑，如果从存储层查不到数据则不写入缓存，这将导致这个不存在的数据每次请求都要到存储层去查询，失去了缓存的意义。

#### 解决方案

1. 对空值缓存

   **操作**：为查找的数据不存在的key设置一个空值（key,null）。

   **缺陷**：①即便设置为null也照样占用了内存空间，因此最好设置较短的过期时间到期删除。②缓存层与数据库中的数据存在一段时间窗口的不一致，例如为数据设置5分钟过期，但刚好在这时间里，数据库中添加了该数据，在这段时间里，用户反而依旧会查询不到该数据。

2. 设置可访问的名单

3. 采用布隆过滤器

   将存在的key保存在布隆过滤器中，并让布隆过滤器作为第一层访问拦截，当验证所要查询的数据存在时，才能继续到缓存层中查询。

4. 进行实时监控

## 缓存击穿

key对应的数据存在，但在redis中过期，此时若有大量并发请求过来，这些请求发现缓存过期一般都会从后端DB加载数据并回设到缓存，这个时候大并发的请求可能会瞬间把后端DB压垮。

#### 解决方案

1. 预先设置热门数据
2. 实时调整
3. 使用锁

## 缓存雪崩

key对应的数据存在，但在redis中过期，此时若有大量并发请求过来，这些请求发现缓存过期一般都会从后端DB加载数据并回设到缓存，这个时候大并发的请求可能会瞬间把后端DB压垮。

缓存雪崩与缓存击穿的区别在于这里针对很多key缓存，前者则是某一个key

#### 解决方案

1. 构建多级缓存架构
2. 使用锁或队列
3. 设置过期标志更新缓存
4. 将缓存失效时间分散开

## 分布式锁

随着业务发展的需要，原单体单机部署的系统被演化成分布式集群系统后，由于分布式系统多线程、多进程并且分布在不同机器上，这将使原单机部署情况下的并发控制锁策略失效，单纯的Java API并不能提供分布式锁的能力。为了解决这个问题就需要一种跨JM的互斥机制来控制共享资源的访问，这就是分布式锁要解决的问题！

#### 实现方案

1. 基于数据库实现分布式锁
2. 基于缓存(Redis等)
3. 基于Zookeeper

