[![github](https://badgen.net/badge/github/github?icon)](https://github.com/gqzdev)[![csdn](https://badgen.net/badge/blog/ganquanzhong/red)](https://blog.csdn.net/ganquanzhong)[![gitee](https://badgen.net/badge/gitee/zhong96/orange)](https://gitee.com/zhong96)

- [IDEA](https://www.cnblogs.com/gqzdev/p/idea.html)

- JDK8

- Gradle4.10.3

  <center><span style="color:green;font-size:25px;font-weight:bolder;">Spring Framework 5.1x源码分析</span> </center>

1. 下载源码【Spring源码已经经过了处理，下载后按照下面的步骤进行测试】

```shell
git clone https://gitee.com/zhong96/spring-framework-5.1.x.git
```

2. 在[idea](https://www.cnblogs.com/gqzdev/p/idea.html)中导入`spring5`源码构建时，`spring-core`模块报错，缺失`cglib`相关的jar包依赖。

> 为了避免第三方class的冲突，Spring把最新的`cglib`和`obj`巳nesis给重新打包（repack）了，它并没有在源码里提供这部分的代码，而是直接将其放在jar包当中，这也就导致了我们拉取代码后出现编译错误。那么为了画过编译，我们要把缺失的jar补回来![在这里插入图片描述](https://img-blog.csdnimg.cn/20190910100845635.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2dhbnF1YW56aG9uZw==,size_16,color_FFFFFF,t_70)

> 2. `Aspectj`编译问题解决，主要是`aspectj`关键字Java语法违背，需要使用ajc编译器执行。下面就切换ajc编译器，首先需要下载`aspectj`，并且安装。   
- 为spring-aspect工程添加Facets属性
![为spring-aspect工程添加Facets属性](https://img-blog.csdnimg.cn/20191204100207422.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2dhbnF1YW56aG9uZw==,size_16,color_FFFFFF,t_70)
- 编译器要改为`Ajc`，同时要设置Ajc的安装目录，如图1-27所示。记住，要选择到a spec飞jtools.jar这个层面，同时，务必要边择`Delegateto Javac`选顷，它的作用是只编译AspectJ的Facets项目，而其他则使用JDK代理。如果不勾选，则全部使用Ajc编译，那么会导致编译错误。
![编译器要改为Ajc](https://img-blog.csdnimg.cn/20191204100438207.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2dhbnF1YW56aG9uZw==,size_16,color_FFFFFF,t_70)

3. 在[idea](https://www.cnblogs.com/gqzdev/p/idea.html)中导入`spring5`源码构建时，`spring-oxm`模块报错，在gradle中找到spring-oxm的`genCastor`、`genJaxb`命令。
  ![在这里插入图片描述](https://img-blog.csdnimg.cn/20190910103004223.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2dhbnF1YW56aG9uZw==,size_16,color_FFFFFF,t_70)

  经过上面的jar修复操作后，在依赖library中应该有以下几个模块！！！！

  ![library依赖](https://img-blog.csdnimg.cn/20191205150406256.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2dhbnF1YW56aG9uZw==,size_16,color_FFFFFF,t_70)



3. build finish
  ![在这里插入图片描述](https://img-blog.csdnimg.cn/20191203180338769.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2dhbnF1YW56aG9uZw==,size_16,color_FFFFFF,t_70)

  ![测试环境搭建](https://img-blog.csdnimg.cn/20191205151101269.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2dhbnF1YW56aG9uZw==,size_16,color_FFFFFF,t_70)



<img src="src/docs/asciidoc/images/spring-framework.png" width="80" height="80"> Spring Framework

## 		中文：

​		这是Spring框架的所在地：所有[Spring项目](https://spring.io/projects)的基础。 总体来说，Spring框架和Spring项目系列通常简称为“ Spring”。

​		Spring提供了Java编程语言以外的所有需要，可用于为各种场景和体系结构来创建企业应用程序。 请阅读[概述](https://docs.spring.io/spring/docs/current/spring-framework-reference/overview.html#spring-introduction)部分作为参考，以获取更完整的介绍。

## 编码准则

​		此项目受[Spring行为准则](CODE_OF_CONDUCT.adoc)的约束。 通过参与，您将遵守此行为准则。 请向spring-code-of-conduct@pivotal.io报告不可接受的行为。

## 访问二进制文件

​		有关对工件或分发zip的访问，请参见[Spring Framework Artifacts](https://github.com/spring-projects/spring-framework/wiki/Spring-Framework-Artifacts)Wiki页面。

## 文档

​		Spring框架维护参考文档（[已发布](https://docs.spring.io/spring-framework/docs/current/spring-framework-reference/)和[source](src/docs/asciidoc)），Github [wiki页面](https://github.com/spring-projects/spring-framework/wiki)，以及
[API参考](https://docs.spring.io/spring-framework/docs/current/javadoc-api/)。 Spring项目中也有[guides and tutorials](https://spring.io/guides)

## 从源代码构建

​		请参阅[从源构建](https://github.com/spring-projects/spring-framework/wiki/Build-from-Source)Wikipedia页面和[CONTRIBUTING.md](CONTRIBUTING.md)文件。

## 保持联系

​		关注[@SpringCentral](https://twitter.com/springcentral)，[@SpringFramework](https://twitter.com/springframework)及其[团队成员](https://twitter.com/springframework/lists/team/members)。可以在[The Spring Blog](https://spring.io/blog/)上找到深入的文章，并通过我们的[news feed](https://spring.io/blog/category/news)宣布发布。 ）。

## 执照

​		Spring Framework是在[Apache许可](https://www.apache.org/licenses/LICENSE-2.0)的2.0版下发布的。



## 		英文：

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
