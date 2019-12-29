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
