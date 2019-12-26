package com.gqzdev.ioc;

import com.gqzdev.ioc.bean.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @ClassName Test
 * @Description
 * @Author ganquanzhong
 * @Date2019/12/26 23:24
 * @Version
 **/

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-bean01.xml")
public class XMLTest {
	//从Spring容器中注入user对象
	@Autowired
	private User user;

	@Test
	public void test(){
		System.out.println(user.toString());
	}

	@Autowired
	private User user01;
	@Test
	public void test01(){
		System.out.println(user01.toString());
	}
}
