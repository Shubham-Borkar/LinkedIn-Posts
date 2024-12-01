package com.app.security;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity 
@Configuration 
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true)  //for roleAllowed                                 //@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true)
public class SecurityConfig {

	@Autowired
	private JWTRequestFilter jwtFilter;

	@Bean
	public SecurityFilterChain authorizeRequests(HttpSecurity http) throws Exception {
		System.out.println("Using Genral Filter Chain, Role Based Auth");
		 http
		 .securityMatcher("/auth/**")
		 .csrf(AbstractHttpConfigurer::disable)
		 .cors(cors->cors.disable())
	     .exceptionHandling(exception -> exception
	             .authenticationEntryPoint(null)
	             .accessDeniedHandler(null))
	     .authorizeHttpRequests(request ->
	             request
	                     .requestMatchers(
	                    		 "/auth/users/signin",
	                    		 "/auth/users/register",
	                    		 "/auth/users/message",
	                    		 "/auth/ml/pre-authorize/1",
	                    		 "/auth/users/generateQrCode",
	                    		 "/auth/users/generate-qr",
	                    		 "/auth/products/view", 
	     						"/auth/users/register",
	     						"/auth/ml/private-data",
	     						"/swagger-ui/**",
	     						"/spel/**",
	     						"/v*/api-docs/**"
	                     ).permitAll()
	                    .requestMatchers("/auth/products/browse").hasAnyRole("CUSTOMER","ADMIN","USER")																			// authorization needed
	             		.requestMatchers("/auth/products/purchase").hasRole("CUSTOMER")
	             		.requestMatchers("/auth/products/useronly").hasRole("USER")
	             		.requestMatchers("/auth/products/add","/auth/product/delete").hasRole("ADMIN") 
	             		.anyRequest().authenticated())
	     .sessionManagement(manager -> manager.sessionCreationPolicy(STATELESS))
	     .addFilterBefore(
	             jwtFilter, UsernamePasswordAuthenticationFilter.class);
	     //.addFilterAfter(new OTPFilter("/auth/users/signin") , UsernamePasswordAuthenticationFilter.class);
	return http.build();
	}
	
	/**
	 * @param http
	 * @return
	 * @throws Exception
	 */
	/**
	 * @param http
	 * @return
	 * @throws Exception
	 */
	@Bean
	public SecurityFilterChain authorizeRequestsforMobile(HttpSecurity http) throws Exception {
		System.out.println("Using Filter Chain for Mobile, No Auth Required");
		 http
		 .securityMatcher("/mobile/**")
		 .csrf(AbstractHttpConfigurer::disable)
		 .cors(cors->cors.disable())
	     .exceptionHandling(exception -> exception
	             .authenticationEntryPoint(null)
	             .accessDeniedHandler(null))
	     .authorizeHttpRequests(request ->
	             request
	                     .requestMatchers(
	     						"/**",
//	                    		 "/mobile/one",
//	                    		 "/mobile/two",
	     						 "/users/message",
	     						 "/users/generateQrCode",
	     						"/swagger-ui/**",
	     						"/v*/api-docs/**"
	                     ).permitAll()
	             		.anyRequest().authenticated())
	     .sessionManagement(manager -> manager.sessionCreationPolicy(STATELESS))
	     .addFilterBefore(
	             jwtFilter, UsernamePasswordAuthenticationFilter.class);
	  //  .addFilterAfter(new OTPFilter("/auth/mobile/**") , UsernamePasswordAuthenticationFilter.class);
	return http.build();
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}
	
	@Bean
	public ModelMapper mapper() {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		return modelMapper;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
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
