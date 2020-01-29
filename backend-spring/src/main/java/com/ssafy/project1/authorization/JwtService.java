package com.ssafy.project1.authorization;

import java.util.Map;

public interface JwtService {
	<T> String create(String key, T data, String subject);
	Map<String, Object> get(String key);
	int geteMemberId();
	boolean isUsable(String jwt);
}
