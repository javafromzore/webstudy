package org.test.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@org.aspectj.lang.annotation.Aspect
@Component
public class Aspect {
    //定义切入点可以使用@Pointcut配合字符串，或者自定义注解来定义
    @Pointcut("execution(public * org.test.car.controller.CarController.*(..))")
    public void aspectPointcut() {
    }

    //插入方法：通知
//    @Before(value = "aspectPointcut()")
    @Before(value = "@annotation(org.test.aop.AopAnnotation)")
    public void before() {
        System.out.println("@Before标注的方法在被增强方法执行之前执行");
    }

//    @After(value = "aspectPointcut()")
    @After(value = "@annotation(org.test.aop.AopAnnotation)")
    public void after() {
        System.out.println("@After标注的方法在被增强的方法执行之后执行，即使中途抛出异常仍会执行");
    }

    //JoinPoint可以获取被增强方法的一些信息，而ProceedingJoinPoint是JoinPoint的子接口，可以调用业务方法
    @Around(value = "@annotation(org.test.aop.AopAnnotation)")
    public void around(ProceedingJoinPoint point) throws Throwable {
        System.out.println("@Around是@After和@Before的叠加，但是抛出异常后不会接着执行。需要自行调用业务方法");
        point.proceed();
        System.out.println("再次调用@Around注释的方法");
    }

    @AfterReturning(value = "@annotation(org.test.aop.AopAnnotation)")
    public void afterReturning() {
        System.out.println("@AfterReturning标注的方法最后执行，中途抛出异常不会执行");
    }

    //异常通知生效了，但是异常仍然会被抛出
    //target只能匹配类，execution可以匹配方法签名，@annotation可以匹配指定注解对应的方法，args
    @AfterReturning(value = "@annotation(org.test.aop.AopAnnotation)")
    public void afterThrowing() {
        System.out.println("@AfterThrowing执行了");
    }
}