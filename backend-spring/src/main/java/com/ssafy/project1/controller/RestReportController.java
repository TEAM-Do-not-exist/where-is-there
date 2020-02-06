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
import com.ssafy.project1.dto.MemberDTO;
import com.ssafy.project1.dto.ReportDTO;
import com.ssafy.project1.service.IReportService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins= {"*"},maxAge=6000)
@RequestMapping("/api/report")
@RestController

public class RestReportController {
	@Autowired
	IReportService reSer;
	
	@PostMapping("/insert")
	@ApiOperation(value="report 등록 서비스 / 입력: rid, rcode, rreason/ 결과 : 성공시 1")
	public ResponseEntity<Map> reInsert(@RequestBody ReportDTO dto){
		ResponseEntity<Map> resEntity=null;
		int state=0;
		try {
			int insert = reSer.insert(dto);
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
	
	@DeleteMapping("/delete/{rnum}")
	@ApiOperation(value="report 삭제 서비스 / 입력 : rnum")
	public ResponseEntity<Map> comDelete(@PathVariable("rnum")int rnum){
		ResponseEntity<Map> resEntity=null;
		int state=0;
		try {
			ReportDTO dto = new ReportDTO();
			dto.setRnum(rnum);
			int delete = reSer.delete(dto);
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
	
	@GetMapping("/selectReportList")
	@ApiOperation(value="report 전체 조회 서비스 ")
	public ResponseEntity<Map> reSelect(){
		ResponseEntity<Map> resEntity=null;
		int state=0;
		ReportDTO dto = new ReportDTO();
		try {
			List<ReportDTO> selectReportList = reSer.selectReportList(dto);
			System.out.println(selectReportList.toString());
			Map<String,Object> msg = new HashMap<String, Object>();
				state = 1;
				msg.put("state", state);
				msg.put("regmsg", "조회했습니다");
				msg.put("resvalue",selectReportList);
				resEntity = new ResponseEntity<Map>(msg,HttpStatus.OK);
		}catch(RuntimeException e) {
			state = -1;
			Map<String, Object> msg = new HashMap<String, Object>();
			msg.put("resmsg","조회실패");
			resEntity = new ResponseEntity<Map>(msg,HttpStatus.OK);
		}
		return resEntity;
	}
	
	@GetMapping("/selectReportOne/{rnum}")
	@ApiOperation(value="rnum을 이용하여 report 상세 조회")
	public ResponseEntity<Map> selectReportOne(@PathVariable("rnum")int rnum){
		ResponseEntity<Map> resEntity=null;
		int state=0;
		try {
			ReportDTO dto = new ReportDTO();
			dto.setRnum(rnum);
			System.out.println("1111111");
			ReportDTO selectReportOne = reSer.selectReportOne(dto);
			System.out.println("2222222");
			Map<String,Object> msg = new HashMap<String, Object>();
			state = 1;
			msg.put("state", state);
			msg.put("regmsg", "조회 성공");
			msg.put("resvalue",selectReportOne);
			System.out.println(msg.toString());
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
