package edu.tongji.comm;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @Description:
 * @Author: chenkangqiang
 * @Date: 2019-08-11
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = {"classpath:spring/config/appcontext-core.xml"})
public class CustomXmlTagTest {

    @Autowired
    private Apple apple;

    @Test
    public void testAppleBean() {
        System.out.println(apple);
    }

}
