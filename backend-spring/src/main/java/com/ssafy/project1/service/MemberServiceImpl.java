package com.ssafy.project1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.project1.dto.EmailAuthDTO;
import com.ssafy.project1.dto.MemberDTO;
import com.ssafy.project1.dto.SuccessTemp;
import com.ssafy.project1.repository.EmailAuthRepository;
import com.ssafy.project1.repository.MemberRepository;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	MemberRepository memRepo;
	@Autowired
	EmailAuthRepository emailAuthRepo;
	
	@Override
	public int insert(MemberDTO dto) {
		// TODO Auto-generated method stub
		return memRepo.insert(dto);
	}

	@Override
	public int delete(String str) {
		// TODO Auto-generated method stub
		return memRepo.delete(str);
	}

	@Override
	public int update(MemberDTO dto) {
		// TODO Auto-generated method stub
		return memRepo.update(dto);
	}

	@Override
	public List<MemberDTO> selectList() {
		// TODO Auto-generated method stub
		return memRepo.selectList();
	}

	@Override
	public MemberDTO selectOneId(MemberDTO dto) {
		// TODO Auto-generated method stub
		return memRepo.selectOneId(dto);
	}

	@Override
	public int selectOneIdPw(MemberDTO dto) {
		// TODO Auto-generated method stub
		return memRepo.selectOneIdPw(dto);
	}

	@Override
	public int duplicateCheckId(MemberDTO dto) {
		// TODO Auto-generated method stub
		EmailAuthDTO dto2 = new EmailAuthDTO();
		dto2.setId(dto.getId());
		SuccessTemp authIdCheck = emailAuthRepo.exists_check_email_auth(dto2);
		emailAuthRepo.delete_useless();	//쓸데 업는 것들을 삭제
		if(authIdCheck.success==0 && memRepo.selectOneId(dto)==null) {	//인증받는리스트에 없고 회원리스트에서 찾았을때 null이면 중복되는 것이 없으므로 만들어도 된다
			return 1;
		}else
			return -1;
	}

}
