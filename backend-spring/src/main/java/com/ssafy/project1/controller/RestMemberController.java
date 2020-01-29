package com.ssafy.project1.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.project1.authorization.JwtService;
import com.ssafy.project1.dto.MemberDTO;
import com.ssafy.project1.service.MemberService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins= {"*"},maxAge=6000)
@RequestMapping("/api/member")
@RestController
public class RestMemberController {
	
	@Autowired
	MemberService memSer;
	
	@Autowired
	JwtService jwtService;
	
	@PostMapping("/insert")
	@ApiOperation(value="member 등록 서비스 / 입력: id,pw,name,email,phone / 결과: 성공시 1")
	public ResponseEntity<Map> memInsert(@RequestBody MemberDTO dto){
		ResponseEntity<Map> resEntity=null;
		int state = 0;
		try {
			int insert = memSer.insert(dto);
			Map<String,Object> msg = new HashMap<String, Object>();
			state = 1;
			msg.put("state", state);
			msg.put("regmsg", "입력했습니다");
			msg.put("resvalue",insert);
			resEntity = new ResponseEntity<Map>(msg,HttpStatus.OK);
		}catch(RuntimeException e) {
			Map<String, Object> msg = new HashMap<String, Object>();
			state = -1;
			msg.put("state", state);
			msg.put("resmsg","입력실패");
			resEntity = new ResponseEntity<Map>(msg,HttpStatus.OK);
		}
		return resEntity;
	}
	@DeleteMapping("/delete/{id}")
	@ApiOperation(value="member id를 입력하여 삭제하는 서비스")
	public ResponseEntity<Map> memDelete(@PathVariable("id")String id){
		ResponseEntity<Map> resEntity=null;
		int state = 0;
		try {
			int delete = memSer.delete(id);
			Map<String,Object> msg = new HashMap<String, Object>();
			if(delete>0) {
				state = 1;
				msg.put("state", state);
				msg.put("regmsg", "삭제했습니다");
				msg.put("resvalue",delete);
			}else {
				state = -1;
				msg.put("state", state);
				msg.put("regmsg", "삭제실패했습니다");
				msg.put("resvalue",delete);
			}
			resEntity = new ResponseEntity<Map>(msg,HttpStatus.OK);
		}catch(RuntimeException e) {
			Map<String, Object> msg = new HashMap<String, Object>();
			state = -1;
			msg.put("state", state);
			msg.put("resmsg","삭제실패");
			resEntity = new ResponseEntity<Map>(msg,HttpStatus.OK);
		}
		return resEntity;
	}

	@PutMapping("/update")
	@ApiOperation(value="id를 받아 회원정보 수정 서비스 / 입력: id, pw, name, email, phone / 결과 : 성공시 1")
	public ResponseEntity<Map<String,Object>> update(@RequestBody MemberDTO dto) {
		ResponseEntity<Map<String, Object>> resEntity = null;
		int state=0;
		try {
			int update = memSer.update(dto);
			Map<String, Object> map = new HashMap();
			if(update>0) {
				state = 1;
				map.put("state", state);
				map.put("resmsg","수정성공");
				map.put("resvalue",update);
			}else {
				state = -1;
				map.put("state", state);
				map.put("resmsg","수정실패");
				map.put("resvalue",update);
			}
			resEntity = new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
		}catch(RuntimeException e){
			Map<String, Object> map = new HashMap();
			state = -1;
			map.put("state", state);
			map.put("resmsg", "수정실패");
			resEntity = new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
		}
		return resEntity;
	}
	
	@GetMapping("/selectList")
	@ApiOperation(value="member 전체 조회 서비스")
	public ResponseEntity<Map> memSelect(){
		ResponseEntity<Map> resEntity=null;
		int state=0;
		try {
			List<MemberDTO> selectList = memSer.selectList();
			Map<String,Object> msg = new HashMap<String, Object>();
			state = 1;
			msg.put("state", state);
			msg.put("regmsg", "조회했습니다");
			msg.put("resvalue",selectList);
			resEntity = new ResponseEntity<Map>(msg,HttpStatus.OK);
		}catch(RuntimeException e) {
			Map<String, Object> msg = new HashMap<String, Object>();
			state = -1;
			msg.put("state", state);
			msg.put("resmsg","조회실패");
			resEntity = new ResponseEntity<Map>(msg,HttpStatus.OK);
		}
		return resEntity;
	}
	@GetMapping("/selectOneId/{id}")
	@ApiOperation(value="member id를 입력하여 회원정보 조회")
	public ResponseEntity<Map> memSelectOneId(@PathVariable("id")String id){
		ResponseEntity<Map> resEntity=null;
		int state=0;
		try {
			MemberDTO dto = new MemberDTO();
			dto.setId(id);
			MemberDTO selectOneId = memSer.selectOneId(dto);
			Map<String,Object> msg = new HashMap<String, Object>();
			state = 1;
			msg.put("state", state);
			msg.put("regmsg", "조회 성공");
			msg.put("resvalue",selectOneId);
			resEntity = new ResponseEntity<Map>(msg,HttpStatus.OK);
		}catch(RuntimeException e) {
			Map<String, Object> msg = new HashMap<String, Object>();
			state = -1;
			msg.put("state", state);
			msg.put("resmsg","조회실패");
			resEntity = new ResponseEntity<Map>(msg,HttpStatus.OK);
		}
		return resEntity;
	}
	@GetMapping("/selectOneIdPw/{id}/{pw}")
	@ApiOperation(value="member id와 pw를 입력하여 그에 맞는 멤버가 있으면 1 없으면 0을 반환하는 서비스")
	public ResponseEntity<Map> memSelectOneId(@PathVariable("id")String id,@PathVariable("pw")String pw){
		ResponseEntity<Map> resEntity=null;
		int state = 0;
		try {
			MemberDTO dto = new MemberDTO();
			dto.setId(id);
			dto.setPw(pw);
			int selectOneIdPw = memSer.selectOneIdPw(dto);
			Map<String,Object> msg = new HashMap<String, Object>();
			if(selectOneIdPw==1) {
				String token = jwtService.create("member", dto, "user");
				msg.put("token",token);
			}
			state = 1;
			msg.put("state", state);
			msg.put("regmsg", "조회했습니다");
			msg.put("resvalue",selectOneIdPw);
			resEntity = new ResponseEntity<Map>(msg,HttpStatus.OK);
		}catch(RuntimeException e) {
			Map<String, Object> msg = new HashMap<String, Object>();
			state = -1;
			msg.put("state", state);
			msg.put("resmsg","조회실패");
			resEntity = new ResponseEntity<Map>(msg,HttpStatus.OK);
		}
		return resEntity;
	}
	@GetMapping("/duplicateCheckId/{id}")
	@ApiOperation(value="member id 중복 체크 아이디 생성가능하면 1을 반환 불가능하면 -1을 반환")
	public ResponseEntity<Map> duplicateCheckId(@PathVariable("id")String id){
		ResponseEntity<Map> resEntity=null;
		int state=0;
		try {
			MemberDTO dto = new MemberDTO();
			dto.setId(id);
			int duplicateCheckId = memSer.duplicateCheckId(dto);
			Map<String,Object> msg = new HashMap<String, Object>();
			state = 1;
			msg.put("state", state);
			msg.put("regmsg", "조회 성공");
			msg.put("resvalue",duplicateCheckId);
			resEntity = new ResponseEntity<Map>(msg,HttpStatus.OK);
		}catch(RuntimeException e) {
			Map<String, Object> msg = new HashMap<String, Object>();
			state = -1;
			msg.put("state", state);
			msg.put("resmsg","조회실패");
			resEntity = new ResponseEntity<Map>(msg,HttpStatus.OK);
		}
		return resEntity;
	}
}
