package com.ssafy.project1.util.naverLogIn;

import java.math.BigInteger;
import java.security.SecureRandom;

public class AccessToken {
	// 토큰 직접 만들기?
	public static String generateState() {
		SecureRandom random = new SecureRandom();
		return new BigInteger(130, random).toString();
	}
}
