## 1.Spring 架构

Spring 总共大约有 20 个模块， 由 1300 多个不同的文件构成。 

而这些组件被分别整合在`核心容器（Core Container）` 、 `AOP（Aspect Oriented Programming）` 和`设备支持（Instrmentation）` 、`数据访问及集成（Data Access/Integeration）` 、 `Web`、 `报文发送（Messaging）` 、 `Test`， 6 个模块集合中。 以下是 Spring 5 的模块结构图：
![](/images/gqzdev-2020-07-06_11-42-18.png)



从这张图中，我们可以看到，Spring Framework 中包含的东西还是非常多的，虽然东西很多，但是主次其实很明显。

我们在学习 Spring 的时候，有两个非常重要的地方：

1. Core Container
2. AOP

这两个是核心，也是我们到时候源码分析的重点，其他的 Data Access 、Web 等，基本都是以这两个为基础扩展出来的功能。

我来给大家挨个介绍一下。

## 2.Spring 模块

### 2.1 Core Container

Core Container 是 Spring 容器的核心模块，里边主要包含四个模块：Beans、Core、Context 以及 Expression Language，我们所熟知的 IoC/DI 就是由 Beans 和 Core 来提供。

我来分别介绍一下这几个模块的作用。

- Core：这个是 Spring 的核心模块，它里边主要是 Spring 框架的一些基础工具类，比如一些序列化工具、类型转换器、我们常用的优先级注解等等，都是它提供的。
- Beans：Beans 就没啥好说的，我们所熟知的 IoC/DI 就是由它提供的。
- Context：Context 虽然不像前两个模块那么基础，因为它是基于 Core 和 Beans 构建的，但是 Context 也是我们在 Web 项目中必不可少的工具，资源加载、Event 等等都需要 Context。
- Expression Language：SpEL 虽然归类于 Core Container，但是在目前前后端分离的背景下，其实 SpEL 的使用场景大大缩水。SpEL 是一个支持查询并在运行时可以操纵一个对象图的表达式语言，它的语法类似于统一 EL，但提供了更多的功能，而且它可以独立使用。

### 2.2 AOP

AOP 也是 Spring 中一个非常重要的功能模块，其实小伙伴们从平时的面试中应该就能感觉出来 AOP 的分量，可以说，如果没有 AOP，你就见不到 Spring 中很多令人惊叹的功能。像我们熟知的 Spring 中的事务管理，就离不开 Spring AOP。关于 AOP 的更多介绍，大家可以参考已经录制的 【Spring 基础篇】的视频，我就不再赘述。

### 2.3 Data Access

Data Access 模块中，主要是封装了一些数据库持久化相关的操作。比如 JDBC、ORM、OXM、JMS 以及事务。

- JDBC：这个是对传统的 JDBC 的封装，传统的 JDBC 里边有很多冗余代码，Spring 利用自身特性对其进行封装，简化了数据库访问。
- ORM：ORM 为我们常见的 ORM 框架（如 Hibernate、MyBatis 等）提供了一个交互层。
- OXM：OXM 模块抽象了对象和 XML 之间的转换，O 是 Object，X 是 XML。
- JMS：JMS 主要是对消息中间件的消息发送/消费提供封装。

### 2.4 Web

Web 模块里边包含几个部分，不过对于我们而言，使用更多的是 webmvc，也就是我们常说的 SpringMVC。

Web 主要包含如下模块：

- Web：提供基础的 Web 功能，构建 Web 上下文、提供文件上传等功能。
- WebMVC：提供 MVC 支持。
- 其他一些冷门的模块我就不多做介绍了。

### 2.5 Test

对测试功能提供支持。





坚持，会给你结果的！