package edu.tongji.comm.spring;

import edu.tongji.comm.spring.annotation.Filter;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.type.filter.AnnotationTypeFilter;

/**
 * @Description:
 * @Author: chenkangqiang
 * @Date: 2019-08-14
 */
public class ClassPathFilterScanner extends ClassPathBeanDefinitionScanner {

    public ClassPathFilterScanner(BeanDefinitionRegistry registry) {
        super(registry);
        addIncludeFilter(new AnnotationTypeFilter(Filter.class));
    }

    @Override
    public int scan(String... basePackages) {
        return super.scan(basePackages);
    }
}
