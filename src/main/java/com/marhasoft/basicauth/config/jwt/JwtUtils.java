package com.marhasoft.basicauth.config.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Slf4j
@Component
public class JwtUtils {

    @Value("${jwt.secret-key}")
    private String secretKey;

    @Value("${jwt.time-expiration}")
    private String timeExpiration;

    //Codifica o token
    public String genetareAccessToken(String login) {
        return Jwts.builder()
                .setSubject(login)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + Long.parseLong(timeExpiration)))
                .signWith(getSignatureKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    //Validar token de acesso, decodifica o token
    public boolean isTokenValid(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(getSignatureKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            return true;
        } catch (Exception e) {
            log.error("Token inválido ".concat(e.getMessage()));
            return false;
        }
    }

    //Obtem o login do usuario de dentro do token
    public String getLoginFromToken(String token) {
        return getClaim(token, Claims::getSubject);
    }

    //Obter um valor especifico do claim
    //Eh usada um funcao do tipo generic porque no payload pode existir valor do tipo String, int...
    public <T> T getClaim(String token, Function<Claims, T> claimsTFunction) {
        Claims claims = extractAllClaims(token);
        return claimsTFunction.apply(claims);
    }

    //Obter os claims do token, o payload
    public Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignatureKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    //Obtem a chave secreta da assinatura do token
    private Key getSignatureKey() {
        byte[] keyBates = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBates);
    }
}
