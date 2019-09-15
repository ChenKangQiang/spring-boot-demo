package edu.tongji.comm.initializing;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.support.BeanDefinitionReader;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: Spring Bean 初始化相关方法测试Bean
 * @Author: chenkangqiang
 * @Date: 2019/8/25
 */
public class InitializingTestBean<T> implements InitializingBean, BeanFactoryAware {

    private String propertyOne;

    private String propertyTwo;

    private Class<T> type;

    private T obj;

    private List<T> objList;

    private BeanFactory beanFactory;

    public InitializingTestBean(String propertyOne, String propertyTwo, Class<T> type) {
        this.propertyOne = propertyOne;
        this.propertyTwo = propertyTwo;
        this.type = type;
    }

    public static void main(String[] args) {
        //Resource resource = new ClassPathResource("spring/config/appcontext-init.xml");
        //BeanFactory beanFactory = new DefaultListableBeanFactory();
        //BeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader((BeanDefinitionRegistry) beanFactory);
        //beanDefinitionReader.loadBeanDefinitions(resource);
        //InitializingTestBeanPostProcessor initializingTestBeanPostProcessor = (InitializingTestBeanPostProcessor) beanFactory.getBean("initializingTestBeanPostProcessor");

        ApplicationContext beanFactory = new ClassPathXmlApplicationContext("spring/config/appcontext-init.xml");
        //XmlBeanFactory废弃
        //BeanFactory beanFactory = new XmlBeanFactory(resource);
        InitializingTestBean testBean = beanFactory.getBean("initializingTestBean", InitializingTestBean.class);
        //InitializingTestBeanPostProcessor initializingTestBeanPostProcessor = (InitializingTestBeanPostProcessor) beanFactory.getBean("initializingTestBeanPostProcessor");
        System.out.println("propertyOne: " + testBean.propertyOne);
        System.out.println("propertyTwo: " + testBean.propertyTwo);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("执行Bean的afterPropertiesSet方法");
        objList = new ArrayList<>();
        objList.add(type.newInstance());
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("执行Bean的aware方法");
        this.beanFactory = beanFactory;
    }

    public void init() throws IllegalAccessException, InstantiationException {
        System.out.println("执行Bean的自定义方法init");
        obj = type.newInstance();
    }


}
