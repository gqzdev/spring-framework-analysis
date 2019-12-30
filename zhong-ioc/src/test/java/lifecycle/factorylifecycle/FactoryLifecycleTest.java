package lifecycle.factorylifecycle;

import com.gqzdev.lifecycle.beanlifcycle.BeanLifecycle;
import org.springframework.context.support.ClassPathXmlApplicationContext;

 /**
  * @description: Bean级生命周期 + 容器级生命周期 + 工厂级生命周期测试
  * @Author: ganquanzhong
  * @Date:  2019/12/30 9:37
  */
public class FactoryLifecycleTest {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
        		"classpath:spring-ioc-beanlifecycle.xml",
				"classpath:spring-ioc-containerlifecycle.xml",
				"classpath:spring-ioc-factorybeanlifecycle.xml");
        BeanLifecycle beanLifecycle = context.getBean("beanLifecycle",BeanLifecycle.class);
        beanLifecycle.sayHello();
        context.close();
    }
}
/**
 *	运行结果
 * 一 【工厂级别】FactoryLifecycle构造器执行了
 * 二 【工厂级别】postProcessBeanFactory方法执行了
 * ① 【容器级别】ContainerLifecycle构造器执行了
 * ② 【容器级别】postProcessBeforeInstantiation方法执行了，class=class com.gqzdev.lifecycle.beanlifcycle.BeanLifecycle
 * 1. 【Bean级别】构造器执行了
 * ③ 【容器级别】postProcessPropertyValues方法执行了，beanName=class com.gqzdev.lifecycle.beanlifcycle.BeanLifecycle
 * 2. 【Bean级别】setBeanName方法执行了
 * 3. 【Bean级别】setApplicationContext方法执行了
 * 4. 【Bean级别】afterPropertiesSet方法执行了
 * 5. 【Bean级别】init-method指定的方法执行了
 * ④ 【容器级别】postProcessAfterInitialization方法执行了，beanName=class com.gqzdev.lifecycle.beanlifcycle.BeanLifecycle
 * 6. 【Bean级别】sayHello方法执行了
 * 7. 【Bean级别】destroy方法执行了
 * 8. 【Bean级别】destroy-method属性指定的方法执行了
 */

