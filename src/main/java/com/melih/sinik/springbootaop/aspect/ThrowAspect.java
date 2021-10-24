package com.melih.sinik.springbootaop.aspect;

import com.melih.sinik.springbootaop.util.SessionContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author Melih ŞİNİK
 * @since 19.10.2021
 */
@Aspect
@Component
@Slf4j
@RequiredArgsConstructor
public class ThrowAspect {

    @AfterThrowing(pointcut = "throwingPointCut()", throwing = "ex")
    public void throwLogging(JoinPoint joinPoint, Exception ex) {
        var signature = joinPoint.getSignature();
        var args = joinPoint.getArgs();

        log.info("bean: {}, errMassage: {}, args: {} , session: {}",
                signature, ex.getMessage(), args, SessionContext.getSessionData().toString());
    }

    @Pointcut("execution(public * com.melih..*.*(..))")
    public void throwingPointCut() {
    }
}
