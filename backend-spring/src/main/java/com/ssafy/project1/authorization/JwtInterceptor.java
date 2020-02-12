package com.ssafy.project1.authorization;

import java.util.Collection;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.HandlerInterceptor;

import com.ssafy.project1.exception.UnauthorizedException;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@Component
public class JwtInterceptor implements HandlerInterceptor {

	private static final String HEADER_AUTH = "Authorization";

	@Autowired
	JwtService jwtService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		// content-type
		final String token = request.getHeader(HEADER_AUTH);
		if (request.getHeader("access-control-request-headers") != null) {
			return true;
		}
		if (token != null && jwtService.isUsable(token)) {
			return true;
		} else {
			throw new UnauthorizedException();
		}
	}
}
