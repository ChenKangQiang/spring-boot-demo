package edu.tongji.comm.controller;

import edu.tongji.comm.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author chenkangqiang
 * @Data 2017/10/5
 */

@Controller
public class HelloController {

    @RequestMapping("/hello")
    public String welcome(ModelMap model) {
        model.put("time", new Date());
        model.put("message", "springBoot+FreeMarker");
        //返回的名称就是templates下的hello.ftl
        return "hello";
    }

    @RequestMapping("/list")
    public String user(Model model){
        List<User> list = new ArrayList<>();
        User u1 = new User(100L, "Tom", "17717097564");
        User u2 = new User(101L, "Jack", "18817367898");
        User u3 = new User(102L, "Mary", "17834889890");
        list.add(u1);
        list.add(u2);
        list.add(u3);
        model.addAttribute("userList", list);
        model.addAttribute("sysUser", "用户列表");
        return "list";
    }

    @RequestMapping("/reload")
    @ResponseBody
    public String reloadTest() {
        return "reload start";
    }

}
