XML方式实现IoC：
    通过ClassPathXmlApplicationContext方法创建上下文 入门 进行分析
User 无参构造器
Order带有两个参数的构造器


    流程：
        ClassPathXmlApplicationContext类
            加载配置文件  ConfigLocation
               设置环境  等
            refresh()方法  重要13个方法
            	1. prepareRefresh();
                2. obtainFreshBeanFactory();
                3. prepareBeanFactory(beanFactory);
                4. postProcessBeanFactory(beanFactory);
                5. invokeBeanFactoryPostProcessors(beanFactory);
                6. registerBeanPostProcessors(beanFactory);
                7. initMessageSource();
                8. initApplicationEventMulticaster();
                9. onRefresh();
                10.registerListeners();
                11.finishBeanFactoryInitialization(beanFactory);
                12.destroyBeans();
                13.cancelRefresh(ex);
