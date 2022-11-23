# :heart_eyes:  2022蓝山工作室: Java方向第三节课  



![img](https://github.com/YuanErya/-Java-/blob/main/picture/%E7%8B%97%E5%A4%B4.jpg?raw=true)





# 面向对象进阶



在上周我们已经了解了java面向对象的基础部分，面向对象的概念还有面向对象的三大特性（封装，继承，多态），相信大家应该已经比较熟悉了吧。Java的面向对象是很重要的一部分，这节课我们会在上次课的基础之上继续讲解java面向对象的进阶部分。



## final和static关键字

### final关键字

final关键字代表最终，不可改变的意思，从字面上相信大家大概也能猜测到它的大概使用效果。

#### final的主要用法：

- 1.可以用来修饰一个类

当final关键字修饰一个类时，格式为：

```java
public final class 类名称{
//...
} 
```

含义：当前该类不可有任何子类，即**不能被继承**

**注意：一个类如果是final修饰的，那么其中所有方法都无法进行覆盖重写（因为没有子类）**

- 2.可以用来修饰一个方法

当final关键字修饰一个方法时，这个方法就是最终方法，也就是**不能被覆盖重写**。强行覆盖重写就报错。

格式：

```
修饰符 final 返回值类型 方法名称（参数列表）{
//方法体
}
```

注意事项

**对于类和方法来说，abstract关键字（下面会讲到）和final关键字不能同时使用，因为矛盾。**

- 3.还可以用来修饰一个局部变量

**一旦使用final用来修饰局部变量，那么这个变量就不能进行更改**

**一次赋值，终生不变，再次赋值相同的值也不可以。**

**对于基本类型来说，不可变说的是变量中的数据不可改变**

**对于引用类型来说，不可变说的是变量中的地址值不可改变**

```java
        final Student stu =new Student("杨姐姐");
        //stu=new Student("赵丽颖");//错误写法，地址值不可改变
        stu.setName("彬哥哥");//正确写法,对于引用类型来说，不可变说的是变量中的地址值不可改变，地址所指向的数据是可以修改的
        System.out.println(stu.getName());
```

- 4.还可以用来修饰一个成员变量

对于成员变量来说，如果使用final关键字修饰，那么这个变量照样不可改变

1.由于成员变量有默认值，所以使用final之后就必须手动赋值，不会再给默认值了。

2.对于final的成员变量，要么使用直接赋值，要么通过构造方法赋值，二选其一。

3.必须保障类当中的所有重载的构造方法，都最终会对final的成员变量赋值



#### **使用final关键字所能带来的好处：**

- final关键字提高了性能。JVM和Java应用都会缓存final变量。使用final关键字，JVM会对方法、变量及类进行优化。

- 创建不可变类要使用final关键字。不可变类是指它的对象一旦被创建了就不能被更改了。String是不可变类的代表。不可变类有很多好处，譬如它们的对象是只读的，可以在多线程环境下安全的共享，不用额外的同步开销等等。

  

### 静态static关键字

所谓static，就是静态的意思。static关键字主要有以下的一些特点。

1.如果一个成员变量使用static，那么这个变量不再属于对象自己，而是属于所在的类，**多个对象共享一份数据**

典例使用：生成对象个数自动计数器

```java
public class Count {
    //静态的全局int类型用于统计该类被实例化的次数
    public static int count = 0;
    public Count() {
        count++;
        System.out.println("第" + count + "次创建对象！！！");
    }
}
```

2.成员方法使用static修饰，成为了静态方法。**静态方法不属于对象，而属于类**

对于静态方法来说，可以通过对象名调用，也可以通过类名称调用。

但更推荐用类名称调用（毕竟该方法是隶属于整个类的，而不是某个对象）。

**注意事项：**

1.**静态方法不能直接访问非静态**

原因：因为内存当中是**<u>先有静态（在编译时就已经分配空间了），后才有非静态内容</u>**。

注意：

在程序运行的过程中，static修饰的静态变量和全局变量都是储存在一个叫全局数据区的地方。也并不是说全局变量在定义时加了static关键字才是静态存储，不加static就是动态存储，不是的。不管加不加static，全局变量都是存储在静态存储区的，都是在编译时分配存储空间的，两者只是作用域不同，全局变量默认具有外部链接性，作用域是整个工程，**全局静态变量的作用域仅限本文件，不能在其他文件中引用**

2.**静态方法当中不可使用this** 

原因：this代表当前对象，通过谁调用的方法，谁即为当前对象，但是静态方法并不会属于某个对象，而是属于整个类。



大家看一下这段代码，应该比较熟悉吧(彬彬哥带着大家写的第一个程序Hello World!:heart_eyes: :heart_eyes: ）

```java
public class Test
{
    public static void main(String[] args)
    {
        System.out.println("(♥◠‿◠)ﾉﾞ  hello world!   ლ(´ڡ`ლ)ﾞ  \n" +
                " .-------.       ____     __        \n" +
                " |  _ _   \\      \\   \\   /  /    \n" +
                " | ( ' )  |       \\  _. /  '       \n" +
                " |(_ o _) /        _( )_ .'         \n" +
                " | (_,_).' __  ___(_ o _)'          \n" +
                " |  |\\ \\  |  ||   |(_,_)'         \n" +
                " |  | \\ `'   /|   `-'  /           \n" +
                " |  |  \\    /  \\      /           \n" +
                " ''-'   `'-'    `-..-'              ");
    }
}
	
```

大家可以看到我们java程序的main函数也是有static关键字修饰的。就简单和大家简单讲一下这里为什么main函数也是静态的。

为什么 main 方法是静态的（static）？

static是静态修饰符，被他修饰的方法我们称之为静态方法，静态方法有一个特点，那就是静态方法独立于该类的任何对象，它不依赖类特定的实例，被类的所有实例共享。只要这个类被加载，Java虚拟机就能根据类名在运行时数据区的方法区内定找到他们。而对于main方法来说，他的调用过程是经历了类加载、链接和初始化的。但是并没有被实例化过，这时候如果想要调用一个类中的方法。那么这个方法必须是静态方法，否则是无法调用的。（简言之，在程序刚刚启动时还不存在任何的对象，此时静态的main方法将执行并构造程序所需要的对象）



### 静态代码块

格式：

```Java
public class 类名称{
  //静态代码块  
static{
//静态代码块内容
}
}
```

静态代码块直接写在类当中。

**特点：当第一次执行本类时，静态代码块执行唯一一次**

主要用途：用来一次性地对静态成员变量进行赋值。

#### 使用static关键字的好处：

- 不需要创建具体的对象，便可以直接调用类当中的静态方法
- 在类中修饰变量时，可以实现同一个类当中的所有对象共享一份数据
- 被static修饰的部分只在类第一次加载时被初始化一次，可以提高程序性能。





## 抽象类

在说抽象类之前，我们有必要先了解一下什么是抽象方法。

### 抽象方法

抽象方法只有声明，而没有具体的实现，如果父类中的方法还不能确定如何具体进行{ }实现，那么这就应该写成一个抽象方法

1.抽象方法的定义：

加上abstract关键字，去掉大括号，直接分号结束

```java
//如下
abstract void eat();
```

抽象方法只能存在抽象类或者接口当中才行，所以要在class（及在定义类的时候）前写上abstract才行（但是抽象类中并不是只能有抽象方法，可以存在有具体方法体的方法）

```java
public abstract class Animal{

}
```

那现在我们在回过头来讲什么是抽象类。

抽象类就是被abstract关键字所修饰的类。抽象类里面一般都包含有抽象方法（并不是必须有抽象方法，但是抽象类中不含有抽象方法，那么也就失去了将它定义为抽象类的意义了）。我觉得抽象类其实可以理解成一张不完整的设计图，一般作为父类，让子类来继承。当父类知道子类一定要完成某种行为，但是每个子类该行为的实现又不同，于是该父类就把该行为定义成抽象方法的形式，具体实现交给子类去完成。此时这个类就可以声明成抽象类。

2.如何使用抽象类和抽象方法：

- 不能直接创建new 抽象类对象

- 必须用一个子类来继承抽象父类

- 子类必须覆盖重写抽象父类中的所有抽象方法（如果子类没有重写抽象父类的全部抽象方法，则该子类也必须为抽象类）

覆盖重写（实现）：子类去掉抽象方法的abstract关键字，然后补上方法体

- 创建子类对象开始使用

3.注意事项：

- 抽象类中可以有构造方法，是供子类创建对象时，初始化父类成员使用的。

理解：子类的构造方法中有默认的super（），需要访问父类的构造方法

- 抽象类中，不一定包含抽象方法

- 抽象类的子类，必须重写抽象父类中所有的抽象方法，否则无法通过编译而报错，**除非该子类也是抽象类**



现在我就用代码来演示一下抽象类的实现过程

```java
public class Test {
    //main方法用于测试
    public static void main(String[] args) {
        //老虎
        Animal tiger = new Tiger();
        tiger.eat();
        //羊
        Animal sheep = new Sheep();
        sheep.eat();
        //上面是使用Animal来接收了子类对象，是使用了向上转型（面向对象的多态）
    }
}
//定义的一个抽象类
abstract class Animal {
    String name;
    public Animal(String name) {
        this.name = name;
    }
    abstract void eat();
}

//定义的实现上方抽象类的一个子类
class Tiger extends Animal {
    public Tiger() {
        super("老虎");
    }
    @Override
    void eat() {
        System.out.println("我是" + super.name + "我要吃肉！");
    }
}
//定义的实现上方抽象类的一个子类
class Sheep extends Animal {
    public Sheep() {
        super("羊");
    }
    @Override
    void eat() {
        System.out.println("我是" + super.name + "我要吃草！");
    }
}
```



### 抽象类与普通的类的不同：

- 最大的区别主要就是，抽象类不能直接用new关键字进行实例化操作，必须由它的子类来实现该抽象父类的全部抽象方法。
- 抽象类中可以定义不含有方法体的抽象方法，但是抽象方法必须声明为`public`或者`protected`，如果未加任何修饰符则默认为public类型。

抽象类更像是定义了一种规范，只要继承于该抽象类的子类都要按照抽象类中的抽象方法的声明来进行实现。

## 接口 - Interface

怎么来理解接口这个概念喃。先讲点通俗易懂的。

在⽇常⽣活中，两个实体之间进⾏连接的部分称为接⼝。如电脑和U盘连接的标准USB接⼝。接⼝可以确保不同实体之间的顺利连接。如不同的电脑⼚家和U盘⼚家只要按照相同的USB接⼝进⾏⽣产，那么所有的电脑和U盘就可以顺利的连接起来。USB就像是定义出来了一种规范，各厂家只需要按照这种规范来生产接口，就能实现连接的功能。

大家都知道在我们java中，类是只能进行单继承的，一个类它只能拥有一个父类。为了解决类只能单继承所带来的问题，又引入了接口这个概念，一个类是可以实现多个接口的。

接口更像是抽象类的延伸，一般情况下接口中大部分的方法都是没有方法体（Jdk8以前，接口中是直接不允许有方法体的，会直接报错，但在jdk8以及后来的版本的jdk当中，又开始允许接口中的方法有方法体），我们可以把它看作更加纯粹的抽象类。接口中方法的修饰符必须是public和abstract其他的权限修饰符都会导致编译不通过，接口中的方法也可以不写权限修饰符，它会默认为public，接口中的所有属性的修饰符都默认是public static final。

在接口中可以定义实现的方法体是java8的一大特性，可以定义多个静态或者默认的方法，静态必须加上static，默认方法必须加上default关键字。

(1)如果多个接口定义了同样的静态方法，即使存在实现类，同时实现多个接口，仍然是不可使用实现类名调用接口的方法

(2)如果多个接口定义了同样的默认方法，实现类实现多个接口时，必须重写掉默认方法，否则编译失败。

总的来说其实**不是很推荐**在接口中写已经实现的方法（即拥有具体方法体的方法）

### 接口的定义以及实现：

下面就是通过代码来演示一下接口的定义以及接口的实现

```java
public class Test {
    public static void main(String[] args) {
        Plane plane = new Plane();
        plane.ToFly();
        Bird bird = new Bird();
        bird.ToFly();
    }
}
//定义了一个接口
interface Fly {
    void ToFly();//抽象方法去飞行
}
//定义了实体类飞机并实现了飞行接口
class Plane implements Fly {
    @Override
    public void ToFly() {
        System.out.println("我是飞机，我是靠喷气飞行！");
    }
}
//定义了实体类飞机并实现了飞行接口
class Bird implements Fly {
    @Override
    public void ToFly() {
        System.out.println("我是小鸟，我是靠振动翅膀飞行！");
    }
}
```



### 接口回调:

接口回调是指：可以把使用某一接口的类创建的对象的引用赋给该接口声明的接口变量，那么该接口变量就可以调用被类实现的接口的方法。实际上，当接口变量调用被类实现的接口中的方法时，就是通知相应的对象调用接口的方法，这一过程称为对象功能的接口回调

先通过一段演示代码做一个讲解吧

```java
interface Fight {
    void beAttacked(Hero hero);
}
//英雄战斗类实现了Fight接口
class HeroFight implements Fight {
    @Override
    public void beAttacked(Hero hero) {
        System.out.println("英雄受到了伤害值");
        hero.setHp(hero.getHp() - 50);
        if (hero.getHp() <= 0) {
            System.out.println("该英雄受到过多伤害，已阵亡。");
            hero.Die();//回调实体类Hero的方法
        }
    }
}
//英雄类
class Hero {
    private Integer hp = 100;
    private Boolean isDead = false;
    //战斗的方法
    public void toFight(Fight fight) {
        fight.beAttacked(this);
    }
    public void Die() {
        this.isDead = true;
    }
    public Integer getHp() {
        return hp;
    }
    public void setHp(Integer hp) {
        this.hp = hp;
    }
}
```

在英雄的战斗方法中Fight接口的实现类HeroFight的一个实例对象作为参数传进方法里，调用该类的beAttacked方法，如果hp<=0就会调用英雄的Die方法。回调主要就是体现在beAttacked方法中调用了Hero的Die方法，所以Die方法也就是**回调函数**

### Lambda表达式

简单讲一下这个Lambda表达式，Lambda表达式是在java8所新增的新特性。其实我感觉这个不太常用，这种表达不是很通俗易，但是写这种代码挺有逼格的。

lambda表达式允许你通过表达式来代替功能接口。 lambda表达式就和方法一样,它提供了一个正常的参数列表和一个使用这些参数的主体(body,可以是一个表达式或一个代码块)。 **Lambda 表达式可以看作是一个匿名函数**，基于数学中的λ演算得名，也可称为闭包。

换句简单的话说： Lambda表达式允许你直接把一个代码块赋值给一个变量

要了解Lambda表达式,首先需要了解什么是函数式接口，**函数式接口定义：一个接口有且只有一个抽象方法 。**就像上面的演示代码中的Fight接口，就很明显是一个函数式接口。

```java
//有且仅有一个接口
interface Fight {
    void beAttacked(Hero hero);
}
```

**Lambda表达式基本语法: (参数) ->表达式 或 (参数) ->{ 语句; }**

```java
Fight heroFight=(Hero hero)->{
            System.out.println("Lambda表达式调用");
        };
```



### 抽象类和接口的比较：

大家听了抽象类和接口肯定觉得这两个东西有点像。

接下来我就具体讲一下两者之间的一些区别：

先从使用场景的角度来说：

接口是对行为的抽象，也可以说是对类局部（行为）的抽象。抽象类是对一种事物的抽象，即对类抽。抽象类是对整个类整体进行抽象，包括属性、行为。一个类继承了抽象类，那么该类应该和抽象父类之间存在某种关系。举个栗子：

定义一个抽象父类   飞机，有两个子类 直升飞机和喷气式飞机都继承了该抽象父类飞机。该抽象父类定义了一个抽象方法   飞行。两个子类在继承了该抽象父类，并且按照各自的特点实现了抽象方法  飞行

在上面这种情况父类和子类之间有一种很明显的关系（飞机是一个大类），这种情况就使用抽象类显然会更加合适。

像我们之前代码演示的飞机和小鸟之间就并不存在什么太大的关系，他们只是都具备了飞行的能力，所以根据各自的实际情况实现同一个飞行接口。

再从语法相关的角度来分析：

- **接口不能被实例化**，一般我们都采用向上转型的方法来创建接口的实现类（当然也可以直接实例化接口的实现类）。
- **接口中的所有方法必须被其实现类全部实现**。
- **接口中所有方法默认是公开的、抽象的、不可被默认实现的**，即默认的前缀修饰符为`public abstract`。同时，其中的**所有成员变量都是公开的、静态的、不可变的**，即默认的前缀修饰符为`public static final`。

## 内部类

如果一个事物内部含有另一个事物，那么这就是一个类内部包含另一个类。

如同身体和心脏的关系

### Java中内部类的分类：

#### 1.成员内部类

成员内部类的定义格式：

```
修饰符 class 外部类名称{
  修饰符 class 成员内部类名称{
     //...
   }
   //...
}
```

**注意：内用外，随意访问；外用内，一定要有内部类对象**

编译后，内部类也会生成一个class文件，且自动命名为**外部类名称$内部类名称**

所以在给类命名的时候不建议使用$符号

成员内部类的使用：

1.间接方式：在外部类的方法中，使用内部类，然后main只是调用外部类方法。

2.直接方式：

**类名称  对象名 = new 类名称（）；**

**外部类名称.内部类名称  对象名 =new 外部类名称（）.new 内部类名称（）；**

成员变量重名时：

如果出现重名现象，那么格式是：外部类名称.this.外部类成员变量

```java
public class Outer{
int num=10;//外部类成员变量
  public class Inner{
   int num=20;//内部类成员变量
      public void methodInner(){
    int num=30;//内部类方法的局部变量
    System.out.println(num);//局部变量，就近原则
    System.out.println(this.num);//内部类的成员变量
    System.out.println(Outer.this.num);//外部类的成员变量
    }
  }
}
```



#### 2.局部内部类（包含匿名内部类）

如果一个类是定义在一个方法内部的，那么这就是一个局部内部类。

“局部”，只有当前所属的方法才能使用它，出了这个方法外面就不能用了

定义格式：

```java
修饰符 class 外部类名称{
    修饰符 返回值类型 外部类方法名称（参数列表）{
       class 局部内部类名称{
       //....
       }
    } 
}
```

**注意事项：**

局部内部类，如果希望访问所在方法的局部变量，那么 这个局部变量必须是【**有效final的**】

备注：从Java 8+开始，只要局部变量事实不变，那么final关键字可以省略

原因：

1. new 出来的对象在堆内存中
2. 局部变量是跟着方法走，在栈内存中
3. 方法运行结束后，立即出栈，局部变量就会消失
4. 但是new出来的对象会在堆内存中持续存在，直到垃圾回收消失

类的权限修饰符：

定义一个类的时候，权限修饰符规则：

1.外部类：public /（default）

2.成员内部类：public / protected /（default）/ private

3.局部内部类：什么也不能写【效果与（default）不同】

**匿名内部类：**

如果接口的实现类（或者是父类的子类）只需要使用唯一的一次，那么这种情况下就可以省略该类的定义，而改为使用【匿名内部类】，直接new接口或父类

**匿名内部类的定义格式：**

```java
接口名称 对象名 =new 接口名称（）{
    @Override
   //覆盖重写所有抽象方法
}；
```

对格式“new 接口名称（）{...}”进行解析

1.new代表创建对象的动作

2.接口名称就是匿名内部类需要实现哪个接口，也可以是抽象父类

3.{......}这才是匿名内部类的内容

另外还要注意几点问题：

1.匿名内部类，在【创建对象】时，只能使用唯一一次。

如果希望多次创建相同对象，还是需要写出具体的实现类来实现接口或者是继承抽象父类。

**匿名内部类所需要注意的事项**

- 匿名内部类中不能存在任何静态成员或方法
- 匿名内部类是没有构造方法的，因为它没有类名
- 与局部内部相同匿名内部类也可以引用局部变量。此变量也必须声明为 final（局部变量与匿名内部类的生命周期不同）（从Java 8+开始，只要局部变量事实不变，那么final关键字可以省略）



![](https://github.com/YuanErya/TheThird/blob/main/picture/1.jpg?raw=true)



## 作业：

自行设计完成一个简单的装备养成类的文字游戏。

这里提供的是一些简单的思路：

角色系统，角色拥有很多属性，角色也可以通过一些其他的方式（比如锻炼？）提升类似属性等级啊啥的

装备系统，角色可以通过装备各种装备可以提升对应的数值，装备可以有一定的培养机制。

货币系统，可以让角色通过一些方式收集货币，然后可以通过消费货币提升角色属性，装备那些什么。

这次作业是一个比较开放性的作业，上面也只是提供一点思路，大家有什么自己的想法，也可以自己进行实现。

加分内容：

设计出两个角色的战斗过程，角色与野外小怪的战斗过程

这算是一个综合性比较强的作业，重点在于对类和接口的设计，希望能在完成作业的过程中能对多接口的实现和抽象类的继承还有抽象方法的实现有进一步的理解，大家都尽力完成哟！（对作业的完成度不做强制要求）

![](https://github.com/YuanErya/TheThird/blob/main/picture/2.jpg?raw=true)
