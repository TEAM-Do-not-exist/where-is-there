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

import com.ssafy.project1.dto.PhotoCheckDTO;
import com.ssafy.project1.service.PhotoCheckService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RequestMapping("/api/photocheck")
@RestController
public class RestPhotoCheckController {

	@Autowired
	PhotoCheckService photoCheckSer;

	@PostMapping("/insert")
	@ApiOperation(value = "photo check 등록 서비스 / 입력:  purl / 출력 : 성공시 1")
	public ResponseEntity<Map> photoInsert(@RequestBody PhotoCheckDTO dto) {
		int state = 0;
		ResponseEntity<Map> resEntity = null;
		try {
			int insert = photoCheckSer.insert(dto);
			Map<String, Object> msg = new HashMap<String, Object>();
			state = 1;
			msg.put("state", state);
			msg.put("regmsg", "입력했습니다");
			msg.put("resvalue", insert);
			resEntity = new ResponseEntity<Map>(msg, HttpStatus.OK);
		} catch (RuntimeException e) {
			Map<String, Object> msg = new HashMap<String, Object>();
			state = -1;
			msg.put("state", state);
			msg.put("resmsg", "입력실패");
			resEntity = new ResponseEntity<Map>(msg, HttpStatus.OK);
		}
		return resEntity;
	}

	@DeleteMapping("/delete/{pcode}")
	@ApiOperation(value = "photo check 삭제 서비스")
	public ResponseEntity<Map> photoDelete(@PathVariable("pcode") int pcode) {
		ResponseEntity<Map> resEntity = null;
		int state = 0;
		try {
			int delete = photoCheckSer.delete(pcode);
			Map<String, Object> msg = new HashMap<String, Object>();
			if (delete > 0) {
				state = 1;
				msg.put("state", state);
				msg.put("regmsg", "삭제했습니다");
				msg.put("resvalue", delete);
			} else {
				state = -1;
				msg.put("state", state);
				msg.put("regmsg", "삭제실패했습니다");
				msg.put("resvalue", delete);
			}
			resEntity = new ResponseEntity<Map>(msg, HttpStatus.OK);
		} catch (RuntimeException e) {
			Map<String, Object> msg = new HashMap<String, Object>();
			state = -1;
			msg.put("state", state);
			msg.put("resmsg", "삭제실패");
			resEntity = new ResponseEntity<Map>(msg, HttpStatus.OK);
		}
		return resEntity;
	}

	@PutMapping("/update")
	@ApiOperation(value = "code에 맞는 사진의 정보 수정 서비스 / 입력: pcode, purl / 출력: 성공시 1")
	public ResponseEntity<Map<String, Object>> photoUpdate(@RequestBody PhotoCheckDTO dto) {
		ResponseEntity<Map<String, Object>> resEntity = null;
		int state = 0;
		try {
			int update = photoCheckSer.update(dto);
			Map<String, Object> map = new HashMap();
			if (update > 0) {
				state = 1;
				map.put("state", state);
				map.put("resmsg", "수정성공");
				map.put("resvalue", update);
			} else {
				state = -1;
				map.put("state", state);
				map.put("resmsg", "수정실패");
				map.put("resvalue", update);
			}
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		} catch (RuntimeException e) {
			Map<String, Object> map = new HashMap();
			state = -1;
			map.put("state", state);
			map.put("resmsg", "수정실패");
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		}
		return resEntity;
	}

	@GetMapping("/selectList")
	@ApiOperation(value = "photo check 전체 조회 서비스")
	public ResponseEntity<Map> photoSelect() {
		ResponseEntity<Map> resEntity = null;
		int state = 0;
		try {
			List<PhotoCheckDTO> selectList = photoCheckSer.selectList();
			Map<String, Object> msg = new HashMap<String, Object>();
			state = 1;
			msg.put("state", state);
			msg.put("regmsg", "조회했습니다");
			msg.put("resvalue", selectList);
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

	@GetMapping("/selectOne/{pcode}")
	@ApiOperation(value = "photo check 조회 서비스")
	public ResponseEntity<Map> photoSelectOneId(@PathVariable("pcode") int pcode) {
		ResponseEntity<Map> resEntity = null;
		int state = 0;
		try {
			PhotoCheckDTO dto = new PhotoCheckDTO();
			dto.setPcode(pcode);
			PhotoCheckDTO selectOneId = photoCheckSer.selectOne(dto);
			Map<String, Object> msg = new HashMap<String, Object>();
			state = 1;
			msg.put("state", state);
			msg.put("regmsg", "조회했습니다");
			msg.put("resvalue", selectOneId);
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
