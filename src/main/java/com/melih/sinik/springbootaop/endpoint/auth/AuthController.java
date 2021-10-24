package com.melih.sinik.springbootaop.endpoint.auth;

import com.melih.sinik.springbootaop.annotation.NoSession;
import com.melih.sinik.springbootaop.dto.auth.ValidationDTO;
import com.melih.sinik.springbootaop.service.auth.TokenManager;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Melih ŞİNİK
 * @since 17.10.2021
 */
@RestController
@RequestMapping(value = "/auth")
@RequiredArgsConstructor
public class AuthController {

    private final TokenManager tokenManager;

    @NoSession
    @GetMapping(value = "/{userName}/token", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ValidationDTO> getToken(@PathVariable("userName") String username) {
        return new ResponseEntity<>(tokenManager.generateToken(username), HttpStatus.OK);
    }
}
