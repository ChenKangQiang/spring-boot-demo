package edu.tongji.comm.spring;

import edu.tongji.comm.spring.AnnotationBeanDefinitionParser;
import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * @Description:
 * @Author: chenkangqiang
 * @Date: 2019-08-07
 */
public class CustomNameSpaceHandler extends NamespaceHandlerSupport {

    public CustomNameSpaceHandler() {
    }

    @Override
    public void init() {
        this.registerBeanDefinitionParser("apple", new AnnotationBeanDefinitionParser());
    }
}
