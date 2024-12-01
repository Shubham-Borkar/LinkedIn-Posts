package com.app.security;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@EnableWebSecurity 
@Configuration 
public class SecurityConfig {

	@Autowired
	private JWTRequestFilter jwtFilter;

	@Bean
	public SecurityFilterChain authorizeRequests(HttpSecurity http) throws Exception {
		System.out.println("Using Genral Filter Chain, Role Based Auth");
		 http
		 .securityMatcher("/products/**")
		 //.csrf(AbstractHttpConfigurer::disable)
		 .csrf(csrf -> csrf
	                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
	            )
		 .cors(cors->cors.disable())
	     .exceptionHandling(exception -> exception
	             .authenticationEntryPoint(null)
	             .accessDeniedHandler(null))
	     .authorizeHttpRequests(request ->
	             request
	                     .requestMatchers(
	                    		 "/users/signin",
	                    		 "/products/view", 
	                    		 "/products/post-view",
	     						"/users/register",
	     						"/swagger-ui/**",
	     						"/v*/api-docs/**"
	                     ).permitAll()
	                    .requestMatchers("/products/browse").hasAnyRole("CUSTOMER","ADMIN","USER")																			// authorization needed
	             		.requestMatchers("/products/purchase").hasRole("CUSTOMER")
	             		.requestMatchers("/products/add","/product/delete").hasRole("ADMIN") 
	             		.anyRequest().authenticated())
	     .sessionManagement(manager -> manager.sessionCreationPolicy(SessionCreationPolicy.ALWAYS))
	     .addFilterBefore(
	             jwtFilter, UsernamePasswordAuthenticationFilter.class);
	return http.build();
	}
	
	@Bean
	public SecurityFilterChain authorizeRequestsforMobile(HttpSecurity http) throws Exception {
		System.out.println("Using Filter Chain for Mobile, No Auth Required");
		 http
		 .securityMatcher("/mobile/**")
		 //.csrf(AbstractHttpConfigurer::disable)
		 .csrf(csrf -> csrf
	                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
	            )
		 .cors(cors->cors.disable())
	     .exceptionHandling(exception -> exception
	             .authenticationEntryPoint(null)
	             .accessDeniedHandler(null))
	     .authorizeHttpRequests(request ->
	             request
	                     .requestMatchers(
	     						"/**",
	     						"/swagger-ui/**",
	     						"/v*/api-docs/**"
	                     ).permitAll()
	             		.anyRequest().authenticated())
	     .sessionManagement(manager -> manager.sessionCreationPolicy(SessionCreationPolicy.ALWAYS))
	     .addFilterBefore(
	             jwtFilter, UsernamePasswordAuthenticationFilter.class);
	return http.build();
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}
	
	
}



//normal single matcher for all

//http
//.exceptionHandling()
//.authenticationEntryPoint(
//		(request, resp, exc) -> 
//		resp.sendError(HttpStatus.UNAUTHORIZED.value(), "Not yet authenticated"))
//		.and()
//.cors().and()
//.csrf().disable()
//.authorizeRequests() 
//.antMatchers(   "/products/view", 
//				"/users/signin", 
//				"/users/register",
//				"/swagger*/**", 
//				"/v*/api-docs/**"
//				).permitAll() 
//.antMatchers("/products/browse").hasAnyRole("CUSTOMER","ADMIN","USER")																			// authorization needed
//.antMatchers("/products/purchase").hasRole("CUSTOMER")
//.antMatchers("/products/add").hasRole("ADMIN") 
//.anyRequest().authenticated()
//.and().sessionManagement() 
//.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//.and().addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
//return http.build();







//package com.app.security;
//
//import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//@EnableWebSecurity 
//@Configuration 
//public class SecurityConfig {
//
//	@Autowired
//	private JWTRequestFilter jwtFilter;
//
//	@Bean
//	public SecurityFilterChain authorizeRequests(HttpSecurity http) throws Exception {
//		System.out.println("Using Filter Chain for product");
//		 http
//		 .securityMatcher("/products/**")
//		 .csrf(AbstractHttpConfigurer::disable)
//		 .cors(cors->cors.disable())
//	     .exceptionHandling(exception -> exception
//	             .authenticationEntryPoint(null)
//	             .accessDeniedHandler(null))
//	     .authorizeHttpRequests(request ->
//	             request
//	                     .requestMatchers(
//	                    		 "/users/signin",
//	                    		 "/products/view", 
//	     						"/users/register",
//	     						"/swagger-ui/**",
//	     						"/v*/api-docs/**"
//	                     ).permitAll()
//	                    .requestMatchers("/products/browse").hasAnyRole("CUSTOMER","ADMIN","USER")																			// authorization needed
//	             		.requestMatchers("/products/purchase").hasRole("CUSTOMER")
//	             		.requestMatchers("/products/add","/product/delete").hasRole("ADMIN") 
//	             		.anyRequest().authenticated())
//	     .sessionManagement(manager -> manager.sessionCreationPolicy(STATELESS))
//	     .addFilterBefore(
//	             jwtFilter, UsernamePasswordAuthenticationFilter.class);
//		      //.authenticationProvider(authenticationProvider)
//	return http.build();
//	}
//	
//	@Bean
//	public SecurityFilterChain authorizeRequestsforMobile(HttpSecurity http) throws Exception {
//		System.out.println("Using Filter Chain for Mobile");
//		 http
//		 .securityMatcher("/mobile/**")
//		 .csrf(AbstractHttpConfigurer::disable)
//		 .cors(cors->cors.disable())
//	     .exceptionHandling(exception -> exception
//	             .authenticationEntryPoint(null)
//	             .accessDeniedHandler(null))
//	     .authorizeHttpRequests(request ->
//	             request
//	                     .requestMatchers(
//	     						"/**",
//	     						"/swagger-ui/**",
//	     						"/v*/api-docs/**"
//	                     ).permitAll()
//	             		.anyRequest().authenticated())
//	     .sessionManagement(manager -> manager.sessionCreationPolicy(STATELESS))
//	     .addFilterBefore(
//	             jwtFilter, UsernamePasswordAuthenticationFilter.class);
//		 //     .authenticationProvider(authenticationProvider)
//	return http.build();
//	}
//
//
//	
//	
//	@Bean
//	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
//		return config.getAuthenticationManager();
//	}
//	
//	
//}
