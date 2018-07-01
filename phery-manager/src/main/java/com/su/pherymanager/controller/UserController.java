package com.su.pherymanager.controller;

import com.su.pherymanager.api.UserService;
import com.su.pherymanager.pojo.User;
import java.util.Date;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user")
public class UserController
{

  @Autowired
  private UserService userService;

  /***
   * 测试
   * @return
   */
  @PostMapping(value = "/add")
  public String addUser()
  {
    User user = new User();
    user.setUsername("sunwukong");
    user.setPhone("13670081376");
    user.setEmail("niesu@163.com");
    user.setCreated(new Date());
    user.setUpdated(user.getCreated());
    user.setPassword("123456");

    userService.addUser(user);
    return "list";
  }


}
