package com.gqzdev.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

/**
 * @ClassName AspectJTest
 * @Description
 * @Author ganquanzhong
 * @Date2020/8/4 23:15
 * @Version
 **/
@Aspect
public class AspectJTest {

	/*
		使用@Pointcut注解  封装切点表达式
	 */
	@Pointcut(" execution(* com.gqzdev.aop.*.*(..)) ")
	public void test(){
	}

	/*
		前置通知
	 */
	@Before("test()")
	public void beforeTest(){
		System.out.println("前置通知 beforeTest");
	}
	/*
		后置通知
	 */
	@After("test()")
	public void after(){
		System.out.println("后置通知 afterTest");
	}
	/*
		环绕通知
	 */
	@Around("test()")
	public Object aroundTest(ProceedingJoinPoint p){
		System.out.println("环绕通知 before1");
		Object o = null;
		try {
			o = p.proceed();
		}catch (Throwable e){
			e.printStackTrace();
		}
		System.out.println("环绕通知 after1");
		return o;
	}
	/*
		返回通知
	 */
	@AfterReturning("test()")
	public void afterReturning(){
		System.out.println("返回通知 after-return");
	}
	/*
		异常通知
	 */
	@AfterThrowing("test()")
	public void afterThrowing(){
		System.out.println("异常通知 after-throwing");
	}

}
