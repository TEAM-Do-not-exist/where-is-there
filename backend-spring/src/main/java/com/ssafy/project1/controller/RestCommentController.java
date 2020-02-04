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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.project1.dto.CommentDTO;
import com.ssafy.project1.dto.MemberDTO;
import com.ssafy.project1.service.ICommentService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins= {"*"},maxAge=6000)
@RequestMapping("/api/comment")
@RestController

public class RestCommentController {
	
	@Autowired
	ICommentService comSer;
	
	@PostMapping("/insert")
	@ApiOperation(value="comment 등록 서비스 / 입력: cid, ccode, content / 결과 : 성공시 1")
	public ResponseEntity<Map> memInsert(@RequestBody CommentDTO dto){
		ResponseEntity<Map> resEntity=null;
		int state=0;
		try {
			int insert = comSer.insert(dto);
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
	
	@DeleteMapping("/delete/{ccode}/{cid}")
	@ApiOperation(value="comment 삭제 서비스")
	public ResponseEntity<Map> comDelete(@PathVariable("ccode")int ccode,@PathVariable("cid")String cid){
		ResponseEntity<Map> resEntity=null;
		int state=0;
		try {
			CommentDTO dto = new CommentDTO();
			dto.setCcode(ccode);
			dto.setCid(cid);
			int delete = comSer.delete(dto);
			Map<String,Object> msg = new HashMap<String, Object>();
			if(delete>0) {
				state = 1;
				msg.put("state", state);
				msg.put("regmsg", "삭제했습니다-해당하는 comment가 없습니다.");
				msg.put("resvalue",delete);
			}else {
				state = -1;
				msg.put("state", state);
				msg.put("regmsg", "삭제실패했습니다");
			}
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
		int state=0;
		try {
			CommentDTO dto = new CommentDTO();
			dto.setCid(cid);
			List<CommentDTO> selectMyList = comSer.selectMyList(dto);
//			System.out.println(selectMyList.toString());
			Map<String,Object> msg = new HashMap<String, Object>();
			if(selectMyList.size()>0) {
				state = 1;
				msg.put("state", state);
				msg.put("regmsg", "조회했습니다");
				msg.put("resvalue",selectMyList);
			}
			System.out.println(msg.toString());
			resEntity = new ResponseEntity<Map>(msg,HttpStatus.OK);
		}catch(RuntimeException e) {
			Map<String, Object> msg = new HashMap<String, Object>();
			msg.put("resmsg","조회실패");
			resEntity = new ResponseEntity<Map>(msg,HttpStatus.OK);
		}
		return resEntity;
	}
	
	@GetMapping("/selectPhotoList/{ccode}")
	@ApiOperation(value="comment 사진별 댓글 조회 서비스")
	public ResponseEntity<Map> comSelect(@PathVariable("ccode")int ccode){
		ResponseEntity<Map> resEntity=null;
		int state=0;
		try {
			CommentDTO dto = new CommentDTO();
			dto.setCcode(ccode);
			List<CommentDTO> selectMyList = comSer.selectPhotoList(dto);
			Map<String,Object> msg = new HashMap<String, Object>();
			if(selectMyList.size()>0) {
				state = 1;
				msg.put("state", state);
				msg.put("regmsg", "조회했습니다");
				msg.put("resvalue",selectMyList);
			}
			resEntity = new ResponseEntity<Map>(msg,HttpStatus.OK);
		}catch(RuntimeException e) {
			Map<String, Object> msg = new HashMap<String, Object>();
			msg.put("resmsg","조회실패");
			resEntity = new ResponseEntity<Map>(msg,HttpStatus.OK);
		}
		return resEntity;
	}
	
	@PutMapping("/update")
	@ApiOperation(value="댓글 수정 서비스")
	public ResponseEntity<Map<String,Object>> update(@RequestBody CommentDTO dto) {
		ResponseEntity<Map<String, Object>> resEntity = null;
		int state = 1;
		try {
			int update = comSer.update(dto);
			Map<String, Object> map = new HashMap();
			if(update>0) {
				state = 1;
				map.put("state", state);
				map.put("resmsg","수정성공");
				map.put("resvalue",update);
			}else {
				state = -1;
				map.put("state", state);
				map.put("regmsg", "수정실패했습니다-해당하는 comment가 없습니다.");
			}
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
}
