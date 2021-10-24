package com.melih.sinik.springbootaop.aspect;

import com.melih.sinik.springbootaop.util.SessionContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @author Melih ŞİNİK
 * @since 20.10.2021
 */
@Component
@Aspect
@Slf4j
@RequiredArgsConstructor
public class LoggingAspect {

    @Around("loggingPointCut()")
    public Object logging(ProceedingJoinPoint point) {
        long start = System.currentTimeMillis();
        Object result = null;

        try {
            result = point.proceed();
        } catch (Throwable throwable) {
            log.error("bean: {}, errMassage: {}, args: {} , session: {}, working time: {} ms",
                    point.getSignature().getName(), throwable.getMessage(), Arrays.toString(point.getArgs()),
                    SessionContext.getSessionData().toString(), System.currentTimeMillis() - start);
        }

        log.info("bean: {}, args: {}, result: {}, working time: {} ms",
                point.getSignature().getName(),
                point.getArgs(),
                result,
                System.currentTimeMillis() - start
        );

        return result;
    }

    @Pointcut("@annotation(com.melih.sinik.springbootaop.annotation.Loggable)")
    public void loggingPointCut() {
    }
}
