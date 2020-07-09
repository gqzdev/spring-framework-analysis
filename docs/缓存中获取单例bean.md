getBean 

doGetBean

createBean



## GoGetBean方法

```java
//真正实现向IOC容器获取Bean的功能，也是触发 依赖注入 功能的地方   核心步骤是createBean
	@SuppressWarnings("unchecked")
	protected <T> T doGetBean(final String name, @Nullable final Class<T> requiredType,
			@Nullable final Object[] args, boolean typeCheckOnly) throws BeansException {

		//根据指定的名称获取被管理Bean的名称，剥离指定名称中对容器的相关依赖
		//如果指定的是别名，将别名 转换为规范的Bean名称
		/*
			1. 转换为对应的beanName
			这里传入的参数可能是别名，也可能是FactoryBean，所以需要进行解析
			-去除FactoryBean 的修饰符，也就是如果是name＝"&aa"，那么会首先去除&而使name="aa"。
			-取指定alias 所表示的最终beanName
		 */
		final String beanName = transformedBeanName(name);
		Object bean;

		// Eagerly check singleton cache for manually registered singletons. 急切地检查单例缓存中手动注册的单例。
		/*
			2. 尝试从缓存中加载单例
			检查缓存中 或者 实例工厂中是否有对应的实例
			为什么首先会使用这段代码呢？
			因为在创建单例bean的时候会存在 依赖注入 的情况，而在创建依赖的时候为了避免循环依赖，
			Spring创建bean的原则 是不等bean创建完成就 将创建bean的 ObjectFactory提早曝光
			也就是将ObjectFactory加入到缓存中，（引入三级缓存  三个map，）
			一旦下个bean创建时候需要依赖上个bean，则直接使用ObjectFactory
		*/
		// 先直接尝试 从缓存或者 singletonFactories 中的ObjectFactory中获取
		Object sharedInstance = getSingleton(beanName);
		//IOC容器创建单例模式Bean实例对象 ，整个IOC容器中只创建一次
		if (sharedInstance != null && args == null) {
			//如果指定名称的Bean在容器中已有单例模式的Bean被创建
			//直接返回已经创建的Bean
			if (logger.isTraceEnabled()) {
				if (isSingletonCurrentlyInCreation(beanName)) {
					logger.trace("Returning eagerly cached instance of singleton bean '" + beanName +
							"' that is not fully initialized yet - a consequence of a circular reference");
				}
				else {
					logger.trace("Returning cached instance of singleton bean '" + beanName + "'");
				}
			}
			/*
			 	3. bean的实例化
			 	返回对应的实例，有时候存在诸如BeanFactory的情况，
			 		并不是直接返回实例本身，而是返回指定方法返回的实例
			 */
			//获取给定Bean的实例对象，主要是完成FactoryBean的相关处理
			/*
				注意：BeanFactory是管理容器中Bean的工厂，
				而FactoryBean是创建创建对象的工厂Bean，两者之间有区别
			 */
			bean = getObjectForBeanInstance(sharedInstance, name, beanName, null);
		}
		else {
			// Fail if we're already creating this bean instance:
			// We're assumably within a circular reference.
			//缓存没有  正在创建的单例模式Bean
			//缓存中已经有创建的原型模式Bean
			//但是由于循环引用的问题导致实例化对象失败
			/*
				4. 原型模式的依赖检查
				只有在单例情况才会尝试解决 循环依赖，
				原型模式情况下，如果存在A中有B的属性，B中有A的属性，
					那么在依赖注入的时候就会产生 A还未创建完的时候，因为对于B的创建再次返回创建A，造成循环依赖。
					则抛出异常
			 */
			if (isPrototypeCurrentlyInCreation(beanName)) {
				throw new BeanCurrentlyInCreationException(beanName);
			}

			// Check if bean definition exists in this factory.
			/*
				5. 检测parentBeanFactory
			 	对IOC容器中是否存在指定名称的BeanDefinition进行检查，
			 	首先检查是否能在当前的BeanFactory中获取的所需要的Bean，
			 	如果不能则 委托当前容器的父级容器去查找，
			 	如果还是找不到则沿着容器的继承体系向父级容器查找
			 */
			BeanFactory parentBeanFactory = getParentBeanFactory();

			//当前容器的父级容器存在，且当前容器中不存在指定名称的Bean
			// 如果在beanDefinitionMap中 也就是在所有已经加载的类中不包括beanName，则尝试从parentBeanFactory中检测
			if (parentBeanFactory != null && !containsBeanDefinition(beanName)) {
				// Not found -> check parent.
				//解析指定Bean名称的原始名称
				String nameToLookup = originalBeanName(name);
				if (parentBeanFactory instanceof AbstractBeanFactory) {
					// 递归到BeanFactory中寻找
					return ((AbstractBeanFactory) parentBeanFactory).doGetBean(
							nameToLookup, requiredType, args, typeCheckOnly);
				}
				else if (args != null) {
					// Delegation to parent with explicit args.
					// 委派父级容器 根据指定名称和显式的参数查找
					return (T) parentBeanFactory.getBean(nameToLookup, args);
				}
				else if (requiredType != null) {
					// No args -> delegate to standard getBean method.
					// 委派父级容器 根据指定名称和类型查找
					return parentBeanFactory.getBean(nameToLookup, requiredType);
				}
				else {
					// 委派父级容器根据指定名称查找
					return (T) parentBeanFactory.getBean(nameToLookup);
				}
			}
			//创建的Bean是否需要进行类型验证，一般不需要
			if (!typeCheckOnly) {
				//向容器标记指定的Bean 为已经被创建 ；添加到alreadyCreated Set集合中
				markBeanAsCreated(beanName);
			}

			try {
				/*
					6. 将存储XML配置文件的GenericBeanDefinition 转换为RootBeanDefinition
					将存储XML配置文件的GenericBeanDefinition 转换为 RootBeanDefinition，
					如果指定BeanName是子Bean的话 ，会合并父类的相关属性
				 */
				//根据指定Bean名称获取其父级的Bean定义
				//主要解决Bean继承时子类合并父类公共属性问题
				final RootBeanDefinition mbd = getMergedLocalBeanDefinition(beanName);
				checkMergedBeanDefinition(mbd, beanName, args);

				/*
					7.寻找依赖
					确保当前bean 依赖的bean的初始化。
					首先获取当前Bean依赖关系mbd.getDependsOn()
				 	接着根据依赖的BeanName递归调用getBean()方法
				 	直到调用getSingleton()返回依赖
				 */
				// Guarantee initialization of beans that the current bean depends on.  bean的依赖次序 属性
				String[] dependsOn = mbd.getDependsOn();
				// 若存在依赖 则需要递归实例化依赖的bean
				if (dependsOn != null) {
					for (String dep : dependsOn) {
						if (isDependent(beanName, dep)) {
							throw new BeanCreationException(mbd.getResourceDescription(), beanName,
									"Circular depends-on relationship between '" + beanName + "' and '" + dep + "'");
						}
						// 缓存依赖调用
						registerDependentBean(dep, beanName);
						try {
							//递归调用getBean方法，获取当前Bean的依赖Bean
							getBean(dep);
						}
						catch (NoSuchBeanDefinitionException ex) {
							throw new BeanCreationException(mbd.getResourceDescription(), beanName,
									"'" + beanName + "' depends on missing bean '" + dep + "'", ex);
						}
					}
				}

				// Create bean instance.
				/*
					8. 针对不同的scope 进行bean 的创建  默认的是singleton
					实例化依赖的bean后 便可以实例化mbd本身了
				 */
				// 8.1 创建单例模式Bean的实例对象
				if (mbd.isSingleton()) {
					//这里使用了一个匿名内部类，创建Bean实例对象，并且注册给所依赖的对象 ObjectFactory
					sharedInstance = getSingleton(beanName, () -> {
						try {
							// 直到调用getSingleton()返回依赖
							// 创建一个指定Bean实例对象，如果有父级继承，则合并子类和父类的定义
							return createBean(beanName, mbd, args);
						}
						catch (BeansException ex) {
							// Explicitly remove instance from singleton cache: It might have been put there
							// eagerly by the creation process, to allow for circular reference resolution.
							// Also remove any beans that received a temporary reference to the bean.
							destroySingleton(beanName);
							throw ex;
						}
					});
					//获取给定Bean的实例对象
					bean = getObjectForBeanInstance(sharedInstance, name, beanName, mbd);
				}
				// 8.2 IOC容器创建原型模式Bean实例对象
				else if (mbd.isPrototype()) {
					// It's a prototype -> create a new instance.
					//原型模式(Prototype)是每次都会创建一个新的对象
					Object prototypeInstance = null;
					try {
						//回调beforePrototypeCreation方法，默认的功能是注册当前创建的原型对象
						beforePrototypeCreation(beanName);
						//创建指定Bean对象实例
						prototypeInstance = createBean(beanName, mbd, args);
					}
					finally {
						//回调afterPrototypeCreation方法，默认的功能告诉IOC容器指定Bean的原型对象不再创建
						afterPrototypeCreation(beanName);
					}
					//获取给定Bean的实例对象
					bean = getObjectForBeanInstance(prototypeInstance, name, beanName, mbd);
				}
				/*
					8.3 其他scope类型
					要创建的Bean既不是单例模式，也不是原型模式，则根据Bean定义资源中	配置的生命周期范围，
					选择实例化Bean的合适方法，这种在Web应用程序中
					比较常用，如：request、session、application等生命周期
				 */
				else {
					String scopeName = mbd.getScope();
					final Scope scope = this.scopes.get(scopeName);
					//Bean定义资源中没有配置生命周期范围，则Bean定义不合法
					if (scope == null) {
						throw new IllegalStateException("No Scope registered for scope name '" + scopeName + "'");
					}
					try {
						//这里又使用了一个匿名内部类，获取一个指定生命周期范围的实例
						Object scopedInstance = scope.get(beanName, () -> {
							beforePrototypeCreation(beanName);
							try {
								return createBean(beanName, mbd, args);
							}
							finally {
								afterPrototypeCreation(beanName);
							}
						});
						//获取给定Bean的实例对象
						bean = getObjectForBeanInstance(scopedInstance, name, beanName, mbd);
					}
					catch (IllegalStateException ex) {
						throw new BeanCreationException(beanName,
								"Scope '" + scopeName + "' is not active for the current thread; consider " +
								"defining a scoped proxy for this bean if you intend to refer to it from a singleton",
								ex);
					}
				}
			}
			catch (BeansException ex) {
				cleanupAfterBeanCreationFailure(beanName);
				throw ex;
			}
		}

		// Check if required type matches the type of the actual bean instance.
		/*
			9. 类型转换、将返回的bean的类型进行检查
			并可以是将返回的bean 转换为requiredType 所指定的类型
			对创建的Bean实例对象进行类型检查，是否符合实际类型
		 */
		if (requiredType != null && !requiredType.isInstance(bean)) {
			try {
				T convertedBean = getTypeConverter().convertIfNecessary(bean, requiredType);
				if (convertedBean == null) {
					throw new BeanNotOfRequiredTypeException(name, requiredType, bean.getClass());
				}
				return convertedBean;
			}
			catch (TypeMismatchException ex) {
				if (logger.isTraceEnabled()) {
					logger.trace("Failed to convert bean '" + name + "' to required type '" +
							ClassUtils.getQualifiedName(requiredType) + "'", ex);
				}
				throw new BeanNotOfRequiredTypeException(name, requiredType, bean.getClass());
			}
		}
		return (T) bean;
	}
```

## getSingleton(beanName);

 尝试从缓存中加载单例

```java
/*
		这个方法涉及 循环依赖的检测。
		首先尝试从singletonObjects（一级缓存）里获取实例，
		如果获取不到 再从earlySingletonObjects（二级缓存）里获取，
		如果还是获取不到，再尝试从singletonFactories（三级缓存）里获取beanName对应的ObjectFactory，
		然后调用这个ObjectFactory的getObject来创建bean，并放到earlySingletonObjects中，
		并且从singletonFactories里面remove掉这个ObjectFactory
	 */
	/*
		返回注册在给定名称下的(原始)单例对象。
		检查已经实例化的单例，并且允许早期依赖
		对当前创建的单例对象的引用(解决循环引用)。
	 */
	@Nullable
	protected Object getSingleton(String beanName, boolean allowEarlyReference) {
		// 检查一级缓存 中是否存在实例
		Object singletonObject = this.singletonObjects.get(beanName);
		// isSingletonCurrentlyInCreation(beanName)返回指定的单例bean当前是否在创建中(在整个工厂中)。
		if (singletonObject == null && isSingletonCurrentlyInCreation(beanName)) {
			// 如果为空，则锁定全局变量并进行处理
			synchronized (this.singletonObjects) {
				// 二级缓存中查找，如果这个bean正在加载，则不处理。半成品
				singletonObject = this.earlySingletonObjects.get(beanName);
				// allowEarlyReference 是否允许早期依赖（默认为true）
				if (singletonObject == null && allowEarlyReference) {
					// 三级缓存中查找
					/*
						当某些方法需要提前初始化的时候
						则会调用addSingletonFactory方法对应的ObjectFactory初始化策略
						并存储在singletonFactories中
					 */
					ObjectFactory<?> singletonFactory = this.singletonFactories.get(beanName);
					if (singletonFactory != null) {
						// 调用预先设定的getObject方法
						singletonObject = singletonFactory.getObject();
						// 将生成的singletonObject放入二级缓存中
						this.earlySingletonObjects.put(beanName, singletonObject);
						// 从singletonFactories中移除已处理的beanName
						// earlySingletonObjects 和 singletonFactories互斥
						this.singletonFactories.remove(beanName);
					}
				}
			}
		}
		return singletonObject;
	}
```





