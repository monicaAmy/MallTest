package com.su.pherycommon.util;

import java.lang.reflect.Method;
import javax.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 切点类
 *
 * @author tiangai
 * @version 1.0
 * @since 2014-08-05 Pm 20:35
 */
@Aspect
@Component
public class SystemLogAspect
{
  //注入Service用于把日志保存数据库 nodo service 层实现

  //本地异常日志记录对象
  private static final Logger logger = LoggerFactory.getLogger(SystemLogAspect.class);

  /**
   * 获取注解中对方法的描述信息 用于service层注解
   *
   * @param joinPoint 切点
   * @return 方法描述
   */
  public static String getServiceMthodDescription(JoinPoint joinPoint)
      throws Exception
  {
    String targetName = joinPoint.getTarget().getClass().getName();
    String methodName = joinPoint.getSignature().getName();
    Object[] arguments = joinPoint.getArgs();
    Class targetClass = Class.forName(targetName);
    Method[] methods = targetClass.getMethods();
    String description = "";
    for (Method method : methods)
    {
      if (method.getName().equals(methodName))
      {
        Class[] clazzs = method.getParameterTypes();
        if (clazzs.length == arguments.length)
        {
          description = method.getAnnotation(SystemServiceLog.class).description();
          break;
        }
      }
    }
    return description;
  }

  /**
   * 获取注解中对方法的描述信息 用于Controller层注解
   *
   * @param joinPoint 切点
   * @return 方法描述
   */
  public static String getControllerMethodDescription(JoinPoint joinPoint) throws Exception
  {
    String targetName = joinPoint.getTarget().getClass().getName();
    String methodName = joinPoint.getSignature().getName();
    Object[] arguments = joinPoint.getArgs();
    Class targetClass = Class.forName(targetName);
    Method[] methods = targetClass.getMethods();
    String description = "";
    for (Method method : methods)
    {
      if (method.getName().equals(methodName))
      {
        Class[] clazzs = method.getParameterTypes();
        if (clazzs.length == arguments.length)
        {
          description = method.getAnnotation(SystemControllerLog.class).description();
          break;
        }
      }
    }
    return description;
  }

  /**
   * Service层切点 使用到了我们定义的 SystemServiceLog 作为切点表达式。 而且我们可以看出此表达式基于 annotation。
   */
  @Pointcut("@annotation(com.su.pherycommon.util.SystemServiceLog)")
  public void serviceAspect()
  {
  }

  /**
   * Controller层切点 使用到了我们定义的 SystemControllerLog 作为切点表达式。 而且我们可以看出此表达式是基于 annotation 的。
   */
  @Pointcut("@annotation(com.su.pherycommon.util.SystemControllerLog)")
  public void controllerAspect()
  {
  }

  /**
   * 前置通知 用于拦截Controller层记录用户的操作
   *
   * @param joinPoint 连接点
   */
  @Before("controllerAspect()")
  public void doBefore(JoinPoint joinPoint)
  {
    HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
        .getRequestAttributes()).getRequest();
    //请求的IP
    String ip = request.getRemoteAddr();
    System.out.println(ip + "sdsdsdsdsd");
    try
    {
      //控制台输出
      System.out.println("=====前置通知开始=====");
      Object object = joinPoint.getTarget();
      System.out.println(
          "请求方法:" + (joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature()
              .getName() + "()"));
      System.out.println("方法描述:" + getControllerMethodDescription(joinPoint));
     // System.out.println("请求人:" + ScottSecurityUtil.getLoginName());
      System.out.println("请求IP:" + ip);
      //构造数据库日志对象

      //保存数据库

      System.out.println("=====前置通知结束=====");
    }
    catch (Exception e)
    {
      //记录本地异常日志
      logger.error("==前置通知异常==");
      logger.error("异常信息:{}", e.getMessage());
    }
  }

  /**
   * 异常通知 用于拦截service层记录异常日志
   */
  @AfterThrowing(pointcut = "serviceAspect()", throwing = "e")
  public void doAfterThrowing(JoinPoint joinPoint, Throwable e)
  {
    HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
        .getRequestAttributes()).getRequest();
    //获取请求ip
    String ip = request.getRemoteAddr();
    //获取用户请求方法的参数并组织成字符串
    String params = "";
    if (joinPoint.getArgs() != null && joinPoint.getArgs().length > 0)
    {
      for (int i = 0; i < joinPoint.getArgs().length; i++)
      {
        params += joinPoint.getArgs()[i] + ",";
      }
    }
    try
    {
      //控制台输出
      System.out.println("=====异常通知开始=====");
      System.out.println("异常代码:" + e.getClass().getName());
      System.out.println("异常信息:" + e.getMessage());
      System.out.println(
          "异常方法:" + (joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature()
              .getName() + "()"));
      System.out.println("方法描述:" + getServiceMthodDescription(joinPoint));
     // System.out.println("请求人:" + ScottSecurityUtil.getLoginName());
      System.out.println("请求IP:" + ip);
      System.out.println("请求参数:" + params);
      //构造数据库日志对象

      //保存数据库

      System.out.println("=====异常通知结束=====");
    }
    catch (Exception ex)
    {
      //记录本地异常日志
      logger.error("==异常通知异常==");
      logger.error("异常信息:{}", ex);
    }
    //录本地异常日志
    logger.error("异常方法:{}异常代码:{}异常信息:{}参数:{}",
        joinPoint.getTarget().getClass().getName() + joinPoint.getSignature().getName(),
        e.getClass().getName(), e.getMessage(), params);
  }

}
