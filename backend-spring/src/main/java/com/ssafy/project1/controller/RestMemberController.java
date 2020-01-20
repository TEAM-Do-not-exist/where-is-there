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

import com.ssafy.project1.dto.MemberDTO;
import com.ssafy.project1.service.MemberService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins= {"*"},maxAge=6000)
@RequestMapping("/api/member")
@RestController
public class RestMemberController {
	
	@Autowired
	MemberService memSer;
	
	@PostMapping("/insert")
	@ApiOperation(value="member 등록 서비스")
	public ResponseEntity<Map> memInsert(@RequestBody MemberDTO dto){
		ResponseEntity<Map> resEntity=null;
		try {
			int insert = memSer.insert(dto);
			Map<String,Object> msg = new HashMap<String, Object>();
			msg.put("regmsg", "입력했습니다");
			msg.put("resvalue",insert);
			resEntity = new ResponseEntity<Map>(msg,HttpStatus.OK);
		}catch(RuntimeException e) {
			Map<String, Object> msg = new HashMap<String, Object>();
			msg.put("resmsg","입력실패");
			resEntity = new ResponseEntity<Map>(msg,HttpStatus.OK);
		}
		return resEntity;
	}
	@DeleteMapping("/delete/{id}")
	@ApiOperation(value="member 삭제 서비스")
	public ResponseEntity<Map> memDelete(@PathVariable("id")String id){
		ResponseEntity<Map> resEntity=null;
		try {
			int delete = memSer.delete(id);
			Map<String,Object> msg = new HashMap<String, Object>();
			msg.put("regmsg", "삭제했습니다");
			msg.put("resvalue",delete);
			resEntity = new ResponseEntity<Map>(msg,HttpStatus.OK);
		}catch(RuntimeException e) {
			Map<String, Object> msg = new HashMap<String, Object>();
			msg.put("resmsg","삭제실패");
			resEntity = new ResponseEntity<Map>(msg,HttpStatus.OK);
		}
		return resEntity;
	}

	@PutMapping("/update")
	@ApiOperation(value="id를 받아 board 수정 서비스")
	public ResponseEntity<Map<String,Object>> update(@RequestBody MemberDTO dto) {
		ResponseEntity<Map<String, Object>> resEntity = null;

		try {
			int update = memSer.update(dto);
			Map<String, Object> map = new HashMap();
			map.put("resmsg","수정성공");
			map.put("resvalue",update);
			resEntity = new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
		}catch(RuntimeException e){
			Map<String, Object> map = new HashMap();
			map.put("resmsg", "수정실패");
			resEntity = new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
		}
		return resEntity;
	}
	
	@GetMapping("/selectList")
	@ApiOperation(value="member 전체 조회 서비스")
	public ResponseEntity<Map> memSelect(){
		ResponseEntity<Map> resEntity=null;
		try {
			List<MemberDTO> selectList = memSer.selectList();
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
	@GetMapping("/selectOneId/{id}")
	@ApiOperation(value="member 조회 서비스")
	public ResponseEntity<Map> memSelectOneId(@PathVariable("id")String id){
		ResponseEntity<Map> resEntity=null;
		try {
			MemberDTO dto = new MemberDTO();
			dto.setId(id);
			MemberDTO selectOneId = memSer.selectOneId(dto);
			Map<String,Object> msg = new HashMap<String, Object>();
			msg.put("regmsg", "조회했습니다");
			msg.put("resvalue",selectOneId);
			resEntity = new ResponseEntity<Map>(msg,HttpStatus.OK);
		}catch(RuntimeException e) {
			Map<String, Object> msg = new HashMap<String, Object>();
			msg.put("resmsg","조회실패");
			resEntity = new ResponseEntity<Map>(msg,HttpStatus.OK);
		}
		return resEntity;
	}
	@GetMapping("/selectOneIdPw/{id}/{pw}")
	@ApiOperation(value="member 조회 서비스")
	public ResponseEntity<Map> memSelectOneId(@PathVariable("id")String id,@PathVariable("pw")String pw){
		ResponseEntity<Map> resEntity=null;
		try {
			MemberDTO dto = new MemberDTO();
			dto.setId(id);
			dto.setPw(pw);
			int selectOneIdPw = memSer.selectOneIdPw(dto);
			Map<String,Object> msg = new HashMap<String, Object>();
			msg.put("regmsg", "조회했습니다");
			msg.put("resvalue",selectOneIdPw);
			resEntity = new ResponseEntity<Map>(msg,HttpStatus.OK);
		}catch(RuntimeException e) {
			Map<String, Object> msg = new HashMap<String, Object>();
			msg.put("resmsg","조회실패");
			resEntity = new ResponseEntity<Map>(msg,HttpStatus.OK);
		}
		return resEntity;
	}
}
