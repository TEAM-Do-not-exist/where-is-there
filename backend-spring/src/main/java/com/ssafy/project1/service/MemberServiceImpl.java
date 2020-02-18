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
		return memRepo.insert0(dto);
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
	public int duplicateCheckId(MemberDTO dto) {
		// TODO Auto-generated method stub
		EmailAuthDTO emailDto = new EmailAuthDTO();
		emailDto.setEmail(dto.getEmail());
		emailAuthRepo.delete_useless(); // 쓸데 업는 것들을 삭제
		SuccessTemp authIdCheck = emailAuthRepo.exists_check_email_auth(emailDto);
		if (authIdCheck.success == 0 && memRepo.selectOneId(dto) == null) { // 인증받는리스트에 없고 회원리스트에서 찾았을때 null이면 중복되는 것이
																			// 없으므로 만들어도 된다
			return 1;
		} else
			return -1;
	}

	@Override
	public int login(MemberDTO dto) {
		// TODO Auto-generated method stub
		MemberDTO mem = memRepo.selectOneId(dto);
		if (mem.getExternal() == 0) {
			return memRepo.selectOneIdPw(dto);
		} else if (mem.getExternal() == 1) {
			return 2;
		} else if (mem.getExternal() == 2) {
			return 3;
		}
		return -1;
	}

	@Override
	public int loginNaver(MemberDTO dto) {
		// TODO Auto-generated method stub
		MemberDTO mem = memRepo.selectOneId(dto);
		if (mem == null) { // 처음 로그인 시
			memRepo.insert1(dto);
			return 2;
		} else { // 처음이 아닐 시
			if (mem.getExternal() == 1) { // 네이버로 가입했는지 확인 후
				return 2;
			} else if (mem.getExternal() == 0) {
				return 1;
			} else if (mem.getExternal() == 2) {
				return 3;
			}
		}
		return -1;
	}

	@Override
	public int loginKakao(MemberDTO dto) {
		// TODO Auto-generated method stub
		MemberDTO mem = memRepo.selectOneId(dto);
		if (mem == null) { // 처음 로그인 시
			memRepo.insert2(dto);
			return 3;
		} else {
			System.out.println("not null");
			if (mem.getExternal() == 2) {
				return 3;
			} else if (mem.getExternal() == 0) {
				return 1;
			} else if (mem.getExternal() == 1) {
				return 2;
			}
		}
		return -1;
	}

}
