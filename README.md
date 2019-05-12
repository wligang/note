# 一、Java
## 1. 基础

###  1.1 Java修饰符
**访问修饰符**：
 1. default (即缺省，什么也不写）: 在同一包内可见，不使用任何修饰符。使用对象：类、接口、变量、方法。
 2. private : 在同一类内可见。使用对象：变量、方法。 注意：不能修饰类（外部类）
 3.  public : 对所有类可见。使用对象：类、接口、变量、方法
 4. protected : 对同一包内的类和所有子类可见。使用对象：变量、方法。 注意：不能修饰类（外部类）。
 5. 父类中声明为 public 的方法在子类中也必须为 public。
 6. 父类中声明为 protected 的方法在子类中要么声明为 protected，要么声明为 public，不能声明为 private。
 7. 父类中声明为 private 的方法，不能够被继承。
 
 **非访问修饰符**：
 1. static 修饰符，用来修饰类方法和类变量。
 2. final 修饰符，用来修饰类、方法和变量，final 修饰的类不能够被继承，修饰的方法不能被继承类重新定义，修饰的变量为常量，是不可修改的。
 3. abstract 修饰符，用来创建抽象类和抽象方法。
 4. synchronized 和 volatile 修饰符，主要用于线程的编程。
  
>【eg】:
在Java中 ++i、i++并不是线程安全的，要想线程安全，就用使用volatile修饰符完善的AtomicInteger

### 1.2 Java类加载原理与顺序
 归类总结为：加载、链接、初始化
 连接过程包括 验证，准备、解析三个阶段
【加载】：指的是把class字节码文件从各个来源通过类加载器装载入内存中。
>【字节码来源】：一般的加载来源包括从本地路径下编译生成的.class文件，从jar包中的.class文件，从远程网络，以及动态代理实时编译
【类加载器】：一般包括启动类加载器，扩展类加载器，应用类加载器，以及用户的自定义类加载器。

【验证】：保证加载进来的字节流符合虚拟机规范，不会造成安全错误
【准备】：主要是为类变量（注意，不是实例变量）分配内存，并且赋予初值。
【解析】：将常量池内的符号引用替换为直接引用的过程。
>符号引用。即一个字符串，但是这个字符串给出了一些能够唯一性识别一个方法，一个变量，一个类的相关信息,直接引用。可以理解为一个内存地址，或者一个偏移量。比如类方法，类变量的直接引用是指向方法区的指针；
	而实例方法，实例变量的直接引用则是从实例的头指针开始算起到这个实例变量位置的偏移量
	
【初始化】：主要是对类变量初始化，是执行类构造器的过程，另说只对static修饰的变量或语句进行初始化
可以参考如下图:![Java类声明周期简单分析](https://img-my.csdn.net/uploads/201208/26/1345979728_9052.jpg)
当初始化完成后，就可以使用了，在使用完之后，会在方法区GC的过程中对其进行卸载。至此Java的的整合生命周期也完成。[参考](https://blog.csdn.net/ln152315/article/details/79223441)
## 2. 容器
## 3. 多线程
多线程能满足程序员编写高效率的程序来达到充分利用 CPU 的目的。
一条线程指的是进程中一个单一顺序的控制流，一个进程中可以并发多个线程，每条线程并行执行不同的任务。
多线程是多任务的一种特别的形式，但多线程使用了更小的资源开销。

Java 三种创建线程的方法：
通过实现 Runnable 接口；
通过继承 Thread 类本身；
通过 Callable 和 Future 创建线程。

## 4. JVM
### 4.1 JVM介绍
JVM是Java Virtual Machine的缩写，JVM是一种用于计算设备的规范，它是一个虚构出来的计算机，是通过在实际的计算机上仿真模拟各种计算机功能来实现的。
Java语言的一个非常重要的特点就是与平台的无关性。而使用JVM是实现这一特点的关键。
Java语言使用jvm屏蔽了与具体平台相关的信息，使得Java语言编译程序只需生成在jvm上运行的目标代码（字节码），就可以在多种平台上不加修改地运行。Java虚拟机在执行字节码时，把字节码解释成具体平台上的机器指令执行。这就是Java的能够“一次编译，到处运行”的原因。
如图![在这里插入图片描述](https://gss3.bdstatic.com/7Po3dSag_xI4khGkpoWK1HF6hhy/baike/s=220/sign=26c48af78a13632711edc531a18fa056/c8ea15ce36d3d539811b19e83a87e950352ab0f2.jpg =300x300)
JVM在它的生存周期中有一个明确的任务，那就是运行Java程序，因此当Java程序启动的时候，就产生JVM的一个实例；当程序运行结束的时候，该实例也跟着消失了。下面我们从JVM的体系结构和它的运行过程这两个方面来对它进行比较深入的研究。

### 4.2 JVM内存结构
JVM都包含：堆内存、栈内存、方法区、本地方法栈、指令计数器及其他隐含寄存器

【堆内存】：所有通过new创建的对象和数组的内存都在堆中分配，堆内存中的实体不再被应用时，JVM启动垃圾回收机制，自动清除，是GC的主要区域，同样是线程共享的内存区域。堆被所有线程共享，堆区中不存放基本类型和对象引用，只存放对象本身。
 > 其大小可以通过-Xmx和-Xms来控制

【栈内存】：栈内存保存基本数据类型、局部变量，对象的引用变量都是在栈内存中的。栈内存中的数据，没有默认初始化值，用完就消失。
【方法区】：保存方法代码(编译后的java代码)和符号表；存放要加载的类信息、静态变量、final类型的常量、属性和方法信息。类信息是由类加载器从类文件中提取出来的。
>JVM用持久代（Permanet Generation）来存放方法区，可通过-XX:PermSize和-XX:MaxPermSize来指定最小值和最大值。

【本地方法栈】：用于支持native方法的执行，存储了每个native方法调用的状态。

结构示意图如下：

![jvm结构示意图](https://img-blog.csdn.net/20131113150006437?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQveF9wYW5kYQ==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/Center)
## 5.GC
### 5.1 GC介绍
GC是垃圾收集的意思，Java提供的GC功能可以自动监测对象是否超过作用域从而达到自动回收内存的目的，Java语言没有提供释放已分配内存的显式操作方法。GC可以有效的防止内存泄露，提高可使用的内存的使用效率。
在HotSpot虚拟机中，主要有:
Minor GC：新生代GC，指发生在新生代的垃圾收集动作，所有的Minor GC都会触发全世界的暂停（stop-the-world），停止应用程序的线程，不过这个过程非常短暂。
Major GC/Full GC：老年代GC，指发生在老年代的GC。

### 5.2 GC算法
Java堆是被所有线程共享的一块内存区域，所有对象实例和数组都在堆上进行内存分配。为了进行高效的垃圾回收，虚拟机把堆内存划分成新生代（Young Generation）、老年代（Old Generation）和永久代（Permanent Generation）3个区域。

JVM针对新生代，主要采用复制算法；针对老年代，通常采用标记-清除算法或者标记-整理算法来进行回收。

【复制算法】：新生代由 Eden 与 Survivor Space（S0，S1）构成，Survivor 幸存区是将内存分成大小相等的两块区域，每次使用其中的一块。当这一块的内存用完了，就将还存活的对象复制到另一块区域上，然后对该块进行内存回收。大小通过-Xmn参数指定，Eden 与 Survivor Space 的内存大小比例默认为8:1，可以通过-XX:SurvivorRatio 参数指定，比如新生代为10M 时，Eden分配8M，S0和S1各分配1M。
【标记算法】：老年代对象存活的时间比较长、比较稳定，因此采用**标记**(Mark)算法来进行回收，所谓标记就是扫描出存活的对象，然后再进行回收未被标记的对象，回收后对用空出的空间要么进行合并、要么标记出来便于下次进行分配，**目的就是要减少内存碎片带来的效率损耗**。
老年代的空间大小即-Xmx 与-Xmn 两个参数之差，用于存放经过几次Minor GC之后依旧存活的对象。当老年代的空间不足时，会触发Major GC/Full GC。



## 6. I/o
## 7. java8
### 7.1 Java8新特新
【Lambda 表达式】 − Lambda允许把函数作为一个方法的参数（函数作为参数传递进方法中。
【方法引用】 − 方法引用提供了非常有用的语法，可以直接引用已有Java类或对象（实例）的方法或构造器。与lambda联合使用，方法引用可以使语言的构造更紧凑简洁，减少冗余代码。
【默认方法】 − 默认方法就是一个在接口里面有了一个实现的方法。
【Stream API】 −新添加的Stream API（java.util.stream） 把真正的函数式编程风格引入到Java中。
【Date Time API】 − 加强对日期与时间的处理。
【Optional 类】 − Optional 类已经成为 Java 8 类库的一部分，用来解决空指针异常。
【Base64编码】内置了 Base64 编码的编码器和解码器。
【Nashorn, JavaScript 引擎】 − Java 8提供了一个新的Nashorn javascript引擎，它允许我们在JVM上运行特定的javascript应用。
[Java8新特新](https://www.runoob.com/java/java8-streams.html)
[Java9新特性](https://www.runoob.com/java/java9-new-features.html)


# 二、框架
## 1. spring
### 1.1spring介绍
 spring的核心是一个轻量级的容器（Container）,它是实现IoC(Inversion of Control)容器和非侵入性（No intrusive）的框架，并提供AOP(Aspect-oriented Programming)的实现方式，提供对持久层（Persistence）、事务（Transcation）的支持;提供MVC Web框架的实现，并对一些常用的企业服务API提供了一致的模型封装，是一个全方位的应用程序框架，除此之外，对现存的各种框架（Structs、JSF、hibernate、Ibatis、Webwork等），Spring也提供了与他们相整合的方案。
 
 >【细节列表】：
 > 1、Spring的核心是一个轻量级（Lightweight）的容器（Container）。 
2、Spring是实现IoC（Inversion of Control）容器和非入侵性（No intrusive）的框架。 
3、Spring提供AOP（Aspect-oriented programming）的实现方式，把应用业务逻辑和系统服务分开。 
4、Spring提供对持久层（Persistence）、事物（Transcation）的支持。 
5、Spring供MVC Web框架的实现，并对一些常用的企业服务API（Application Interface）提供一致的模型封装。 
6、Spring提供了对现存的各种框架（Structs、JSF、Hibernate、Ibatis、Webwork等）相整合的方案。 
总之，Spring是一个全方位的应用程序框架。
### 1.2 spring核心IOC和AOP
【控制反转/IoC】：指的是程序中的一些对象或者变量的控制权，在传统的程序中都是由应用程序自己控制对象创建或者变量赋值，这是一种主动式的控制，导致组件之间的完全耦合；现在将一些对象或者变量的创建控制权交给一个叫做Ioc容器的东西，由这个容器来控制应用程序中所需要的资源,这样就变成了被动的控制，对组件之间的关系进行解耦，所以所谓的**反转就是将控制权由应用程序转交到Ioc容器**。 
【依赖注入/DI】：注入就是IoC容器向应用程序中进行注入应用程序所需要的资源，由应用程序主动装配对象的依赖变应用程序被动接受依赖，所以IoC容器也叫DI容器
【AOP(面向切面编程)】：将具体的通用的应用从业务逻辑中分离出来，各自做各自专业的事情。
### 1.3 Srping加载bean的原理
(1)创建一个上下文context = createApplicationContext; 
(2)context中都会有一个BeanFactory（默认是DefaultListableBeanFactory）,在该类的子类类xmlBeanFactory中进行xml文件的解析； 
(3)在类XmlBeanDefinitionParser 中用Dom解析xml文件(DefaultXmlBeanDefinitionParser)，解析xml文件中所有bean，并将bean放到BeanDefinitionHolder中，封装成BeanDefinition； 
(4)再进行bean的注册，具体在BeanDefinitionReaderUtils类调用DefaultListableBeanFactory类的registerBeanDefinition进行bean的注册，在这里用了一HashMap存放bean,其中用Beanname作为键值，其封装好的beanDefinition作为值。还有用一个List存放所有的bean的名字
### 1.4 springBean的自动装配模式
【no】: spring默认的设置，在该设置下自动装配是关闭的，开发者需要在配置文件中用标签明确依赖关系；
【byName】: 该选项可以根据bean名称设置依赖关系。当向一个bean中自动装配一个属性时，容器将根据bean的名称自动在在配置文件中查询一个匹配的bean。如果找到的话，就装配这个属性，如果没找到的话就报错。
【byType】: 该选项可以根据bean类型设置依赖关系。当向一个bean中自动装配一个属性时，容器将根据bean的类型自动在在配置文件中查询一个匹配的bean。如果找到的话，就装配这个属性，如果没找到的话就报错。
【constructor】: 构造器自动装载，仅仅适用于与有构造器相同参数的bean，如果在容器中没有找到与构造器参数类型一致的bean，那么将会抛出异常。
【autodetect】: 该模式自动探测使用构造器自动装配或者byType自动装配。先尝试找合适的带参数的构造函数，若没有则自动选择byType的自动装配模式。

**基于注解的自动装配，spring默认是关闭注解模式的，所以需要在配置文件中设置**

### 1.5 spring的事件类型
1. 上下文更新事件（ContextRefreshedEvent）:该事件会在ApplicationContext被初始化或者更新时发布，也可以在调用ConfigurableApplicationContext接口中的refresh()方法时被触发。 
2. 上下文开始事件（ContextStartedEvent）: 当容器调用ConfigurableApplicationContext的Start()方法开始/重新开始容器时被触发。 
3. 上下文停止事件（ContextStoppedEvent）: 当容器调用ConfigurableApplicationContext的Stop()方法停止容器时触发该事件。 
4. 上下文关闭事件（ContextClosedEvent）: 当ApplicationContext被关闭时触发该事件。容器被关闭时，其管理的所有单例Bean都被销毁。 
5. 请求处理事件（RequestHandledEvent）: 在Web应用中，当一个http请求（request）结束触发该事件。
### 1.6 Spring的七种事务传播特性
propagation_requierd：如果当前没有事务，就新建一个事务，如果已存在一个事务中，加入到这个事务中，这是最常见的选择。
propagation_supports：支持当前事务，如果没有当前事务，就以非事务方法执行。
propagation_mandatory：使用当前事务，如果没有当前事务，就抛出异常。
propagation_required_new：新建事务，如果当前存在事务，把当前事务挂起。
propagation_not_supported：以非事务方式执行操作，如果当前存在事务，就把当前事务挂起。
propagation_never：以非事务方式执行操作，如果当前事务存在则抛出异常。
propagation_nested：如果当前存在事务，则在嵌套事务内执行。如果当前没有事务，则执行与propagation_required类似的操作

### 1.7 Spring中设计模式
1.【工厂模式】在各种BeanFactory以及ApplicationContext创建中都用到了；
2.【模版模式】各种BeanFactory以及ApplicationContext实现中也都用到了；
3.【代理模式】在Aop实现中用到了JDK的动态代理；
4.【单例模式，这个比如在创建bean的时候。
5.【Tomcat中有很多场景都使用到了外观模式，因为Tomcat中有很多不同的组件，每个组件需要相互通信，但又不能将自己内部数据过多地暴露给其他组件。用外观模式隔离数据是个很好的方法。
6.【策略模式】Comparator这个接口简直就是为策略模式而生的。Comparable和Comparator的区别一文中，详细讲了Comparator的使用。比方说Collections里面有一个sort方法，因为集合里面的元素有可能是复合对象，复合对象并不像基本数据类型，可以根据大小排序，复合对象怎么排序呢？基于这个问题考虑，Java要求如果定义的复合对象要有排序的功能，就自行实现Comparable接口或Comparator接口.
7.【原型模式】：使用原型模式创建对象比直接new一个对象在性能上好得多，因为Object类的clone()方法是一个native方法，它直接操作内存中的二进制流，特别是复制大对象时，性能的差别非常明显。
8.【迭代器模式】：Iterable接口和Iterator接口 这两个都是迭代相关的接口，可以这么认为，实现了Iterable接口，则表示某个对象是可被迭代的；Iterator接口相当于是一个迭代器，实现了Iterator接口，等于具体定义了这个可被迭代的对象时如何进行迭代的
[资料](https://blog.csdn.net/zhongxiangbo/article/details/70859650)

## 2 springMvc
Spring MVC 提供了一种分离式的方法来开发 Web 应用。通过运用像 DispatcherServelet，MoudlAndView 和 ViewResolver 等一些简单的概念，开发 Web 应用将会变的非常简单。

##  3 Springboot
### 3.1 springboot的说明
Spring Boot 是 Spring 开源组织下的子项目，是 Spring 组件一站式解决方案，主要是简化了使用 Spring 的难度，简省了繁重的配置，提供了各种启动器，开发者能快速上手。
### 3.2 springboot的优点
独立运行、简化配置、自动配置、无代码生成和XML配置、应用监控、上手容易
### 3.3 springboot自动配置原理
注解 @EnableAutoConfiguration, @Configuration, @ConditionalOnClass 就是自动配置的核心，首先它得是一个配置文件，其次根据类路径下是否有这个类去自动配置。

启动类上面的注解是@SpringBootApplication，它有三部分组成：
**@SpringBootConfiguration**：组合了 @Configuration 注解，实现配置文件的功能。
**@EnableAutoConfiguration**：打开自动配置的功能，也可以关闭某个自动配置的选项，如关闭数据源自动配置功能： @SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })。
**@ComponentScan**：Spring组件扫描。
[资料](https://www.jianshu.com/p/63ad69c480fe/)

## 4 mybaties
![在这里插入图片描述](https://img-blog.csdn.net/20180421203113233)
[资料](http://ju.outofmemory.cn/entry/370116)

 9. 
 

# 三、中间件
## 1. MQ

# 四、数据库
## 1. mysql
##  2. oracle
##  3. redis
##  4. mongodb
[资料一](https://blog.csdn.net/szm1234560/article/details/78991577)    &nbsp;&nbsp;  [资料二](https://www.cnblogs.com/java-spring/p/9488227.html)
# 五、网络


# 六、操作系统
##  1. Linux

# 七、系统设计
## 1 面向对象设计原则
1、【开闭原则】（Open Close Principle）：**对扩展开放，对修改关闭**。在程序需要进行拓展的时候，不能去修改原有的代码，实现一个热插拔的效果。简言之，是为了使程序的扩展性好，易于维护和升级。想要达到这样的效果，我们需要使用接口和抽象类，后面的具体设计中我们会提到这点。
2、【里氏代换原则】（Liskov Substitution Principle）：**任何基类可以出现的地方，子类一定可以出现**。LSP 是继承复用的基石，只有当派生类可以替换掉基类，且软件单位的功能不受到影响时，基类才能真正被复用，而派生类也能够在基类的基础上增加新的行为。里氏代换原则是对开闭原则的补充。实现开闭原则的关键步骤就是抽象化，而基类与子类的继承关系就是抽象化的具体实现，所以里氏代换原则是对实现抽象化的具体步骤的规范。
3、【依赖倒转原则】（Dependence Inversion Principle）：DIP是开闭原则的基础，具体内容：**针对接口编程，依赖于抽象而不依赖于具体**。
4、【接口隔离原则】（Interface Segregation Principle）：ISP **使用多个隔离的接口，比使用单个接口要好**。它还有另外一个意思是：降低类之间的耦合度。由此可见，其实设计模式就是从大型软件架构出发、便于升级和维护的软件设计思想，它强调**降低依赖，降低耦合**。
总结一点：要为各个类建立它们需要的专用接口，而不要试图去建立一个很庞大的接口供所有依赖它的类去调用。
5、【迪米特法则】（Demeter Principle）:又称最少知道原则。是指：一个实体应当尽量少地与其他实体之间发生相互作用，使得系统功能模块相对独立。
6、【合成复用原则】（Composite Reuse Principle）：合成复用原则是指：尽量使用合成/聚合的方式，而不是使用继承。通俗就是通过将已有的对象纳入新对象中，作为新对象的成员对象来实现的，新对象可以调用已有对象的功能，从而达到复用。
7、【单一职责】Single Responsibility Principle，SRP）：单一职责原则规定一个类应该有且仅有一个引起它变化的原因，否则类应该被拆分
[面向对象设计](http://c.biancheng.net/design_pattern)

## 2. 设计模式
 又称设计模式，是一套被反复使用、多数人知晓的、经过分类编目的、代码设计经验的总结。
 应用设计模式的目的是为了提高代码的**可重用性、代码的可读性和代码的可靠性**。
### 1.1 设计模式的意义
1）可以提高程序员的思维能力、编程能力和设计能力。
2）使程序设计更加标准化、代码编制更加工程化，使软件开发效率大大提高，从而缩短软件的开发周期。
3）使设计的代码可重用性高、可读性强、可靠性高、灵活性好、可维护性强。
### 1.2 GoF的设计模式
单例（Singleton）模式：某个类只能生成一个实例，该类提供了一个全局访问点供外部获取该实例，其拓展是有限多例模式。
原型（Prototype）模式：将一个对象作为原型，通过对其进行复制而克隆出多个和原型类似的新实例。
工厂方法（Factory Method）模式：定义一个用于创建产品的接口，由子类决定生产什么产品。
抽象工厂（AbstractFactory）模式：提供一个创建产品族的接口，其每个子类可以生产一系列相关的产品。
建造者（Builder）模式：将一个复杂对象分解成多个相对简单的部分，然后根据不同需要分别创建它们，最后构建成该复杂对象。
代理（Proxy）模式：为某对象提供一种代理以控制对该对象的访问。即客户端通过代理间接地访问该对象，从而限制、增强或修改该对象的一些特性。
适配器（Adapter）模式：将一个类的接口转换成客户希望的另外一个接口，使得原本由于接口不兼容而不能一起工作的那些类能一起工作。
桥接（Bridge）模式：将抽象与实现分离，使它们可以独立变化。它是用组合关系代替继承关系来实现，从而降低了抽象和实现这两个可变维度的耦合度。
装饰（Decorator）模式：动态的给对象增加一些职责，即增加其额外的功能。
外观（Facade）模式：为多个复杂的子系统提供一个一致的接口，使这些子系统更加容易被访问。
享元（Flyweight）模式：运用共享技术来有效地支持大量细粒度对象的复用。
组合（Composite）模式：将对象组合成树状层次结构，使用户对单个对象和组合对象具有一致的访问性。
模板方法（TemplateMethod）模式：定义一个操作中的算法骨架，而将算法的一些步骤延迟到子类中，使得子类可以不改变该算法结构的情况下重定义该算法的某些特定步骤。
策略（Strategy）模式：定义了一系列算法，并将每个算法封装起来，使它们可以相互替换，且算法的改变不会影响使用算法的客户。
命令（Command）模式：将一个请求封装为一个对象，使发出请求的责任和执行请求的责任分割开。
职责链（Chain of Responsibility）模式：把请求从链中的一个对象传到下一个对象，直到请求被响应为止。通过这种方式去除对象之间的耦合。
状态（State）模式：允许一个对象在其内部状态发生改变时改变其行为能力。
观察者（Observer）模式：多个对象间存在一对多关系，当一个对象发生改变时，把这种改变通知给其他多个对象，从而影响其他对象的行为。
中介者（Mediator）模式：定义一个中介对象来简化原有对象之间的交互关系，降低系统中对象间的耦合度，使原有对象之间不必相互了解。
迭代器（Iterator）模式：提供一种方法来顺序访问聚合对象中的一系列数据，而不暴露聚合对象的内部表示。
访问者（Visitor）模式：在不改变集合元素的前提下，为一个集合中的每个元素提供多种访问方式，即每个元素有多个访问者对象访问。
备忘录（Memento）模式：在不破坏封装性的前提下，获取并保存一个对象的内部状态，以便以后恢复它。
解释器（Interpreter）模式：提供如何定义语言的文法，以及对语言句子的解释方法，即解释器。
[设计模式资料](https://www.runoob.com/design-pattern/design-pattern-tutorial.html)
[资料二](https://www.cnblogs.com/yueguanguanyun/p/9584501.html)

# 八、数据结构与算法
 1. 排序算法
 2. 递归算法
