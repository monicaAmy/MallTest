package com.su.pheryweb.controller;

import com.su.pherycommon.util.AES;
import com.su.pherycommon.util.SystemControllerLog;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * NieSu 2018/6/30
 */

@RestController
@RequestMapping(value="/user")
public class UserController
{
  @PostMapping("/uu")
  @SystemControllerLog(description = "haha")
  @AES
  public String uu()
  {
    System.out.println("xixixi=================");
    return "hello";
  }


}
