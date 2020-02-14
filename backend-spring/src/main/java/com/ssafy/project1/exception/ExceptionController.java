package com.ssafy.project1.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@ControllerAdvice
public class ExceptionController {

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map> exceptionHandler(Exception e) {
        ResponseEntity<Map> resEntity = null;
        Map<String, Object> msg = new HashMap<String, Object>();
        final String errorMsg = e.getMessage();
        int state = -1; // 일반 에러
        if (errorMsg == null) {
            msg.put("regmsg", "서버에러");
        } else {
            msg.put("regmsg", errorMsg);
        }
        if (e instanceof UnauthorizedException) {
            state = -2; // 권한 오류
        }
        msg.put("state", state);
        resEntity = new ResponseEntity<Map>(msg, HttpStatus.OK);

        return resEntity;
    }
}
