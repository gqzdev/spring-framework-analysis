/*
 * Copyright 2002-2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.context.annotation;

import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.util.Assert;

import java.lang.annotation.Annotation;

/**
 * A {@link ScopeMetadataResolver} implementation that by default checks for
 * the presence of Spring's {@link Scope @Scope} annotation on the bean class.
 *
 * <p>The exact type of annotation that is checked for is configurable via
 * {@link #setScopeAnnotationType(Class)}.
 *
 * @author Mark Fisher
 * @author Juergen Hoeller
 * @author Sam Brannen
 * @since 2.5
 * @see org.springframework.context.annotation.Scope
 */
public class AnnotationScopeMetadataResolver implements ScopeMetadataResolver {

	private final ScopedProxyMode defaultProxyMode;

	protected Class<? extends Annotation> scopeAnnotationType = Scope.class;


	/**
	 * Construct a new {@code AnnotationScopeMetadataResolver}.
	 * @see #AnnotationScopeMetadataResolver(ScopedProxyMode)
	 * @see ScopedProxyMode#NO
	 */
	public AnnotationScopeMetadataResolver() {
		this.defaultProxyMode = ScopedProxyMode.NO;
	}

	/**
	 * Construct a new {@code AnnotationScopeMetadataResolver} using the
	 * supplied default {@link ScopedProxyMode}.
	 * @param defaultProxyMode the default scoped-proxy mode
	 */
	public AnnotationScopeMetadataResolver(ScopedProxyMode defaultProxyMode) {
		Assert.notNull(defaultProxyMode, "'defaultProxyMode' must not be null");
		this.defaultProxyMode = defaultProxyMode;
	}


	/**
	 * Set the type of annotation that is checked for by this
	 * {@code AnnotationScopeMetadataResolver}.
	 * @param scopeAnnotationType the target annotation type
	 */
	public void setScopeAnnotationType(Class<? extends Annotation> scopeAnnotationType) {
		Assert.notNull(scopeAnnotationType, "'scopeAnnotationType' must not be null");
		this.scopeAnnotationType = scopeAnnotationType;
	}

	//解析 注解Bean定义类 中的作用域元信息
	@Override
	public ScopeMetadata resolveScopeMetadata(BeanDefinition definition) {
		ScopeMetadata metadata = new ScopeMetadata();
		if (definition instanceof AnnotatedBeanDefinition) {
			AnnotatedBeanDefinition annDef = (AnnotatedBeanDefinition) definition;

			//从注解Bean定义类的属性中查找属性为”Scope”的值，即@Scope注解的值
			// annDef.getMetadata().getAnnotationAttributes方法将Bean中所有的注解和注解的值存放在一个map集合中
			AnnotationAttributes attributes = AnnotationConfigUtils.attributesFor(
					annDef.getMetadata(), this.scopeAnnotationType);

			//将获取到的@Scope注解的值设置到要返回的对象中
			if (attributes != null) {
				metadata.setScopeName(attributes.getString("value"));
				//获取@Scope注解中的proxyMode属性值，在创建代理对象时会用到
				ScopedProxyMode proxyMode = attributes.getEnum("proxyMode");
				//如果@Scope的proxyMode属性为DEFAULT或者NO
				if (proxyMode == ScopedProxyMode.DEFAULT) {
					//设置proxyMode为NO
					proxyMode = this.defaultProxyMode;
				}
				//为返回的元数据设置proxyMode
				metadata.setScopedProxyMode(proxyMode);
			}
		}
		//返回解析的作用域元信息对象   Scope作用域
		//System.out.println("返回解析的作用域元信息对象"+metadata.getScopeName()+" "+metadata.getScopedProxyMode());
		return metadata;
	}

}
