package com.su.pherymanager.mapper;

import com.su.pherymanager.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestBody;

@Mapper
public interface UserMapper
{

  void addUser(@Param("user") User user);
}
