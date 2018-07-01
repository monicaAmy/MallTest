package com.su.pherymanager.pojo;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;

/**
 * NieSu 2018/7/1
 * 用户类 (TB_USER)
 *
 */

@Data
public class User
{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="ID")
  private Long id;

  /**
   * 用户名
   */
  @Column(name="USERNAME")
  private String username;

  /**
   * 用户密码
   */
  @Column(name ="PASSWORD")
  private String password;

  /**
   *
   */
  private String phone;

  /**
   * 邮件地址,存储需要加密
   */
  @Column(name="EMAIL")
  private String email;

  /**
   *
   */
  private Date created;

  /**
   *
   */
  private Date updated;


}
