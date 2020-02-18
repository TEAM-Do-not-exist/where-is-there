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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.ssafy.project1.authorization.JwtService;
import com.ssafy.project1.dto.MemberDTO;
import com.ssafy.project1.service.MemberService;
import com.ssafy.project1.util.kakaoLogIn.HttpConnectionKakao;
import com.ssafy.project1.util.kakaoLogIn.KakaoMember;
import com.ssafy.project1.util.naverLogIn.AccessToken;
import com.ssafy.project1.util.naverLogIn.HttpConnectionNaver;
import com.ssafy.project1.util.naverLogIn.NaverMember;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RequestMapping("/api/external")
@RestController
public class RestExternalLogInController {

	@Autowired
	JwtService jwtService;
	@Autowired
	MemberService memSer;

	@GetMapping("/login_naver/{access_token}")
	@ApiOperation(value = "access_token을 받아서 네이버 로그인을 시도")
	public ResponseEntity<Map> loginNaver(@PathVariable("access_token") String access_token) {
		ResponseEntity<Map> resEntity = null;
		int state = 0;
		try {
			Map<String, Object> msg = new HashMap<String, Object>();
			NaverMember naverMem = HttpConnectionNaver.getNaverMember(access_token);
			MemberDTO dto = new MemberDTO(naverMem.getEmail(), naverMem.getNickName(), naverMem.getName());
			int loginNaver = memSer.loginNaver(dto);
			if (loginNaver == 2) {
				state = 1;
				String token = jwtService.create("member", memSer.selectOneId(dto), "user");
				msg.put("state", state);
				msg.put("token", token);
				msg.put("regmsg", "조회 성공");
				msg.put("resvalue", memSer.selectOneId(dto));

			} else if (loginNaver > 0) {
				state = -2;
				msg.put("state", state);
				msg.put("resvalue", loginNaver);
			} else {
				state = -3;
				msg.put("state", state);
				msg.put("regmsg", "다른 아이디가 존재");
			}
			resEntity = new ResponseEntity<Map>(msg, HttpStatus.OK);
		} catch (RuntimeException e) {
			Map<String, Object> msg = new HashMap<String, Object>();
			state = -1;
			msg.put("state", state);
			msg.put("resmsg", "조회실패");
			resEntity = new ResponseEntity<Map>(msg, HttpStatus.OK);
		}
		return resEntity;
	}

	@GetMapping("/login_kakao/{access_token}")
	@ApiOperation(value = "access_token을 받아서 카카오 로그인을 시도")
	public ResponseEntity<Map> loginKakao(@PathVariable("access_token") String access_token) {
		ResponseEntity<Map> resEntity = null;
		int state = 0;
		System.out.println("access_token "+access_token);
		try {
			Map<String, Object> msg = new HashMap<String, Object>();
			KakaoMember kakaoMember = HttpConnectionKakao.getUserInfo(access_token);
			System.out.println("http conn");
			MemberDTO dto = new MemberDTO(kakaoMember.getEmail(), kakaoMember.getNickName());
			System.out.println("get email");
			int loginKakao = memSer.loginKakao(dto);
			System.out.println("kkk");
			if (loginKakao == 3) {
				state = 1;
				String token = jwtService.create("member", memSer.selectOneId(dto), "user");
				msg.put("state", state);
				msg.put("token", token);
				msg.put("regmsg", "조회 성공");
				msg.put("resvalue", memSer.selectOneId(dto));

			} else if (loginKakao > 0) {
				state = -2;
				msg.put("state", state);
				msg.put("resvalue", loginKakao);
			} else {
				state = -3;
				msg.put("state", state);
				msg.put("regmsg", "다른 아이디가 존재");
			}
			resEntity = new ResponseEntity<Map>(msg, HttpStatus.OK);
		} catch (RuntimeException e) {
			Map<String, Object> msg = new HashMap<String, Object>();
			state = -1;
			msg.put("state", state);
			msg.put("resmsg", "조회실패");
			resEntity = new ResponseEntity<Map>(msg, HttpStatus.OK);
		}
		return resEntity;
	}

}
