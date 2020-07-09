bean的获取过程

![](images/gqzdev_2020-07-08_23-45-19.png)

## BeanFactory和FactoryBean的区别

共同点：

​     	都是接口

区别：

   BeanFactory 以Factory结尾，表示它是一个工厂类，用于管理Bean的一个工厂

​       在Spring中，所有的Bean都是由BeanFactory(也就是IOC容器)来进行管理的。

   FactoryBean，这个Bean不是简单的Bean，而是一个能生产或者修饰对象生成的工厂Bean,

​       它的实现与设计模式中的工厂模式和修饰器模式类似。

###  1、BeanFactory

   BeanFactory定义了IOC容器的最基本形式，并提供了IOC容器应遵守的的最基本的接口，也就是Spring IOC所遵守的最底层和最基本的编程规范。

```java
BeanFactory (org.springframework.beans.factory.BeanFactory)

HierarchicalBeanFactory (org.springframework.beans.factory)
    ConfigurableBeanFactory (org.springframework.beans.factory.config)
        AbstractBeanFactory (org.springframework.beans.factory.support)
            AbstractAutowireCapableBeanFactory (org.springframework.beans.factory.support)
                DefaultListableBeanFactory (org.springframework.beans.factory.support)
                    XmlBeanFactory (org.springframework.beans.factory.xml)
        ConfigurableListableBeanFactory (org.springframework.beans.factory.config)
            DefaultListableBeanFactory (org.springframework.beans.factory.support)
                XmlBeanFactory (org.springframework.beans.factory.xml)
    ApplicationContext (org.springframework.context)
        ConfigurableApplicationContext (org.springframework.context)
            AbstractApplicationContext (org.springframework.context.support)
                AbstractRefreshableApplicationContext (org.springframework.context.support)
                    AbstractRefreshableConfigApplicationContext (org.springframework.context.support)
                        AbstractXmlApplicationContext (org.springframework.context.support)
                            FileSystemXmlApplicationContext (org.springframework.context.support)
                            ClassPathXmlApplicationContext (org.springframework.context.support)
                                ToolingTestApplicationContext in JmsNamespaceHandlerTests (org.springframework.jms.config)
                                Anonymous in abstractRefreshableContextWithMisconfiguredBean() in SerializableBeanFactoryMemoryLeakTests (org.springframework.context.support)
                                Anonymous in testResourceAndInputStream() in ClassPathXmlApplicationContextTests (org.springframework.context.support)
                                Anonymous in testSingletonDestructionOnStartupFailure() in ContextLoaderTests (org.springframework.web.context)
                        AbstractRefreshableWebApplicationContext (org.springframework.web.context.support)
                            XmlWebApplicationContext (org.springframework.web.context.support)
                                Anonymous in customAbstractRefreshableWAC_fallsBackToInitParam() in Spr8510Tests (org.springframework.web.context.support)
                            GroovyWebApplicationContext (org.springframework.web.context.support)
                            AnnotationConfigWebApplicationContext (org.springframework.web.context.support)
                GenericApplicationContext (org.springframework.context.support)
                    GenericXmlApplicationContext (org.springframework.context.support)
                    StaticApplicationContext (org.springframework.context.support)
                        TestContext in testExpectedBehavior() in EventPublicationInterceptorTests (org.springframework.context.event)
                        StaticWebApplicationContext (org.springframework.web.context.support)
                            SimpleWebApplicationContext (org.springframework.web.servlet)
                            ComplexWebApplicationContext (org.springframework.web.servlet)
                            Anonymous in testXmlViewResolverDefaultLocation() in ViewResolverTests (org.springframework.web.servlet.view)
                            Anonymous in testXmlViewResolverWithoutCache() in ViewResolverTests (org.springframework.web.servlet.view)
                    GenericWebApplicationContext (org.springframework.web.context.support)
                    ResourceAdapterApplicationContext (org.springframework.jca.context)
                    GenericGroovyApplicationContext (org.springframework.context.support)
                    AnnotationConfigApplicationContext (org.springframework.context.annotation)
            ConfigurableWebApplicationContext (org.springframework.web.context)
                GenericWebApplicationContext (org.springframework.web.context.support)
                StaticWebApplicationContext (org.springframework.web.context.support)
                    SimpleWebApplicationContext (org.springframework.web.servlet)
                    ComplexWebApplicationContext (org.springframework.web.servlet)
                    Anonymous in testXmlViewResolverDefaultLocation() in ViewResolverTests (org.springframework.web.servlet.view)
                    Anonymous in testXmlViewResolverWithoutCache() in ViewResolverTests (org.springframework.web.servlet.view)
                AbstractRefreshableWebApplicationContext (org.springframework.web.context.support)
                    XmlWebApplicationContext (org.springframework.web.context.support)
                        Anonymous in customAbstractRefreshableWAC_fallsBackToInitParam() in Spr8510Tests (org.springframework.web.context.support)
                    GroovyWebApplicationContext (org.springframework.web.context.support)
                    AnnotationConfigWebApplicationContext (org.springframework.web.context.support)
            UnknownApplicationContext in ContextLoaderTests (org.springframework.web.context)
        WebApplicationContext (org.springframework.web.context)
            StubWebApplicationContext (org.springframework.test.web.servlet.setup)
            ConfigurableWebApplicationContext (org.springframework.web.context)
                GenericWebApplicationContext (org.springframework.web.context.support)
                StaticWebApplicationContext (org.springframework.web.context.support)
                    SimpleWebApplicationContext (org.springframework.web.servlet)
                    ComplexWebApplicationContext (org.springframework.web.servlet)
                    Anonymous in testXmlViewResolverDefaultLocation() in ViewResolverTests (org.springframework.web.servlet.view)
                    Anonymous in testXmlViewResolverWithoutCache() in ViewResolverTests (org.springframework.web.servlet.view)
                AbstractRefreshableWebApplicationContext (org.springframework.web.context.support)
                    XmlWebApplicationContext (org.springframework.web.context.support)
                        Anonymous in customAbstractRefreshableWAC_fallsBackToInitParam() in Spr8510Tests (org.springframework.web.context.support)
                    GroovyWebApplicationContext (org.springframework.web.context.support)
                    AnnotationConfigWebApplicationContext (org.springframework.web.context.support)
SimpleJndiBeanFactory (org.springframework.jndi.support)
AutowireCapableBeanFactory (org.springframework.beans.factory.config)
    ConfigurableListableBeanFactory (org.springframework.beans.factory.config)
        DefaultListableBeanFactory (org.springframework.beans.factory.support)
            XmlBeanFactory (org.springframework.beans.factory.xml)
    AbstractAutowireCapableBeanFactory (org.springframework.beans.factory.support)
        DefaultListableBeanFactory (org.springframework.beans.factory.support)
            XmlBeanFactory (org.springframework.beans.factory.xml)
    StubBeanFactory in StubWebApplicationContext (org.springframework.test.web.servlet.setup)
ListableBeanFactory (org.springframework.beans.factory)
    StaticListableBeanFactory (org.springframework.beans.factory.support)
        StubBeanFactory in StubWebApplicationContext (org.springframework.test.web.servlet.setup)
    ApplicationContext (org.springframework.context)
        ConfigurableApplicationContext (org.springframework.context)
            AbstractApplicationContext (org.springframework.context.support)
                AbstractRefreshableApplicationContext (org.springframework.context.support)
                    AbstractRefreshableConfigApplicationContext (org.springframework.context.support)
                        AbstractXmlApplicationContext (org.springframework.context.support)
                            FileSystemXmlApplicationContext (org.springframework.context.support)
                            ClassPathXmlApplicationContext (org.springframework.context.support)
                                ToolingTestApplicationContext in JmsNamespaceHandlerTests (org.springframework.jms.config)
                                Anonymous in abstractRefreshableContextWithMisconfiguredBean() in SerializableBeanFactoryMemoryLeakTests (org.springframework.context.support)
                                Anonymous in testResourceAndInputStream() in ClassPathXmlApplicationContextTests (org.springframework.context.support)
                                Anonymous in testSingletonDestructionOnStartupFailure() in ContextLoaderTests (org.springframework.web.context)
                        AbstractRefreshableWebApplicationContext (org.springframework.web.context.support)
                            XmlWebApplicationContext (org.springframework.web.context.support)
                                Anonymous in customAbstractRefreshableWAC_fallsBackToInitParam() in Spr8510Tests (org.springframework.web.context.support)
                            GroovyWebApplicationContext (org.springframework.web.context.support)
                            AnnotationConfigWebApplicationContext (org.springframework.web.context.support)
                GenericApplicationContext (org.springframework.context.support)
                    GenericXmlApplicationContext (org.springframework.context.support)
                    StaticApplicationContext (org.springframework.context.support)
                        TestContext in testExpectedBehavior() in EventPublicationInterceptorTests (org.springframework.context.event)
                        StaticWebApplicationContext (org.springframework.web.context.support)
                            SimpleWebApplicationContext (org.springframework.web.servlet)
                            ComplexWebApplicationContext (org.springframework.web.servlet)
                            Anonymous in testXmlViewResolverDefaultLocation() in ViewResolverTests (org.springframework.web.servlet.view)
                            Anonymous in testXmlViewResolverWithoutCache() in ViewResolverTests (org.springframework.web.servlet.view)
                    GenericWebApplicationContext (org.springframework.web.context.support)
                    ResourceAdapterApplicationContext (org.springframework.jca.context)
                    GenericGroovyApplicationContext (org.springframework.context.support)
                    AnnotationConfigApplicationContext (org.springframework.context.annotation)
            ConfigurableWebApplicationContext (org.springframework.web.context)
                GenericWebApplicationContext (org.springframework.web.context.support)
                StaticWebApplicationContext (org.springframework.web.context.support)
                    SimpleWebApplicationContext (org.springframework.web.servlet)
                    ComplexWebApplicationContext (org.springframework.web.servlet)
                    Anonymous in testXmlViewResolverDefaultLocation() in ViewResolverTests (org.springframework.web.servlet.view)
                    Anonymous in testXmlViewResolverWithoutCache() in ViewResolverTests (org.springframework.web.servlet.view)
                AbstractRefreshableWebApplicationContext (org.springframework.web.context.support)
                    XmlWebApplicationContext (org.springframework.web.context.support)
                        Anonymous in customAbstractRefreshableWAC_fallsBackToInitParam() in Spr8510Tests (org.springframework.web.context.support)
                    GroovyWebApplicationContext (org.springframework.web.context.support)
                    AnnotationConfigWebApplicationContext (org.springframework.web.context.support)
            UnknownApplicationContext in ContextLoaderTests (org.springframework.web.context)
        WebApplicationContext (org.springframework.web.context)
            StubWebApplicationContext (org.springframework.test.web.servlet.setup)
            ConfigurableWebApplicationContext (org.springframework.web.context)
                GenericWebApplicationContext (org.springframework.web.context.support)
                StaticWebApplicationContext (org.springframework.web.context.support)
                    SimpleWebApplicationContext (org.springframework.web.servlet)
                    ComplexWebApplicationContext (org.springframework.web.servlet)
                    Anonymous in testXmlViewResolverDefaultLocation() in ViewResolverTests (org.springframework.web.servlet.view)
                    Anonymous in testXmlViewResolverWithoutCache() in ViewResolverTests (org.springframework.web.servlet.view)
                AbstractRefreshableWebApplicationContext (org.springframework.web.context.support)
                    XmlWebApplicationContext (org.springframework.web.context.support)
                        Anonymous in customAbstractRefreshableWAC_fallsBackToInitParam() in Spr8510Tests (org.springframework.web.context.support)
                    GroovyWebApplicationContext (org.springframework.web.context.support)
                    AnnotationConfigWebApplicationContext (org.springframework.web.context.support)
    ConfigurableListableBeanFactory (org.springframework.beans.factory.config)
        DefaultListableBeanFactory (org.springframework.beans.factory.support)
            XmlBeanFactory (org.springframework.beans.factory.xml)

```



   它的职责包括：实例化、定位、配置应用程序中的对象及建立这些对象间的依赖。

  在Spring代码中，BeanFactory只是个接口，并不是IOC容器的具体实现，

​     但是Spring容器给出了很多种实现，如 DefaultListableBeanFactory、XmlBeanFactory、ApplicationContext等，都是附加了某种功能的实现。





### 2、FactoryBean

​		一般情况下，Spring通过反射机制利用<bean>的class属性指定实现类实例化Bean，在某些情况下，实例化Bean过程比较复杂，

​		如果按照传统的方式，则需要在<bean>中提供大量的配置信息。配置方式的灵活性是受限的，这时采用编码的方式可能会得到一个简单的方案。

​		Spring为此提供了一个org.springframework.bean.factory.FactoryBean的工厂类接口，用户可以通过实现该接口定制实例化Bean的逻辑。
   	**FactoryBean**接口对于Spring框架来说占用重要的地位，Spring自身就提供了70多个FactoryBean的实现。

​		它们隐藏了实例化一些复杂Bean的细节，给上层应用带来了便利。从Spring3.0开始，FactoryBean开始支持泛型，即接口声明改为FactoryBean<T>的形式

```java
package org.springframework.beans.factory;

public interface FactoryBean<T> {
    T getObject() throws Exception;

    Class<?> getObjectType();

    boolean isSingleton();
}
```

在该接口中还定义了以下3个方法：

1. T getObject()：

​          返回由FactoryBean创建的Bean实例，如果isSingleton()返回true，则该实例会放到Spring容器中单实例缓存池中；

2. boolean isSingleton()：

​          返回由FactoryBean创建的Bean实例的作用域是singleton还是prototype；

3. Class<T> getObjectType()：

​          返回FactoryBean创建的Bean类型。



> 当调用getBean("user")时，Spring通过反射机制发现UserFactoryBean实现了FactoryBean的接口， 
>
>  这时Spring容器就调用接口方法CarFactoryBean#getObject()方法返回。
>
> 如果希望获取CarFactoryBean的实例，则需要在使用getBean(beanName)方法时在beanName前显示的加上"&"前缀：如getBean("&user");
>
> Object bean1 = app.getBean("&user");//UserFactoryBean
>
> Object bean2 = app.getBean("user");//User



## FactoryBean的使用

如果使用传统方式配置下面Car的<bean>时，Car的每个属性分别对应一个<property>元素标签。

`普通的bean 类`

```java
package com.gqzdev.ioc.factorybean;

/**
 * @ClassName: Car
 * @author: ganquanzhong
 * @date: 2020/7/9 9:52
 */
public class Car {
	private int maxSpeed;
	private String brand;
	private double price;

	public int getMaxSpeed() {
		return maxSpeed;
	}

	public void setMaxSpeed(int maxSpeed) {
		this.maxSpeed = maxSpeed;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
}

```

如果用FactoryBean的方式实现就灵活点，下例通过逗号分割符的方式一次性的为Car的所有属性指定配置值：

```java
package com.gqzdev.ioc.factorybean;

import org.springframework.beans.factory.FactoryBean;

/**
 * @ClassName: CarFactoryBean
 * 
 * 	实现FactoryBean接口，
 * 	Spring通过反射机制发现CarFactoryBean实现了FactoryBean的接口，
 * 		这时Spring容器就调用接口方法CarFactoryBean#getObject()方法返回。
 * 
 * @author: ganquanzhong
 * @date: 2020/7/9 9:59
 */
public class CarFactoryBean implements FactoryBean {

	private String carInfo;

	/**
	 *	自定义创建bean的过程，可以定制复杂的创建过程
	 */
	@Override
	public Object getObject() throws Exception {
		Car car = new Car();
		String[] infos = carInfo.split(",");
		car.setBrand(infos[0]);
		car.setMaxSpeed(Integer.valueOf(infos[1]));
		car.setPrice(Double.valueOf(infos[2]));
		return car;
	}

	/**
	 *	获取FactoryBean创建bean的类型
	 */
	@Override
	public Class<?> getObjectType() {
		return Car.class;
	}

	/**
	 *	创建的bean，是否是单例
	 */
	@Override
	public boolean isSingleton() {
		return false;
	}

	public String getCarInfo() {
		return carInfo;
	}

	/**
	 *	接受逗号分隔符，设置属性
	 */
	public void setCarInfo(String carInfo) {
		this.carInfo = carInfo;
	}
}
```

配置文件spring-factorybean.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">


	<bean id="car" class="com.gqzdev.ioc.factorybean.Car"/>


	<bean id="carFactoryBean" class="com.gqzdev.ioc.factorybean.CarFactoryBean">
		<property name="carInfo" value="法拉利跑车,420,2600000"/>
	</bean>

</beans>
```

测试

```java
package com.gqzdev.ioc.factorybean;

import org.springframework.beans.factory.FactoryBean;

/**
 * @ClassName: CarFactoryBean
 *
 * 	实现FactoryBean接口，
 * 	Spring通过反射机制发现CarFactoryBean实现了FactoryBean的接口，
 * 		这时Spring容器就调用接口方法CarFactoryBean#getObject()方法返回。
 *
 * @author: ganquanzhong
 * @date: 2020/7/9 9:59
 */
public class CarFactoryBean implements FactoryBean {

	private String carInfo;

	/**
	 *	自定义创建bean的过程，可以定制复杂的创建过程
	 */
	@Override
	public Object getObject() throws Exception {
		Car car = new Car();
		String[] infos = carInfo.split(",");
		car.setBrand(infos[0]);
		car.setMaxSpeed(Integer.valueOf(infos[1]));
		car.setPrice(Double.valueOf(infos[2]));
		return car;
	}

	/**
	 *	获取FactoryBean创建bean的类型
	 */
	@Override
	public Class<?> getObjectType() {
		return Car.class;
	}

	/**
	 *	创建的bean，是否是单例
	 */
	@Override
	public boolean isSingleton() {
		return false;
	}

	public String getCarInfo() {
		return carInfo;
	}

	/**
	 *	接受逗号分隔符，设置属性
	 */
	public void setCarInfo(String carInfo) {
		this.carInfo = carInfo;
	}
}
```

输出结果：

```
Car{brand='法拉利跑车', maxSpeed=420, price=2600000.0}
com.gqzdev.ioc.factorybean.CarFactoryBean@46fbb2c1
Car{brand='法拉利跑车', maxSpeed=420, price=2600000.0}
```

