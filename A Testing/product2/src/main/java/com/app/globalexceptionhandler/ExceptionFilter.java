package com.app.globalexceptionhandler;

import java.io.IOException;

//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;

@Component
public class ExceptionFilter implements Filter {
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// Initialization code, if needed
	}

	@Override
	public void destroy() {
		// Cleanup code, if needed
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		String apiEndpoint = httpRequest.getRequestURI();
		long startTime = System.currentTimeMillis();

		try {
			chain.doFilter(request, response);
		} catch (Exception ex) {
			// Authentication authentication =
			// SecurityContextHolder.getContext().getAuthentication();
			// String username = authentication != null ? authentication.getName() : "anonymous";
			String errorMessage = ex.getMessage();
			System.err.println("\n____________ Retreiving Error info using Custom Filter  ____________\n"+
					           "Exception in API Endpoint  -> " + apiEndpoint);
			System.err.println("Exception message  -> " + errorMessage);

//			 Re-throw so it can be handled by other mechanisms (e.g., global exception handler)
			throw ex;
		} finally {
			long endTime = System.currentTimeMillis();
			//System.err.println("Total time for Execution in milliseconds -> " + 
			//				   (endTime - startTime) + " for endpoint ->" + apiEndpoint+"\n");
		}
	}

	
}
