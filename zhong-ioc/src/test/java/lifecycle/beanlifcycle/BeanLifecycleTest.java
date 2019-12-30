package lifecycle.beanlifcycle;

import com.gqzdev.lifecycle.beanlifcycle.BeanLifecycle;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author ganquanzhong
 * @Date 2019/12/30
 * @Description Bean生命周期测试
 */
@RunWith(SpringRunner.class)
@ContextConfiguration("classpath:spring-ioc-beanlifecycle.xml")
public class BeanLifecycleTest {
    @Autowired
    private BeanLifecycle beanLifecycle;

    @Test
    public void test() {
        beanLifecycle.sayHello();
    }
}
/**
 *  运行结果:
 * 1. 【Bean级别】构造器执行了
 * 2. 【Bean级别】setBeanName方法执行了
 * 3. 【Bean级别】setApplicationContext方法执行了
 * 4. 【Bean级别】afterPropertiesSet方法执行了
 * 5. 【Bean级别】init-method指定的方法执行了
 * 6. 【Bean级别】sayHello方法执行了
 * 7. 【Bean级别】destroy方法执行了
 * 8. 【Bean级别】destroy-method属性指定的方法执行了
 */

