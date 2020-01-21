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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.project1.dto.CommentDTO;
import com.ssafy.project1.dto.FavoriteDTO;
import com.ssafy.project1.service.IFavoriteService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins= {"*"},maxAge=6000)
@RequestMapping("/api/favorite")
@RestController

public class RestFavoriteController {
	@Autowired
	IFavoriteService favSer;
	
	@PostMapping("/insert")
	@ApiOperation(value="좋아요 등록 서비스")
	public ResponseEntity<Map> memInsert(@RequestBody FavoriteDTO dto){
		ResponseEntity<Map> resEntity=null;
		try {
			int insert = favSer.insert(dto);
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
	@DeleteMapping("/delete/{fcode}/{fid}")
	@ApiOperation(value="좋아요 취소 서비스")
	public ResponseEntity<Map> comDelete(@PathVariable("fcode")int fcode,@PathVariable("fid")String fid){
		ResponseEntity<Map> resEntity=null;
		try {
			FavoriteDTO dto = new FavoriteDTO();
			dto.setFcode(fcode);
			dto.setFid(fid);
			int delete = favSer.delete(dto);
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
	
	@GetMapping("/selectMyList/{fid}")
	@ApiOperation(value="나의 좋아요 조회 서비스")
	public ResponseEntity<Map> comSelect(@PathVariable("fid")String fid){
		ResponseEntity<Map> resEntity=null;
		try {
			FavoriteDTO dto = new FavoriteDTO();
			dto.setFid(fid);
			List<FavoriteDTO> selectMyList = favSer.selectMyList(dto);
			Map<String,Object> msg = new HashMap<String, Object>();
			msg.put("regmsg", "조회했습니다");
			msg.put("resvalue",selectMyList);
			resEntity = new ResponseEntity<Map>(msg,HttpStatus.OK);
		}catch(RuntimeException e) {
			Map<String, Object> msg = new HashMap<String, Object>();
			msg.put("resmsg","조회실패");
			resEntity = new ResponseEntity<Map>(msg,HttpStatus.OK);
		}
		return resEntity;
	}
	
	@GetMapping("/selectPhotoList/{fcode}")
	@ApiOperation(value=" 사진별 좋아요 개수 조회 서비스")
	public ResponseEntity<Map> comSelect(@PathVariable("fcode")int fcode){
		ResponseEntity<Map> resEntity=null;
		try {
			FavoriteDTO dto = new FavoriteDTO();
			dto.setFcode(fcode);
			List<FavoriteDTO> selectMyList = favSer.selectPhotoList(dto);
			Map<String,Object> msg = new HashMap<String, Object>();
			msg.put("regmsg", "조회했습니다");
			msg.put("resvalue",selectMyList);
			resEntity = new ResponseEntity<Map>(msg,HttpStatus.OK);
		}catch(RuntimeException e) {
			Map<String, Object> msg = new HashMap<String, Object>();
			msg.put("resmsg","조회실패");
			resEntity = new ResponseEntity<Map>(msg,HttpStatus.OK);
		}
		return resEntity;
	}

}
