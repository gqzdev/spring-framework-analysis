package com.gqzdev.ioc.beanfactorypostprocessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

import java.util.Iterator;

/**
 * BeanFactoryPostProcessor接口  介绍
 *
 * BeanFactoryPostProcessor是在spring容器加载了bean的定义文件之后，在bean实例化之前执行的。
 * 接口方法的入参是ConfigurrableListableBeanFactory，使用该参数，可以获取到相关bean的定义信息
 *
 * description:  自定义BeanFactory后置处理器
 * @Author：ganquanzhong
 * @Date： 2020/7/1 22:25
 */
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    /**
     * 主要是用来自定义修改持有的bean
     * ConfigurableListableBeanFactory 其实就是DefaultListableBeanDefinition对象
     * @param beanFactory
     * @throws BeansException
     */

    @Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("调用了自定义的BeanFactoryPostProcessor " + beanFactory);
        Iterator<String> it = beanFactory.getBeanNamesIterator();
		String[] names = beanFactory.getBeanDefinitionNames();
        // 获取了所有的bean名称列表
        for(int i=0; i<names.length; i++){
            String name = names[i];
			BeanDefinition bd = beanFactory.getBeanDefinition(name);
			System.out.println("BeanDefinitionName："+name);

			System.out.println(name + " bean properties（Bean的属性值）:  " + bd.getPropertyValues().toString());
			MutablePropertyValues pv = bd.getPropertyValues();
			if (pv.contains("username")){
				pv.addPropertyValue("username","甘全中-gqzdev");
				//新加属性时，需要注意对象中是否存在
				//pv.addPropertyValue("blog","http://blog.javaaj.com");
			}
			//bd.setScope(BeanDefinition.SCOPE_PROTOTYPE);
        }
    }

    /*
    	spring中，有内置的一些BeanFactoryPostProcessor实现类，常用的有：
		org.springframework.beans.factory.config.PropertyPlaceholderConfigurer
		org.springframework.beans.factory.config.PropertyOverrideConfigurer
		org.springframework.beans.factory.config.CustomEditorConfigurer：用来注册自定义的属性编辑器
     */
}