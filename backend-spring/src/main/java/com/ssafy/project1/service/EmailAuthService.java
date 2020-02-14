package com.ssafy.project1.service;

import java.util.List;

import com.ssafy.project1.dto.EmailAuthDTO;

public interface EmailAuthService {
	public int auth_request(EmailAuthDTO dto);

	// 인증요청 성공시 1 실패시 -1
	public int auth_request_re(EmailAuthDTO dto);

	// 인증 재요청
	public int auth_check(EmailAuthDTO dto);

	// 인증확인
	public List<EmailAuthDTO> selectList();
	// 인증중인 리스트
}
