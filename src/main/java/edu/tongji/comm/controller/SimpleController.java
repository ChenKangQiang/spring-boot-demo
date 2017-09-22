package edu.tongji.comm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author chenkangqiang
 * @date 2017/9/21
 */

@Controller
public class SimpleController {

    @RequestMapping("/home")
    @ResponseBody
    String home() {
        return "Hello World!";
    }

}
