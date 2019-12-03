package com.gqzdev.config;

import com.gqzdev.bean.User;
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
@ComponentScan
public class JavaConfig {

	@Bean
	public User user(){
		return new User(101,"ganquanzhong","pwd","13995978321","china");
	}
}