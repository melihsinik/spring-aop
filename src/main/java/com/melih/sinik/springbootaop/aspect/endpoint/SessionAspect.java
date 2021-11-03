package com.melih.sinik.springbootaop.aspect.endpoint;

import com.melih.sinik.springbootaop.service.auth.TokenManager;
import com.melih.sinik.springbootaop.util.SessionContext;
import com.melih.sinik.springbootaop.util.SessionData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.core.annotation.Order;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * @author Melih ŞİNİK
 * @since 17.10.2021
 */
@Component
@Aspect
@Slf4j
@Order(0)
@RequiredArgsConstructor
public class SessionAspect {

    private final TokenManager tokenManager;

    @Before("endPoint()")
    public void before() throws ParseException {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        var jsonSessionData = (JSONObject) new JSONParser().parse(tokenManager.getSubject(authentication.getDetails().toString()));
        var sessionData = SessionData.builder()
                .userName(jsonSessionData.get("username").toString())
                .userId(jsonSessionData.get("id").toString())
                .build();
        SessionContext.setSessionData(sessionData);
        log.info("Session data: {}", sessionData.toString());
    }

    @Pointcut("execution(* com.melih.sinik.springbootaop.endpoint..*(..)) " +
            "&& !@annotation(com.melih.sinik.springbootaop.annotation.NoSession)")
    public void endPoint() {
    }

    @After("endPoint()")
    public void after() {
        log.info("Removing session data.");
        SessionContext.removeSessionData();
    }
}
