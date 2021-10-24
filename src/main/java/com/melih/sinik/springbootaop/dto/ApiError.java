package com.melih.sinik.springbootaop.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Melih ŞİNİK
 * @since 22.10.2021
 */
@RequiredArgsConstructor
@Getter
@Setter
@ToString
public class ApiError {
    private final String code;
    private final String message;
}
