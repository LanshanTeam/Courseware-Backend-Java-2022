# 🚀蓝山2022Java第六次课🚀

## 前言

恭喜各位卷王坚持到了这节课。这节课过后，本学期的课程应该就告一段落了。

接下来请各位系好安全带，接下来要起飞了✈

![](https://raw.githubusercontent.com/GNK48-Ava/imgs/main/1111.png)

## Maven📚

### Maven简介

官方的介绍是(节选):

> The result is a tool that can now be used for building and managing any Java-based project. We hope that we have created something that will make the day-to-day work of Java developers easier and generally help with the comprehension of any Java-based project.

官方对其作用的简述是:

> - Making the build process easy
> - Providing a uniform build system
> - Providing quality project information
> - Encouraging better development practices

在这里我先给个中文的介绍:

> Maven是专门用于管理和构建Java项目的工具，它的主要功能有：
>
> * 提供了一套标准化的项目结构
>
> * 提供了一套标准化的构建流程（编译，测试，打包，发布……）
>
> * 提供了一套依赖管理机制

### 标准化的项目结构

我们使用不同的IDE（如idea、eclipse），所创建出来的项目结构是不一样的。这也就意味着某一IDE创建出来的项目是无法在其他IDE中使用，这是一种极大的不方便。

就比如你idea的项目结构长这样:

![](https://raw.githubusercontent.com/GNK48-Ava/imgs/main/projectStructure-idea.png)

而我eclipse的项目结构长这样:

![](https://raw.githubusercontent.com/GNK48-Ava/imgs/main/projectStructure-esclipse.png)

而maven就为我们提供了一套标准化的项目项目结构。这意味着所有IDE创建的maven项目结构都是统一的，即创建出的maven项目是通用的。

话不多说，我们赶紧创建一个maven项目瞧瞧吧。

### 创建maven工程

我们伟大的IDEA其实已经集成了maven，这让你无需在本地安装maven(当然也不复杂)也可创建maven工程。

轻车熟路地点击创建一个新项目(图略)。

然后在Build system里选择maven就可以选择maven作为项目构建工具了 = =

第一个Intellij是IDEA自带的构建工具。

而第三个Gradle则是一款比较新的项目构建工具，Android开发基本都是用它，还有springboot3默认也使用gradle了。

![](https://raw.githubusercontent.com/GNK48-Ava/imgs/main/mavenNewProject.png)



点击create后，经过一段等待后，maven项目就构建好了。

芝士maven的项目结构，不算复杂，大家可以对着图看一下。现在看不懂也不要紧，慢慢来，以后就熟了

![](https://raw.githubusercontent.com/GNK48-Ava/imgs/main/projectStructure-maven.png)



### 标准化的构建流程

maven能让我们方便地进行项目构建。我们编写好项目后，往往要进行编译、测试、打包这几个过程。而maven能让我们较为方便地完成这些过程。

顺便推荐一个插件:Maven Helper。它能让我们较为方便地执行一些maven操作。

![](https://raw.githubusercontent.com/GNK48-Ava/imgs/main/Maven%20%20Helper.png)





#### 项目构建的生命周期

所谓项目构建生命周期描述的是一次构建过程中经历了多少个事件

而maven对项目构建的生命周期大致划分为三套:

> - clean:清理工作
> - default:核心工作，包括编译、测试、打包、安装、部署等
> - site:产生报告，发布站点等(用的不多好像)

可以看出，一套生命周期是有很多个事件的。

而在同一套生命周期内，如果执行后边的事件，前面的事件就会自动执行，这里我们以常用的default生命周期进行讲解。

![](https://raw.githubusercontent.com/GNK48-Ava/imgs/main/maven-defaultLifeCycle.png)

还是挺多的，但我们一般常用的也就标红的几个。当我们执行package时，maven会将前边的事件全都按顺序执行一遍，最终才会执行到packetage，也就是说如果前面的环节有问题，例如测试不通过，你打包也别想成功。

我们在一个maven项目的pom.xml所在文件夹中，打开命令提示符，就可输入maven命令对项目执行构建的生命周期了(前提是将maven配置到了环境变量)。

IDEA可以让我们较为方便地执行maven的生命周期

![](https://raw.githubusercontent.com/GNK48-Ava/imgs/main/IDEA-maven-lifecycle.png)

选定一个操作后，再点击标出了绿色三角就能执行对应的命令了。

而刚刚我推荐的Maven Helper插件能允许我们更为方便地执行maven命令。我们在项目结构中点击右键就可以看到多出来的run maven操作了，在项目模块多的时候也挺方便的。(截不出来图，摆了)

### 依赖管理

maven可以方便地让我们通过坐标进行依赖的引入。

还记得项目结构中的pom.xml吗？

![](https://raw.githubusercontent.com/GNK48-Ava/imgs/main/pom.png)

我们就是在这里进行依赖的配置的。先看看里面有啥吧。

以我这里创建的项目的pom.xml为例，内容如下:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>org.example</groupId>
    <artifactId>mavenDemo</artifactId>
    <version>1.0-SNAPSHOT</version>

    <properties>
        <maven.compiler.source>8</maven.compiler.source>
        <maven.compiler.target>8</maven.compiler.target>
    </properties>

</project>
```

我们来介绍一下其中较重要的一部分吧

在maven中，坐标是资源的唯一标识，所以我们可以通过坐标引入所需的依赖，坐标的主要构成如下:

* groupId：定义当前Maven项目隶属组织名称（通常是域名反写，例如：org.springframework.boot）

* artifactId：定义当前Maven项目名称（通常是模块名称，例如 movie-service、user-service）

* version：定义当前项目版本号

  所以我们可以看出我这个项目的坐标如下:

  ```xml
      <groupId>org.example</groupId>
      <artifactId>mavenDemo</artifactId>
      <version>1.0-SNAPSHOT</version>
  ```



当然，重头戏是依赖引入。在pom文件中，我们将各种依赖的坐标放到<dependencies></dependencies>标签中。而单个依赖的坐标则是放到<dependency></dependency>标签中。这里我们以引入lombok和fastjson的依赖为例

```xml
<dependencies>
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <version>1.18.24</version>
    </dependency>
    <dependency>
        <groupId>com.alibaba</groupId>
        <artifactId>fastjson</artifactId>
        <version>1.2.75</version>
    </dependency>
</dependencies>
```



当你修改pom.xml后，会出现这么个图案，当你点击后，修改才会被载入。

![](https://raw.githubusercontent.com/GNK48-Ava/imgs/main/maven-refresh-16699815444661.png)













## JSON🧻

### 概述

我们在开发的过程中，后端的同学可能并不懂前端的同学的开发语言、环境，反过来也是如此。

那么我们如何有效率地进行前后端信息的沟通呢？这时候就得用上JSON了。

> **JSON**（JavaScript Object Notation）是一种轻量级的数据交换格式，可使人们很容易地进行阅读和编写，同时也方便了机器进行解析和生成。
>
> JSON适用于进行数据交互的场景，如网站前台与后台之间的数据交互。

换句话说，JSON是一种数据格式，是一种数据的载体，本质上来说和word文档、Excel表格一样，都是用来传递信息的。

### JSON语法

json是以键值对进行书写的，而具体的语法如下:

> - 数组（Array）用方括号(“[]”)表示。
> - 对象（0bject）用大括号(“{}”)表示。
> - 名称/值对(name/value）组合成数组和对象。
> - 名称(name）置于双引号中，值（value）有字符串、数值、布尔值、null、对象和数组。
> - 并列的数据之间用逗号(“,”）分隔

接下来我们可以一段github接口提供的JSON(简化版)为例来进行一下对照:



```json
[
  {
    "login": "feiWoSCun",
    "id": 98465393,
    "node_id": "U_kgDOBd52cQ",
    "avatar_url": "https://avatars.githubusercontent.com/u/98465393?v=4",
    "gravatar_id": "",
    "url": "https://api.github.com/users/feiWoSCun",
    "type": "User",
    "site_admin": false,
    "contributions": 10
  },
  {
    "login": "YxYL6125",
    "id": 91076160,
    "node_id": "MDQ6VXNlcjkxMDc2MTYw",
    "avatar_url": "https://avatars.githubusercontent.com/u/91076160?v=4",
    "gravatar_id": "",
    "url": "https://api.github.com/users/YxYL6125",
    "type": "User",
    "site_admin": false,
    "contributions": 7
  }
]
```

可以看出这个JSON所表示的是一个只有两个元素的数组。

而依据数组中的元素我们也可以很容易地构造出对应的java实体类

```java
public class Contributors {
    private String login;
    private Long id;
    private String node_id;
    private String avatar_url;
    private String gravatar_id;
    private String url;
    private String type;
    private Boolean site_admin;
    private Integer contributions;
}
```

这里顺便讲一个IDEA的使用小技巧。

按住Alt的同时鼠标左键上下拖动，即可做到多行同时统一编辑。

![](https://raw.githubusercontent.com/GNK48-Ava/imgs/main/GIF.gif)





## springboot🌿⚙

好了，接下来就是今天课程的重头戏了。它就是 springboot 

那么，什么是springboot呢？

首先，我们来看看==spring==的官网定义:

> The Spring Framework provides a comprehensive programming and configuration model for modern Java-based enterprise applications - on any kind of deployment platform.

简单来说就是一个java 的应用开发框架。

但我们今天并不会讲spring，有兴趣的同学和卷王们也可自行了解学习😘

再让我们看看springboot的官网定义:

> Spring Boot makes it easy to create stand-alone, production-grade Spring based Applications that you can "just run".

简单来说，就是可以让你快速地基于spring进行java应用开发。

到目前为止，我们大多是在学习前置知识，没进行过一个web应用的开发(卷王除外🧎‍♂️),在经过今天的课程后，想必大家也都能轻松地地上手web应用开发了。

### Hello world

若要说该上手的第一个程序，想必非“Hello world”不可。

接下来，我将演示一下如何使用springboot创建出一个Hello world

先从创建一个springboot项目开始

这里像name、group、artifact、packagename 随便写写就行了

选择maven作为项目构建工具

这里的jdk和java版本选自己的版本且不低于8即可(现在不会还有人用8以下的版本吧🤔)



![](https://raw.githubusercontent.com/GNK48-Ava/imgs/main/%40%605%7DLKGET%24J%5DSVCU%7DRD%40DG2.png)



接下来就会让我们选择一下springboot的版本以及对应的依赖，这里我以2.76的版本为例并选择spring web的依赖。springboot版本别高于3低于2就好了(3前不久才出正式版，也欢迎各位卷王学习springboot3🥵)。当然各种依赖和springboot的版本后面也是可以改的。

![](https://raw.githubusercontent.com/GNK48-Ava/imgs/main/springbootInit.png)

然后点击create

接下来一般就是等一下依赖下载了 = =





可以看到springboot项目的结构如下

![](https://raw.githubusercontent.com/GNK48-Ava/imgs/main/ProjectStruct-16697945968782.PNG)

让我们来看一下这个自动生成的SpringbootLessonApplication类，以各位卷王们的功力应该能一眼就能看出这是一个启动类

不过这个启动类可不是一个普通的启动类，这是一个springboot的启动类(有点废话呃呃)。反正和之前的main方法区别不大，要启动程序就启动这个类就好了 = =

```java
@SpringBootApplication
public class SpringbootLessonApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootLessonApplication.class, args);
    }

}

```

然后我们创建一个HelloWorld类，其代码如下

```java
@RestController
public class HelloWorld {


    @GetMapping
    public String helloWorld() {
        return "hello world";
    }

}
```

接下来我们就运行一下启动类。

接着就会看到控制台打印了一大堆日志。以我的为例，就是这个样子:

![](https://raw.githubusercontent.com/GNK48-Ava/imgs/main/console.PNG)

大家看着这些日志估计也能猜出springboot帮我们进行了启动了一系列的东西。

接下来我们使用浏览器访问一下 http://localhost:8080/

就可以看到浏览器上为我们打印出了 hello world了🥳

~~好了，你已经学会springboot了，快去和项目对线吧~~

我知道大家或许有些一脸懵逼，但先别急，且先容我来简单介绍一下springboot项目结构中一些特殊文件的作用

### 配置文件

芝士配置文件，我们在可以在这里进行springboot的配置

后面的各种配置大多都要在这里进行。当然关于springboot的配置文件的高级用法(也不一定算高级用法🤔)想必大家不久后也会接触学习到

![](https://raw.githubusercontent.com/GNK48-Ava/imgs/main/properties.png)

可以看出springboot默认为我们生成的是properties格式。

所以我们就先来讲讲properties的书写格式。

#### properties

properties以键值对的格式来进行书写，即:

> -  **key=value**
> - **#号表示注释**



我们给出以下例子:

```properties
server.port= 8081
#logging.level.root= debug
```

这里我们将服务的端口号修改成了8081。这时重新启动一下服务，就会发现http://localhost:8080/访问不通了，转而只能访问http://localhost:8081/了。

不过properties是相对较旧的配置文件格式了，我们也可以将扩展名修改成yaml(yml)。

#### yaml/yml

yaml和yml这两种扩展名本质上都是一个文件格式，那就是YAML

> YAML（YAML Ain't Markup Language），一种数据序列化格式。具有容易阅读、容易与脚本语言交互、以数据为核心，重数据轻格式的特点。

(出于方便，可能用yml扩展名更多一点)

yaml的语法格式如下:

> - 大小写敏感
> - 属性层级关系使用多行描述，每行结尾使用冒号结束
> - 使用缩进表示层级关系，同层级左侧对齐，只允许使用空格（不允许使用Tab键）
> - ==属性值前面添加空格（属性名与属性值之间使用冒号+空格作为分隔）==
> - #号 表示注释



在此，我同样给出一些简单的例子，同学们可以参考着看一下

```yaml
server:                 #与properties中的server.port= 8081 等效
  port: 8081

student:
 name: cyx       #可直接书写字符串
 age: 18
 boolean: true   #TRUE,true，True，FLASE,false,False 均可
 date: 2003-01-01 #日期必须为yyyy-mm-dd格式
 weight：120.114514 
 likes:               #数组   属性下方以一组-号开头，-号与属性间也要留存空格
  - sleep
  - slack off
  - movie        
 
```



大家也不用太着急着就记住这些语法，写多了也可以慢慢熟练的



### pom.xml

芝士springboot项目的pom.xml 。让我们来康康里面都有啥。

![](https://raw.githubusercontent.com/GNK48-Ava/imgs/main/springboot-pom.xml.png)

以本项目为例，其pom.xml如下

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.7.6</version>
        <relativePath/> <!-- lookup parent from repository -->
    </parent>
    
    <groupId>com.example</groupId>
    <artifactId>springbootLesson</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    
    <name>springbootLesson</name>
    <description>springbootLesson</description>
    
    <properties>
        <java.version>1.8</java.version>
    </properties>
    
    <dependencies>
        <dependency>                                  <!-- 之前引入的springweb依赖  -->
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <dependency>                                  <!--  自动帮我们引入的测试依赖          -->
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>

</project>

```

大家经过之前对maven 的学习应该也能大致看明白这里的内容。这里我们再从中挑几个特殊的讲一讲。

首先是这个:

```xml
   <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.7.6</version>
        <relativePath/> <!-- lookup parent from repository -->
    </parent>
```

这个就是springboot的特殊处之一了。这个parent坐标里定义了一大堆依赖的版本号，当后面你自己添加某些依赖时，不用自己选择版本，可以直接使用提供springboot提供的版本号，以避免依赖冲突这种麻烦问题。

可以看到这里的springboot版本号就是我们之前在创建项目的过程中所选择的2.7.6。当然，你也可以在这里直接更改springboot的版本



其次是这个依赖项:

```xml
        <dependency>                                
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
```

也正如我们刚刚所说，它使用了父坐标提供的版本号，因此在这里我们不用选择版本号。

此外，这也是springboot中特殊的一类坐标，叫做==starter==，是springboot为了简化我们开发时引依赖的过程而整出的一类"整合包"。

玩过Minecraft的同学或许对这个"整合包"这个概念熟悉一点。就以我为例，我以前不懂整太多mod、插件、材质包，我就喜欢从论坛下一些大佬搞好的整合包来玩，开箱即玩。

这里springboot为我们做的也是类似的工作，把一些开发要用的依赖整合好，我们开发者只需要导入这一个start就可以上手开发了。

最后康康这个webStarter里边有些啥🧐

```xml
  <dependencies>
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter</artifactId>
      <version>2.7.6</version>
      <scope>compile</scope>
    </dependency>
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-json</artifactId>
      <version>2.7.6</version>
      <scope>compile</scope>
    </dependency>
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-tomcat</artifactId>
      <version>2.7.6</version>
      <scope>compile</scope>
    </dependency>
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-web</artifactId>
      <version>5.3.24</version>
      <scope>compile</scope>
    </dependency>
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-webmvc</artifactId>
      <version>5.3.24</version>
      <scope>compile</scope>
    </dependency>
  </dependencies>
```

噢噢，光是依赖项就有这么多，还有其他的starter也在里边，像springboot框架、web服务器(tomcat)、json处理器等东西都给它包含在里边了。

这时再看一下springboot的介绍:

> Spring Boot makes it easy to create stand-alone, production-grade Spring based Applications that you can "just run".

或许大家也会有了更多一点的感悟。

### maven创建springboot项目

有些同学可能会发现springboot的项目结构和maven差不多，那我们是否可以自己构建出一个springboot项目呢？答案是肯定的。

目前看来，springboot的项目结构的特殊处无非就是配置文件和pom.xml里的配置罢了，接下来我们讲讲如何通过maven来创建一个springboot项目。

首先在IDEA里创建一个maven项目，之后在项目里的pom.xml里添加一下springboot的parent坐标：

```xml
<parent>
   <artifactId>spring-boot-starter-parent</artifactId>
    <groupId>org.springframework.boot</groupId>
    <version>2.7.6</version>           <!--版本号选择自己需要的  -->
</parent> 
```

然后再引入web的start

```xml
    <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
```

并且在resource目录下创建一个application.yml文件

并且创建一个启动类，并在类添加==@SpringbootApplication==注解，在里面编写一个main方法，并调用SpringApplication.run()方法。

我这里同样给出一个启动类的示例

```java
@SpringBootApplication
public class MavenSpringApplication {          //类名自己定

    public static void main(String[] args) {
        SpringApplication.run(MavenSpringApplication.class, args);
    }                         //传入当前类的字节码和args
}
```

至此，一个springboot项目又被我们创建好了🥳。







### 知识加油站⛽

现在我们暂时先把目光从springboot上离开，先来讲讲另一个很重要的东西——Mybatis，还有一点架构的知识。

[Ctrl+鼠标左键点我跳转到Mybatis部分](#mybatis🐤)

[Ctrl+鼠标左键点我跳转到MVC部分](#MVC与三层架构)

### 控制反转与依赖注入⚙

接下来有一小段理论知识，理论知识现在听不懂也没关系。

#### 控制反转

我们先来讲讲什么是控制反转(Ioc)。

以机械钟表为例，一个钟表的精密运行离不开其背后齿轮之间的啮和、在一起相互协调工作。而我们的软件也是如此，一个软件的运行也离不开背后对象的相互耦合。可随着软件的复杂度不断上升，经常会出现对象间多重依赖的关系。为了解决对象耦合度过高的问题，软件专家Michael Mattson 提出了IoC理论，用来实现对象间的解耦。IoC是 Inversion of Control 的缩写，即控制反转。而Ioc的思想大体上是这样的:借助”第三方“实现具有依赖关系的对象之间的解耦。而这个”第三方“我们一般叫他IoC容器。

![](https://raw.githubusercontent.com/GNK48-Ava/imgs/main/IMG_6210(20221207-150941).JPG)

可以看到，对象A、B、C、D间已没有了耦合关系，他们之间的依赖关系完全是靠IoC容器来进行的。当我们去除IoC容器后，A、B、C、D之间就无任何的关联了。

![](https://raw.githubusercontent.com/GNK48-Ava/imgs/main/IMG_6211(20221207-151141).JPG)

而在引入IoC容器之前，对象之间的关系是这样的:

![](https://raw.githubusercontent.com/GNK48-Ava/imgs/main/IMG_6209(20221207-150656).JPG)

由于A依赖于B，当对象A初始化或运行到某一点时，A就必须自己创建一个B或者使用已经创建好的B。这时候A对B的控制权都是在自己的手上，可当我们引入IoC容器后，A需要使用到B时，是由IoC容器创建B并给A使用的，这个时候A获得B或使用B的行为就变为了被动行为。这也就是控制反转的意思。

#### 依赖注入

##### 概念

控制反转后，获得依赖对象的过程由自身管理变为了由Ioc容器主动注入，于是Martin Fowler给控制反转齐了一个更合适的名字，叫做依赖注入(Dependency Injection)，简称DI。所谓依赖注入，指的是由IoC容器在运行期间，动态地将某种依赖关系注入到对象中。

##### 实战

假设我这里有个汽车类，其内部有关于引擎类的依赖，并且Car的run方法要调用我们该如何让他获得Engine的依赖呢？

```java
public class Car {
    Engine engine;  
    
    public void run() {
        engine.run();
    }  
  }
interface Engine {
    public void run();

}
```

Java是一门面向对象的语言，没有对象可以new一个，或许我们可以直接让Car自己创建出一个Engine对象。

```java
public Car() {
    engine = new EngineImpl();
}
```

好像没什么问题，但耦合度过高了一点。如果对Engine的类型需求改变了呢(比如改成了)?这个时候就只能修改Car中的代码这未免太麻烦了。

这个时候就需要进行依赖注入了。

其实我们之前也一直都有在进行依赖注入的操作，那我们之前都是如何进行依赖注入的呢？

很简单，可以使用构造器注入，通过Car的构造器向其传递engine对象：

```java
    public Car(Engine engine) {
        this.engine = engine;
    }
```

或者使用set方法注入，:

```java
public void setEngine(Engine engine) {
    this.engine = engine;
}
```

这些注入方法大家之前应该也都有用到过，但对象较多的时候这些依赖要我们一个一个手动创建注入或许有些麻烦了。

而spring为我们提供了一个IoC容器，将各种类放到容器中进行管理，帮助我们方便地进行依赖注入。

### @Component

这个注解用来标注一个类，表示将这个类填装到spring容器中。

继续以刚刚的Car为例。我们为Engine的实现类EngineImpl上加上@Component注解，将其填装到spring容器中。

```java
@Component
class EngineImpl implements Engine {
    @Override
    public void run() {
        System.out.println("engineImpl is running....");
    }

}
```

并且也在Car类上添加@Component注解，毕竟想让spring来进行依赖注入，它本身也得先交由Spring管理。

```java
@Component
public class Car {
      ...
}
```

### @Autowired

见名知意，表示自动填装。

这个注解可以用来标注一个字段，也可以标注方法。

我们在Car的Engine engine字段上添加@Autowired,或者在setEngine方法上添加@Autowired或者在构造器上添加@Autowired，将engine的注入工作交由spring容器完成。

```java
@Autowired
Enginie engine
```

或

```java
@Autowired
public void setEngine(Engine engine) {
    this.engine = engine;
}
```

或

```java
    @Autowired
    public Car(Engine engine) {
        this.engine = engine;
    }
```

然后在测试类中测试一下。

```java
@SpringBootTest
class SpringbootLessonApplicationTests {
    

    @Autowired
    Car car;

    @Test
    public void testIOC() {
        car.run();
    }

}
```

这里的car也由spring自动填装，因为spring是无法为我们自己 new出来的Car进行依赖注入的。

运行测试方法，合乎预期

 ![](https://raw.githubusercontent.com/GNK48-Ava/imgs/main/Ioc-print.png)

### @Configuration&@Bean

我们也可以在编写一个类专门用于配置bean。使用@Configuration标注一个类告诉spring这是配置类。

而@Bean则是用来标注方法的，在配置类中创建一个bean。这里我们自定义返回一个Engine。

```
@Configuration
public class MConfiguration {

    @Bean
    public Engine electricEngine(){
        return ()->{
            System.out.println("electricEngine is running...");
        };
    }
}
```

继续run一下刚刚的测试方法，不过记得把之前的EngineImpl的@Component的注解去掉，否则会冲突报错的。

输出结果:

![](https://raw.githubusercontent.com/GNK48-Ava/imgs/main/Ioc-print2.png)

看得出来我们创建的bean确实注册到容器中了。





### @Service

这个注解主要是标注到我们之前讲的业务逻辑层上，将其注入到spring容器中，方便我们将其注入到其他类中(例如controller)。本质上还是一个@Component。

### @Mapper

这个注解在mybatis部分有简单介绍过。实际这个注解是由mybatis提供的，而不是spring。告诉spring容器由mybatis实现该接口，并让mybatis找到它，为该接口生成实现类，并最终放到spring容器中。

### @RestController

我们再回来看看一开始Hello world案例中的HelloWorld类


```java
@RestController
public class HelloWorld {
@GetMapping
public String helloWorld() {
    return "hello world";
}
}
```



我们在经过对MVC与三层架构的学习后，应该就能明白这个类的意义了，这里我们使用@RestController标注将其指定为controller，并在这里接收并封装前台数据，调用服务，最终返回数据。不过这里命名不太规范，得在类名后面加个Controller变成HelloWorldController。

但是刚刚讲的不是controller吗？为什么这里加的却是@RestController的注解呢?

如果你点进去@RestController就会发现其实@RestController是包含@Controller的，(虽然感觉看名字都能猜得出来).

![](https://raw.githubusercontent.com/GNK48-Ava/imgs/main/%2540RestController.png)

并且还包含了@ResponseBody，这个注解会将我们的方法返回值转化为json数据放到responseBody中。之前我们也讲过了，前后端的数据的交流一般还是通过json进行的，而@RestController=@Controller+@ResponseBody，所以这里我们使用了@RestController。当然，这也意味着我们同时使用@Controller和@ResponseBody来替换掉@RestController也是可以的🤗。



### Http Method

Http规定了9中请求方法，其分别是

![](https://raw.githubusercontent.com/GNK48-Ava/imgs/main/HTTP%20method.png)

发送这些类型的请求是前台的事，我们后端要做的是接收处理这些类型的请求。

### @RequestMapping

@RequestMapping注解是一个用来处理请求地址映射的注解，可用于映射一个请求或一个方法，可以用在类或方法上。

我们可以通过设置@RequestMapping的value(或path)属性来设置对应接口的路径。也可以设置它的method属性来设置映射到方法的请求类型。

例如

```java
@RestController
public class Controller {
    @RequestMapping(path = "/hello", method = RequestMethod.GET)
    public String helloWorld() {
        return "hello world";
    }
}
```

则helloWorld方法只会响应请求路径为/hello、请求方法为GET的请求。

当然，这是旧版的操作了。对于不同的请求方法，现在我们可以使用@GetMapping 等注解来简化操作。

则有

```java
@RestController
public class Controller {
    @GetMapping("/hello")
    public String helloWorld() {
        return "hello world";
    }
}
```

这里@GetMapping("/hello")的效果是和 @RequestMapping(path = "/hello", method = RequestMethod.GET)一样的。

对应的还有@PostMapping、@PutMapping、@DeleteMapping来专门映射对应的HTTP请求类型



@RequestMapping在类上使用时并定义路径时，则设置了该类中的所有的响应请求的方法的父路径:

```java
@RestController
@RequestMapping( path = "/demo")
public class Controller {
    @GetMapping("/hello")
    public String helloWorld() {
        return "hello world";
    }
}
```

这样子helloWorld响应的路径就成了/demo/hello了.

### RESTful API

> REST(**Representational State Transfer**,即表现层状态转移)，简单来说是一种架构风格。

而RESTful API 就是具有REST风格的网络接口。

在RESTful API 中，在URL中就可以获知操作的资源是什么，而要对资源进行什么操作则放到了Http Method中，即RESTful API是面向资源的。这也就意味着RESTful API的URL中一般只使用名词，而不推荐使用动词。

举个例子，我们针对文章的RESTful API可以这么设计:

| HTTP 请求方法 | 接口           | 具体操作           |
| ------------- | -------------- | ------------------ |
| GET           | /articles      | 获取全部文章       |
| GET           | /articles/{id} | 依据ID获取单个文章 |
| POST          | /articles      | 上传一篇文章       |
| PUT           | /articles/{id} | 修改一篇文章       |
| DELETE        | /articles/{id} | 删除一篇文章       |

最后提一下，RESTful API是一种风格，一种约定方式，而不是规范，你不一定非得按着RESTful API来写。

### 获取参数

#### 通过Request获取

我们可以通过获取到的Request获取到的参数，如:

```java
@RestController
@RequestMapping("/demo")
public class DemoController {

    @GetMapping("/request")
    public String testRequest(HttpServletRequest request) {
        return request.getParameter("name");
    }
 }
```

然后访问一下http://localhost:8080/demo/request?name=123,就会发现我们成功地获取到了name参数。

当参数不存在时，获取到的即为null值。

#### 直接方法参数获取

我们也可以直接在接口的参数中获取，要求方法参数名与url上的参数名完全相同，但对多个参数不要求顺序，如

```java
@RestController
@RequestMapping("/demo")
public class DemoController {

    @GetMapping("/user")
    public String getUserName(String name, String pwd) {
        return "name:"+name+"  pwd:"+pwd;
    }
 }
```

然后访问一下http://localhost:8080/demo/request?pwd=123&name=4，就会发现能成功获取到传入的参数。

当参数不存在时，获取到的即为null值。

#### @RequestParam

与上一种方法较为相似，不过更为强大。

给出示例

```java
    @GetMapping("/RequestParam")
    public String testRequestParam(@RequestParam(name = "pwd") String password, @RequestParam(name = "name") String username) {
        return "name:" + username + "  pwd:" + password;
    }
```

为url上的变量名则为我们设定@RequestParam的name(value)属性，与方法的参数名无关。当我们未设定name(value)属性时，参数名规则见上一条。

访问http://localhost:8080/demo/RequestParam?pwd=123&name=aaa，就会发现能成功获取到传入的参数。

不过和前两种方法不一样，默认情况下@RequestParam会要求指定的参数必须有，否则就会报错。所以如果参数是可选的，请设定@RequestParam的require属性为false。



#### @PathVariable

直译为路径变量。

一般配合RESTful API食用

比如我们这样定义一个接口

```java
@RestController
@RequestMapping("/demo")
public class DemoController {
    @GetMapping("/user/{id}")  //{id}为占位符
    public Integer getId(@PathVariable Integer id) {//使用@PathVariable注解和占位符中的变量名进行接收
        return id;
    }
}
```

当我们访问路径http://localhost:8080/demo/user/1234时，接收到的id就是1234了。也比较简单



#### @RequestBody

> @requestBody注解常用来处理content-type不是默认的application/x-www-form-urlcoded编码的内容，比如说：application/json或者是application/xml等。一般情况下来说常用其来处理application/json类型。

简单来说就是一般用来接收前端传来的信息的，大多数都是接收json。

在后端的同一个接收方法中，只允许有一个@RequestBody

@RequestBody不支持GET类型的请求.



直接给出个示例吧:

```java
@RestController
@RequestMapping("/demo")
public class DemoController {
    @PostMapping("/requestBody")
    public User getUserJSON(@RequestBody User user) {
        return user;
    }
  }
```

对了，User类的定义如下，这里使用了lombok来简化代码:

```java
@Data
public class User {
    private Long id;
    private String username;
    private String password;
    private String gender;
    private String homeAddr;
    
}
```

我们提交的json数据能自动转化为User对象。

我们在这个方法上打上断点debug，并在postman中测试一下接口。

![](https://raw.githubusercontent.com/GNK48-Ava/imgs/main/postmanTest.png)

在debug中可以看见User对象的属性和我们提交的一致

![](https://raw.githubusercontent.com/GNK48-Ava/imgs/main/debug.png)



### 读取YAML文件

我们也是可以读取YAML配置文件来进行一些我们自定义的配置的。这部分就来讲讲怎么读取YAML文件中的数据

我们先在YAML中给出测试数据

```yaml
fruit: apple

student:
 name: cyx       
 age: 18
 date: 2003-01-01 
 weight: 120.114514 
 likes:               
  - sleep
  - slack off
  - movie  
```

#### 读取单一数据

在某一个spring容器管理的bean内，我们可以在字段上使用@Value注解来获取yaml中的单一数据。

而在属性名引用规则为:  ${一级属性名.二级属性名……}

这里我们在测试类中进行测试

代码如下:

```java
@SpringBootTest
class SpringbootLessonApplicationTests {
    @Value("${fruit}")
    String fruitName;
    
    @Value("${student.name}")
    String name;
    
    @Test
    public void testYAML(){
        System.out.println(fruitName);
        System.out.println(name);
    }
    }
```

可以看到打印出的结果合乎预期,证明成功读取

#### 读取对象数据

使用@Value读取数据固然快捷方便，但当数据较多时，我们总不能一条一条属性地写。并且很多个@Value分散在不同的类中也会带来维护上的问题。而我们Java是一门面向对象的编程语言，我们将读取到的数据封装成一个类就能较好地解决以上问题。

首先我们先创建出一个类，并且为其加上@Component注解。这一点还是比较好理解的，我们想让springboot帮我们填装数据，就得先把类交给它管理。

并且在这个类上添加@ConfigurationProperties注解，并指定加载哪些属性。

这里依据刚才yaml示例中的student创建出对应的实体类:

```java
@Component
@ConfigurationProperties(prefix = "student")
@Data         //使用lombok自动生成getter、setter、toString
public class Student {

    String name;
    Integer age;
    Float weight;
    String[] likes;


}

```

然后在测试类中测试打印，合乎预期

```java
@SpringBootTest
class SpringbootLessonApplicationTests {

    @Autowired
    Student student;

    @Test
    public void testConfigurationProperties() {
        System.out.println(student);
    }
    }
```

![](https://raw.githubusercontent.com/GNK48-Ava/imgs/main/1NAQN75G7Q8W0NJ.png)



此外，以后使用配置中心时，@ConfigurationProperties允许我们进行配置热更新。

## mybatis🐤

上节课都阳哥哥为我们讲解了mysql相关。当时我们对mysql 的操作大多尽在黑窗口或Navicat或DataGrip中进行的。但我们作为java程序员，在开发中肯定需要在java中进行数据库的操作。

于是乎，就有了JDBC

### 何为JDBC?

> JDBC   就是使用Java语言操作==关系型数据库==的==一套API==
>
> 全称：( Java DataBase Connectivity ) Java 数据库连接

这里我们出于时间与篇幅考虑就不对JDBC的概念及原生JDBC的使用进行过多展开了。有感兴趣的同学和卷王们可课后自行去了解。

### Mybatis概述

> - MyBatis 是一款优秀的==持久层框架==，用于简化 JDBC 开发
> - MyBatis 本是 Apache 的一个开源项目iBatis, 2010年这个项目由apache software foundation 迁移到了google code，并且改名为MyBatis 。2013年11月迁移到Github
> - 官网：https://mybatis.org/mybatis-3/zh/index.html 

mybatis的图标长这样(忍者神龟既视感):

![](https://raw.githubusercontent.com/GNK48-Ava/imgs/main/mybatisLogo.webp)

关于这里的介绍，我来讲讲什么是持久层。持久层，又称数据访问层，这一层级往往可以直接跟我们的数据库打交道，也就是进行curd工作。









时间紧任务重。不多废话，我们直接上手编写出一个mybatis的项目。

![](https://raw.githubusercontent.com/GNK48-Ava/imgs/main/QQ%E5%9B%BE%E7%89%8720221202225016.jpg)

（注：此次我们都是使用springboot整合mybatis进行演示，原生mybatis使用稍微有点复杂，感兴趣的同学和卷王们可自行了解）

### 入门项目

这是我这里给出的测试用的mysql表，比较简单

```sql

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `password` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `gender` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `home_addr` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES (1, 'zhangsan', '123', '男', '北京');
INSERT INTO `tb_user` VALUES (2, '李四', '234', '女', '天津');
INSERT INTO `tb_user` VALUES (3, '王五', '11', '男', '西安');
INSERT INTO `tb_user` VALUES (4, '赵六', 'adcsad58', '男', '重庆');
INSERT INTO `tb_user` VALUES (5, '小明', '123652', '男', '北京');
INSERT INTO `tb_user` VALUES (6, '小红', 'fadsf543', '女', '重庆');
INSERT INTO `tb_user` VALUES (7, '罗小王', 'dasf52', '男', '上海');

SET FOREIGN_KEY_CHECKS = 1;
```

顺便这里推荐一个插件：MybatisX

![](https://raw.githubusercontent.com/GNK48-Ava/imgs/main/MybatisX.png)

这玩意能让我们更为丝滑地使用mybatis进行开发，简直就是我这种懒狗的福音。

至于这个插件有什么用，大伙自己试试就知道了。

![](https://raw.githubusercontent.com/GNK48-Ava/imgs/main/QQ%E5%9B%BE%E7%89%8720221203224122.jpg)

我们先在springboot工程的pom.xml里引入mybatis和JDBC的驱动。毕竟刚刚也讲过mybatis只是简化JDBC开发，所以还是得引入一个JDBC的驱动的。

```xml
  <dependency>
            <groupId>org.mybatis.spring.boot</groupId>
            <artifactId>mybatis-spring-boot-starter</artifactId>
            <version>2.2.2</version>
   </dependency>
  <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
  </dependency>
```

在application.yml里进行Mybatis和mysql的配置

```yml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/bluem   #localhost：地址  3306：你mysql的端口号   bluem：连接的数据库
    username: root         #mysql用户名
    password: 123456       #mysql密码
    driver-class-name: com.mysql.cj.jdbc.Driver   #jdbc的驱动




mybatis:
  mapper-locations: mapper/*.xml    #由于使用mapper进行代理开发所以要指定一下mapper的位置
```

一般我们都是使用java的类来接收数据库中的数据的，而java中的概念和关系型数据库中的概念映射可参考下表

| Java概念 | 数据库概念 |
| -------- | ---------- |
| 类       | 表         |
| 属性     | 字段/列    |
| 对象     | 记录/行    |

应该还是比较清晰易懂的

基于测试用表构建出一个实体类：

```java
public class User {
    private Long Id;
    private String username;
    private String password;
    private String Gender;
    private String homeAddr;

    @Override
    public String toString() {
        return "User{" +
                "Id=" + Id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", Gender='" + Gender + '\'' +
                ", homeAddr='" + homeAddr + '\'' +
                '}';
    }
}
```

#### 创建mapper接口

由于一般我们都是使用mapper映射文件进行代理开发的，但还是需要一个接口为我们提供方法调用的。

这里我们创建一个TestMapper接口，为其加上一个@Mapper注解，告诉spring容器由mybatis实现该接口，并让mybatis找到它，为该接口生成实现类

```java
@Mapper
public interface TestMapper {

}
```

#### 创建映射文件

我们一般都是使用mybatis的映射文件mapper并在其中编写sql语句进行代理开发，这能较好地解决原生的硬编码和后期维护问题。

> - 映射文件中的namespace与接口全类名的相同
> - 映射文件中编写sql语句的标签id属性与mapper接口中的方法名保持一致

我们在resource目录下新建个mapper文件夹。

然后在resource.mapper目录下新建一个TestMapper.xml 文件，这个xml文件也就是一直讲的映射文件了

这里mapper的格式相对固定，大家平时CV着用就好了

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.example.springbootlesson.mapper.TestMapper">  <!--这里的namespace即为我们之前创建的mapper接口的全类名-->

   
</mapper>
```

#### 编写接口方法

在接口中编写方法来接收数据库中查询出的数据，mybatis会自动的帮我们将查询到中的数据填充到返回值里的

而接收数据的方法有以下几种

> 1. 如果查询出的数据只有一条，可以通过
>    1. 实体类对象接收
>    2. List集合接收
>    3. Map集合接收，结果`{password=123456, sex=男, id=1, age=23, username=admin}`
> 2. 如果查询出的数据有多条，一定不能用实体类对象接收，会抛异常TooManyResultsException，可以通过
>    1. 实体类类型的LIst集合接收
>    2. Map类型的LIst集合接收
>    3. 在mapper接口的方法上添加@MapKey注解

我们在TestMapper接口中编写一个selectAll方法，使用List<User>来进行接收:

```java
@Mapper
public interface TestMapper{
    public List<User> selectAll();

}
```

但是，如果只有这一个接口的话，Mybatis 是完全不懂你要对哪个表进行查询和怎么查询的。

所以我们就需要在映射文件里编写sql语句。

#### 编写映射文件

在映射文件中的mapper标签内加入一个select标签，并在标签中书写sql语句:

```xml
 <select id="selectAll" resultType="com.example.springbootlesson.bean.User">   <!---这里的id属性即为接口中的方法名-->
           select *                                           <!--resultType即为返回的类型-->
        from tb_user;
 </select>
```

然后在测试类中进行测试，这里使用spring的@Autowired注解来让spring容器帮我们进行依赖注入.

```java
@SpringBootTest
public class SpringbootLessonApplicationTests {

    @Autowired
    TestMapper testMapper;


    @org.junit.jupiter.api.Test
    public void testSelect() {
        System.out.println(testMapper.selectAll());
    }
}
```

运行一下测试方法，看看打印出来的结果

![](https://raw.githubusercontent.com/GNK48-Ava/imgs/main/mybatis_log1.png)

?为什么User的homeAddr属性都是null呢？

#### 自定义映射resultMap

在刚刚的例子中，我们发User类的所有属性都有，且被正确地映射了，但唯独homeAddr属性没有被映射上。

我们再观察一下数据库和User类便能找到答案。

![](https://raw.githubusercontent.com/GNK48-Ava/imgs/main/compare.png)

噢噢，原来是在java类中的属性为homeAddr，而数据库中的字段名却为home_addr，这才导致mybatis无法识别属性并将其填充到对象中。(顺便一提，数据库字段与实体类中属性大小写不同不会对映射产生影响)

为了解决这种情况，我们需要用到resultMap来自定义映射规则。

> resultMap：自定义映射，用于一对多或多对一或字段名和==属性名不一致的情况==  

我们来讲讲resultMap中常用属性

> resultMap标签:
>
> id：表示自定义映射的唯一标识，不能重复
>
> type：查询的数据要映射的实体类的类型  
>
> 子标签：  
>
> id：设置主键的映射关系  
>
> result：设置普通字段的映射关系  
>
> - 子标签属性：  
> - property：设置映射关系中实体类中的属性名  
> - column：设置映射关系中表中的字段名

我们在mapper映射文件中编写出一个resultMap，并且编写出映射的关系

```xml
    <resultMap id="userResultMap" type="com.example.bean.User">
        <result column="home_addr" property="homeAddr"></result>
    </resultMap>
```

并且将原来的select标签的resultType去掉，替换成resultMap，并使用自定义的userresultMap。最终映射文件如下:



```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.example.springbootLesson.mapper.TestMapper">
    <resultMap id="userResultMap" type="com.example.springbootlesson.bean.User">
        <result column="home_addr" property="homeAddr"></result>
    </resultMap>

    <select id="selectAll" resultMap="userResultMap">     
        select *
        from tb_user;
    </select>
</mapper>
```

接下来再重新，发现homeAddr属性果然被正确填充了。



至此，查询所有用户的需求已经被我们实现了，但关于mybatis的使用还是有几点需要补充的。

我们刚刚只演示了查询方法，接下来讲讲curd的标签该如何在映射文件中进行书写。(虽然一般我都是让插件帮我快速生成就是了)

### Mybatis的增删改查

这里主要是讲讲各种类型操作的标签如何在映射文件中书写。

id为对应接口的方法名，标签内写上具体的sql语句。

比较简单，难不倒卷王们。

#### 插入数据

```xml
<insert id="XXXX" >
        
  </insert>
```

#### 删除数据

```xml
<delete id="XXXX">
    

</delete>
```

#### 更改数据

```xml
<update id="XXXX">
    
</update>
```

#### 查询数据

```xml
<select id="XXXX" resultType="XXXX">
    
</select>
```

### 参数的传递

我们在实际开发中也很少会像刚刚演示的案例一样什么参数都没有只查询表中的所有数据。

那么我们如何在mybatis中将参数进行传递呢?

#### 单个参数的传递

 如果我们需要传入单个参数，很简单，只需要先在接口方法里添加参数，并在映射文件里使用#{参数名}即可使用。

这里我继续基于刚刚的示例进行讲解。这里我要添加一个依据用户id进行查询的方法为例。

在TestMapper接口里编写方法，并使用User来接收数据

```java
 public User selectById(Integer id);
```

可以看到我们传入了一个名为id的参数，其类型为Integer。

然后在映射文件里编写出对应的映射:

```xml
    <select id="selectById" resultMap="userResultMap">
        select *
        from tb_user
        where id = #{id}
    </select>
```

使用resultMap刚刚已经讲过了。

可以看到，我们在这里使用了#{id}来进行占位，而mybatis会自动将接口方法的参数填充进去。

测试一下，结果符合预期。

那我们如果传递一个对象，该如何调用对象的属性呢？

其实很简单，直接使用#{}进行接收就好了  ヾ(•ω•`)o

接下来我以将数据添加到数据库为例，在TestMapper接口中编写出方法:

```java
public void insert(User user);
```

在并且编写映射中加入对应的标签，#{}内就是User的属性

```xml
<insert id="insert">
    insert into tb_user
    values (#{id}, #{username}, #{password}, #{gender}, #{homeAddr})
</insert>
```

应该还是比较简单的。

顺便一提，其实我们也可以在映射文件中使用${}接收参数，只不过这个使用起来较为麻烦，需要自己拼接sql语句，故安全性不如#{}，故实际上用的比较少。

#### 多个参数的传递

如果我们要传递多个参数呢?哈哈，这还用讲？上手开写

接口方法:

```java
   public List<User> selectByGenderAndHomeAddr(String gender,String homeAddr);
```

映射文件:

```xml
<select id="selectByGenderAndHomeAddr" resultType="com.example.springbootLesson.bean.User">
    select *
    from tb_user
    where gender = #{gender}
      and home_addr = #{homeAddr}
</select>
```

直接跑起来。

.

..

...

还真跑起来了我擦

![](https://raw.githubusercontent.com/GNK48-Ava/imgs/main/QQ%E5%9B%BE%E7%89%8720221205184829.jpg)

咳咳，经测试，好像是springboot帮我们整了点小动作，进一步简化了开发。但原生的mybatis就会报错。

但我接下来还是得讲讲原本mybatis的多参数传递是怎么使用的。

当你传入多个参数时，mybatis实际上会使用一个Map来接收参数，大致为:

```java
map.put("arg0",方法参数1);
map.put("param1",方法参数1);
map.put("arg1",方法参数2);
map.put("param2",方法参数2);
........
```

所以我们可以使用#{arg0}或#{param1}来接收第一个参数，#{arg1}或#{param2}来接收第二个参数。

但显然这样子写出来的程序的可读性多少有些差。所以在实际开发中我们还得用到一个@Param注解。





##### 使用@Param注解

我们也可以使用为属性加上@Param来自定义属性名称,然后就可以在映射文件中使用自定义属性名进行接收了

例如:

```java
public void deleteByUserId(@Param("id") Integer uId);
```

而在映射文件中，#{}就得用id接收而不是uId了。

```xml
<delete id="deleteByUserId">
    delete from tb_user where id=#{id}
</delete>
```

而对于多参数也是同理的，并且会将存储map的agrX键该替换成@Param注解中传入的属性名。

```java
public List<User> selectByGenderAndHomeAddr(@Param("gender") String gender, @Param("homeAddr") String homeAddr);
```



#### Map参数的传递

也可以在方法中传入map类来作为参数，而在映射文件中同理也可以使用#{key}或${key}接收到对应的value。

但是由于多经过了一次map的封装，我们往往无法很好获知内部的参数有哪些，所以可能用得少一点。

同学们可以自己去尝试一下😋。

#### 数组和collection参数的传递

加入我有一个需求，想依据id进行批量删除，该如何实现呢？

难道要遍历id数组，多次调用单个删除方法？还是在映射文件中#{list[1]},#{list[2]}地写？

不管如何，这两个方法都有点捞了。mybatis为我们提供了动态sql语句来简化一些开发，其中就包括了允许我们在映射文件中遍历数组/collection。

仍以上面的例子为基础，在TestMapper接口中编写以下方法:

```java
 public void deleteByIds(int[] ids);
```

在映射文件中编写一下对应的标签:

```xml
    <delete id="deleteByIds">
        delete from tb_user where id in
        <foreach collection="ids" item="id" open="(" close=")" separator=",">
            #{id}
        </foreach>
    </delete>
```

介绍一下foreach标签的属性吧。

> - collection:传进来的数组/collection属性的名称
> - item：单个元素的名称
> - open：循环开始输出的字符
> - close：循环结束输出的字符
> - separator：元素输出间的分隔符

而标签内编写的就是循环输出的东西，由于这里我们将单个元素命名为id,故采用#{id}进行接收。

当我们将数组{1,2,3}作为参数传递调用该方法时，可以看到日志中看到:

```sql
delete from tb_user where id in ( ? , ? , ? )
```

这里的?是占位符，mybatis会将这里的？转化为实际的参数的。



至于mybatis的其他动态sql的使用，就先留给同学们课后学习掌握了。

### 使用注解编写sql语句

mybatis还允许我们通过为接口的方法上添加注解来编写sql.（注解真是无敌啊)

比较简单，这里只给出个简单的例子。

```java
@Select("select * from  tb_user")
@ResultMap("userResultMap")
public List<User> selectAll();
```

同时也可以看到，resultMap也可以通过注解指定(前提是对应的resultMap已在映射文件中定义)。

至于其他类型的操作，看到下面几个注解同学们应该也能猜到怎么进行了，就不多bb了

```java
@Insert()
@Delete()
@Update()
```

[点我跳回](#知识加油站⛽)



## MVC与三层架构💻

在此之前，我们编写项目都是各种类和接口放一起。在项目较为简单时或许没什么问题，甚至更方便。但当项目规模逐渐增大时，这个项目一眼看上去就很乱了，所以还是要学会将类和接口依据功能分包的。

MVC 模式和三层架构是一些理论的知识，将来我们使用了它们进行代码开发会让我们代码维护性和扩展性更好。

### MVC模式

> * M：Model，业务模型，处理业务
>
> * V：View，视图，界面展示
>
> * C：Controller，控制器，处理请求，调用模型和视图

实际上还有什么mvp、mvvm、mvi架构😥，前端和安卓的同学可能比较了解这方面（给卷王跪了😱），但我们后端这里就先不多展开了。

##### mvc的好处

> * 职责单一，互不影响。每个角色做它自己的事，各司其职。
>
> * 有利于分工协作。
>
> * 有利于组件重用

### 三层架构

三层架构是将我们的项目分成了三个层面，分别是 `表现层`、`业务逻辑层`、`数据访问层`。

> * 数据访问层：对数据库的CRUD基本操作
> * 业务逻辑层：对业务逻辑进行封装，组合数据访问层层中基本功能，形成复杂的业务逻辑功能。例如 `注册业务功能` ，我们会先调用 `数据访问层` 的 `selectByName()` 方法判断该用户名是否存在，如果不存在再调用 `数据访问层` 的 `insert()` 方法进行数据的添加操作
> * 表现层：接收请求，封装数据，调用业务逻辑层，响应数据

看到这里的数据访问层了吗？记忆力好的同学应该还记得mybatis的介绍

> MyBatis 是一款优秀的==持久层框架==，用于简化 JDBC 开发

一般三层架构的业务流程如下图:

![](https://raw.githubusercontent.com/GNK48-Ava/imgs/main/scjg.png)

而在springboot开发中，表现层就是我们的controller，业务逻辑层就是service，而数据访问层就是mapper(dao).

看到这里大家也应该大致要猜到项目中我们该怎么分包了，至少三层架构中的不同层级的包应该分开，并且数据实体类要放到一个包里。大概如图所示。

![](https://raw.githubusercontent.com/GNK48-Ava/imgs/main/package.png)

### MVC与三层架构的关系

可以说MVC是一个更宽泛的思想，而三层架构就是基于并实现mvc的一种架构。

而它两的关系如下图(图源CSDN)

![](https://raw.githubusercontent.com/GNK48-Ava/imgs/main/aHR0cHM6Ly9ibG9naW1hZ2UtMTI1NTYxODU5Mi5jb3MuYXAtY2hlbmdkdS5teXFjbG91ZC5jb20vaW1nMjAyMDAzMTkxMDQ1MDAucG5n.png)

[点我跳回](#知识加油站⛽)



