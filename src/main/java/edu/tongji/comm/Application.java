package edu.tongji.comm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by chen on 2017/6/30.
 *
 * 基于JAVA的配置类一般位于root package里面
 *
 */

// @SpringBootApplication等价于同时使用@Configuration、@ComponentScan、@EnableAutoConfiguration

//@Configuration
//@ComponentScan
//@EnableAutoConfiguration
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

}
