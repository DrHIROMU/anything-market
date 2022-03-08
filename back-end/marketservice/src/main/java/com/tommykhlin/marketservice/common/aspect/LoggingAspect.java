package com.tommykhlin.marketservice.common.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.logging.Logger;

@Component
@Aspect
public class LoggingAspect {
    @Pointcut("execution(* com.tommykhlin.marketservice.order.*.*(..))")
    public void log() {}

    @Around("log()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        log(proceedingJoinPoint, "around before calling proceed()");
        Object result = proceedingJoinPoint.proceed();
        log(proceedingJoinPoint, "around after calling proceed()");
        return result;
    }

    @Before("log()")
    public void before(JoinPoint joinPoint) {
        log(joinPoint, "before");
    }

    @After("log()")
    public void after(JoinPoint joinPoint) {
        log(joinPoint, "after");
    }

    @AfterReturning(pointcut = "log()", returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result) {
        log(joinPoint, String.format("afterReturning %s", result));
    }

    @AfterThrowing(pointcut="log()", throwing="throwable")
    public void afterThrowing(JoinPoint joinPoint, Throwable throwable) {
        log(joinPoint, String.format("afterThrowing %s", throwable));
    }

    private void log(JoinPoint joinPoint, String adviceType) {
        Object target = joinPoint.getTarget();
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        Logger.getLogger(target.getClass().getName()).info(String.format("%s %s.%s(%s)",
                adviceType, target.getClass().getName(), methodName, Arrays.toString(args)));
    }
}