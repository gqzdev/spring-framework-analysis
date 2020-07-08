## BeanFactory和FactoryBean的区别

 https://www.cnblogs.com/xingzc/p/9138256.html 

共同点：

​     都是接口

区别：

   BeanFactory 以Factory结尾，表示它是一个工厂类，用于管理Bean的一个工厂

​       在Spring中，所有的Bean都是由BeanFactory(也就是IOC容器)来进行管理的。

   但对FactoryBean而言，这个Bean不是简单的Bean，而是一个能生产或者修饰对象生成的工厂Bean,

​       它的实现与设计模式中的工厂模式和修饰器模式类似。

###  1、BeanFactory

   BeanFactory定义了IOC容器的最基本形式，并提供了IOC容器应遵守的的最基本的接口，也就是Spring IOC所遵守的最底层和最基本的编程规范。

   它的职责包括：实例化、定位、配置应用程序中的对象及建立这些对象间的依赖。

  在Spring代码中，BeanFactory只是个接口，并不是IOC容器的具体实现，

​     但是Spring容器给出了很多种实现，如 DefaultListableBeanFactory、XmlBeanFactory、ApplicationContext等，都是附加了某种功能的实现。





### 2、FactoryBean

   一般情况下，Spring通过反射机制利用<bean>的class属性指定实现类实例化Bean，在某些情况下，实例化Bean过程比较复杂，

如果按照传统的方式，则需要在<bean>中提供大量的配置信息。配置方式的灵活性是受限的，这时采用编码的方式可能会得到一个简单的方案。

Spring为此提供了一个org.springframework.bean.factory.FactoryBean的工厂类接口，用户可以通过实现该接口定制实例化Bean的逻辑。
   FactoryBean接口对于Spring框架来说占用重要的地位，Spring自身就提供了70多个FactoryBean的实现。

它们隐藏了实例化一些复杂Bean的细节，给上层应用带来了便利。从Spring3.0开始，FactoryBean开始支持泛型，即接口声明改为FactoryBean<T>的形式





## FactoryBean的使用



bean的获取过程

![](images/gqzdev_2020-07-08_23-45-19.png)

一般情况下， Spring 通过反射机制利用bean 的class 属性指定实现类来实例化bean。