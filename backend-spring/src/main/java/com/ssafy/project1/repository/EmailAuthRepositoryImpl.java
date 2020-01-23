package com.ssafy.project1.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.project1.dto.EmailAuthDTO;
import com.ssafy.project1.dto.SuccessTemp;

@Repository
public class EmailAuthRepositoryImpl implements EmailAuthRepository {
	
	@Autowired
	SqlSession session;

	@Override
	public int delete_useless() {
		// TODO Auto-generated method stub
		return session.delete("ssafy.emailauth.delete_useless");
	}

	@Override
	public SuccessTemp exists_check_member(EmailAuthDTO dto) {
		// TODO Auto-generated method stub
		return session.selectOne("ssafy.emailauth.exists_check_member",dto);
	}

	@Override
	public SuccessTemp exists_check_email_auth(EmailAuthDTO dto) {
		// TODO Auto-generated method stub
		return session.selectOne("ssafy.emailauth.exists_check_email_auth",dto);
	}

	@Override
	//이거구현은 미루자 안쓸거같기도해서 쓰게되면 그때해보자
	public SuccessTemp exists_check_email_auth_expire(EmailAuthDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insert(EmailAuthDTO dto) {
		// TODO Auto-generated method stub
		return session.insert("ssafy.emailauth.insert",dto);
	}

	@Override
	public int update_key(EmailAuthDTO dto) {
		// TODO Auto-generated method stub
		return session.update("ssafy.emailauth.update_key",dto);
	}

	@Override
	public int update_check(EmailAuthDTO dto) {
		// TODO Auto-generated method stub
		return session.update("ssafy.emailauth.update_check",dto);
	}

	@Override
	public List<EmailAuthDTO> selectList() {
		// TODO Auto-generated method stub
		return session.selectList("ssafy.emailauth.selectList");
	}

	@Override
	public EmailAuthDTO selectOne(EmailAuthDTO dto) {
		// TODO Auto-generated method stub
		return session.selectOne("ssafy.emailauth.selectOne",dto);
	}

	@Override
	public EmailAuthDTO selectOneIdTime(EmailAuthDTO dto) {
		// TODO Auto-generated method stub
		return session.selectOne("ssafy.emailauth.selectOneIdTime",dto);
	}
}
