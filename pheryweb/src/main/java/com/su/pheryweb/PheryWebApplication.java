package com.su.pheryweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

@EnableEurekaClient
@SpringBootApplication
@ComponentScan("com.su.*")
public class PheryWebApplication
{

  public static void main(String[] args)
  {
    SpringApplication.run(PheryWebApplication.class, args);
  }
}
