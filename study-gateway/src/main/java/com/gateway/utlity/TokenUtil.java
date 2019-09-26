package com.gateway.utlity;

import com.gateway.model.TokenUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class TokenUtil {

    //private static final long VALIDITY_TIME_MS = 10 * 24 * 60 * 60 * 1000;// 10 days Validity
    private static final long VALIDITY_TIME_MS = 360 * 24 * 60 * 60 * 1000; // 一年

    private static String secret="mrin";



    //Get User Info from the Token
    public static TokenUser parseUserFromToken(String token){

        TokenUser user = new TokenUser();
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
            user.setName((String)claims.get("userName"));
            user.setUserId((int)claims.get("userId"));
        }catch(Exception e){
            return null;
        }
        return user;
    }



    public static String createTokenForUser(TokenUser user) {
        return Jwts.builder()
                .setExpiration(new Date(System.currentTimeMillis() + VALIDITY_TIME_MS))
                .claim("userName", user.getName())
                .claim("userId", user.getUserId())
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

}