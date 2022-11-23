# :star2:蓝山2022Java第四次课:star2:

[TOC]

---

如果在场的各位坚持到我这节课了的话，恭喜你们，在Java这门编程语言的学习暂时告一段落了(bushi)，当然，一些卷王请自便**Deep Learning**

从第一节课，罗神的讲的Java语法基础

到第二节课，籍楠姐姐讲的面向对象基础，

到第三节课，海神介绍的面向对象进阶，相信各位坚持下来的卷王们，肯定对Java这门语言，有了完全不同的认识



那么Java第四节课，主要就是介绍一些Java的一些高级特性

- IO
- 异常处理
- 反射
- 泛型
- 线程
- ...

首先讲讲异常处理吧

艹，走，忽略

<img src="https://raw.githubusercontent.com/YxYL6125/imgBad/main/img/image-20221122222714567.png" alt="image-20221122222714567" align="left" style="zoom:80%;" />

## :boom:异常处理



在日常生活中，很多事情并非总是按照人们自己设计意愿顺利发展的，经常出现这样那样的异常情况。

> 例如：沃日，今天部门要开例会，我tm中午回寝室就睡觉了。部长打过来电话都没接到，下次指定没我好果汁吃....

<img src="https://raw.githubusercontent.com/YxYL6125/imgBad/main/img/image-20221028204320223.png" alt="image-20221028204320223" align="left" alignstyle="zoom:100%;" />

计算机程序的编写其实有时候也需要考虑处理这些异常情况，`异常(Exception)`，就是在运行程序的时候产生的一种异常情况

同时这也已经成为了衡量一门语言是否成熟的标准。目前的主流编程语言，如：C++、C#、Ruby、Rust、Python....等大都提供了异常处理机制

所以本节我们来介绍Java异常处理的相关知识

---

### 异常介绍

Java中的异常又称：**"例外"**，是一个在程序执行期间发生的时间，他中断正在执行程序的**正常指令**，或者**指令流**，(也就是一串指令)。为了能够及时、有效第处理程序中的运行错误，这个时候必须使用异常类，这可以让程序具有极其可观的**容错性和健壮性**。

在Java中，一个异常的产生，主要有以下三中情况：

- Java内部错误发生异常，Java虚拟机产生的异常
- 编写的程序代码中的错误所产生的的异常，例如`NullPointException`空指针异常、`IndexBoundException`数组越界异常等等
- 通过`throw`语句手动生成的异常，一般用来告知该方法的调用者一些必要信息
  `throw`这个关键字等下会讲，我知道你很急，但你先别急....
  <img src="https://raw.githubusercontent.com/YxYL6125/imgBad/main/img/image-20221028205457764.png" align="left" alt="image-20221028205457764" style="zoom:80%;" />























### Java中的异常处理机制与语法

语法格式：

> ```java
> try{
> 	//可能出现异常的的代码
>     	//if (try中的代码出现问题了 && 这里catch捕获的时候异常类型与try中跑出的异常类型一致的时候，或者是try中抛出异常的父类的时候，) ——>  就会对异常进行异常处理
>     	//处理完成后，就跳出 try-catch 语句，继续执行后续的代码
> 
> }catch(Exception e){//Exception 是需要捕获的异常类型
>    	e.printStackTrance();
>     	//...其他的一些异常处理
> }finally{
>  	//不管try代码里面是否出现了异常，
> }
> ```
>

一首小悟结束异常处理：

- 世界上最遥远的距离，是我在if里你在else里，
  似乎一直相伴有永远分离；
- 世界上最痴心的等待，是我当case你是switch，
  或许永远都选不上自己；
- 世界上最真情的相依，是你在try我在catch
  无论你发什么什么脾气，
  我都默默承受，静静处理
  到那时候，再来期待我们的finally

——来自BIlibili尚硅谷宋红康老师的笔记

---



### 异常的类型

为了能够及时有效的处理出现的异常，Java专门引入了异常类`(Exceptoin)`，在Java中所有异常类型都是内置`java.lang.Throwable`类的子类，也就是说，`Throwable`是站在**异常链顶端的男人**

<img src="https://raw.githubusercontent.com/YxYL6125/imgBad/main/img/image-20221029081055771.png" align="left" alt="image-20221029081055771" style="zoom: 90%;" />





**常见的异常类型**

| 异常类型名称              | 异常类型说明   |
| ------------------------- | -------------- |
| ArithmeticException       | 算数异常       |
| IndexOutOfBoundsException | 角标越界异常   |
| ClassCastException        | 类型转换异常   |
| NullPointException        | 空指针异常     |
| NumberFormatException     | 数字格式化异常 |
| ...                       | ...            |

```java
import java.util.ArrayList;
import java.util.List;

/**
 * @program: Courseware-Backend-Java-2022
 * @description: 常见异常类
 * @author: YxYL
 * @create: 2022-11-22 19:20
 **/

public class CommonException {
    public static void main(String[] args) {
        //NullPointerException
        Integer integer = null;
        System.out.println("integer.toString() = " + integer.toString());
        
        //ClassCastException
        Object x = new Integer(0);
        System.out.println((String)x);

        //IndexOutOfBoundsException
        List list = new ArrayList<>();
        System.out.println(list.get(0));
        
    }
}
```



---

### 自定义异常类

为什么要自定义异常类呢？？？？？

当我们的文件中出现多个异常的时候，显然`RuntimeException`就不能满足我们的需求了

从业务角度去理解的话，就比如处理一个文件，有可能会出现网络异常，有可能会出现数据库异常，有可能会出现……

这个时候自定义异常类，就相当于将他们细化了

手搓一个：

```java
/**
 * @program: Courseware-Backend-Java-2022
 * @description: 自定义异常类
 * @author: YxYL
 * @create: 2022-11-22 19:31
 **/
public class MeiQianException extends Exception {

    private Double money;

    public MeiQianException(Double money) {
        super("余额不足，还差：" + money);
        this.money = money;
    }

    public static class AccountAdmin {
        private Double balance;

        public AccountAdmin(Double balance) {
            this.balance = balance;
        }

        //存钱的方法
        public void deposit(double money) {
            this.balance += money;
        }

        //取钱的方法
        public void withdraw(double money) throws MeiQianException {
            if (balance >= money) {
                balance -= money;
            } else {
                double needMoney = money - balance;
                throw new MeiQianException(needMoney);
            }
        }
    }

    public static void main(String[] args) {
        AccountAdmin accountAdmin = new AccountAdmin(100.0);
        accountAdmin.deposit(200);//先存二伯块
        try {
            //再取四伯块
            accountAdmin.withdraw(400);
        } catch (MeiQianException e) {
            throw new RuntimeException(e);
        }
    }
}
```



### Throw、Throws

#### 什么是Throw

throw是java中关于异常的一种操作如果在`try{}catch{}`中使用了throw，即代表自己去书写这个方法——自己处理异常，也是一个**手动抓取**的动作，可以使程序停止并报出异常信息。

**其好处是：**

- 可以在自己定义的地方报出异常停止程序；
- 而不是在错误的地方报出异常停止程序

可以是自己书写的程序自己监测错误，而不需要最后一道防线JVM来处理错误

如果没有使用的话，报错的时候只能是JVM虚拟机来默认的报错处理(立即在报错位置停止程序的运行，报出异常原因)



#### 什么是Throws

`throws`是一个java的关键字，通常被应用在声明方法的时候，用来指定可能抛出的异常。声明之后，可以让Java的编译器对这个java文件进行正常的编译class文件(演示一下)，这也是平常我们所说的**自动抛出**的动作。一旦运行class文件期间出现错误，JVM虚拟机就会检测错误，停止程序的运行，并报出异常信息





#### 两者的区别

- 位置：throws放在方法声明的后面，表示报出异常，由该方法的调用者来处理；throw作用在方法内部，后面跟着的是异常的对象
- 功能：throws用来声明方法在运行过程中可能出现的异常，以便调用者根据不同的异常类型预先定义不同的处理方式；throw用来抛出封装了异常信息的对象，程序在执行到throw时后续的代码将不在执行，而是跳转到调用者，并将异常信息抛给调用者，也就是说，throw后面百度语句将无法被执行(finally语句块除外)





### 由以上总结出：Java处理异常的方式：

- 方式一：try-catch-finally
- 方式二：throws+异常类型





## :wrench:IO流

### 什么是IO流

这里引用百度的回答：

> 流是一种抽象概念，它代表了数据的无结构化传递。按照流的方式进行输入输出，数据被当成无结构的字节序或字符序列。从流中取得数据的操作称为提取操作，而向流中添加数据的操作称为插入操作。用来进行输入输出操作的流就称为IO流。换句话说，IO流就是以流的方式进行输入输出

简而言之就是：你的程序和系统之间读写文件的操作就是IO操作，和系统之间读写用的东西就是IO流

所以，Java IO流就是Java程序和操作系统之间通信用的方法

这里出于篇幅，时间原因，只简单介绍Java IO流中的**字节流**和**字符流**，其他流，像**NIO**，**AIO**，**BIO**这些，各位卷王有余力的话可以自己去学习。



### 字节流和字符流的区别

字节流和字符流操作的本质区别只有一个：

> 字节流是原生的操作，字符流是处理后的操作

搓个图，字节流在操作的时候不会用到缓冲区，也就是不会用到内存，直接操作文件本身；

而字符流在操作的时候使勇了缓冲区，通过缓冲区再操作文件：

<img src="https://raw.githubusercontent.com/YxYL6125/imgBad/main/img/image-20221122081522641.png" alt="image-20221122081522641" style="zoom: 200%;" align="left" />

那么为什么会有字符流而不直接使用字节流呢？非得搞个字符流出来。

我的理解是，字节流处理机多个字节表示的东西的时候，有可能会出现乱码的问题

比如说汉字，用字节流读取的时候有可能因为以为字节没有读到就变成了乱码，字符流呢，就完美的解决了这个问题。这样理解，`字节流+编码表=字符路`因为有了编码表，多以可以确定这个汉字有多少个字节，这样字节流就可以根据位数准确的写汉字了



### 字节流

字节流顾名思义。就是通过字节直接操作字符，更为的底层

字节流最基础的两个类就是`InputStream`和`OutputStream`。根据这两个派生而来类都含有`read()`和`write()`的基本方法，用来读写单个字节或者字节数组

- InputStream类是一个抽象类 ,是所有字节输入流类的父类。
- OutputStream类是一个抽象类，是所有字节输出流的父类

他俩常见的子类有：

| InputStream的常见子类 | 描述                                          |
| --------------------- | --------------------------------------------- |
| FileInputStream       | 用于从文件中读取信息                          |
| ByteArrayInputStream  | 字节数组输入流                                |
| ObjectInputStream     | 序列化时使用 一般和ObjectOutputStream一起使用 |
| FilterInputStream     | 过滤输入流,为基础的输入流提供一些额外的操作   |

| OutputStream的常见子类 | 描述                                         |
| ---------------------- | -------------------------------------------- |
| FileOutPutStream       | 文件输出流对文件进行操作                     |
| ByteArrayOutputStream  | 字节数组输出流                               |
| ObjectOutputStream     | 序列化时使用 一般和OjbectInputStream一起使用 |
| FilterOutputStream     | 过滤输出流,为基础的输出流提供一些额外的操作  |

举个栗子，我们往F盘下面hello.txt里面输入“hello world”，再将他读出来，在程序里面输出

```java
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @program: Courseware-Backend-Java-2022
 * @description: 字节流Test
 * @author: YxYL
 * @create: 2022-11-22 09:02
 **/

public class Byte {

    public static void main(String[] args) throws IOException {
        //根据文件夹的名字来创建对象
        FileOutputStream fileOutputStream = new FileOutputStream("F:\\hello.txt");
        //往文件里面一个字节一个字节的写入数据
        fileOutputStream.write((int)'h');
        fileOutputStream.write((int)'e');
        fileOutputStream.write((int)'l');
        fileOutputStream.write((int)'l');
        fileOutputStream.write((int)'o');

        
        //入文件里面一个字节数组的写入文件
        String s = " world";
        fileOutputStream.write(s.getBytes());
        fileOutputStream.close();
        
        
        
        //传文件夹的名字来创建对象
        FileInputStream fileInputStream = new FileInputStream("F:\\hello.txt");
        int len = 0;
        //一个字节一个字节的读出数据
        while((len = fileInputStream.read()) != -1){
            System.out.println((char)len);
        }
        //关闭流
        fileInputStream.close();
        //通过File对象来创建对象
        fileInputStream = new FileInputStream("F:\\hello.txt");
        
        byte []bytes = new byte[1024];
        //一个字节数组的读出数据
        while ((len = fileInputStream.read(bytes)) != -1){
            for(int i = 0; i< len ; i++){
                System.out.print((char) bytes[i]);
            }
        }
        //关闭流
        fileInputStream.close();
    }
}

```

再来个栗子，复制某个位置的图片，到指定位置下。平常我们都是用CV来复制粘贴，这次我们用程序来操作：

```java
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @program: Courseware-Backend-Java-2022
 * @description: 复制图片Test
 * @author: YxYL
 * @create: 2022-11-22 09:05
 **/

public class CopyPhoto {
    public static void main(String[] args) throws IOException {
        // 1.创建流对象
        // 1.1 指定数据源
        FileInputStream fis = new FileInputStream("F:\\元帅的艺术.jpg");
        // 1.2 指定目的地
        FileOutputStream fos = new FileOutputStream("test_copy.jpg");

        // 2.读写数据
        // 2.1 定义数组
        byte[] b = new byte[1024];
        // 2.2 定义长度
        int len;
        // 2.3 循环读取
        while ((len = fis.read(b))!=-1) {
            // 2.4 写出数据
            fos.write(b, 0 , len);
        }
        // 3.关闭资源
        fos.close();
        fis.close();
    }
}

```

基本上我们平常如果有使用到IO流的地方，常用的方法就上面栗子里面使用到的几个，详细的方法各位可以参照官方文档，或者CSDN上博主们总结的。这里就不造赘述了。



除了`FileInputStream`剩下3个`InputStream`的子类，平常开发中不经常用到，有兴趣的可以自行了解。





## :triangular_ruler:泛型T\R

### 泛型的解释

> 泛型属于一种程序语言的标准和规范，或者也可以说为特性

通俗解释就是：泛型其实就是我们参数化类型，就比如在定义List的时候，我们就可以写作`List<String>`这样我们就在编码阶段，将List存放的类型定义好。这样的话，我们在操作List的时候就可以通过泛型对我们集合内的值进行规范和约束，如果我们传入非法的类型时程序在编译阶段就能帮我们找出问题，就不会出现隐形的异常。

就比如下面的梨子，如果我们不通过泛型进行约束，直接开写，在运行代码的时候我们都是不知道会出错的，只有当程序run到这句话的时候才会抛出异常：

```java
List list = new ArrayList();
list.add("abc");
list.add(100);
for(int i = 0; i< list.size();i++){
    System.out.println("执行到第 " + (i+1) + " 次循环。");
    String str = (String) list.get(i);
    System.out.println(str);
}
```

run过后，执行结果：

```txt
执行到第 1 次循环。
abc
执行到第 2 次循环。
Exception in thread "main" java.lang.ClassCastException: java.lang.Integer cannot be cast to java.lang.String
    at com.zhj.business.test.Test01.main(Test01.java:18)
```

可以看出程完整的完成了第一次for

但是在第二次for的时候触发了类转换异常`ClassCastException`

当项目很大的时候，像这样的细节是很容易被忽略的，所以我们通过泛型，约束类型，这样我们在提供数据保存是对应类型即可，不需要进行强转，就不会出现上述问题





### 泛型的用法

泛型一共有三种用法，分别为：

- 泛型类

  ```java
  package entity;
  
  import java.io.Serializable;
  
  /**
   * @program: Courseware-Backend-Java-2022
   * @description: 返回结果类
   * @author: YxYL
   * @create: 2022-11-22 09:32
   **/
  public class Result<T> implements Serializable {
      /**
       * 状态码
       */
      private Integer code;
  
      /**
       * 消息
       */
      private String msg;
  
      /**
       * 返回数据
       */
      private T data;
      
      public Result() {
      }
  
      public Result(Integer code, String msg, T data) {
          this.code = code;
          this.msg = msg;
          this.data = data;
      }
  
  
      public Integer getCode() {
          return code;
      }
  
      public void setCode(Integer code) {
          this.code = code;
      }
  
      public String getMsg() {
          return msg;
      }
  
      public void setMsg(String msg) {
          this.msg = msg;
      }
  
      public T getData() {
          return data;
      }
  
      public void setData(T data) {
          this.data = data;
      }
  }
  ```

  这样的话，我们就可以定义一个，由我们自己    定义类型的数据域   的对象

  ```java
  Result<String> r1 = new Result<>();
  r1.setData("111");
  
  Result<Integer> r2 = new Result<>();
  r2.setData(111);
  
  Result<Boolean> r3 = new Result<>();
  r3.setData(true);
  ```

- 泛型接口

  ```java
  public interface List<E> extends Collection<E> {
  	......
  }    
  ```

- 泛型方法
  List接口中的`toArray()`方法就是经典的泛型方法

  ```java
  /**
   * 泛型方法的基本介绍
   * @param T[] 传入的泛型实参
   * @return T[] 返回值为T[]类型
   * 说明：
   *     1）public与返回值中间的<T>，可以理解为声明此方法为泛型方法。
   *     2）只有声明了<T>的方法才是泛型方法，泛型类中的使用了泛型的成员方法并不是泛型方法。
   *     3）<T>表明该方法将使用泛型类型T，此时才可以在方法中使用泛型类型T。
   *     4）与泛型类的定义一样，此处T可以随便写为任意标识，常见的如T、E、K、V等形式的参数常用于表示泛型。
   *     5）必须在权限声明public与返回值之间的<T>上添加上下边界，即在泛型声明的时候添加
   */
  public <T> T[] toArray(T[] a) {
      int size = size();
      if (a.length < size)
          return Arrays.copyOf(this.a, size,(Class<? extends T[]>) a.getClass());
      System.arraycopy(this.a, 0, a, 0, size);
      if (a.length > size)
          a[size] = null;
      return a;
  }
  ```




使用泛型方法的时候，通常不必指明类型参数，因为编译器会为我们找出具体的类型。

这称为类型参数推断`(type argument inference)`类型推断只对赋值操作有效，其他时候并不起作用

> 关于泛型数组：在Java中，在running时在数据中推送任何不兼容的类型将抛出`ArrayStoreException`
>
> 这意味着数据在running时保留期类型信息，而泛型在running时使用类型擦除，由于上述冲突，不允许在Java中实例化泛型数组



### 类型通配

在泛型代码中，`?`通配符表示位置类型。例如`List<?>`，在逻辑上是List、List等所有List<类型实参>的父类



#### 无界通配符

`<?>`叫做无界通配符，意味着对类型实参没有任何限制

```java
ArrayList<?>  list = new ArrayList<Long>();  
ArrayList<?>  list = new ArrayList<String>();  
ArrayList<?>  list = new ArrayList<Employee>();  
```

可以使用无界通配符来缩小类型参数的类型范围

#### 上界通配符和下界通配符

这里就不讲上下界通配符了，一是平常开发的时候一般来说不用我们手写通配符；二是确实有点难以理解，各位如果有余力的可以自己下来钻研 ——> [这里](https://juejin.cn/post/7126717331522191368) 



### 泛型不被允许的那些事儿

- **不能在类中定义泛型参数化的静态属性。**
  任何这样做的尝试都会产生编译时错误：`Cannot make a static reference to the non-static type T.`
  比如像这样：

  ```java
  public class GenericsExample<T>
  {
     private static T member; //编译不通过
  }
  ```

  <img src="C:/Users/%E9%9B%B7%E7%A5%9E%E6%88%98%E6%9C%BA/AppData/Roaming/Typora/typora-user-images/image-20221122191241756.png" align="left" alt="image-20221122191241756" style="zoom:150%;" />

- **不能创建类型参数的实例。**
  任何创建T实例的尝试都将失败
  抛出编译错误：`Cannot instantiate the type T`
  比如像这样

  ```java
  public class GenericsExample<T>
  {
     public GenericsExample(){
        new T();
     }
  }
  ```

  <img src="https://raw.githubusercontent.com/YxYL6125/imgBad/main/img/image-20221122191414631.png" align="left" alt="image-20221122191414631" style="zoom:150%;" />

- **泛型的类型参数不能是类型**
  泛型的类型参数不能使用`int` `double`这样的基本类型，必须是引用类型，如果需要，请使用起对应的包装类型`Integer` `Double`等
  比如这样：

  ```java
  List<int> list1 = new ArrayList<>();    // 不允许
  List<Integer> list2 = new ArrayList<>();	//可以
  ```

  <img src="https://raw.githubusercontent.com/YxYL6125/imgBad/main/img/image-20221122191324448.png" align="left" alt="image-20221122191324448" style="zoom:150%;" />

- **不能创建泛型的异常类**

  ```java
  public class GenericException<T> extends Exception {}
  ```

  <img src="https://raw.githubusercontent.com/YxYL6125/imgBad/main/img/image-20221122191100836.png" align="left" alt="image-20221122191100836" style="zoom: 150%;" />











## :slot_machine:线程

**什么是线程？**

> 线程是程序执行的时候最小单位，他是进程的一个执行流，是CPU调度和分配资源的基本单位

一个进程可以有很多个线程组成，线程之间共享进程的所有资源，每个线程有自己的堆栈和局部变量

线程有CPU独立调度执行，在多CPU环境下就允许多个线程同时运行。同样多线程也可以实现并发操作，每个请求分配一个线程来处理

<img src="https://raw.githubusercontent.com/YxYL6125/imgBad/main/img/image-20221122195404294.png" align="left" alt="image-20221122195356392" style="zoom:80%;" />



**什么是进程？**

> 进程是资源(CPU、内存等等)分配的最小，或者说基本单位，他是程序执行时的一个实例

程序run的时候系统就会创建一个进程，并为他分配资源，然后吧这个进程放到进程就绪队列，进程调度器选中它的时候，就会为他分配CPU时间，程序真正开始run

<img src="https://raw.githubusercontent.com/YxYL6125/imgBad/main/img/image-20221122195356392.png" align="left" alt="image-20221122195356392" style="zoom:80%;" />





**线程和进程的区别**

根据上面的一些介绍，我们知道的是进程和线程的概念是在建立在操作系统层面上的

进程是程序的一次执行，进程是资源（CPU、内存等）分配的基本单位，它是程序执行时的一个实例。线程是 CPU 的基本调度单位，是进程的一个执行流。

在 Java 程序中体现在 main 方法的执行是一个进程，我们在 main 方法中通过 Thread 类来创建多个线程进而运行他们，也就达到多线程的目的。



### 创建线程的几种方式

那么我们来如何实现多线程呢：

- 继承Thread对象
- 重写run方法
- 创建线程对象
- 调用start()方法

这里我们介绍4种方式来创建线程

#### 实现Runable接口

```java
  class MyRunable implements Runnable {
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName()+"线程跑起来了!!!");        
        }
    }
```



#### Callable+Future Task方法

```java
class MyCallable implements Callable<String> {
   private int n;

   public MyCallable(int n) {
       this.n = n;
   }

   //重写call方法(任务方法)(求n的加和)
   @Override
   public String call() throws Exception {
       int sum = 0;
       for (int i = 0; i <= n; i++) {
           sum += i;
       }
       return "子线程执行的结果是：" + sum;
   }
}
```



#### 匿名内部类

```java
 new Thread(
     () -> {
        for (int i = 0; i < 10; i++) {
            System.out.println("子线程执行输出" + i);
            }
        }
).start();
```

我们来对比一下这3种方式

| 方式                        |         优点         |          缺点          |
| :-------------------------- | :------------------: | :--------------------: |
| 继承Thread类                |     编程比较简单     |        扩展性差        |
| 实现Runnable接口            |       扩展性强       | 相对复杂，没有返回结果 |
| 实现Callable+FutureTask接口 | 扩展性强，可得到结果 |        编程复杂        |



细心的你肯定已经发现了，明明上面说的是《四种方式》，但这里只讲了三种，诶~你别急

除了继承Thread类，实现Runnable接口，实现Callable接口之外，最后一种创建线程的方式就是用线程池的方式，创建管理线程。



### 线程池

>- 线程池里固定依一些线程，并重复利用这些线程
>- 有利于系统进程，防止资源的耗尽



​	**如何得到线程池对象：**

>- 使用**ExecutorService（接口）**的实现类：**TreadPollExecutor** 自创建一个线程池对象
>
>```java
>ExecutorService pools = new ThreadPoolExecutor(
>              3,//核心线程个数 corePoolSize
>              5,//最大线程量 maximumPoolSize
>              8,//最大时间 keepAliveTime
>              TimeUnit.SECONDS,//
>              new ArrayBlockingQueue<>(6),//任务队列
>              Executors.defaultThreadFactory(),//线程工厂
>              new ThreadPoolExecutor.AbortPolicy());//任务拒绝策略
>```
>
>- 这七个参数便是
>  1. int `corePoolSize`：**核心线程数**
>  2. int `maximumPoolSize`：**最大创建线程数**
>  3. long `keepAliveTime`：**存活时间**
>  4. TimeUnit `unit`：**Time类管理工具**
>  5. BlockingQueue<Runnable> `workQueue`：指定阻塞队列
>  6. ThreadFactory `threadFactory`：**线程工厂**
>  7. RejectedExecutionHandler `handler`：**拒绝策略**
>- 使用 **Executor** （线程池对象的工具类）调用方法返回不同特点的线程池对象 



#### 		处理Runnable任务

>**如何丢入线程池(如何处理Runnable任务)：**
>
>```java
>// 使用静态变量记住一个线程池对象
>private static ExecutorService pool = new ThreadPoolExecutor(3, 5, 6, TimeUnit.SECONDS,
>                                        new ArrayBlockingQueue<>(2),
>                                        Executors.defaultThreadFactory(),
>                                        new ThreadPoolExecutor.AbortPolicy());
>```
>
>```java
>  ExecutorService pools = new ThreadPoolExecutor(
>                3,//核心线程个数 corePoolSize
>                5,//最大线程量 maximumPoolSize
>                8,//最大时间 keepAliveTime
>                TimeUnit.SECONDS,//
>                new ArrayBlockingQueue<>(6),//任务队列
>                Executors.defaultThreadFactory(),//线程工厂
>                new ThreadPoolExecutor.AbortPolicy());//任务拒绝策略
>
>		// pool 的 execute 方法
>        pools.execute(() -> {
>            for (int i = 0; i < 50; i++) {
>                System.out.println(Thread.currentThread() + "起飞！！！" + "第" + i + "次");
>            }
>        });
>
>        pools.execute(() -> {
>            for (int i = 0; i < 50; i++) {
>                System.out.println(Thread.currentThread() + "GGGGGG" + "第" + i + "次");
>            }
>        });
>
>        pools.shutdown();
>```

>**如何关闭线程池：**
>
>```java
>//(开发中一般不会使用)。
>pool.shutdownNow();//立刻关闭，即使任务没有完成，丢失任务的。
>pool.shutdowm();//会等待全部任务执行完毕之后再关闭
>```



为什么大型并发系统环境中使用 **Executors** 如果不注意可能会出现系统风险捏？？？

这个就作为作业，留给各位啦



### 线程安全问题

什么是线程安全问题：

> 多个线程同时共共享一个资源，并对他进行修改

有一天，老师布置了这样一个问题：使用两个线程将变量`count`自增`10`万次，每个线程承担`5`万次的自增任务，变量`count`的初始值为`0`。
这个问题很简单，最终的结果我们也能够口算出来，答案就是`10`万。
小明同学做事非常迅速，很快就写出了下面的一段代码：

```java
class Counter {//定义一个资源类
    private int count;

    public void increase() {
        ++this.count;
    }

    public int getCount() {
        return this.count;
}

public static class Test {

    private static final int CNT = 50000;
    private static final Counter counter = new Counter();

    public static void main(String[] args) throws InterruptedException {

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < CNT; i++) {
                counter.increase();
            }
        });
        Thread thread2 = new Thread(() -> {
            for (int j = 0; j < CNT; j++) {
                counter.increase();
            }
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println(counter.getCount());
    }
}

```

运行的结果比`10`万要小，你可以试着运行该程序你会发现每次运行的结果都不一样，但绝大部分情况，结果都会比预期的值要小，

上面我们使用多线程运行了一个程序，将一个变量值为0的变量自增10万次，但是最终实际结果比我们预期结果要小，原因就是线程调度的顺序是随机的，造成线程间自增的指令集交叉，导致运行时出现两次自增但值只自增一次的情况，所以得到的结果会偏小。



#### 		解决方法 ->线程同步

 线程同步的思想：

>加锁：让多个线程依次访问同一个资源

```java
synchronized (someObject){ //someObject 为 锁对象 -> 唯一
  //此处的代码只有占有了someObject后才可以执行
  //行为
}
```

```java
class Counter {
    private int count;
    synchronized public void increase() {
        ++this.count;
    }
    public int getCount() {
        return this.count;
    }
}
```



>锁对象为 **任意的** 唯一对象到底好不好呢：
>
>- 不好，**影响其线程的进行**
>
>规范上：
>
>- 建议使用**共享资源**作为锁对象：
>- 对于实例方法建议使用**this**作为锁对象
>- 对于静态方法建议使用**字节码（类名：class）**对象作为锁对象
>
>
>```java
>class Counter {
>    private int count;
>    public void increase() {
>        synchronized (this){//这里就直接用this关键字
>            ++this.count;
>        }
>    }
>    public int getCount() {
>        return this.count;
>    }
>}
>
>```



#### 		同步方法

>直接在返回值类型(int void long···)前面 +  **synchronized**就可以了
>
>```java
>class Counter {
>    private static int count;
>       synchronized public static void increase() {
>           ++count;
>    }
>       public int getCount() {
>           return this.count;
>         }
>     }
>     ```
>     
>     如果一个类，其**方法都是有synchronized修饰的**，那么该类就叫做**线程安全的类**
>     
>     同一时间，只有一个线程能够进入 **这种类的一个实例** 的去修改数据，进而保证了这个实例中的数据的安全(不会同时被多线程修改而变成脏数据)

常见的用法差不多就是这些，对于线程加锁（线程拿锁），如果两个线程同时拿一个对象的锁，就会产生锁竞争，两个线程同时拿两个不同对象的锁不会产生锁竞争。
对于`synchronized`这个关键字，它的英文意思是同步，但是同步在计算机中是存在多种意思的，比如在多线程中，这里同步的意思是“互斥”；而在IO或网络编程中同步指的是“异步”，与多线程没有半点的关系。



#### :star2:synchronized的工作原理

1. 获取互斥锁`lock`
2. 从主内存拷贝变量到工作的内存
3. 执行代码
4. 将更改后面的共享变量的值刷新到主内存
5. 释放互斥锁`unlock`

`synchronized`同步块在对同一条线程来说是可重入的，不会出现自己吧自己锁死的问题——死锁问题，这个以后再做介绍

综上：`synchronized`关键字加锁有如下性质：互斥性，刷新内存性，可重入性



至于后面的用`Lock锁`的方式，我们留到后面的JUC...





## :newspaper:反射

### 反射概述

> Java程序在running状态的时候，
>
> 对于任何一个类，我们都能够知道这个类的所有属性和方法；
>
> 对于人户一个对象，都能够调用它的任何属性和方法，
>
> 这种动态获取信息；以及动态调用对象方法的功能，被我们称之为Java语言的反射机制

接下来说说怎么使用反射

### 反射的使用

首先来介绍一下相关的**API**

- **获取Class对象：**

  ```java
  Class.forName("全类名");
  类名.class;
  对象.getClass();
  ```

<img src="https://raw.githubusercontent.com/YxYL6125/imgBad/main/img/image-20221121223935141.png" alt="image-20221121223935141" style="zoom:50%;" align="left" />

- **反射获取构造方法：**
  Class类中用于获取构造方法的方法

  | 方法名                                                       | 描述                           |
  | ------------------------------------------------------------ | ------------------------------ |
  | Constructor<?>[] getConstructors()                           | 返回所有公共构造方法对象的数组 |
  | Constructor<?>[] getDeclaredConstructors()                   | 返回所有构造方法对象的数组     |
  | Constructor<T> getConstructor(Class<?>... parameterTypes)    | 返回单个公共构造方法对象       |
  | Constructor<T> getDeclaredConstructor(Class<?>... parameterTypes) | 返回单个构造方法对象           |

  Constructor类中用于创建对象的方法

  | 方法名                            | 描述                        |
  | --------------------------------- | --------------------------- |
  | T newInstance(Object... initargs) | 根据指定的构造方法创建对象  |
  | void setAccessible(boolean flag)  | 设置为true,表示取消访问检查 |

- **反射获取成员变量**

  | 方法名                              | 描述                           |
  | ----------------------------------- | :----------------------------- |
  | Field[] getFields()                 | 返回所有公共成员变量对象的数组 |
  | Field[] getDeclaredFields()         | 返回所有成员变量对象的数组     |
  | Field getField(String name)         | 返回单个公共成员变量对象       |
  | Field getDeclaredField(String name) | 返回单个成员变量对象           |
  | void set(Object obj, Object value)  | 给指定对象的成员变量赋值       |
  | Object get(Object obj)              | 返回指定对象的Field的值        |

- **反射获取成员方法并运行**
  `Object invoke(Object obj,Object ... args)`

看完上面的API过后，可能大家头都大了

<img src="https://raw.githubusercontent.com/YxYL6125/imgBad/main/img/image-20221121213629013.png" alt="image-20221121213629013" style="zoom:80%;" align="left" />

接下来说说该如何做吧：

1. 得到Class对象：

   ```java
   //静态方法：forName(权限名：包名 + 类名 );
   //方法1
   Class<?> class1 = Class.forName("entity.Student");
   //方法2
   Student student = new Student();
   Class class2 = student.getClass();
   //方法3
   Class class3 = Student.class;
   ```

   

2. 获取Constructor对象：

   - 所有构造器

   - 有参构造器

     ```java
     Class class1 = Class.forName("entity.Student");
     Constructor<?> constructor = class1.getDeclaredConstructor(String.class, int.class);
     System.out.println(constructor.getName() +//提取构造器名称
             "===>" +
             constructor.getParameterCount());//提取构造器参数个数
     ```

3. 创建对象类：

   ```java
   Class class1 = Class.forName("entity.Student");
   Constructor<?> constructor = class1.getDeclaredConstructor(String.class, int.class);
           System.out.println(constructor.getName() +//提取构造器名称
                   "===>" +
                   constructor.getParameterCount());//提取构造器参数个数
           Object o = constructor.newInstance("yxy;",19);
   ```
   
4. 成员变量对象

   ```java
   //方法1
   Class class1 = Class.forName("entity.Student");
   
   //获取对象
   Object o = constructor.newInstance("yxyl",19);
   
   //获取字段对象
   Field field = class1.getDeclaredField("name");
   field.setAccessible(true);
   //获取字段值
   Object value = field.get(o);
   System.out.println("value = " + value);
   //设置字段值
   field.set(o,"杨晨");
   Object overwriteValue = field.get(o);
   System.out.println("overwriteValue = " + overwriteValue);
   ```

   

5. 成员方法提取

   ```java
   //这里给Student类添加一个成员方法
   public class Student {
   ...
    public void eat(String food){
           System.out.println(this.name+"吃了"+food);
       }
   ...
   }
   
   //方法1
   Class class1 = Class.forName("entity.Student");
   
   //获取对象
   Object o = constructor.newInstance("yxyl",19);
   
   Method method = class1.getDeclaredMethod("eat", String.class);
   
   method.invoke(o, "学校的逆天饭菜");
   ```





### 反射的作用

说了这么多，敲了这么多，为什么要有反射这个东西呢，明明我们平常直接new，就可以得到一个对象

> 对，没对象，就new一个吧

原因是：通过反射可以是程序代码访问装载到JVM中的类的内部信息，以此来：

- 获取已装载类的成员变量信息
- 获取已装载类的方法
- 获取一装载类的构造方法信息



这里就以我目前做的一个项目一小块来说吧，具体的就不在课件里面展示，到时候上课的时候说。

---

但是万物都有两面性，

> 毕竟new出来的对象始终看得到，但是摸不着，反射也当然是

其最突出的便是其性能问题：

是用反射基本上是一种解释操作，用于字段和方法接入时要远慢于直接代码(意思就是，通过反射得到一个对象，远远不如直接new)。因此，Java反射机制主要是应用在对灵活性和扩展性要求很高的系统框架上，基本程序不建议使用。

使用反射会模糊程序内部逻辑

程序人员希望在源代码中看到程序的逻辑，而反射等技术绕过了源代码的技术，因而会带来维护问题，反射代码比相应的直接代码更复杂



至于反射的代理模式，各位卷王请便 ——> [这里](https://blog.csdn.net/weixin_43438052/article/details/117292687?ops_request_misc=%7B%22request%5Fid%22%3A%22164172376316781683959885%22%2C%22scm%22%3A%2220140713.130102334.pc%5Fall.%22%7D&request_id=164172376316781683959885&biz_id=0&utm_medium=distribute.pc_search_result.none-task-blog-2~all~first_rank_ecpm_v1~rank_v31_ecpm-14-117292687.pc_search_insert_ulrmf&utm_term=Java%E5%8A%A8%E6%80%81%E4%BB%A3%E7%90%86%E9%87%8D%E8%A6%81%E5%90%97&spm=1018.2226.3001.4187)



## :speech_balloon:网络编程

这里出于时间原因和篇幅原因，就不讲网络编程的相关知识，等后面讲**计网知识**的时候会详细讲



**......**

