package edu.tongji.comm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @Description:
 * @Author: chenkangqiang
 * @Date: 2019-07-21
 */
@SpringBootApplication(scanBasePackages = {"edu.tongji.comm.**"})
@EnableCaching
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
