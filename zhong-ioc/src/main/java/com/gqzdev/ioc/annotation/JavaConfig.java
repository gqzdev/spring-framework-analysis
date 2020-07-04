package com.gqzdev.ioc.annotation;


import com.gqzdev.ioc.bean.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName JavaConfig
 * @Description
 * @Author ganquanzhong
 * @Date2019/12/3 22:39
 * @Version
 **/
@Configuration
@ComponentScan("com.gqzdev.ioc")
public class JavaConfig {

	@Bean(initMethod = "initMethod")
	public User user(){
		return new User(101,"ganquanzhong","pwd","13995978321","china");
	}

	/*@Bean
	public Student student(){
		return new Student(2020001,"小明",18,"china");
	}

	@Bean
	public Student student01(){
		return new Student();
	}*/
}
