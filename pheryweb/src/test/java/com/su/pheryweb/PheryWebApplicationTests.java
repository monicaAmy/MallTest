package com.su.pheryweb;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PheryWebApplicationTests
{
  private MockMvc mockMvc; // 模拟MVC对象，通过MockMvcBuilders.webAppContextSetup(this.wac).build()初始化。

  @Autowired
  private WebApplicationContext wac; // 注入WebApplicationContext

  @Before // 在测试开始前初始化工作
  public void setup()
  {
    this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
  }

  @Test
  public void contextLoads()
  {

  }

  @Test
  public void testPage() throws Exception
  {
    MvcResult result = mockMvc.perform(
        MockMvcRequestBuilders.post("/user/uu"))
        .andExpect(MockMvcResultMatchers.status().isOk())
//        .andExpect(MockMvcResultMatchers.content()
//            .contentType(MediaType.TEXT_PLAIN_VALUE))
        .andReturn();// 返回执行请求的结果
    System.out.println("=================" + result.getResponse().getContentAsString());
  }

}
