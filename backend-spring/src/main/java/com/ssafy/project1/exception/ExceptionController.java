package com.ssafy.project1.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionController {
	
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map> exceptionHandler(Exception e){
    	ResponseEntity<Map> resEntity = null;
		Map<String,Object> msg = new HashMap<String, Object>();
        final String errorMsg = e.getMessage();
        int errorCode = -1;
        if(errorMsg == null){
        	msg.put("regmsg", "서버에러");
        }else{
        	msg.put("regmsg", errorMsg);
        }
        if(e instanceof  UnauthorizedException) {
        	errorCode = -2;
        }
        msg.put("errorCode",errorCode);
        resEntity=new ResponseEntity<Map>(msg,HttpStatus.OK);

        return resEntity;
    }
}
