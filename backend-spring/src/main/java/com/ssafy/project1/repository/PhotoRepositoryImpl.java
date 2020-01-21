package com.ssafy.project1.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.project1.dto.PhotoDTO;

@Repository
public class PhotoRepositoryImpl implements PhotoRepository {

	@Autowired
	SqlSession session;
	
	@Override
	public int insert(PhotoDTO dto) {
		// TODO Auto-generated method stub
		return session.insert("ssafy.photo.insert",dto);
	}

	@Override
	public int delete(int pcode) {
		// TODO Auto-generated method stub
		return session.delete("ssafy.photo.delete",pcode);
	}

	@Override
	public int update(PhotoDTO dto) {
		// TODO Auto-generated method stub
		return session.update("ssafy.photo.update",dto);
	}

	@Override
	public List<PhotoDTO> selectList() {
		// TODO Auto-generated method stub
		return session.selectList("ssafy.photo.selectList");
	}

	@Override
	public PhotoDTO selectOne(PhotoDTO dto) {
		// TODO Auto-generated method stub
		return session.selectOne("ssafy.photo.selectOne",dto);
	}

	@Override
	public List<PhotoDTO> selectList_favorite() {
		// TODO Auto-generated method stub
		return session.selectList("ssafy.photo.selectListFavorite");
	}
	
}
