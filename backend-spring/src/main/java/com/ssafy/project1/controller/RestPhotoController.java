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

import com.ssafy.project1.dto.PhotoDTO;
import com.ssafy.project1.service.PhotoService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins= {"*"},maxAge=6000)
@RequestMapping("/api/photo")
@RestController
public class RestPhotoController {
	
	@Autowired
	PhotoService photoSer;
	
	@PostMapping("/insert")
	@ApiOperation(value="photo 등록 서비스")
	public ResponseEntity<Map> photoInsert(@RequestBody PhotoDTO dto){
		ResponseEntity<Map> resEntity=null;
		try {
			int insert = photoSer.insert(dto);
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
	@DeleteMapping("/delete/{pcode}")
	@ApiOperation(value="photo 삭제 서비스")
	public ResponseEntity<Map> photoDelete(@PathVariable("pcode")int pcode){
		ResponseEntity<Map> resEntity=null;
		try {
			int delete = photoSer.delete(pcode);
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
	@ApiOperation(value="code에 맞는 사진의 정보 수정 서비스")
	public ResponseEntity<Map<String,Object>> photoUpdate(@RequestBody PhotoDTO dto) {
		ResponseEntity<Map<String, Object>> resEntity = null;

		try {
			int update = photoSer.update(dto);
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
	@ApiOperation(value="photo 전체 조회 서비스")
	public ResponseEntity<Map> photoSelect(){
		ResponseEntity<Map> resEntity=null;
		try {
			List<PhotoDTO> selectList = photoSer.selectList();
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
	@GetMapping("/selectListFavorite")
	@ApiOperation(value="photo 전체 조회 서비스 좋아요 많은 순서로")
	public ResponseEntity<Map> photoSelectFavorite(){
		ResponseEntity<Map> resEntity=null;
		try {
			List<PhotoDTO> selectList_favorite = photoSer.selectList_favorite();
			Map<String,Object> msg = new HashMap<String, Object>();
			msg.put("regmsg", "조회했습니다");
			msg.put("resvalue",selectList_favorite);
			resEntity = new ResponseEntity<Map>(msg,HttpStatus.OK);
		}catch(RuntimeException e) {
			Map<String, Object> msg = new HashMap<String, Object>();
			msg.put("resmsg","조회실패");
			resEntity = new ResponseEntity<Map>(msg,HttpStatus.OK);
		}
		return resEntity;
	}
	@GetMapping("/selectOne/{pcode}")
	@ApiOperation(value="photo 조회 서비스")
	public ResponseEntity<Map> photoSelectOneId(@PathVariable("pcode")int pcode){
		ResponseEntity<Map> resEntity=null;
		try {
			PhotoDTO dto = new PhotoDTO();
			dto.setPcode(pcode);
			PhotoDTO selectOneId = photoSer.selectOne(dto);
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
}
