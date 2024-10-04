package com.riwi.project.utils.enu.helpers;

import com.riwi.project.domain.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JWTService {


    private final String  SECRET_KEY = "ZXN0YSBlcyBsYSBjb250cmFzZcOxYSBzdXBlciBzZWd1cmEgcXVlIGxlIHRlbmdvIGEgbGEgYXBsaWNhdGlvbiByaXdpIHByb2plY3QgcGFyYSBxdWUgbmFkaWUgbGEgYWRpdmluZQ==";
    //esta es la contraseña super segura que le tengo a la aplication riwi project para que nadie la adivine

    //Obtengo la SECRET KEY y cifro la secretKey
    public SecretKey getKey(){
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    //crear jwt
    public String generateJWT(Map<String,Object> claims, User user){
        return Jwts.builder()
                .claims(claims)
                .subject(user.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+100*60*60*24))
                .signWith(getKey())
                .compact();
    }

    public String getToken(User user){
        Map<String,Object> claims = new HashMap<>();
        claims.put("username",user.getUsername());
        claims.put("role", user.getRole().name());

        return generateJWT(claims,user);
    }

}
