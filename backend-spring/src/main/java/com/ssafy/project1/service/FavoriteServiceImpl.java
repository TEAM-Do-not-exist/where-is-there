package com.ssafy.project1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.project1.dto.FavoriteDTO;
import com.ssafy.project1.repository.IFavoriteRepository;

@Service
public class FavoriteServiceImpl implements IFavoriteService{

	@Autowired
	IFavoriteRepository favRepo;
	
	@Override
	public int insert(FavoriteDTO dto) {
		System.out.println("service들어옴");
		// TODO Auto-generated method stub
		return favRepo.insert(dto) ;
	}

}
