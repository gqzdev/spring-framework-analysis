package lifecycle.containerlifecycle;

import com.gqzdev.lifecycle.beanlifcycle.BeanLifecycle;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author ganquanzhong
 * @Date 2018/8/19
 * @Description Bean级生命周期+容器级生命周期测试
 */
public class ContainerLifecycleTest {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"classpath:spring-ioc-beanlifecycle.xml",
				"classpath:spring-ioc-containerlifecycle.xml");
        BeanLifecycle beanLifecycle = context.getBean("beanLifecycle",BeanLifecycle.class);
        beanLifecycle.sayHello();
        context.close();
    }
}
