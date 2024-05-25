package com.amyurov.smartphoneecom.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class ControllerLogAspect {
    @Before("execution(* com.amyurov.smartphoneecom.controller..*(..))")
    public void logBeforeControllerMethods(JoinPoint joinPoint) {
        log.info("Executing method: {}", joinPoint.getSignature().toShortString());
    }

    @AfterReturning(pointcut = "execution(* com.amyurov.smartphoneecom.controller..*(..))")
    public void logAfterControllerMethods(JoinPoint joinPoint) {
        log.info("Method executed: {}", joinPoint.getSignature().toShortString());
    }
}
