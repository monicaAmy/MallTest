package com.su.pherymanager.service;

import com.su.pherymanager.api.UserService;
import com.su.pherymanager.pojo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService
{

  Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

  @Override
  public void addUser(User user)
  {
    logger.debug("控制层中接收到的数据：" + user);

  }
}
