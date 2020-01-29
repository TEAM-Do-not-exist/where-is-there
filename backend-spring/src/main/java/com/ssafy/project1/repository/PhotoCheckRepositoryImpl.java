package com.ssafy.project1.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.project1.dto.PhotoCheckDTO;
import com.ssafy.project1.dto.PhotoCheckDTO;

@Repository
public class PhotoCheckRepositoryImpl implements PhotoCheckRepository {

	@Autowired
	SqlSession session;
	
	@Override
	public int insert(PhotoCheckDTO dto) {
		// TODO Auto-generated method stub
		return session.insert("ssafy.photocheck.insert",dto);
	}

	@Override
	public int delete(int pcode) {
		// TODO Auto-generated method stub
		return session.delete("ssafy.photocheck.delete",pcode);
	}

	@Override
	public int update(PhotoCheckDTO dto) {
		// TODO Auto-generated method stub
		return session.update("ssafy.photocheck.update",dto);
	}

	@Override
	public List<PhotoCheckDTO> selectList() {
		// TODO Auto-generated method stub
		return session.selectList("ssafy.photocheck.selectList");
	}

	@Override
	public PhotoCheckDTO selectOne(PhotoCheckDTO dto) {
		// TODO Auto-generated method stub
		return session.selectOne("ssafy.photocheck.selectOne",dto);
	}



}
