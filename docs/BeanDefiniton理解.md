## 1.BeanDefinition

​		在 Spring 容器中，我们广泛使用的是一个一个的 Bean，BeanDefinition 从名字上就可以看出是关于 Bean 的定义。

​		事实上就是这样，我们在 XML 文件中配置的 Bean 的各种属性，这些属性不仅仅是和对象相关，Spring 容器还要解决 Bean 的生命周期、销毁、初始化等等各种操作，我们定义的`关于 Bean 的生命周期、销毁、初始化等`操作总得有一个对象来承载，那么这个对象就是 BeanDefinition。

​		XML 中定义的各种属性都会先加载到 BeanDefinition 上，然后通过 BeanDefinition 来生成一个 Bean，从这个角度来说，BeanDefinition 和 Bean 的关系有点类似于类和对象的关系。

要理解 BeanDefinition，我们从 BeanDefinition 的继承关系开始看起。

![img](https://mmbiz.qpic.cn/mmbiz_png/GvtDGKK4uYnSibutG3kH1bMZP5olnOVanOIjia1SNw3yjfP255JXKVoQcoz2FCgibfzicvG8RXBe1NuzYlReU1AfoA/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1)

BeanDefinition 是一个接口，继承自 BeanMetadataElement 和 AttributeAccessor 接口。

- BeanMetadataElement：该接口只有一个方法 getSource，该方法返回 Bean 的来源。
- AttributeAccessor：该接口主要规范了问任意对象元数据的方法。

我们来看下 AttributeAccessor：

```
public interface AttributeAccessor { void setAttribute(String name, @Nullable Object value); @Nullable Object getAttribute(String name); @Nullable Object removeAttribute(String name); boolean hasAttribute(String name); String[] attributeNames();}
```

这里定义了元数据的访问接口，具体的实现则是 AttributeAccessorSupport，这些数据采用 LinkedHashMap 进行存储。

这是 BeanDefinition 所继承的两个接口。接下来我们来看下 BeanDefinition 接口：

```
public interface BeanDefinition extends AttributeAccessor, BeanMetadataElement { String SCOPE_SINGLETON = ConfigurableBeanFactory.SCOPE_SINGLETON; String SCOPE_PROTOTYPE = ConfigurableBeanFactory.SCOPE_PROTOTYPE; int ROLE_APPLICATION = 0; int ROLE_SUPPORT = 1; int ROLE_INFRASTRUCTURE = 2; void setParentName(@Nullable String parentName); @Nullable String getParentName(); void setBeanClassName(@Nullable String beanClassName); @Nullable String getBeanClassName(); void setScope(@Nullable String scope); @Nullable String getScope(); void setLazyInit(boolean lazyInit); boolean isLazyInit(); void setDependsOn(@Nullable String... dependsOn); @Nullable String[] getDependsOn(); void setAutowireCandidate(boolean autowireCandidate); boolean isAutowireCandidate(); void setPrimary(boolean primary); boolean isPrimary(); void setFactoryBeanName(@Nullable String factoryBeanName); @Nullable String getFactoryBeanName(); void setFactoryMethodName(@Nullable String factoryMethodName); @Nullable String getFactoryMethodName(); ConstructorArgumentValues getConstructorArgumentValues(); default boolean hasConstructorArgumentValues() {  return !getConstructorArgumentValues().isEmpty(); } MutablePropertyValues getPropertyValues(); default boolean hasPropertyValues() {  return !getPropertyValues().isEmpty(); } void setInitMethodName(@Nullable String initMethodName); @Nullable String getInitMethodName(); void setDestroyMethodName(@Nullable String destroyMethodName); @Nullable String getDestroyMethodName(); void setRole(int role); int getRole(); void setDescription(@Nullable String description); @Nullable String getDescription(); ResolvableType getResolvableType(); boolean isSingleton(); boolean isPrototype(); boolean isAbstract(); @Nullable String getResourceDescription(); @Nullable BeanDefinition getOriginatingBeanDefinition();}
```

BeanDefinition 中的方法虽然多，但是结合我们平时在 XML 中的配置，这些方法其实都很好理解：

1. 首先一开始定义了两个变量用来描述 Bean 是不是单例的，后面的 setScope/getScope 方法可以用来修改/获取 scope 属性。
2. ROLE_xxx 用来描述一个 Bean 的角色，ROLE_APPLICATION 表示这个 Bean 是用户自己定义的 Bean；ROLE_SUPPORT 表示这个 Bean 是某些复杂配置的支撑部分；ROLE_INFRASTRUCTURE 表示这是一个 Spring 内部的 Bean，通过 setRole/getRole 可以修改。
3. setParentName/getParentName 用来配置 parent 的名称，这块可能有的小伙伴使用较少，这个对应着 XML 中的 `<bean parent="">` 配置。
4. setBeanClassName/getBeanClassName 这个就是配置 Bean 的 Class 全路径，对应 XML 中的 `<bean class="">` 配置。
5. setLazyInit/isLazyInit 配置/获取 Bean 是否懒加载，这个对应了 XML 中的 `<bean lazy-init="">` 配置。
6. setDependsOn/getDependsOn 配置/获取 Bean 的依赖对象，这个对应了 XML 中的 `<bean depends-on="">` 配置。
7. setAutowireCandidate/isAutowireCandidate 配置/获取 Bean 是否是自动装配，对应了 XML 中的 `<bean autowire-candidate="">` 配置。
8. setPrimary/isPrimary 配置/获取当前 Bean 是否为首选的 Bean，对应了 XML 中的 `<bean primary="">` 配置。
9. setFactoryBeanName/getFactoryBeanName 配置/获取 FactoryBean 的名字，对应了 XML 中的 `<bean factory-bean="">` 配置，factory-bean 松哥在之前的入门视频中讲过，小伙伴们可以参考这里:https://www.bilibili.com/video/BV1Wv41167TU。
10. setFactoryMethodName/getFactoryMethodName 和上一条成对出现的，对应了 XML 中的 `<bean factory-method="">` 配置，不再赘述。
11. getConstructorArgumentValues 返回该 Bean 构造方法的参数值。
12. hasConstructorArgumentValues 判断上一条是否是空对象。
13. getPropertyValues 这个是获取普通属性的集合。
14. hasPropertyValues 判断上一条是否为空对象。
15. setInitMethodName/setDestroyMethodName 配置 Bean 的初始化方法、销毁方法。
16. setDescription/getDescription 配置/返回 Bean 的描述。
17. isSingleton Bean 是否为单例。
18. isPrototype Bean 是否为原型。
19. isAbstract Bean 是否抽象。
20. getResourceDescription 返回定义 Bean 的资源描述。
21. getOriginatingBeanDefinition 如果当前 BeanDefinition 是一个代理对象，那么该方法可以用来返回原始的 BeanDefinition 。

这个就是 BeanDefinition 的定义以及它里边方法的含义。

## 2.BeanDefinition 实现类

上面只是 BeanDefinition 接口的定义，BeanDefinition 还拥有诸多实现类，我们也来大致了解下。

先来看一张继承关系图：

![img](https://mmbiz.qpic.cn/mmbiz_png/GvtDGKK4uYnSibutG3kH1bMZP5olnOVanhw6f6LeqaHcqudNko3Pzw7bnRVbYHo8E4mWrvnIc5qw9jMSibI87vwA/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1)

这么多实现类看着有点眼花缭乱，不过搞清楚了每一个接口和类的作用，再看就很容易了。

### 2.1 AbstractBeanDefinition

AbstractBeanDefinition 是一个抽象类，它根据 BeanDefinition 中定义的接口提供了相应的属性，并实现了 BeanDefinition 中定义的一部分方法。BeanDefinition 中原本只是定义了一系列的 get/set 方法，并没有提供对应的属性，在 AbstractBeanDefinition 中将所有的属性定义出来了。

后面其他的实现类也基本上都是在 AbstractBeanDefinition 的基础上完成的。

### 2.2 RootBeanDefinition

这是一个比较常用的实现类，对应了一般的元素标签。

### 2.3 ChildBeanDefinition

可以让子 BeanDefinition 定义拥有从父 BeanDefinition 那里继承配置的能力。

### 2.4 GenericBeanDefinition

GenericBeanDefinition 是从 Spring2.5 以后新加入的 BeanDefinition 实现类。GenericBeanDefinition 可以动态设置父 Bean，同时兼具 RootBeanDefinition 和 ChildBeanDefinition 的功能。

### 2.5 AnnotatedBeanDefinition

表示注解类型 BeanDefinition，拥有获取注解元数据和方法元数据的能力。

### 2.6 AnnotatedGenericBeanDefinition

使用了 @Configuration 注解标记配置类会解析为 AnnotatedGenericBeanDefinition。

## 3.实践

理论讲了这么多，接下来我们通过几行代码来实践下，验证一下我们前面所说的对不对。

首先项目中添加 spring-context 依赖，如下：

```
<dependency>    <groupId>org.springframework</groupId>    <artifactId>spring-context</artifactId>    <version>5.2.6.RELEASE</version></dependency>
```

然后我们来创建一个 User 类，如下：

```
public class User {    private String username;    private String address;    @Override    public String toString() {        return "User{" +                "username='" + username + '\'' +                ", address='" + address + '\'' +                '}';    }    public String getUsername() {        return username;    }    public void setUsername(String username) {        this.username = username;    }    public String getAddress() {        return address;    }    public void setAddress(String address) {        this.address = address;    }}
```

接下来我们先来验证 RootBeanDefinition。我们自己纯手工定义一个 RootBeanDefinition，并且将之注册到 Spring 容器中去。

```
AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();MutablePropertyValues pvs = new MutablePropertyValues();pvs.add("username", "javaboy");pvs.add("address", "www.javaboy.org");RootBeanDefinition rootBeanDefinition = new RootBeanDefinition(User.class, null, pvs);ctx.registerBeanDefinition("user",rootBeanDefinition);ctx.refresh();User bean = ctx.getBean(User.class);System.out.println(bean);
```

MutablePropertyValues 是定义对象中的一个一个属性，构造 RootBeanDefinition 的时候，我们传入了类名称和属性集合，最终把 rootBeanDefinition 注册到容器中去。剩下的事情由容器完成，然后我们就可以从容器中获取到 User 对象了。

最终输出结果如下：

```
User{username='javaboy', address='www.javaboy.org'}
```

**看了这个例子，小伙伴们应该能够大致明白，我们在 XML 中定义的各种属性，就是先被解析到 BeanDefinition 中，然后再注册到 Spring 容器中去，最后拿到我们需要的 Bean。**

ChildBeanDefinition 具有从父 Bean 继承数据的能力，我们来看下这个怎么用。

首先新建一个 Person 类，Person 类在 User 类的基础上增加一个 nickname 属性，这样 Person 就可以继承到 User 的 username 和 address 两个属性的值了：

```
public class Person {    private String username;    private String address;    private String nickname;    @Override    public String toString() {        return "Person{" +                "username='" + username + '\'' +                ", address='" + address + '\'' +                ", nickname='" + nickname + '\'' +                '}';    }    public String getUsername() {        return username;    }    public void setUsername(String username) {        this.username = username;    }    public String getAddress() {        return address;    }    public void setAddress(String address) {        this.address = address;    }    public String getNickname() {        return nickname;    }    public void setNickname(String nickname) {        this.nickname = nickname;    }}
```

接下来自定义 ChildBeanDefinition：

```
AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();MutablePropertyValues pvs = new MutablePropertyValues();pvs.add("username", "javaboy");pvs.add("address", "www.javaboy.org");RootBeanDefinition rootBeanDefinition = new RootBeanDefinition(User.class, null, pvs);ctx.registerBeanDefinition("user",rootBeanDefinition);ChildBeanDefinition childBeanDefinition = new ChildBeanDefinition("user");childBeanDefinition.setBeanClass(Person.class);childBeanDefinition.getPropertyValues().add("nickname", "江南一点雨");ctx.registerBeanDefinition("person", childBeanDefinition);ctx.refresh();User user = ctx.getBean(User.class);Person person = ctx.getBean(Person.class);System.out.println("user = " + user);System.out.println("person = " + person);
```

首先定义 RootBeanDefinition 并注册到 Spring 容器中，然后再定义 ChildBeanDefinition，ChildBeanDefinition 继承了 RootBeanDefinition 中现有的属性值。

最后我们从 Spring 容器中获取 User 和 Person，打印结果如下：

```
user = User{username='javaboy', address='www.javaboy.org'}person = Person{username='javaboy', address='www.javaboy.org', nickname='江南一点雨'}
```

可以看到，Person 确实继承了 User 的属性值。

RootBeanDefinition 和 ChildBeanDefinition 都可以被 GenericBeanDefinition 代替，效果一样，如下：

```
AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();MutablePropertyValues pvs = new MutablePropertyValues();pvs.add("username", "javaboy");pvs.add("address", "www.javaboy.org");GenericBeanDefinition rootBeanDefinition = new GenericBeanDefinition();rootBeanDefinition.setBeanClass(User.class);rootBeanDefinition.setPropertyValues(pvs);ctx.registerBeanDefinition("user",rootBeanDefinition);GenericBeanDefinition childBeanDefinition = new GenericBeanDefinition();childBeanDefinition.setParentName("user");childBeanDefinition.setBeanClass(Person.class);childBeanDefinition.getPropertyValues().add("nickname", "江南一点雨");ctx.registerBeanDefinition("person", childBeanDefinition);ctx.refresh();User user = ctx.getBean(User.class);Person person = ctx.getBean(Person.class);System.out.println("user = " + user);System.out.println("person = " + person);
```

运行结果如下：

```
user = User{username='javaboy', address='www.javaboy.org'}person = Person{username='javaboy', address='www.javaboy.org', nickname='江南一点雨'}
```

可以看到，和前面的运行效果一致。

在我们本系列前面文章（[Spring 源码第一篇开整！配置文件是怎么加载的？](https://mp.weixin.qq.com/s?__biz=MzI1NDY0MTkzNQ==&mid=2247489036&idx=1&sn=d9d27e24c459af4b7036e10dbdea3873&scene=21#wechat_redirect)）的案例中，默认使用的也是 GenericBeanDefinition，如下：

![img](https://mmbiz.qpic.cn/mmbiz_png/GvtDGKK4uYnSibutG3kH1bMZP5olnOVanhVlIT5RwIY9a5QA4qwy5hewCK5pCNeIibUP2tiamu16GghJPoCRVtxDw/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1)

现在 Spring Boot 广泛流行之后，Java 配置使用越来越多，以 @Configuration 注解标记配置类会被解析为 AnnotatedGenericBeanDefinition；以 @Bean 注解标记的 Bean 会被解析为 ConfigurationClassBeanDefinition。

我们新建一个 MyConfig 配置类，如下：

```
@Configurationpublic class MyConfig {    @Bean    User user() {        return new User();    }}
```

查看获取到的 BeanDefinition 结果如下：

![img](https://mmbiz.qpic.cn/mmbiz_png/GvtDGKK4uYnSibutG3kH1bMZP5olnOVantenonnDmI4Ewzrpss5PNaMfdnVCZegicXvUibJ7OjXq2JiaVzOhTZC58g/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1)

而其他 @Service、@Controller、@Repository 以及 @Component 等注解标记的 Bean 则会被识别为 ScannedGenericBeanDefinition。这个我就不一一演示了，小伙伴们可以自行测试哦。

## 4.小结

好啦，今天主要是和小伙伴们介绍一下 Spring 中的 BeanDefinition。通过上面的原理+案例，相信小伙伴们已经明白，我们通过 XML 或者 Java 注解配置的 Bean，我们定义的东西会先被解析成 BeanDefinition，然后再通过 BeanDefinition 来生成我们所需要的 Bean。