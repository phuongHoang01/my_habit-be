package com.myhabit.filter;

import java.io.IOException;
import java.util.Base64;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.myhabit.entities.UserPrincipal;
import com.myhabit.service.impl.UserDetailsServiceImpl;

import io.jsonwebtoken.Jwts;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {
	
	@Autowired
	UserDetailsService userDetailsService;

	public JWTAuthorizationFilter(AuthenticationManager authenticationManager
			,UserDetailsService userDetailsService) {
		super(authenticationManager);
		// TODO Auto-generated constructor stub
		this.userDetailsService = userDetailsService;
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		final String SECRET_TOKEN = "NTP";
		
		String encodedString = Base64.getEncoder().encodeToString(SECRET_TOKEN.getBytes());
		
		String requestToken = request.getHeader("Authorization");
		
		if(requestToken != null && requestToken.startsWith("Bearer ")) {
			String token = requestToken.replace("Bearer ", "");
			String userName = Jwts.parser()
								.setSigningKey(encodedString)
								.parseClaimsJws(token)
								.getBody()
								.getSubject();
			UserPrincipal userPrincipal = (UserPrincipal) userDetailsService.loadUserByUsername(userName);
			
			UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userPrincipal, null, userPrincipal.getAuthorities());
			
			SecurityContextHolder.getContext().setAuthentication(authenticationToken);
		}
		
		chain.doFilter(request, response);
	}

}
