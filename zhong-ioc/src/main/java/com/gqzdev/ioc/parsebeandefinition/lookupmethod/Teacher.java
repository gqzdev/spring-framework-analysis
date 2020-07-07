package com.gqzdev.ioc.parsebeandefinition.lookupmethod;

/**
 * @ClassName Teacher
 * @Description
 * @Author ganquanzhong
 * @Date2020/7/7 21:56
 * @Version
 **/
public class Teacher extends User {

	@Override
	public void speak() {
		System.out.println("I am teacher!");
	}
}
