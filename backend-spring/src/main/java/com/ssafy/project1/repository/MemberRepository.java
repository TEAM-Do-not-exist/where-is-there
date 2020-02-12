package com.ssafy.project1.repository;

import java.util.List;

import com.ssafy.project1.dto.MemberDTO;

public interface MemberRepository {
	public int insert0(MemberDTO dto);

	// 추가 - 그냥
	public int insert1(MemberDTO dto);

	// 추가 - 네이버
	public int insert2(MemberDTO dto);

	// 추가 - 카카오
	public int delete(String str);

	// 삭제
	public int update(MemberDTO dto);

	// 수정
	public List<MemberDTO> selectList();

	// 전체 회원정보
	public MemberDTO selectOneId(MemberDTO dto);

	// id에 따른 검색했을때 전체정보
	public int selectOneIdPw(MemberDTO dto);
	// id와 pw가 맞으면 1을 출력 없으면 해당사항이 없으면 0을 출력
}
