package com.example.moviecollectionbackend.config.jwt;

import com.example.moviecollectionbackend.model.entity.UserEntity;
import com.example.moviecollectionbackend.model.user.AppUserDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtTokenUtil.class);
    private static final long EXPIRE_DURATION = 12 * 60 * 60 * 1000; //12 hours
//    private static final long EXPIRE_DURATION = 5 * 1000;

    @Value("${jwt.secret}")
    private String SECRET_KEY;

    public String generateAccessToken(AppUserDetails user) {

        return Jwts.builder()
            .setSubject(String.format("%s,%s" , user.getId() , user.getUsername()))
            .setIssuer("MC")
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + EXPIRE_DURATION))
            .signWith(SignatureAlgorithm.HS512 , SECRET_KEY)
            .compact();
    }

    public boolean validateAccessToken(String token) {
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException ex) {
            LOGGER.error("JWT expired");
            //ToDo throw exception
        } catch (IllegalArgumentException ex) {
            LOGGER.error("Token is null, empty or only whitespace");
        } catch (MalformedJwtException ex) {
            LOGGER.error("JWT is invalid");
        } catch (UnsupportedJwtException ex) {
            LOGGER.error("JWT is not supported");
        } catch (SignatureException ex) {
            LOGGER.error("Signature validation failed");
        }

        return false;
    }

    public String getSubject(String token) {
        return parseClaims(token).getSubject();
    }

    private Claims parseClaims(String token) {
        return Jwts.parser()
            .setSigningKey(SECRET_KEY)
            .parseClaimsJws(token)
            .getBody();
    }
}
