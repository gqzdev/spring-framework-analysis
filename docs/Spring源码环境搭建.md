>&emsp;&emsp;前沿：Spring系列生态十分丰富，涉及到各个方面。但是作为Spring生态的核心基础Spring，是最重要的环节，需要理解Spring的设计原理，就需要深度研读Spring源码。
>&emsp;&emsp;在构建Spring源码阅读环境时，遇到一些问题。通过多次尝试，慢慢找到了正确的构建编译方式，下面就记录下 **Spring源码阅读环境的构建编译完整过程** 。【在网上也找到过许多类似的文章，但发现都不是很完整，而且讲述得也不是很清晰】

> [最后提供构建好的源码下载，可以直接使用](https://gitee.com/zhong96/spring-framework-analysis)！！！！！
# 1.搭建构建前环境
- JDK
&emsp;&emsp;安装JDK就不用介绍了，保证是jdk1.8以上的版本就行了。
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190831150220494.png)
- Gradle
&emsp;&emsp;直接在官网下载<a href="https://gradle.org/releases/" target="_blank">gradle</a>构建工具，解压到本地后配置环境变量。增加系统变量`GRADLE_HOME=gradle`的路径，增加`path=%GRADLE_HOME%\bin`
![在这里插入图片描述](https://img-blog.csdnimg.cn/2019083115023641.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2dhbnF1YW56aG9uZw==,size_16,color_FFFFFF,t_70)

- [IDEA开发工具](https://www.cnblogs.com/gqzdev/p/idea.html)
&emsp;&emsp;使用IntelliJ IDEA 2018.3.2、2019.3.1开发工具，<a href="https://www.cnblogs.com/gqzdev/p/idea.html" target="_blank">下载安装激活教程</a>，<a href="https://www.cnblogs.com/gqzdev/p/idea.html">IDEA安装教程</a>


# 2.下载源码
&emsp;&emsp;直接去github下载源码，选择<a href="https://github.com/spring-projects/spring-framework" target="_blank">5.x</a>版本
[https://github.com/spring-projects/spring-framework](https://github.com/spring-projects/spring-framework)
[https://repo.spring.io/libs-release-local/org/springframework/spring/](https://repo.spring.io/libs-release-local/org/springframework/spring/)
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190831191041160.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2dhbnF1YW56aG9uZw==,size_16,color_FFFFFF,t_70)
将下载的源码解压到本地就行，下一步就是开始构建源码了。
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190831191606280.png)

# 3.开始构建
`前提是安装好Gradle环境，Spring源码使用Gradle构建的！`
在cmd中进入源码目录，输入`gradlew.bat`命令
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190831004644956.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2dhbnF1YW56aG9uZw==,size_16,color_FFFFFF,t_70)
		使用IDEA工具编译时，可能会出现内存溢出情况，这里我们编译时需要增加相关参数

` -XX:MaxPermSize=2048m -Xmx2048m -XX:MaxHeapSize=2048m	`

   ![在这里插入图片描述](https://img-blog.csdnimg.cn/20190901231851319.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2dhbnF1YW56aG9uZw==,size_16,color_FFFFFF,t_70)
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190831010135187.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2dhbnF1YW56aG9uZw==,size_16,color_FFFFFF,t_70)
&emsp;&emsp;使用国内ali的地址能明显提高依赖的下载速度

```gradle
maven { url "http://maven.aliyun.com/nexus/content/groups/public/"}
```
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190902100716486.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2dhbnF1YW56aG9uZw==,size_16,color_FFFFFF,t_70)
&emsp;&emsp;发现构建gradle失败，因为需要下载很多gradle依赖包；
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190831010306909.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2dhbnF1YW56aG9uZw==,size_16,color_FFFFFF,t_70)
&emsp;&emsp;解决方法：关于这个问题我在网上找了很多资料，最后发现多更新几次就没问题了，主要是需要下载很多依赖包，但可能由于网速的问题导致失败。总之fail了你就多refresh几次，这个过程需要一定时间的。
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190831011639507.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2dhbnF1YW56aG9uZw==,size_16,color_FFFFFF,t_70)
&emsp;&emsp;等待一定时间后，构建成功！【并没有网上说的那么难，以前的版本可能需要配置很多文件。现在的版本构建很简单，需要失败可能是由下载依赖包失败导致的】
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190901224630681.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2dhbnF1YW56aG9uZw==,size_16,color_FFFFFF,t_70)
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190831013336829.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2dhbnF1YW56aG9uZw==,size_16,color_FFFFFF,t_70)
&emsp;&emsp;在IDEA中，如果能找到`ApplicationContext`类，按下Ctrl+Shift+Alt+U键，出现下图所示类图界面说明构建成功了！
&emsp;&emsp;此时可以查看Spring的源码了，但是我们需要在源码的基础上面进行修改，开发，最好将源码进行编译打包，下面就是将源码编译的过程。
![在这里插入图片描述](https://img-blog.csdnimg.cn/201908310141042.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2dhbnF1YW56aG9uZw==,size_16,color_FFFFFF,t_70)

# 4.编译源码
&emsp;&emsp;在构建完成源码之后，就搭建好了阅读源码的环境了。但是我们还可以将源码编译打包。在编译之前需要进行一些配置修改，可以查看`import-into-idea.md`文档
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190831232629356.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2dhbnF1YW56aG9uZw==,size_16,color_FFFFFF,t_70)
&emsp;&emsp;在编译之前还需要对==dosc.gradle==文档进行修改，因为有些注释，文件路径在编译时需要调整。
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190831232806245.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2dhbnF1YW56aG9uZw==,size_16,color_FFFFFF,t_70)
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190831233022126.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2dhbnF1YW56aG9uZw==,size_16,color_FFFFFF,t_70)
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190831233158388.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2dhbnF1YW56aG9uZw==,size_16,color_FFFFFF,t_70)
&emsp;&emsp;下面将两个地方的路劲替换使用windows环境下的反斜扛 ==\\==

-  &emsp;&emsp;下面给出`task schemaZip`中修改的部分
1.   `it.path.endsWith("META-INF\\spring.schemas")`
2.  `it.path.endsWith(schemas.get(key).replaceAll('\\\\/','\\\\\\\\'))`
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190908184848864.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2dhbnF1YW56aG9uZw==,size_16,color_FFFFFF,t_70)
&emsp;&emsp;也可以用下面的方法判断是在什么环境中编译，直接粘贴复制。

```java
 task schemaZip(type: Zip) {
        group = "Distribution"
        baseName = "spring-framework"
        classifier = "schema"
        description = "Builds -${classifier} archive containing all " +
            "XSDs for deployment at http://springframework.org/schema."
        duplicatesStrategy 'exclude'

        //当前系统是否是windows的标志
        def isWindows = System.properties['os.name'].toUpperCase().contains('WINDOWS')

        //不同的操作系统，表示子目录的符号是不同的
        def schemaPath = isWindows ? "META-INF\\spring.schemas" : "META-INF/spring.schemas"

        moduleProjects.each { subproject ->
            def Properties schemas = new Properties();

            subproject.sourceSets.main.resources.find {
                it.path.endsWith(schemaPath)
            }?.withInputStream { schemas.load(it) }

            for (def key : schemas.keySet()) {
                def shortName = key.replaceAll(/http.*schema.(.*).spring-.*/, '$1')
                assert shortName != key
                File xsdFile = subproject.sourceSets.main.resources.find {
                    //如果是windows环境，就要对路径中的分隔符做替换
                    isWindows ? it.path.endsWith(schemas.get(key).replaceAll('\\/','\\\\')) : it.path.endsWith(schemas.get(key))
                }
                assert xsdFile != null
                into (shortName) {
                    from xsdFile.path
                }
            }
        }
    }
```
&emsp;&emsp;接下来先编译

1. Precompile `spring-oxm` with `./gradlew :spring-oxm:compileTestJava`
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190831233643294.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2dhbnF1YW56aG9uZw==,size_16,color_FFFFFF,t_70)
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190901232004199.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2dhbnF1YW56aG9uZw==,size_16,color_FFFFFF,t_70)
&emsp;&emsp;在Precompile `spring-oxm` with `./gradlew :spring-oxm:compileTestJava`编译完成后，可以编译整个工程了！（这个过程非常耗时间，可能20几分钟！）
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190831233856604.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2dhbnF1YW56aG9uZw==,size_16,color_FFFFFF,t_70)
&emsp;&emsp;进过一段时间编译build成功！（每个人电脑的性能不一样，所需时间也不一样。 ）
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190908190025783.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2dhbnF1YW56aG9uZw==,size_16,color_FFFFFF,t_70)
&emsp;&emsp;发现spring-aspects模块有错误， 选中该模块，右键Load/Unload Modules ，把spring-aspects下的所有项目排除出去。
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190901224850156.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2dhbnF1YW56aG9uZw==,size_16,color_FFFFFF,t_70)
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190901111040356.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2dhbnF1YW56aG9uZw==,size_16,color_FFFFFF,t_70)
&emsp;&emsp;到这里，整个Spring源码的编译和环境准备就完成了，接下来我们编写一个demo来调试一下，看看我们的代码是否没问题。
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190901111508155.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2dhbnF1YW56aG9uZw==,size_16,color_FFFFFF,t_70)

# 5.测试
&emsp;&emsp;完成了上面的过程后，我们可以自己编写一个模块测试该**源码构建编译**过程是否完成！
1. 在Spring中添加自己的module模块，同样选择`gradle`构建。
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190902111935720.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2dhbnF1YW56aG9uZw==,size_16,color_FFFFFF,t_70)
2. 输入`ArtufactId`工程名
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190902112012909.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2dhbnF1YW56aG9uZw==,size_16,color_FFFFFF,t_70)
3. 选择默认的存储路径即可
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190902112111885.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2dhbnF1YW56aG9uZw==,size_16,color_FFFFFF,t_70)
4. 确认，idea会自动帮助我们构建`spring-mytest`模块。
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190902113416687.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2dhbnF1YW56aG9uZw==,size_16,color_FFFFFF,t_70)
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190902112336245.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2dhbnF1YW56aG9uZw==,size_16,color_FFFFFF,t_70)
![在这里插入图片描述](https://img-blog.csdnimg.cn/2019090213275256.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2dhbnF1YW56aG9uZw==,size_16,color_FFFFFF,t_70)
5.  下面编写一个简单的`applicationContext`获取容器用的bean，主要是测试Spring源码构建编译过程是否成功！
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190902132907161.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2dhbnF1YW56aG9uZw==,size_16,color_FFFFFF,t_70)
	新建User.java

```java
package com.gqzdev.springtest;

/**
 * @ClassName: User
 * @author: ganquanzhong
 * @date: 2019/9/2 11:27
 */
public class User {
	private int uid;
	private String username;
	private String pwd;
	private String tel;
	private String addr;

	public User(int uid, String username, String pwd, String tel, String addr) {
		this.uid = uid;
		this.username = username;
		this.pwd = pwd;
		this.tel = tel;
		this.addr = addr;
	}

	@Override
	public String toString() {
		return "User{" +
				"uid=" + uid +
				", username='" + username + '\'' +
				", pwd='" + pwd + '\'' +
				", tel='" + tel + '\'' +
				", addr='" + addr + '\'' +
				'}';
	}

}
```
新建`JavaConfig.java` &emsp;&emsp;(使用注解的方式声明bean)
```java
package com.gqzdev.springtest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName: JavaConfig
 * @author: ganquanzhong
 * @date: 2019/9/2 11:43
 */

@Configuration
@ComponentScan
public class JavaConfig {

	@Bean
	public User user(){
		return new User(101,"ganquanzhong","pwd","13995978321","china");
	}
}

```
最后写一个测试类`Main.java`
```java
package com.gqzdev.springtest;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @ClassName: Main
 * @author: ganquanzhong
 * @date: 2019/9/2 12:29
 */
public class Main {

	public static void main(String[] args){

		ApplicationContext ac =new AnnotationConfigApplicationContext(JavaConfig.class);
		User user = (User) ac.getBean("user");
		System.out.println(user.toString());
	}

}

```

6. 运行
consoloe输出
```console
User{uid=101, username='ganquanzhong', pwd='pwd', tel='13995978321', addr='china'}
```
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190902133249345.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2dhbnF1YW56aG9uZw==,size_16,color_FFFFFF,t_70)


# 6.遇到的问题解决方法
>&emsp;&emsp;这个总结下构建源码阅读环境过程中会遇到的问题和解决办法。

1. 在idea中导入`spring5`源码构建时，`spring-core`模块报错，缺失`cglib`相关的jar包依赖。
> 为了避免第三方class的冲突，Spring把最新的`cglib`和`objenesis`给重新打包（repack）了，它并没有在源码里提供这部分的代码，而是直接将其放在jar包当中，这也就导致了我们拉取代码后出现编译错误。那么为了通过编译，我们要把缺失的jar补回来
>
> ![在这里插入图片描述](https://img-blog.csdnimg.cn/20190910100845635.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2dhbnF1YW56aG9uZw==,size_16,color_FFFFFF,t_70)
>
> 2. Aspect编译问题解决，主要是aspect关键字Java语法违背，需要使用`ajc`编译器执行。下面就切换ajc编译器，首先需要下载`aspectj`，并且安装。   
- 为spring-aspect工程添加Facets属性
![为spring-aspect工程添加Facets属性](https://img-blog.csdnimg.cn/20191204100207422.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2dhbnF1YW56aG9uZw==,size_16,color_FFFFFF,t_70)
- 编译器要改为`Ajc`，同时要设置Ajc的安装目录，如图1-27所示。记住，要选择到**aspecjtools.jar**这个路径，同时，务必要边择Delegateto Javac选顷，它的作用是只编译AspectJ的Facets项目，而其他则使用JDK代理。如果不勾选，则全部使用Ajc编译，那么会导致编译错误。
![编译器要改为Ajc](https://img-blog.csdnimg.cn/20191204100438207.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2dhbnF1YW56aG9uZw==,size_16,color_FFFFFF,t_70)
![在这里插入图片描述](https://img-blog.csdnimg.cn/2020070110254332.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2dhbnF1YW56aG9uZw==,size_16,color_FFFFFF,t_70)

3. 在idea中导入spring5源码构建时，spring-oxm模块报错，在gradle中找到spring-oxm的genCastor、genJaxb命令。
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190910103004223.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2dhbnF1YW56aG9uZw==,size_16,color_FFFFFF,t_70)
经过上面的jar修复操作后，在依赖library中应该有以下几个模块！！！！
4. ![library依赖](https://img-blog.csdnimg.cn/20191205150406256.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2dhbnF1YW56aG9uZw==,size_16,color_FFFFFF,t_70)
5. build finish
    ![在这里插入图片描述](https://img-blog.csdnimg.cn/20191203180338769.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2dhbnF1YW56aG9uZw==,size_16,color_FFFFFF,t_70)
    ![测试环境搭建](https://img-blog.csdnimg.cn/20191205151101269.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2dhbnF1YW56aG9uZw==,size_16,color_FFFFFF,t_70)
# [源码下载](https://gitee.com/zhong96/spring-framework-analysis)
[附上我构建好的源码，并且会一直更新源码的分析过程，感兴趣的可以start下](https://gitee.com/zhong96/spring-framework-analysis)
🌱🌱🌱 欢迎star，一起学习讨论！🎈🎈🎈
[https://gitee.com/zhong96/spring-framework-analysis](https://gitee.com/zhong96/spring-framework-analysis)
[https://github.com/gqzdev/spring-framework-analysis](https://github.com/gqzdev/spring-framework-analysis)

接下来就可以启程了，通过debug模式分析源码！

