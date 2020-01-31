package com.ssafy.project1.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.ssafy.project1.authorization.JwtService;
import com.ssafy.project1.util.naverLogIn.AccessToken;
import com.ssafy.project1.util.naverLogIn.HttpConnectionNaver;
import com.ssafy.project1.util.naverLogIn.NaverMember;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins= {"*"},maxAge=6000)
@RequestMapping("/api/external")
@RestController
public class RestExternalLogInController {

	@Autowired
	JwtService jwtService;
	
	@GetMapping("/request_naver")
	@ApiOperation(value="네이버 로그인 서비스 로그인 후에 토큰을 반환한다. 반환값: state, token, info(name, nickName, email) (redirect를 써서 swagger에선 안보임) //나중에 콜백주소 바꿔야할 수도 있음")
	public RedirectView naverRequest() {
		String clientId = "nsrxqIjEGhhBf9jdPBFD";
		String mydomain = "http%3A%2F%2F192.168.100.52%3A8090%2Fapi%2Fexternal%2Fcallback_naver";
		String requestUrlNaver = "https://nid.naver.com/oauth2.0/authorize?client_id=" + clientId + "&response_type=code&redirect_uri="+ mydomain + "&state="; 
		String generateState = AccessToken.generateState();
		return new RedirectView(requestUrlNaver+generateState);
	}
	
	@GetMapping("/callback_naver")
	@ApiOperation(value="콜백 - 직접 사용하지는 않음")
	public ResponseEntity<Map> callback(@RequestParam String state, @RequestParam String code, HttpServletRequest request) throws UnsupportedEncodingException {

		String accessUrl = HttpConnectionNaver.makeApiUrl(state, code);
		NaverMember naverMem = HttpConnectionNaver.getNaverMem(accessUrl);
		String token = jwtService.create("member", naverMem, "user");
		ResponseEntity<Map> resEntity=null;
		int states = 0;
		try {
			Map<String,Object> msg = new HashMap<String, Object>();
			states = 1;
			msg.put("state", states);
			msg.put("token",token);
			msg.put("info",naverMem);
			msg.put("regmsg", "조회했습니다");
			resEntity = new ResponseEntity<Map>(msg,HttpStatus.OK);
		}catch(RuntimeException e) {
			Map<String, Object> msg = new HashMap<String, Object>();
			states = -1;
			msg.put("state", states);
			msg.put("resmsg","조회실패");
			resEntity = new ResponseEntity<Map>(msg,HttpStatus.OK);
		}
		return resEntity;
	}

}
