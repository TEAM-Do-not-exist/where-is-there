package com.ssafy.project1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.project1.dto.MemberDTO;
import com.ssafy.project1.repository.MemberRepository;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	MemberRepository memRepo;
	
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

}
