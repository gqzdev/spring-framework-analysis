package com.gqzdev.ioc.parsebeandefinition.placedmethod;

import org.springframework.beans.factory.support.MethodReplacer;

import java.lang.reflect.Method;

/**
 * @ClassName: TestChangeMethodReplacer
 *
 *  使用replaced-method， 需要实现MethodReplacer接口
 * 	运行中，对TestChangeMethod进行修改
 *
 * @author: ganquanzhong
 * @date: 2020/7/7 17:18
 */
public class ChangeMethodReplacer implements MethodReplacer {

	@Override
	public Object reimplement(Object obj, Method method, Object[] args) throws Throwable {
		System.out.println("替换 原来的方法changeMe，newChangeMe");
		// 实现各种业务逻辑
		return null;
	}
}
