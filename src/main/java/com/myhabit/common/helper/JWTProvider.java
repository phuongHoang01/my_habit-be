package com.myhabit.common.helper;

import java.time.LocalDate;
import java.util.Base64;
import java.util.Date;

import org.springframework.security.core.Authentication;

import com.myhabit.entities.UserPrincipal;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JWTProvider {

	
	public static String generateToken(Authentication authentication) {
		final String SECRET_TOKEN = "NTP";
		
		final long jWT_EXPIRATION = 864000000L;
		
		UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
		
		String encodedString = Base64.getEncoder().encodeToString(SECRET_TOKEN.getBytes());
		
		Date now = new Date();
		
		Date expiryDate = new Date(now.getTime() + jWT_EXPIRATION);
		
		String JWTToken = Jwts.builder()
							.setSubject(userPrincipal.getUsername())
							.setIssuedAt(now)
							.setExpiration(expiryDate)
							.signWith(SignatureAlgorithm.HS512, encodedString)
							.compact();
		return JWTToken;
	}
}
