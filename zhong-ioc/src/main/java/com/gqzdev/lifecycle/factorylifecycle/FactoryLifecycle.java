package com.gqzdev.lifecycle.factorylifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

 /**
  * @description: 工厂级生命周期
  * 	工厂级级生命周期接口方法的执行时序
  * 实现BeanFactoryPostProcessor接口 的postProcessBeanFactory方法，
  * 传入参数类型为 ConfigurableListableBeanFactory
  *
  *
  * @Author: ganquanzhong
  * @Date:  2019/12/30 9:34
  */
public class FactoryLifecycle implements BeanFactoryPostProcessor {

    /**
     * 构造器
     */
    public FactoryLifecycle () {
        System.out.println("一 【工厂级别】FactoryLifecycle构造器执行了");
    }

    /**
     * Bean实例化之前
     */
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("二 【工厂级别】postProcessBeanFactory方法执行了");
    }
}
