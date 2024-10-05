package com.riwi.project.utils.enu.helpers;

import com.riwi.project.domain.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

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

    //Metodo para obtener todos los claims
    public Claims getALlClaims(String token){
        return Jwts
                .parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();

    }

    //Metodo para obtener todas las claims del token
    public <T> T getClaim(String token ,Function<Claims,T> claimsResolver){
        //obtener todos los claims
        final Claims claims = getALlClaims(token);
        return claimsResolver.apply(claims);
    }

    public String getUsernameFromToken(String token){
        return getClaim(token,Claims::getSubject);
    }

    public Date getDateExpirationFromToken(String token){
        return getClaim(token,Claims::getExpiration);
    }

    public Boolean isTokenExpired(String token){
        return getDateExpirationFromToken(token).before(new Date());
    }

    public Boolean isTokenValid(String token, UserDetails userDetails){
        String username = getUsernameFromToken(token);
        if(username.equals(userDetails.getUsername()) && !isTokenExpired(token)){
            return true;
        }else {
            return false;
        }
    }

}
