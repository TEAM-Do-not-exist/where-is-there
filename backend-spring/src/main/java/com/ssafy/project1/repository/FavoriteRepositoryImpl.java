package com.ssafy.project1.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.project1.dto.FavoriteDTO;

@Repository
public class FavoriteRepositoryImpl implements IFavoriteRepository{
	@Autowired
	SqlSession session;
	
	@Override
	public int insert(FavoriteDTO dto) {
		// TODO Auto-generated method stub
		System.out.println("repositoryasdfasdfasdfasdfassfdasdf");
		return session.insert("ssafy.favorite.insert",dto);
	}
	


}
