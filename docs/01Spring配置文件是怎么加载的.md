>  Spring中的bean是怎么被创建的，怎么使用？
>
> 以前都是通过xml配置方法（现在主要是注解方式，SpringBoot中无xml配置），一个bean标签就是一个bean对象；那么Spring的入口就是加载资源文件，然后解析xml配置文件中的DOM，最后实例化，初始化工作.......

## 1.从何说起

Spring 要从何说起呢？这个问题我考虑了很长时间。

因为 Spring 源码太繁杂了，就先从配置文件加载讲起，在逐步展开，配置文件加载也是我们在使用 Spring 时遇到的第一个问题，今天就先来说说这个话题。

## 2.简单的案例

先来一个简单的案例，大家感受一下，然后我们顺着案例讲起。

首先我们用gradle创建一个普通的项目`zhong-ioc`，(后面大部分的源码研读都是在这个模块下面进行！)

引入 spring-beans 等相关依赖：

```gradle
plugins {
    id 'java'
}

group 'org.springframework'
version '5.1.10.BUILD-SNAPSHOT'

sourceCompatibility = 1.8

repositories {
    mavenCentral()
}

dependencies {
    compile(project(":spring-beans"))
    compile(project(":spring-core"))
    compile(project(":spring-context"))
    compile(project(":spring-aop"))
//    添加spring-test
    compile(project(":spring-test"))
    
    testCompile group: 'junit', name: 'junit', version: '4.12'
}

```

然后我们创建一个实体类，再添加一个简单的配置文件：

```java
package com.gqzdev.ioc.bean;

import org.springframework.beans.factory.InitializingBean;

/**
 * @ClassName User
 * @Description
 * @Author ganquanzhong
 * @Date2019/12/3 22:38
 * @Version
 **/
public class User implements InitializingBean {
	private int uid;
	private String username;
	private String pwd;
	private String tel;
	private String addr;

	/**
		必须有一个空的构造方法
	 */
	public User() {
	}

	/**
	 通过xml的bean中的  setter getter注入 构造器注入方式
	 */
	public User(int uid, String username, String pwd, String tel, String addr) {
		this.uid = uid;
		this.username = username;
		this.pwd = pwd;
		this.tel = tel;
		this.addr = addr;
	}
	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	@Override
	public String toString() {
		return "User{" +
				"uid=" + uid +
				", username='" + username + '\'' +
				", pwd='" + pwd + '\'' +
				", tel='" + tel + '\'' +
				", addr='" + addr + '\'' +
				'}';
	}

	/**
	 * User对象 的初始化方法
	 */
	public void initMethod(){
		System.out.println("【initMethod】-在bean被spring容器初始化时，会执行initMethod属性指定的方法！");
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("【afterPropertiesSet】-实现InitializingBean接口的afterPropertiesSet方法，在bean初始化会自动调用该方法！");
	}
}

```

resources 目录下创建配置文件：

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">


	<bean id="user" class="com.gqzdev.ioc.bean.User" init-method="initMethod">
		<property name="uid" value="2020101"/>
		<property name="username" value="ganquanzhong"/>
		<property name="pwd" value="***888"/>
		<property name="addr" value="china"/>
	</bean>

	<!--通过构造器注入  index一定要和constructor中参数个数相对应-->
	<!--
	<bean id="user01" class="com.gqzdev.ioc.bean.User">
		<constructor-arg index="0" value="2020102"/>
		<constructor-arg index="1" value="阿中"/>
		<constructor-arg index="2" value="***"/>
		<constructor-arg index="3" value="15571398101"/>
		<constructor-arg index="4" value="中国"/>
	</bean>
	-->

	<!--循坏依赖-->
	<bean id="a" class="com.gqzdev.ioc.circulardependency.A">
		<property name="b" ref="b"/>
	</bean>
	
	<bean id="b" class="com.gqzdev.ioc.circulardependency.B">
		<property name="a" ref="a"/>
	</bean>
	

	<bean id="myBeanFactoryPostProcessor" class="com.gqzdev.ioc.beanfactorypostprocessor.MyBeanFactoryPostProcessor"/>
</beans>
```

然后去加载这个配置文件：这是使用过时的方式，XmlBeanFactory

```java
package com.gqzdev.ioc.xml;

import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

/**
 * 使用已过时的XmlBeanFactory 读取Xml中配置的bean，
 * 	  虽然XmlBeanFactory类已过时，但是不影响我们阅读源码
 *
 * XmlBeanFactory
 * 		DefaultListableBeanFactory的方便扩展，它从XML文档中读取bean定义。
 * 下面委托给XmlBeanDefinitionReader;等效于使用XmlBeanDefinitionReader和DefaultListableBeanFactory。
 * 必需的XML文档的结构、元素和属性名称在这个类中被硬编码。
 *
 * (当然，如果需要，可以运行转换来生成这种格式)。“bean”不需要是XML文档的根元素:这个类将解析XML文件中的所有bean定义元素。
 * 这个类用DefaultListableBeanFactory超类注册每个bean定义，并依赖后者对BeanFactory接口的实现。它支持单例、原型和对这两种bean的引用。参见“spring bean - 3. x。xsd”(或历史上的“spring-beans-2.0.dtd”)，以获得有关选项和配置样式的详细信息。
 *
 * @ClassName: XmlBeanFactoryTest
 * @author: ganquanzhong
 * @date: 2020/7/6 13:30
 */
public class XmlBeanFactoryTest {

	public static void main(String[] args) {

		ClassPathResource resource = new ClassPathResource("spring-bean01.xml");
		XmlBeanFactory beanFactory = new XmlBeanFactory(resource);
		for (String beanDefinitionName : beanFactory.getBeanDefinitionNames()) {
			System.out.println(beanDefinitionName);
		}
	}
}
```

这里为了展示数据的读取过程，我就先用这个已经过期的 XmlBeanFactory 来加载，这并不影响我们阅读源码。

上面这个是一个非常简单的 Spring 入门案例，相信很多小伙伴在第一次接触 Spring 的时候，写出来的可能都是这个 Demo。

在上面这段代码执行过程中，首先要做的事情就是先把 XML 配置文件加载到内存中，再去解析它，再去。。。。。

一步一步来吧，先来看 XML 文件如何被加入到内存中去。

## 3.文件读取

文件读取在 Spring 中很常见，也算是一个比较基本的功能，而且 Spring 提供的文件加载方式，不仅仅在 Spring 框架中可以使用，我们在项目中有其他文件加载需求也可以使用。

首先，Spring 中使用 Resource 接口来封装底层资源，Resource 接口本身实现自 InputStreamSource 接口：

![img](https://mmbiz.qpic.cn/mmbiz_png/GvtDGKK4uYkyxMF4S47DMaK1iaJrLIgu3tWLSFyUbz0vF2VYsvd5C0NyX8PQVvgnHibed8xNYoia1Uq9Rzkk5rEPA/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1)

我们来看下这两个接口的定义：

```java
public interface InputStreamSource {
 	InputStream getInputStream() throws IOException;
}
/*Resource 接口 封装了底层资源*/
public interface Resource extends InputStreamSource {
 	boolean exists();
	default boolean isReadable() {
  		return exists();
     }
     default boolean isOpen() {
      	return false;
     }
     default boolean isFile() {
      	return false;
     }
     URL getURL() throws IOException;
     URI getURI() throws IOException;
     File getFile() throws IOException;
     default ReadableByteChannel readableChannel() throws IOException {
      	return Channels.newChannel(getInputStream());
     }
     long contentLength() throws IOException;
     long lastModified() throws IOException;
     Resource createRelative(String relativePath) throws IOException;
     @Nullable
     String getFilename();
     String getDescription();

}
```

代码倒不难，我来稍微解释下：

1. InputStreamSource 类只提供了一个 getInputStream 方法，该方法返回一个 InputStream，也就是说，InputStreamSource 会将传入的 File 等资源，封装成一个 InputStream 再重新返回。
2. Resource 接口实现了 InputStreamSource 接口，并且封装了 Spring 内部可能会用到的底层资源，如 File、URL 以及 classpath 等。
3. exists 方法用来判断资源是否存在。
4. isReadable 方法用来判断资源是否可读。
5. isOpen 方法用来判断资源是否打开。
6. isFile 方法用来判断资源是否是一个文件。
7. getURL/getURI/getFile/readableChannel 分别表示获取资源对应的 URL/URI/File 以及将资源转为 ReadableByteChannel 通道。
8. contentLength 表示获取资源的大小。
9. lastModified 表示获取资源的最后修改时间。
10. createRelative 表示根据当前资源创建一个相对资源。
11. getFilename 表示获取文件名。
12. getDescription 表示在资源出错时，详细打印出出错的文件。

当我们加载不同资源时，对应了 Resource 的不同实现类，来看下 Resource 的继承关系：

![img](https://mmbiz.qpic.cn/mmbiz_png/GvtDGKK4uYkyxMF4S47DMaK1iaJrLIgu3OUWthmicAlK54Er9FOyKMGHNBUJnYPoUhQbKK2vSiamnBR8HK0tnaMlA/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1)

可以看到，针对不同类型的数据源，都有各自的实现，我们这里来重点看下 ClassPathResource 的实现方式。

ClassPathResource 源码比较长

首先，ClassPathResource 的构造方法有四个，一个已经过期的方法我这里没有列出来。另外三个，我们一般调用一个参数的即可，也就是传入文件路径即可，它内部会调用另外一个重载的方法，给 classloader 赋上值（因为在后面要通过 classloader 去读取文件）。

2. 在 ClassPathResource 初始化的过程中，会先调用 StringUtils.cleanPath 方法对传入的路径进行清理，所谓的路径清理，就是处理路径中的相对地址、Windows 系统下的 \\ 变为 / 等。
3. getPath 方法用来返回文件路径，这是一个相对路径，不包含 classpath。
4. resolveURL 方法表示返回资源的 URL，返回的时候优先用 Class.getResource 加载，然后才会用 ClassLoader.getResource 加载，关于 Class.getResource 和 ClassLoader.getResource 的区别，又能写一篇文章出来，我这里就大概说下，Class.getResource 最终还是会调用 ClassLoader.getResource，只不过 Class.getResource 会先对路径进行处理。
5. getInputStream 读取资源，并返回 InputStream 对象。
6. createRelative 方法是根据当前的资源，再创建一个相对资源。

这是 ClassPathResource，另外一个大家可能会接触到的 FileSystemResource ，小伙伴们可以自行查看其源码，比 ClassPathResource 简单。

如果不是使用 Spring，我们仅仅想自己加载 resources 目录下的资源，也可以采用这种方式：

```java
ClassPathResource resource = new ClassPathResource("beans.xml");
InputStream inputStream = resource.getInputStream();
```

拿到 IO 流之后自行解析即可。

在 Spring 框架，构造出 Resource 对象之后，接下来还会把 Resource 对象转为 EncodedResource，这里会对资源进行编码处理，编码主要体现在 getReader 方法上，在获取 Reader 对象时，如果有编码，则给出编码格式：

```java
public Reader getReader() throws IOException {
 if (this.charset != null) {
  return new InputStreamReader(this.resource.getInputStream(), this.charset);
 }
 else if (this.encoding != null) {
  return new InputStreamReader(this.resource.getInputStream(), this.encoding);
 }
 else {
  return new InputStreamReader(this.resource.getInputStream());
 }
}
```

所有这一切搞定之后，接下来就是通过 XmlBeanDefinitionReader 去加载 Resource 了。