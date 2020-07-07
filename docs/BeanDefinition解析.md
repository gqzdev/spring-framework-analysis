​		解析子元素`replaced-method`
这个方法主要是对bean 中replaced-method 子元素的提取，在开始提取分析之前我们还是
预先介绍下这个元素的用法。
方法替换：可以在运行时用新的方法替换现有的方法。与之前的lookup不同的是，
replaced-method 不但可以动态地替换返回实体bean ，而且还能动态地更改原有方法的逻辑。我
们来看看使用示例。

1. 在changeMe中完成某个业务逻辑。

  ```java
  package com.gqzdev.ioc.parsebeandefinition.parsereplacedmethod;
  
  /**
   * @ClassName: TestChangeMethod
   *
   * 	一个普通的方法
   *
   * @author: ganquanzhong
   * @date: 2020/7/7 17:16
   */
  public class ChangeMethod {
  
  	public void changeMe(){
  		System.out.println("原始方法，changeMe");
  	}
  }
  ```

2. 在运营一段时间后需要改变原有的业务逻辑。

```java
package com.gqzdev.ioc.parsebeandefinition.parsereplacedmethod;

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
```

