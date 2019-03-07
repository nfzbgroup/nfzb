package com.wonders.fzb.base.kit;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 提供JSP中使用Spring的工具支持类
 * 
 * @author ZSW
 */
public class SpringKit {
	private final String[] ANNOTATIONPATH = {"com.wonders.fzb.framework", "com.wonders.fzb.reviewsh", "com.wonders.fzb.base.log", "org.springframework.orm.hibernate5.LocalSessionFactoryBean"};

	AnnotationConfigApplicationContext actx = new AnnotationConfigApplicationContext(SpringJspSupportConfig.class);

	public Object getANTBean(String beanName) {
		actx.scan(ANNOTATIONPATH);
		return actx.getBean(beanName);
	}
}
