package com.melih.sinik.springbootaop.dto.auth;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author Melih ŞİNİK
 * @since 17.10.2021
 */
@Getter
@Setter
@Builder
public class ValidationDTO {

    private String accessToken;
    private String userName;
    private String userId;
    private Date expiredTime;
}
