package com.su.pherycommon.util;

import org.junit.Test;

//@AES( "zhangsan")
public class MyAnnoTest
{

  //@AES("zhangsan")
  String name;

  //@MyAnno({ "aaa","bbb","ccc"})
  @AES
  public void show()
  {
    System.out.println("show running..." + name);
  }

  @Test
  public void fn()
  {
    show();
  }
}