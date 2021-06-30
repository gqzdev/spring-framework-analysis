package com.gqzdev.ioc.autowire;

import com.gqzdev.ioc.bean.Student;
import com.gqzdev.ioc.bean.TeacherBean;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

/**
 * @ClassName: JavaConfig
 *
 * 	使用Java注解配置的形式，
 * 		定义两个bean，测试自动注入的过程
 *
 * @author: ganquanzhong
 * @date: 2020/7/10 10:10
 */

@Configurable
@ComponentScan("com.gqzdev.ioc.autowire")
public class JavaConfig {

	@Bean
	public Student student(){

		return  new Student(20200710,"学生0710",18,"湖北黄冈");
	}

	@Bean("teacherBean")
	public TeacherBean teacherBean(){
		return new TeacherBean(20100710,"陈老师","英语");
	}


}
