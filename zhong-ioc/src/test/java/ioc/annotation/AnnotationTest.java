package ioc.annotation;

import com.gqzdev.ioc.annotation.HomeWork;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 测试注解方式的IoC
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-bean02.xml")
public class AnnotationTest {

	//Spring 容器注入依赖的homeWork对象
	/**
	 * 此时IoC中有多个同名Bean  student，需要指定
	 * 不能直接使用@Autowired自动注入
	 */

	//@Autowired
	@Qualifier("student02")
	private HomeWork homeWork;

    @Test
    public void test() {
        homeWork.doHomeWork();
    }
}
