package com.melih.sinik.springbootaop.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * @author Melih ŞİNİK
 * @since 20.10.2021
 */
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface Loggable {
}
