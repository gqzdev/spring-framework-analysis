package com.gqzdev.ioc.config;

import com.gqzdev.ioc.bean.Student;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

/**
 *  FactoryBean 一种特殊的BeanFactory 不需要走Spring中规范的bean生成流程
 *  	直接实现其中的三个方法
 *  	在getObject中创建对象，外部可以直接获取
 *
 * @ClassName MyFactoryBean
 * @Description
 * @Author ganquanzhong
 * @Date2020/6/30 23:17
 * @Version
 **/

@Component
public class MyFactoryBean implements FactoryBean {
	@Override
	public Object getObject() throws Exception {
		Student student = new Student(2020, "gqzdev", 23, "湖北黄冈");
		System.out.println("-----可以进行一系列的操作-----");
		return student;
	}

	@Override
	public Class<?> getObjectType() {
		return Student.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}
}
