package com.ssafy.project1.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.project1.dto.MemberDTO;

@Repository
public class MemberRepositoryImpl implements MemberRepository {

	@Autowired
	SqlSession session;

	@Override
	public int insert0(MemberDTO dto) {
		// TODO Auto-generated method stub
		return session.insert("ssafy.member.insert0", dto);
	}

	@Override
	public int insert1(MemberDTO dto) {
		// TODO Auto-generated method stub
		return session.insert("ssafy.member.insert1", dto);
	}

	@Override
	public int insert2(MemberDTO dto) {
		// TODO Auto-generated method stub
		System.out.println(dto.getEmail());
		System.out.println(dto.getNickname());
		int insert = session.insert("ssafy.member.insert2", dto);
		System.out.println(insert);
		return insert;
	}

	@Override
	public int delete(String str) {
		// TODO Auto-generated method stub
		return session.delete("ssafy.member.delete", str);
	}

	@Override
	public int update(MemberDTO dto) {
		// TODO Auto-generated method stub
		return session.update("ssafy.member.update", dto);
	}

	@Override
	public List<MemberDTO> selectList() {
		// TODO Auto-generated method stub
		return session.selectList("ssafy.member.selectList");
	}

	@Override
	public MemberDTO selectOneId(MemberDTO dto) {
		// TODO Auto-generated method stub
		return session.selectOne("ssafy.member.selectOne_id", dto);
	}

	@Override
	public int selectOneIdPw(MemberDTO dto) {
		// TODO Auto-generated method stub
		return session.selectOne("ssafy.member.selectOne_id_pw", dto);
	}

}
