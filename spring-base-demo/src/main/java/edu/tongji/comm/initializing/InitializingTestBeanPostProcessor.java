package edu.tongji.comm.initializing;

import org.springframework.aop.support.AopUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * @Description:
 * @Author: chenkangqiang
 * @Date: 2019/8/25
 */
public class InitializingTestBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (AopUtils.getTargetClass(bean) == InitializingTestBean.class) {
            System.out.println("对Bean调用postProcessBeforeInitialization方法");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (AopUtils.getTargetClass(bean) == InitializingTestBean.class) {
            System.out.println("对Bean调用postProcessAfterInitialization方法");
        }
        return bean;
    }
}
