package com.melih.sinik.springbootaop.service.auth;

import com.melih.sinik.springbootaop.dto.auth.ValidationDTO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

/**
 * @author Melih ŞİNİK
 * @since 17.10.2021
 */
@Service
public class TokenManager {

    private static final int VALIDITY = 300000;
    Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public ValidationDTO generateToken(String username) {
        var now = System.currentTimeMillis();
        final var expiredTime = new Date(now + VALIDITY);
        final var id = UUID.randomUUID().toString();

        var map = new HashMap<String, String>();
        map.put("username", username);
        map.put("id", id);

        var sessionInfo = new JSONObject();
        sessionInfo.putAll(map);


        final var token = Jwts.builder()
                .setSubject(sessionInfo.toJSONString())
                .setIssuer("system")
                .setIssuedAt(new Date(now))
                .setExpiration(expiredTime)
                .signWith(key)
                .compact();

        return ValidationDTO.builder()
                .accessToken(token)
                .userName(username)
                .userId(id)
                .expiredTime(expiredTime)
                .build();
    }

    public boolean tokenValidate(String token) {
        return getSubject(token) != null && isExpired(token);
    }

    public String getSubject(String token) {
        Claims claims = getClaims(token);
        return claims.getSubject();
    }

    public boolean isExpired(String token) {
        Claims claims = getClaims(token);
        return claims.getExpiration().after(new Date(System.currentTimeMillis()));
    }

    private Claims getClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
    }
}
