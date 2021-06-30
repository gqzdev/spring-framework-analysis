package com.gqzdev.ioc.parsebeandefinition.lookupmethod;

/**
 * @ClassName Student
 * @Description
 * @Author ganquanzhong
 * @Date2020/7/7 21:57
 * @Version
 **/
public class Student extends User {

	@Override
	public void speak() {
		System.out.println("I am student!");
	}
}
