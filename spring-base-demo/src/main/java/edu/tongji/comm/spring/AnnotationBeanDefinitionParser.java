package edu.tongji.comm.spring;

import edu.tongji.comm.Apple;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;

/**
 * @Description:
 * @Author: chenkangqiang
 * @Date: 2019-08-07
 */
public class AnnotationBeanDefinitionParser implements BeanDefinitionParser {

    @Override
    public BeanDefinition parse(Element element, ParserContext parserContext) {
        String id = element.getAttribute("id");
        String name = element.getAttribute("name");

        //生成BeanDefinition
        RootBeanDefinition beanDefinition = new RootBeanDefinition();
        beanDefinition.setBeanClass(Apple.class);
        beanDefinition.getPropertyValues().addPropertyValue("name", name);

        //注册BeanDefinition
        parserContext.getRegistry().registerBeanDefinition(id, beanDefinition);

        return null;
    }
}
