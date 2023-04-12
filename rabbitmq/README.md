# 蓝山工作室 java后端-消息队列（rabbitmq）

# MQ

**mq**: **“消息队列(Message Queue)”是在消息的传输过程中保存消息的容器**。在消息队列中，通常有生产者和消费者两个角色。生产者只负责发送数据到消息队列，谁从消息队列中取出数据处理，他不管。消费者只负责从消息队列中取出数据处理，他不管这是谁发送的数据。

![](https://gitee.com/feiWoSCun/drawing-bed/raw/master/class/:2023/3/27:16:3:{S}-png.1679905335448  1679905335397.png)

### **为什么要学习mq**

(因为不卷没饭吃)

- **解耦**。如图所示。假设有系统B、C、D都需要系统A的数据，于是系统A调用三个方法发送数据到B、C、D。这时，系统D不需要了，那就需要在系统A把相关的代码删掉。假设这时有个新的系统E需要数据，这时系统A又要增加调用系统E的代码。为了降低这种强耦合，就可以使用MQ，**系统A只需要把数据发送到MQ，其他系统如果需要数据，则从MQ中获取即可**。

![](https://gitee.com/feiWoSCun/drawing-bed/raw/master/class/:2023/3/27:16:3:{S}-png.1679905524448  1679905524374.png)



- **异步**。还是如图所示。一个客户端请求发送进来，系统A会处理系统B、C、D三个系统的需求，同步请求的话，响应时间就是系统A、B、C、D的总和。**如果使用MQ，系统A发送数据到MQ，然后就可以返回响应给客户端，不需要再等待系统B、C、D的响应，可以大大地提高性能**。对于一些非必要的业务，比如发送短信，发送邮件等等，就可以采用MQ。
- 削峰。如图所示。这其实是MQ一个很重要的应用。假设系统A在某一段时间请求数暴增，有5000个请求发送过来，系统A这时就会发送5000条SQL进入MySQL进行执行，MySQL对于如此庞大的请求当然处理不过来，MySQL就会崩溃，导致系统瘫痪。**如果使用MQ，系统A不再是直接发送SQL到数据库，而是把数据发送到MQ，MQ短时间积压数据是可以接受的，然后由消费者每次拉取2000条进行处理，防止在请求峰值时期大量的请求直接发送到MySQL导致系统崩溃**。
- ![](https://gitee.com/feiWoSCun/drawing-bed/raw/master/class/:2023/3/27:16:3:{S}-png.1679905633447  1679905632684.png)

由于消息队列的原理部分都差不多，可以做到举一反三的效果。所以这次以rabbitmq为例

## rabbitMq

RabbitMQ是一款使用Erlang语言开发的，实现[^AMQP]的开源消息中间件。首先要知道一些RabbitMQ的特点，[官网](https://www.rabbitmq.com/)可查：

- 可靠性。支持持久化，传输确认，发布确认等保证了MQ的可靠性。
- **灵活的分发消息策略。这应该是RabbitMQ的一大特点。在消息进入MQ前由Exchange(交换机)进行路由消息。分发消息策略有：简单模式、工作队列模式、发布订阅模式、路由模式、通配符模式。**
- **支持集群。多台RabbitMQ服务器可以组成一个集群，形成一个逻辑Broker。**
- 多种协议。RabbitMQ支持多种消息队列协议，比如 STOMP、MQTT 等等。
- 支持多种语言客户端。RabbitMQ几乎支持所有常用编程语言，包括 Java、.NET、Ruby 等等。
- 可视化管理界面。RabbitMQ提供了一个易用的用户界面，使得用户可以监控和管理消息 Broker。
- 插件机制。RabbitMQ提供了许多插件，可以通过插件进行扩展，也可以编写自己的插件。

[^AMQP]: AMQP，即Advanced Message Queuing Protocol，**一个提供统一消息服务的应用层标准高级消息队列协议**，是应用层协议的一个开放标准，为面向消息的中间件设计。 基于此协议的客户端与消息中间件可传递消息，并不受客户端/中间件不同产品，不同的开发语言等条件的限制。



## RabbitMq核心概念

![](https://gitee.com/feiWoSCun/drawing-bed/raw/master/class/:2023/3/27:16:3:{S}-png.1679906425448  1679906425019.png)







| **名称**                 | **作用**                                                     |
| ------------------------ | ------------------------------------------------------------ |
| **Publisher（发布者）**  | 发布者 (或称为生产者) 负责生产消息并将其投递到指定的交换器上。 |
| **Broker**               | 一个真实部署运行的 RabbitMQ 服务。                           |
| **Message（消息）**      | 消息由消息头和消息体组成。消息头用于存储与消息相关的元数据：如目标交换器的名字 (exchange_name) 、路由键 (RountingKey) 和其他可选配置 (properties) 信息。消息体为实际需要传递的数据。 |
| **Exchange（交换器）**   | 交换器负责接收来自生产者的消息，并将将消息路由到一个或者多个队列中，如果路由不到，则返回给生产者或者直接丢弃，这取决于交换器的 mandatory 属性：当 mandatory 为 true 时：如果交换器无法根据自身类型和路由键找到一个符合条件的队列，则会将该消息返回给生产者；当 mandatory 为 false 时：如果交换器无法根据自身类型和路由键找到一个符合条件的队列，则会直接丢弃该消息。 |
| **BindingKey** (绑定键） | 交换器与队列通过 BindingKey 建立绑定关系。                   |
| **Routingkey**（路由键） | 生产者将消息发给交换器的时候，一般会指定一个 RountingKey，用来指定这个消息的路由规则。当 RountingKey 与 BindingKey 基于交换器类型的规则相匹配时，消息被路由到对应的队列中。 |
| **Queue**（消息队列）    | 用于存储路由过来的消息。多个消费者可以订阅同一个消息队列，此时队列会将收到的消息将以轮询 (round-robin) 的方式分发给所有消费者。即每条消息只会发送给一个消费者，不会出现一条消息被多个消费者重复消费的情况。 |
| **Consumer**（消费者）   | 消费者订阅感兴趣的队列，并负责消费存储在队列中的消息。为了保证消息能够从队列可靠地到达消费者，RabbitMQ 提供了消息确认机制 (message acknowledgement)，并通过 autoAck 参数来进行控制：当 autoAck 为 true 时：此时消息发送出去 (写入TCP套接字) 后就认为消费成功，而不管消费者是否真正消费到这些消息。当 TCP 连接或 channel 因意外而关闭，或者消费者在消费过程之中意外宕机时，对应的消息就丢失。因此这种模式可以提高吞吐量，但会存在数据丢失的风险。当 autoAck 为 false 时：需要用户在数据处理完成后进行手动确认，只有用户手动确认完成后，RabbitMQ 才认为这条消息已经被成功处理。这可以保证数据的可靠性投递，但会降低系统的吞吐量。 |
| **Connection**（连接）   | 用于传递消息的 TCP 连接。                                    |
| **Channel**（信道）      | RabbitMQ 采用类似 NIO (非阻塞式 IO ) 的设计，通过 Channel 来复用 TCP 连接，并确保每个 Channel 的隔离性，就像是拥有独立的 Connection 连接。当数据流量不是很大时，采用连接复用技术可以避免创建过多的 TCP 连接而导致昂贵的性能开销。 |
| Virtual Host（虚拟主机） | RabbitMQ 通过虚拟主机来实现逻辑分组和资源隔离，一个虚拟主机就是一个小型的 RabbitMQ 服务器，拥有独立的队列、交换器和绑定关系。用户可以按照不同业务场景建立不同的虚拟主机，虚拟主机之间是完全独立的，你无法将 vhost1 上的交换器与 vhost2 上的队列进行绑定，这可以极大的保证业务之间的隔离性和数据安全。默认的虚拟主机名为 `/` |

### exchange（交换机）与队列的交换类型

###  fanout

这是最简单的一种交换器模型，此时会把消息路由到与该交换器绑定的所有队列中。如下图，任何发送到 X 交换器上的消息，都会被路由到 Q1 和 Q2 两个队列上。

 ![img](https://p1-jj.byteimg.com/tos-cn-i-t2oaga2asx/gold-user-assets/2020/1/6/16f7a4014628ba4c~tplv-t2oaga2asx-zoom-in-crop-mark:4536:0:0:0.image) 

###  direct

把消息路由到 BindingKey 和 RountingKey 完全一样的队列中。如下图，当消息的 RountingKey 为 orange 时，消息会被路由到 Q1 队列；当消息的 RountingKey  为 black 或 green 时，消息会被路由到 Q2 队列。

 ![img](https://p1-jj.byteimg.com/tos-cn-i-t2oaga2asx/gold-user-assets/2020/1/6/16f7a40302a0db52~tplv-t2oaga2asx-zoom-in-crop-mark:4536:0:0:0.image) 

需要特别说明的是一个交换器绑定多个队列时，它们的 BindingKey 是可以相同的，如下图。此时当消息的 RountingKey 为 black 时，消息会同时被路由到 Q1 和 Q2 队列。

 ![img](https://p1-jj.byteimg.com/tos-cn-i-t2oaga2asx/gold-user-assets/2020/1/6/16f7a4030a36acb0~tplv-t2oaga2asx-zoom-in-crop-mark:4536:0:0:0.image) 

### topic

将消息路由到 BindingKey 和 RountingKey 相匹配的队列中，匹配规则如下：

- RountingKey 和 BindingKey 由多个单词使用逗号  `.` 进行连接；
- BindingKey 支持两个特殊符号：`#` 和 `*` 。其中 `*` 用于匹配一个单词， `#` 用于匹配零个或者多个单词。

以下是官方文档中的示例，交换器与队列的绑定情况如图所示，此时的路由情况如下：

 ![img](https://p1-jj.byteimg.com/tos-cn-i-t2oaga2asx/gold-user-assets/2020/1/6/16f7a4015f452d5e~tplv-t2oaga2asx-zoom-in-crop-mark:4536:0:0:0.image) 

- 路由键为 `lazy.orange.elephant` 的消息会发送给所有队列；
- 路由键为 `quick.orange.fox` 的消息只会发送给 Q1 队列；
- 路由键为 `lazy.brown.fox` 的消息只会发送给 Q2 队列；
- 路由键为 `lazy.pink.rabbit` 的消息只会发送给 Q2 队列；
- 路由键为 `quick.brown.fox` 的消息与任何绑定都不匹配；
- 路由键为 `orange` 或 `quick.orange.male.rabbit` 的消息也与任何绑定都不匹配。

### headers

在交换器与队列进行绑定时可以指定一组键值对作为 BindingKey；在发送消息的 headers 中的可以指定一组键值对属性，当这些属性与 BindingKey 相匹配时，则将消息路由到该队列。同时还可以使用 `x-match` 参数指定匹配模式：

- **x-match = all** ：所有的键值对都相同才算匹配成功；
- **x-match = any**：只要有一个键值对相同就算匹配成功。

headers 类型的交换器性能比较差，因此其在实际开发中使用得比较少。



# RabbitMqの初体验

windows安装rabbitmq很繁琐 还不如docker一条命令

## 使用docker安装rabbitmq

```java
docker run -d -v /opt/rabbitmq/data:/var/lib/rabbitmq -p 5672:5672 -p 15672:15672 --name rabbitmq --restart=always --hostname myRabbit rabbitmq:3.9.12-management
```

**简单讲一下这条命令**

| 命令                       | 作用                                                         |
| -------------------------- | ------------------------------------------------------------ |
| -d                         | 后台持续运行 使用docker exec 进入容器内部之后 使用ctrl+p+q或者 ctrl+c退出容器，依然可以让他运行 |
| -p                         | 端口映射，-P是随机端口映射                                   |
| --name                     | 容器的名字                                                   |
| restart                    | 重启规则                                                     |
| hostname                   | 容器的主机名字                                               |
| rabbitmq:3.9.12-management | rabbit版本                                                   |

执行完毕之后 可以尝试访问16572端口，如果出现对应的页面就表示安装成功，初始账号密码 guest guest

**成功图片如下**

![](https://gitee.com/feiWoSCun/drawing-bed/raw/master/class/:2023/3/27:17:3:{S}-png.1679908765448  1679908764876.png)



# idea+springboot整合RabbitMq

**pom依赖**

```java
<dependency>
<groupId>org.springframework.boot</groupId>
<artifactId>spring-boot-starter-amqp</artifactId>
</dependency>
```

//springboot 2.5.14

**yml依赖**

```yml
spring:
  rabbitmq:
    host: 110.42.240.230 //主机ip地址
    port: 5672   //  client端 默认端口
    username: guest//账号
    password: guest//密码
    virtual-host: /   //虚拟主机 默认 /
```

## consumer消费者

### 创建RabbitMq配置文件

```java
@Configuration
public class RabbitMqConfig {
    public static final String EXCHANGE_NAME = "luobin_exchange";
    public static final String QUEUE_NAME = "luobin_queue";
    /**
     * 交换机,这里创建topic交换机
     */
    @Bean("exchange")
    public Exchange getExchange() {
        return ExchangeBuilder.topicExchange(EXCHANGE_NAME).build();
    }

    /**
     * 创建队列
     * @return
     */
    @Bean("queue")
    public Queue getQueue() {
        return QueueBuilder.durable(QUEUE_NAME).build();

    }

    /**
     *绑定交换机和队列
     * binding
     */

    @Bean
    public Binding binding(@Qualifier("queue") Queue queue, @Qualifier("exchange") Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("binding_test").noargs();
    }
}
```

创建controller注入RabbitMqController,模拟生产者发送消息

## direct exchange

```java
@RestController
public class RabbitMqController {
    @Resource
    private RabbitTemplate rabbitTemplate;

    @RequestMapping("/test")
    public String testMq() {
        rabbitTemplate.convertAndSend(RabbitMqConfig.EXCHANGE_NAME, "binding_test", new Animal("罗彬",12,1));
        return "success";
    }
}
```

创建RabbitMqListener 模拟消费者

```java
@Component
public class RabbitMqListener {
    @RabbitListener(queues = RabbitMqConfig.QUEUE_NAME)
    public void getMessage(Message message) {
        //接受序列化对象
        Object o = SerializationUtils.deserialize(message.getBody());
        System.out.println(o);
    }
}
```



## **Topic Exchange**

topic 是RabbitMQ中最灵活的一种方式，可以根据routing_key自由的绑定不同的队列

首先对topic规则配置，这里使用两个队列来测试

```
@Configuration
public class TopicRabbitConfig {

	public final static String TOPIC_ONE = "topic.one";
	public final static String TOPIC_TWO = "topic.two";
	public final static String TOPIC_EXCHANGE = "topicExchange";

	@Bean
	public Queue queue_one(){
		return new Queue(TOPIC_ONE);
	}

	@Bean
	public Queue queue_two(){
		return new Queue(TOPIC_TWO);
	}

	@Bean
	TopicExchange exchange(){
		return new TopicExchange(TOPIC_EXCHANGE);
	}

	@Bean
	Binding bindingExchangeOne(Queue queue_one, TopicExchange exchange){
		return BindingBuilder.bind(queue_one).to(exchange).with("topic.one");
	}

	@Bean
	Binding bindingExchangeTwo(Queue queue_two, TopicExchange exchange){
		//# 表示零个或多个词
		//* 表示一个词
		return BindingBuilder.bind(queue_two).to(exchange).with("topic.#");
	}

}
```

使用queueMessages同时匹配两个队列，queueMessage只匹配”topic.message”队列

```
@Component
public class TopicSender {

	@Autowired
	private AmqpTemplate rabbitTemplate;

	//两个消息接受者都可以收到
	public void send_one() {
		String context = "Hi, I am message one";
		System.out.println("Sender : " + context);
		this.rabbitTemplate.convertAndSend(TopicRabbitConfig.TOPIC_EXCHANGE,"topic.one",context);
	}


	//只有TopicReceiverTwo都可以收到
	public void send_two() {
		String context = "Hi, I am message two";
		System.out.println("Sender : " + context);
		this.rabbitTemplate.convertAndSend(TopicRabbitConfig.TOPIC_EXCHANGE,"topic.two",context);
	}

}
```

发送send1会匹配到topic.#和topic.message 两个Receiver都可以收到消息，发送send2只有topic.#可以匹配所有只有Receiver2监听到消息

## **Fanout Exchange**

Fanout 就是我们熟悉的广播模式或者订阅模式，给Fanout交换机发送消息，绑定了这个交换机的所有队列都收到这个消息。

Fanout 相关配置

```Java
@Configuration
public class FanoutRabbitConfig {

    @Bean
    public Queue AMessage() {
        return new Queue("fanout.A");
    }

    @Bean
    public Queue BMessage() {
        return new Queue("fanout.B");
    }

    @Bean
    public Queue CMessage() {
        return new Queue("fanout.C");
    }

    @Bean
    FanoutExchange fanoutExchange() {
        return new FanoutExchange("fanoutExchange");
    }

    @Bean
    Binding bindingExchangeA(Queue AMessage,FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(AMessage).to(fanoutExchange);
    }

    @Bean
    Binding bindingExchangeB(Queue BMessage, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(BMessage).to(fanoutExchange);
    }

    @Bean
    Binding bindingExchangeC(Queue CMessage, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(CMessage).to(fanoutExchange);
    }

}
```

这里使用了A、B、C三个队列绑定到Fanout交换机上面，发送端的routing_key写任何字符都会被忽略：

```Java
public void send() {
		String context = "hi, fanout msg ";
		System.out.println("Sender : " + context);
		this.rabbitTemplate.convertAndSend("fanoutExchange","", context);
}
```

结果如下：

```
Sender : hi, fanout msg 
...
fanout Receiver B: hi, fanout msg 
fanout Receiver A  : hi, fanout msg 
fanout Receiver C: hi, fanout msg 
```

结果说明，绑定到fanout交换机上面的队列都收到了消息



# RabbitMq如何保证消息不丢失？

#### 第一种：消息持久化

RabbitMQ 的消息默认存放在内存上面，如果不特别声明设置，消息不会持久化保存到硬盘上面的，如果节点重启或者意外crash掉，消息就会丢失。

所以就要对消息进行持久化处理。如何持久化，下面具体说明下：

要想做到消息持久化，必须满足以下三个条件，缺一不可。

1） Exchange 设置持久化

2）Queue 设置持久化

3）Message持久化发送：发送消息设置发送模式deliveryMode=2，代表持久化消息

#### 第二种：ACK确认机制

多个消费者同时收取消息，比如消息接收到一半的时候，一个消费者死掉了(逻辑复杂时间太长，超时了或者消费被停机或者网络断开链接)，如何保证消息不丢？

这个使用就要使用Message acknowledgment 机制，就是消费端消费完成要通知服务端，服务端才把消息从内存删除。

这样就解决了，及时一个消费者出了问题，没有同步消息给服务端，还有其他的消费端去消费，保证了消息不丢的case。

#### 第三种：设置集群镜像模式

我们先来介绍下RabbitMQ三种部署模式：

1）单节点模式：最简单的情况，非集群模式，节点挂了，消息就不能用了。业务可能瘫痪，只能等待。
2）普通模式：默认的集群模式，某个节点挂了，该节点上的消息不能用，有影响的业务瘫痪，只能等待节点恢复重启可用（必须持久化消息情况下）。
3）镜像模式：把需要的队列做成镜像队列，存在于多个节点，属于RabbitMQ的HA方案

为什么设置镜像模式集群，因为队列的内容仅仅存在某一个节点上面，不会存在所有节点上面，所有节点仅仅存放消息结构和元数据。下面自己画了一张图介绍普通集群丢失消息情况：

#### 第四种：消息补偿机制

为什么还要消息补偿机制呢？难道消息还会丢失，没错，系统是在一个复杂的环境，不要想的太简单了，虽然以上的三种方案，基本可以保证消息的高可用不丢失的问题，

但是作为有追求的程序员来讲，要绝对保证我的系统的稳定性，有一种危机意识。

比如：持久化的消息，保存到硬盘过程中，当前队列节点挂了，存储节点硬盘又坏了，消息丢了，怎么办？

产线网络环境太复杂，所以不知数太多，消息补偿机制需要建立在消息要写入DB日志，发送日志，接受日志，两者的状态必须记录。

然后根据DB日志记录check 消息发送消费是否成功，不成功，进行消息补偿措施，重新发送消息处理。





