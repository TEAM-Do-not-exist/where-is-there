package com.ssafy.project1.authorization;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.ssafy.project1.exception.UnauthorizedException;

@Component
public class JwtInterceptor implements HandlerInterceptor {
	
	private static final String HEADER_AUTH = "Authorization";
	
	@Autowired
	JwtService jwtService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		final String token = request.getHeader(HEADER_AUTH);
//		System.out.println(token);
		if(token !=null && jwtService.isUsable(token)) {
			return true;
		}
		else {
			throw new UnauthorizedException();
		}
	}
}