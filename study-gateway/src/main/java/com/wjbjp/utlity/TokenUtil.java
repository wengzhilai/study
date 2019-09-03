package com.wjbjp.utlity;

import com.wjbjp.model.TokenUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jdk.nashorn.internal.ir.Expression;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.stereotype.Service;

import java.util.Date;


public class TokenUtil {

    //private static final long VALIDITY_TIME_MS = 10 * 24 * 60 * 60 * 1000;// 10 days Validity
    private static final long VALIDITY_TIME_MS =  2 * 60 * 60 * 1000; // 2 hours  validity
    private static final String AUTH_HEADER_NAME = "Authorization";

    private String secret="mrin";



    //Get User Info from the Token
    public TokenUser parseUserFromToken(String token){
        TokenUser user = new TokenUser();
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
            user.setName((String)claims.get("userName"));
        }catch(Exception e){
            return null;
        }
        return user;
    }



    public String createTokenForUser(SecurityProperties.User user) {
        return Jwts.builder()
                .setExpiration(new Date(System.currentTimeMillis() + VALIDITY_TIME_MS))
                .claim("userName", user.getName())
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

}