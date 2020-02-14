package com.ssafy.project1.repository;

import java.util.List;

import com.ssafy.project1.dto.EmailAuthDTO;
import com.ssafy.project1.dto.SuccessTemp;

public interface EmailAuthRepository {
	public int delete_useless();

	// 필요없는 데이터들 제거(만료되었거나 인증확인받았을때)
	public SuccessTemp exists_check_member(EmailAuthDTO dto);

	// 회원테이블에 데이터가 있는지 확인
	public SuccessTemp exists_check_email_auth(EmailAuthDTO dto);

	// 이메일인증테이블에 데이터가 있는지 확인
	public SuccessTemp exists_check_email_auth_expire(EmailAuthDTO dto);

	// 이메일인증테이블에 만료된 데이터가 있는지 확인
	public int insert(EmailAuthDTO dto);

	// 인증요청 테이블에 삽입
	public int update_key(EmailAuthDTO dto);

	// 인증키를 업데이트
	public int update_check(EmailAuthDTO dto);

	// 인증확인을 업데이트
	public List<EmailAuthDTO> selectList();

	// 인증중인 리스트 전체를 출력
	public EmailAuthDTO selectOne(EmailAuthDTO dto);

	// 인증중인 것들중에 아이디에 맞는 하나를 출력
	public EmailAuthDTO selectOneIdTime(EmailAuthDTO dto);
	// 인증중인 것들중에 아이디에 맞으면서 시간이 만료되기전이면 반환
}
