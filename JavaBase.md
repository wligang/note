# Java基础值集合

## 一、集合
序号	类描述
1	AbstractCollection 
实现了大部分的集合接口。
2	AbstractList 
继承于AbstractCollection 并且实现了大部分List接口。
3	AbstractSequentialList 
继承于 AbstractList ，提供了对数据元素的链式访问而不是随机访问。
4	LinkedList
该类实现了List接口，允许有null（空）元素。主要用于创建链表数据结构，该类没有同步方法，如果多个线程同时访问一个List，则必须自己实现访问同步，解决方法就是在创建List时候构造一个同步的List。例如：

Listlist=Collections.synchronizedList(newLinkedList(...));
LinkedList 查找效率低。

5	ArrayList
该类也是实现了List的接口，实现了可变大小的数组，随机访问和遍历元素时，提供更好的性能。该类也是非同步的,在多线程的情况下不要使用。ArrayList 增长当前长度的50%，插入删除效率低。

6	AbstractSet 
继承于AbstractCollection 并且实现了大部分Set接口。
7	HashSet
该类实现了Set接口，不允许出现重复元素，不保证集合中元素的顺序，允许包含值为null的元素，但最多只能一个。

8	LinkedHashSet
具有可预知迭代顺序的 Set 接口的哈希表和链接列表实现。
9	TreeSet
该类实现了Set接口，可以实现排序等功能。

10	AbstractMap 
实现了大部分的Map接口。
11	HashMap 
HashMap 是一个散列表，它存储的内容是键值对(key-value)映射。
该类实现了Map接口，根据键的HashCode值存储数据，具有很快的访问速度，最多允许一条记录的键为null，不支持线程同步。
12	TreeMap 
继承了AbstractMap，并且使用一颗树。
13	WeakHashMap 
继承AbstractMap类，使用弱密钥的哈希表。
14	LinkedHashMap 
继承于HashMap，使用元素的自然顺序对元素进行排序.
15	IdentityHashMap 
继承AbstractMap类，比较文档时使用引用相等。

## 2、泛型
1、什么是泛型？
Java 泛型（generics）是 JDK 5 中引入的一个新特性, 泛型提供了编译时类型安全检测机制，该机制允许程序员在编译时检测到非法的类型。
泛型的本质是参数化类型，也就是说所操作的数据类型被指定为一个参数。
2、泛型方法
你可以写一个泛型方法，该方法在调用时可以接收不同类型的参数。根据传递给泛型方法的参数类型，编译器适当地处理每一个方法调用。

下面是定义泛型方法的规则：
所有泛型方法声明都有一个类型参数声明部分（由尖括号分隔），该类型参数声明部分在方法返回类型之前（在下面例子中的<E>）。
每一个类型参数声明部分包含一个或多个类型参数，参数间用逗号隔开。一个泛型参数，也被称为一个类型变量，是用于指定一个泛型类型名称的标识符。
类型参数能被用来声明返回值类型，并且能作为泛型方法得到的实际参数类型的占位符。
泛型方法体的声明和其他方法一样。注意类型参数只能代表引用型类型，不能是原始类型（像int,double,char的等）。
3、类型通配符
类型通配符

1）、类型通配符一般是使用?代替具体的类型参数。
例如 List<?> 在逻辑上是List<String>,List<Integer> 等所有List<具体类型实参>的父类。
2）、类型通配符上限通过形如List来定义，如此定义就是通配符泛型值接受Number及其下层子类类型。
3）、类型通配符下限通过形如 List<? super Number>来定义，表示类型只能接受Number及其三层父类类型，如 Object 类型的实例。


