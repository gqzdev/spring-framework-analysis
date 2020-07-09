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
		/**
		 *	使用BeanFactory进行分析 ，虽然已过期
		 *	现在都是使用ApplicationContext
		 */
		ClassPathResource resource = new ClassPathResource("spring-bean01.xml");
		XmlBeanFactory beanFactory = new XmlBeanFactory(resource);
		for (String beanDefinitionName : beanFactory.getBeanDefinitionNames()) {
			System.out.println(beanDefinitionName);
		}
	}
}
