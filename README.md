[![github](https://badgen.net/badge/github/github?icon)](https://github.com/gqzdev)[![csdn](https://badgen.net/badge/blog/ganquanzhong/red)](https://blog.csdn.net/ganquanzhong)[![gitee](https://badgen.net/badge/gitee/zhong96/orange)](https://gitee.com/zhong96)



1. 下载源码

```shell
git clone https://gitee.com/zhong96/spring-framework-5.1.x.git
```

2. 构建build时会报错，主要是仓库上面缺少两个target，将其添加到target就行，提交时没有上传

>&emsp;&emsp;这个总结下构建源码阅读环境过程中会遇到的问题和解决办法。

1. 在idea中导入spring5源码构建时，spring-core模块报错，缺失cglib相关的jar包依赖。
> 为了避免第三方class的冲突，Spring把最新的cglib和obj巳nesis给重新打包（repack）了，它并没有在源码里提供这部分的代码，而是直接将其放在jar包当中，这也就导致了我们拉取代码后出现编译错误。那么为了画过编译，我们要把缺失的jar补回来![在这里插入图片描述](https://img-blog.csdnimg.cn/20190910100845635.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2dhbnF1YW56aG9uZw==,size_16,color_FFFFFF,t_70)

> 2. Aspecu编译问题解决，主要是aspect关键字Java语法违背，需要使用ajc编译器执行。下面就切换ajc编译器，首先需要下载aspectj，并且安装。   
- 为spring-aspect工程添加Facets属性
![为spring-aspect工程添加Facets属性](https://img-blog.csdnimg.cn/20191204100207422.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2dhbnF1YW56aG9uZw==,size_16,color_FFFFFF,t_70)
- 编译器要改为Ajc，同时要设置Ajc的安装目录，如图1-27所示。记住，要选择到a spec飞jtools.jar这个层面，同时，务必要边择Delegateto Javac选顷，它的作用是只编译AspectJ的Facets项目，而其他则使用JDK代理。如果不勾选，则全部使用Ajc编译，那么会导致编译错误。
![编译器要改为Ajc](https://img-blog.csdnimg.cn/20191204100438207.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2dhbnF1YW56aG9uZw==,size_16,color_FFFFFF,t_70)

3. 在idea中导入spring5源码构建时，spring-oxm模块报错，在gradle中找到spring-oxm的==genCastor、genJaxb== 命令。
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190910103004223.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2dhbnF1YW56aG9uZw==,size_16,color_FFFFFF,t_70)

4. build finish
![在这里插入图片描述](https://img-blog.csdnimg.cn/20191203180338769.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2dhbnF1YW56aG9uZw==,size_16,color_FFFFFF,t_70)

<img src="src/docs/asciidoc/images/spring-framework.png" width="80" height="80"> Spring Framework

This is the home of the Spring Framework: the foundation for all [Spring projects](https://spring.io/projects). Collectively the Spring Framework and the family of Spring projects is often referred to simply as "Spring". 

Spring provides everything required beyond the Java programming language for creating enterprise applications for a wide range of scenarios and architectures. Please read the [Overview](https://docs.spring.io/spring/docs/current/spring-framework-reference/overview.html#spring-introduction) section as reference for a more complete introduction.

## Code of Conduct

This project is governed by the [Spring Code of Conduct](CODE_OF_CONDUCT.adoc). By participating, you are expected to uphold this code of conduct. Please report unacceptable behavior to spring-code-of-conduct@pivotal.io.

## Access to Binaries

For access to artifacts or a distribution zip, see the [Spring Framework Artifacts](https://github.com/spring-projects/spring-framework/wiki/Spring-Framework-Artifacts) wiki page.

## Documentation

The Spring Framework maintains reference documentation ([published](https://docs.spring.io/spring-framework/docs/current/spring-framework-reference/) and [source](src/docs/asciidoc)), Github [wiki pages](https://github.com/spring-projects/spring-framework/wiki), and an
[API reference](https://docs.spring.io/spring-framework/docs/current/javadoc-api/). There are also [guides and tutorials](https://spring.io/guides) across Spring projects.

## Build from Source

See the [Build from Source](https://github.com/spring-projects/spring-framework/wiki/Build-from-Source) Wikipedia page and the [CONTRIBUTING.md](CONTRIBUTING.md) file.

## Stay in Touch

Follow [@SpringCentral](https://twitter.com/springcentral), [@SpringFramework](https://twitter.com/springframework), and its [team members](https://twitter.com/springframework/lists/team/members) on Twitter. In-depth articles can be found at [The Spring Blog](https://spring.io/blog/), and releases are announced via our [news feed](https://spring.io/blog/category/news).

## License

The Spring Framework is released under version 2.0 of the [Apache License](https://www.apache.org/licenses/LICENSE-2.0).
