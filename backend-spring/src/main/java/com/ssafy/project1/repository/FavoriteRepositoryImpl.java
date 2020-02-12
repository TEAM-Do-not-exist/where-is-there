package com.ssafy.project1.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.project1.dto.FavoriteDTO;

@Repository
public class FavoriteRepositoryImpl implements IFavoriteRepository {
	@Autowired
	SqlSession session;

	@Override
	public int insert(FavoriteDTO dto) {
		// TODO Auto-generated method stub
		return session.insert("ssafy.favorite.insert", dto);
	}

	@Override
	public int delete(FavoriteDTO dto) {
		// TODO Auto-generated method stub
		return session.delete("ssafy.favorite.delete", dto);
	}

	@Override
	public List<FavoriteDTO> selectMyList(FavoriteDTO dto) {
		// TODO Auto-generated method stub
		return session.selectList("ssafy.favorite.selectMyList", dto);
	}

	@Override
	public List<FavoriteDTO> selectPhotoList(FavoriteDTO dto) {
		// TODO Auto-generated method stub
		return session.selectList("ssafy.favorite.selectPhotoList", dto);
	}

	@Override
	public List<FavoriteDTO> selectBestList(FavoriteDTO dto) {
		// TODO Auto-generated method stub
		return session.selectList("ssafy.favorite.selectBestList", dto);
	}

}
