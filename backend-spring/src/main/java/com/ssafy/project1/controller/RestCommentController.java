package com.ssafy.project1.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
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
import com.ssafy.project1.service.ICommentService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins= {"*"},maxAge=6000)
@RequestMapping("/api/comment")
@RestController

public class RestCommentController {
	
	@Autowired
	ICommentService comSer;
	
	@PostMapping("/insert")
	@ApiOperation(value="comment 등록 서비스")
	public ResponseEntity<Map> memInsert(@RequestBody CommentDTO dto){
		ResponseEntity<Map> resEntity=null;
		try {
			int insert = comSer.insert(dto);
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
	
	@DeleteMapping("/delete/{ccode}")
	@ApiOperation(value="comment 삭제 서비스")
	public ResponseEntity<Map> comDelete(int ccode){
		ResponseEntity<Map> resEntity=null;
		try {
			
			int delete = comSer.delete(ccode);
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
	
	@GetMapping("/selectMyList/{cid}")
	@ApiOperation(value="comment 내가 쓴 댓글 조회 서비스")
	public ResponseEntity<Map> comSelect(@PathVariable("cid")String cid){
		ResponseEntity<Map> resEntity=null;
		try {
			CommentDTO dto = new CommentDTO();
			dto.setCid(cid);
			List<CommentDTO> selectMyList = comSer.selectMyList(dto);
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
