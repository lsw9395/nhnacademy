package com.nhnacademy.edu.springframework.project.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ElapsedTimeAspect {
    private static final Logger log = LoggerFactory.getLogger(ElapsedTimeAspect.class);
    @Around("execution( * * (..))")
    public Object doTimeCheck(ProceedingJoinPoint pjp) throws Throwable{
        long begin = 0, end = 0;
        Object retVal;
        try{
            begin = System.currentTimeMillis();
            retVal = pjp.proceed();
            end = System.currentTimeMillis();
        } catch (Throwable e){
            e.printStackTrace();
            throw e;
        } finally {
            long result = end - begin;
            log.debug("{}.{}:{}ms",pjp.getTarget().getClass().getSimpleName(),pjp.getSignature().getName(), result);
        }
        return retVal;
    }
}
