package com.su.pherycommon.util;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * NieSu 2018/7/1
 */

@Aspect
@Component
public class AesAspect
{

  @Pointcut("@annotation(com.su.pherycommon.util.AES)")
  public void aesAspect()
  {
  }

  @Before("aesAspect()")
  public void bafore(JoinPoint joinPoint)
  {
    System.out.println("前置通知....");
    System.out.println("class : "+joinPoint.getTarget().getClass());

    /**
     * 可以做权限控制
     */
    if("zhangsan".equals(joinPoint.getSignature().getName()))
    {
      throw new RuntimeException(" no auth");
    }
  }

  /**
   *
   * @param joinPoint
   * @param returnVal
   */
  @AfterReturning(value="aesAspect()",returning = "returnVal")
  public void afterReturing(JoinPoint joinPoint,Object returnVal)
  {
    System.out.println("后置处理...");
    /**
     * 下发短信或者邮件
     */
    System.out.println("return value : "+returnVal);
  }

  /**
   *
   * 相当于
   * try {
   *    //前置通知
   *    Object result = proceedingJoinPoint.proceed();
   *    //后置通知
   * }catch(Exception){
   *  //抛出通知
   * }finally{
   *  //最终通知
   * }
   * @param proceedingJoinPoint
   * @return
   * @throws Throwable 必须
   */
  @Around("aesAspect()")
  public Object around(ProceedingJoinPoint proceedingJoinPoint)throws Throwable
  {
    System.out.println("环绕通知...");
    /**
     * 日志,缓存,权限,性能监控,事务管理
     */
    Object proceed = proceedingJoinPoint.proceed();
    return proceed;
  }

  /**
   * 发生异常的时候记录\通知管理员
   * @param joinPoint
   * @param ex
   */
  @AfterThrowing(pointcut = "aesAspect()", throwing = "ex")
  public void afterThrowing(JoinPoint joinPoint,Throwable ex)
  {
    System.out.println(" exception calss is "+joinPoint.getTarget().getClass().getSimpleName());
    System.out.println(" exception method is "+joinPoint.getSignature().getName());
    System.out.println(" exception message is "+ex.getMessage());
  }


  /**
   * 不管目标方法是否发生异常，最终通知都会执行（类似于finally代码功能）
   * 应用场景：释放资源 （关闭文件、 关闭数据库连接、 网络连接、 释放内存对象 ）
   * @param joinPoint
   */
  @After("aesAspect()")
  public void after(JoinPoint joinPoint)
  {
    System.out.println("连接点信息 : "+joinPoint.toLongString());
  }
}
