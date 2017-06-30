package edu.tongji.comm.controller;

import edu.tongji.comm.entity.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by chen on 2017/6/30.
 */

@RestController
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/{id}")
    public User view(@PathVariable("id") Long id) {
        User user = new User();
        user.setId(id);
        user.setName("zhang");
        return user;
    }
}
