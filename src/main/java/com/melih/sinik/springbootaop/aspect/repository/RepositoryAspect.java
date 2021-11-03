package com.melih.sinik.springbootaop.aspect.repository;

import com.melih.sinik.springbootaop.service.auth.UserAuthoritiesService;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @author Melih ŞİNİK
 * @since 13.10.2021
 */
@Aspect
@Component
@RequiredArgsConstructor
@Slf4j
@Order(2)
public class RepositoryAspect {

    private final UserAuthoritiesService userAuthoritiesService;

    @Before("execution(* com.melih.sinik.springbootaop.repository.*.delete(..))")
    public void beforeSave() {
        var isAuthorised = userAuthoritiesService.validateUserAuthorised();
        if (!isAuthorised) {
            throw new AuthorizationServiceException("Bu işlemi yapmaya yetkiniz yoktur!");
        }
    }

    @AfterReturning(value = "execution(* com.melih.sinik.springbootaop.repository.*.getById(..))", returning = "retVal")
    public Object afterGetById(Object retVal) throws NotFoundException {
        if (Objects.isNull(retVal)) {
            throw new NotFoundException("Kayıt Bulunamadı!");
        }

        return retVal;
    }
}
