package com.ssafy.project1.authorization;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.ssafy.project1.exception.UnauthorizedException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtServiceImpl implements JwtService {
	private static final String SALT =  "secretKey";
	
	@Override
	public <T> String create(String key, T data, String subject) {
		// TODO Auto-generated method stub
		Date expireTime = new Date();
        expireTime.setTime(expireTime.getTime() + 1000 * 60 * 100);
 
		String jwt = Jwts.builder()
						.setHeaderParam("typ","JWT")
						.setHeaderParam("regDate", System.currentTimeMillis())
						.setExpiration(expireTime)
						.setSubject(subject)
						.claim(key, data)
						.signWith(SignatureAlgorithm.HS256, this.generateKey())
						.compact();
		return jwt;
	}
	private byte[] generateKey() {
		byte[] key = null;
		try {
			key = SALT.getBytes("UTF-8");
		}catch(UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return key;
	}
	@Override
	public Map<String, Object> get(String key) {
		// TODO Auto-generated method stub
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
		String jwt = request.getHeader("Authorization");
		Jws<Claims> claims = null;
		try {
			claims = Jwts.parser()
							.setSigningKey(SALT.getBytes("UTF-8"))
							.parseClaimsJws(jwt);
		}catch(Exception e) {
			throw new UnauthorizedException();
		}
		@SuppressWarnings("unchecked")
		Map<String, Object> values = (LinkedHashMap<String, Object>)claims.getBody().get(key);
		return values;
	}



	@Override
	public boolean isUsable(String jwt) {
		// TODO Auto-generated method stub
		try {
			Jws<Claims> claims = Jwts.parser()
									.setSigningKey(this.generateKey())
									.parseClaimsJws(jwt);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			throw new UnauthorizedException();
		}

	}
}
