package com.framework.example;

import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.config.ConstructorArgumentValues;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * 自定义IOC容器
 * 
 * @author CHEN XIANXI
 * @since 2016年11月3日 下午2:14:21
 * @version 1.0
 */
public class Example01 {

    public static void main(String[] args) {
        DefaultListableBeanFactory beanRegistry = new DefaultListableBeanFactory();

        BeanFactory container = (BeanFactory) bindViaCode(beanRegistry);

        FXNewsProvider newsProvider = (FXNewsProvider) container.getBean("djNewsProvider");
        newsProvider.getNewsListener().listen();
    }

    private static BeanFactory bindViaCode(BeanDefinitionRegistry registry) {

        AbstractBeanDefinition newsProvider = new RootBeanDefinition(FXNewsProvider.class);
        newsProvider.setScope(ConfigurableBeanFactory.SCOPE_SINGLETON);

        AbstractBeanDefinition newsListener = new RootBeanDefinition(DowJonesNewsListener.class);
        newsListener.setScope(ConfigurableBeanFactory.SCOPE_SINGLETON);

        AbstractBeanDefinition newsPersister = new RootBeanDefinition(DowJonesNewsPersister.class);
        newsPersister.setScope(ConfigurableBeanFactory.SCOPE_SINGLETON);

        // 将bean定义注册到容器中
        registry.registerBeanDefinition("djNewsProvider", newsProvider);
        registry.registerBeanDefinition("djNewsListener", newsListener);
        registry.registerBeanDefinition("jdNewsPersister", newsPersister);

        // 指定依赖关系
        // 1.可以通过构造方法注入方式
        ConstructorArgumentValues argValues = new ConstructorArgumentValues();
        argValues.addIndexedArgumentValue(0, newsListener);
        argValues.addIndexedArgumentValue(1, newsPersister);
        newsProvider.setConstructorArgumentValues(argValues);

        // 2.可以通过setter方法注入
        MutablePropertyValues propertyValues = new MutablePropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("newsListener", newsListener));
        propertyValues.addPropertyValue(new PropertyValue("newsPersister", newsPersister));
        newsProvider.setPropertyValues(propertyValues);

        return (BeanFactory) registry;
    }

}
