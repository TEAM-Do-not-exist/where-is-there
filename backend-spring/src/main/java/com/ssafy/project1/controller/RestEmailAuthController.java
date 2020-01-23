package com.ssafy.project1.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.project1.dto.EmailAuthDTO;
import com.ssafy.project1.service.EmailAuthService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins= {"*"},maxAge=6000)
@RequestMapping("/api/emailauth")
@RestController
public class RestEmailAuthController {
	
	@Autowired
	EmailAuthService emailAuthSer;
	
	@PostMapping("/request")
	@ApiOperation(value="이메일 인증 요청 서비스")
	public ResponseEntity<Map> request_auth(@RequestBody EmailAuthDTO dto){
		ResponseEntity<Map> resEntity=null;
		try {
			int auth_request = emailAuthSer.auth_request(dto);
			if(auth_request>0) {
				Map<String,Object> msg = new HashMap<String, Object>();
				msg.put("regmsg", "입력했습니다");
				msg.put("resvalue",auth_request);
				resEntity = new ResponseEntity<Map>(msg,HttpStatus.OK);
			}else {
				Map<String, Object> msg = new HashMap<String, Object>();
				msg.put("resmsg","입력실패");
				resEntity = new ResponseEntity<Map>(msg,HttpStatus.OK);				
			}
			
		}catch(RuntimeException e) {
			Map<String, Object> msg = new HashMap<String, Object>();
			msg.put("resmsg","입력실패");
			resEntity = new ResponseEntity<Map>(msg,HttpStatus.OK);
		}
		return resEntity;
	}
	@PostMapping("/request_re")
	@ApiOperation(value="이메일 인증 재요청 서비스")
	public ResponseEntity<Map> request_auth_re(@RequestBody EmailAuthDTO dto){
		ResponseEntity<Map> resEntity=null;
		try {
			int auth_request_re = emailAuthSer.auth_request_re(dto);
			if(auth_request_re>0) {
				Map<String,Object> msg = new HashMap<String, Object>();
				msg.put("regmsg", "입력했습니다");
				msg.put("resvalue",auth_request_re);
				resEntity = new ResponseEntity<Map>(msg,HttpStatus.OK);
			}else {
				Map<String, Object> msg = new HashMap<String, Object>();
				msg.put("resmsg","입력실패");
				resEntity = new ResponseEntity<Map>(msg,HttpStatus.OK);				
			}
			
		}catch(RuntimeException e) {
			Map<String, Object> msg = new HashMap<String, Object>();
			msg.put("resmsg","입력실패");
			resEntity = new ResponseEntity<Map>(msg,HttpStatus.OK);
		}
		return resEntity;
	}
	
	@PutMapping("/auth_check")
	@ApiOperation(value="인증 확인 서비스")
	public ResponseEntity<Map> auth_check(@RequestBody EmailAuthDTO dto){
		ResponseEntity<Map> resEntity=null;
		try {
			int auth_check = emailAuthSer.auth_check(dto);
			if(auth_check>0) {
				Map<String,Object> msg = new HashMap<String, Object>();
				msg.put("regmsg", "입력했습니다");
				msg.put("resvalue",auth_check);
				resEntity = new ResponseEntity<Map>(msg,HttpStatus.OK);
			}else {
				Map<String, Object> msg = new HashMap<String, Object>();
				msg.put("resmsg","입력실패");
				resEntity = new ResponseEntity<Map>(msg,HttpStatus.OK);				
			}
			
		}catch(RuntimeException e) {
			Map<String, Object> msg = new HashMap<String, Object>();
			msg.put("resmsg","입력실패");
			resEntity = new ResponseEntity<Map>(msg,HttpStatus.OK);
		}
		return resEntity;
	}
	@GetMapping("/selectList")
	@ApiOperation(value="인증 전체 조회 서비스")
	public ResponseEntity<Map> memSelect(){
		ResponseEntity<Map> resEntity=null;
		try {
			List<EmailAuthDTO> selectList = emailAuthSer.selectList();
			Map<String,Object> msg = new HashMap<String, Object>();
			msg.put("regmsg", "조회했습니다");
			msg.put("resvalue",selectList);
			resEntity = new ResponseEntity<Map>(msg,HttpStatus.OK);
		}catch(RuntimeException e) {
			Map<String, Object> msg = new HashMap<String, Object>();
			msg.put("resmsg","조회실패");
			resEntity = new ResponseEntity<Map>(msg,HttpStatus.OK);
		}
		return resEntity;
	}
}
