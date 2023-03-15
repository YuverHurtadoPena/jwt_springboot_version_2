package com.seguridad_spb_v2.seguridad.jwt;

import java.security.SignatureException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.seguridad_spb_v2.seguridad.entity.UsuarioPrincipal;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * Encargada de proveer el token y validarlo que este bien formado
 * @author yuver
 *
 */
@Component
public class JwtProvider {
	 private final static Logger logger = LoggerFactory.getLogger(JwtProvider.class);

	    @Value("${jwt.secret}")
	    private String secret;

	    @Value("${jwt.expiration}")
	    private int expiration;

	    public String generateToken(Authentication authentication){
	        UsuarioPrincipal usuarioPrincipal = (UsuarioPrincipal) authentication.getPrincipal();
	        return Jwts.builder().setSubject(usuarioPrincipal.getUsername())
	                .setIssuedAt(new Date())
	                .setExpiration(new Date(new Date().getTime() + expiration * 1000))
	                .signWith(SignatureAlgorithm.HS512, secret)
	                .compact();
	    }

	    public String getNombreUsuarioFromToken(String token){
	        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
	    }

	    public boolean validateToken(String token){
	        try {
	            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
	            return true;
	        }catch (MalformedJwtException e){
	            logger.error("token mal formado");
	        }catch (UnsupportedJwtException e){
	            logger.error("token no soportado");
	        }catch (ExpiredJwtException e){
	            logger.error("token expirado");
	        }catch (IllegalArgumentException e){
	            logger.error("token vacío");
	        }
	        return false;
	    }

}
