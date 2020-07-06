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

```java
public interface AttributeAccessor {
     void setAttribute(String name, @Nullable Object value);
     @Nullable
     Object getAttribute(String name);
     @Nullable
     Object removeAttribute(String name);
     boolean hasAttribute(String name);
     String[] attributeNames();
}
```

这里定义了`元数据`的访问接口，具体的实现则是 AttributeAccessorSupport，这些数据采用 LinkedHashMap 进行存储。

这是 BeanDefinition 所继承的两个接口。接下来我们来看下 BeanDefinition 接口：

```java
public interface BeanDefinition extends AttributeAccessor, BeanMetadataElement {
     String SCOPE_SINGLETON = ConfigurableBeanFactory.SCOPE_SINGLETON;
     String SCOPE_PROTOTYPE = ConfigurableBeanFactory.SCOPE_PROTOTYPE;
     int ROLE_APPLICATION = 0;
     int ROLE_SUPPORT = 1;
     int ROLE_INFRASTRUCTURE = 2;
     void setParentName(@Nullable String parentName);
     @Nullable
     String getParentName();
     void setBeanClassName(@Nullable String beanClassName);
     @Nullable
     String getBeanClassName();
     void setScope(@Nullable String scope);
     @Nullable
     String getScope();
     void setLazyInit(boolean lazyInit);
     boolean isLazyInit();
     void setDependsOn(@Nullable String... dependsOn);
     @Nullable
     String[] getDependsOn();
     void setAutowireCandidate(boolean autowireCandidate);
     boolean isAutowireCandidate();
     void setPrimary(boolean primary);
     boolean isPrimary();
     void setFactoryBeanName(@Nullable String factoryBeanName);
     @Nullable
     String getFactoryBeanName();
     void setFactoryMethodName(@Nullable String factoryMethodName);
     @Nullable
     String getFactoryMethodName();
     ConstructorArgumentValues getConstructorArgumentValues();
     default boolean hasConstructorArgumentValues() {
      	return !getConstructorArgumentValues().isEmpty();
     }
     MutablePropertyValues getPropertyValues();
     default boolean hasPropertyValues() {
      	return !getPropertyValues().isEmpty();
     }
     void setInitMethodName(@Nullable String initMethodName);
     @Nullable
     String getInitMethodName();
     void setDestroyMethodName(@Nullable String destroyMethodName);
     @Nullable
     String getDestroyMethodName();
     void setRole(int role);
     int getRole();
     void setDescription(@Nullable String description);
     @Nullable
     String getDescription();
     ResolvableType getResolvableType();
     boolean isSingleton();
     boolean isPrototype();
     boolean isAbstract();
     @Nullable
     String getResourceDescription();
     @Nullable
     BeanDefinition getOriginatingBeanDefinition();
}
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
9. setFactoryBeanName/getFactoryBeanName 配置/获取 FactoryBean 的名字，对应了 XML 中的 `<bean factory-bean="">` 配置
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

​		AbstractBeanDefinition 是一个抽象类，它根据 BeanDefinition 中定义的接口提供了相应的属性，并实现了 BeanDefinition 中定义的一部分方法。BeanDefinition 中原本只是定义了一系列的 get/set 方法，并没有提供对应的属性，在 AbstractBeanDefinition 中将所有的属性定义出来了。

后面其他的实现类也基本上都是在 AbstractBeanDefinition 的基础上完成的。

### 2.2 RootBeanDefinition

​		这是一个比较常用的实现类，对应了一般的元素标签。

### 2.3 ChildBeanDefinition

​		可以让子 BeanDefinition 定义拥有从父 BeanDefinition 那里继承配置的能力。

### 2.4 GenericBeanDefinition

​		GenericBeanDefinition 是从 Spring2.5 以后新加入的 BeanDefinition 实现类。GenericBeanDefinition 可以动态设置父 Bean，同时兼具 RootBeanDefinition 和 ChildBeanDefinition 的功能。

### 2.5 AnnotatedBeanDefinition

​		表示注解类型 BeanDefinition，拥有获取注解元数据和方法元数据的能力。

### 2.6 AnnotatedGenericBeanDefinition

​		使用了 @Configuration 注解标记配置类会解析为 AnnotatedGenericBeanDefinition。

## 3.实践

​		理论讲了这么多，接下来我们通过几行代码来实践下，验证一下我们前面所说的对不对。

```java
package com.gqzdev.ioc.annotation;

import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @ClassName: CustomeApplicationContext
 * @author: ganquanzhong
 * @date: 2020/7/6 17:15
 */
public class CustomizeApplicationContextTest {

	public static void main(String[] args) {
		// AnnotationConfigApplicationContext 创建 应用上下文
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(JavaConfig.class);

		MutablePropertyValues pvs = new MutablePropertyValues();
		pvs.addPropertyValue("id","6601");
		pvs.addPropertyValue("name","花木头");

		//创建BeanDefinition
		RootBeanDefinition rootBeanDefinition = new RootBeanDefinition(Dog.class, null, pvs);
		applicationContext.registerBeanDefinition("dog-自定义元信息",rootBeanDefinition);
		// 刷新  只能刷新一次
		//applicationContext.refresh();

		Dog dog = applicationContext.getBean(Dog.class);
		System.out.println(dog.toString());
	}
}

```



​		今天主要是和小伙伴们介绍一下 Spring 中的 BeanDefinition。通过上面的原理+案例，相信小伙伴们已经明白，我们通过 XML 或者 Java 注解配置的 Bean，我们定义的东西会先被解析成 BeanDefinition，然后再通过 BeanDefinition 来生成我们所需要的 Bean。