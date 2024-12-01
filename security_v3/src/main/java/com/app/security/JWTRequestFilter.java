package com.app.security;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.app.jwt_utils.JwtUtils;

import io.jsonwebtoken.Claims;

@Component
public class JWTRequestFilter extends OncePerRequestFilter {
	@Autowired
	private JwtUtils utils;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String authHeadr = request.getHeader("Authorization");
		if (authHeadr != null && authHeadr.startsWith("Bearer")) {
			System.out.println("got bearer token-->" + authHeadr);
			String token = authHeadr.substring(7);
			Claims claims = utils.validateJwtToken(token);
			String email = utils.getUserNameFromJwtToken(claims);
			List<GrantedAuthority> authorities = utils.getAuthoritiesFromClaims(claims);

			UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, null,
					authorities);
			SecurityContextHolder.getContext().setAuthentication(authentication);

		} else {
			System.out.println("req did not contain any bearer token");
		}
		filterChain.doFilter(request, response);

	}

}
