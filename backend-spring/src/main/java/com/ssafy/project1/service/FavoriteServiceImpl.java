package com.ssafy.project1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.project1.dto.CommentDTO;
import com.ssafy.project1.dto.FavoriteDTO;
import com.ssafy.project1.repository.IFavoriteRepository;

@Service
public class FavoriteServiceImpl implements IFavoriteService {

	@Autowired
	IFavoriteRepository favRepo;

	@Override
	public int insert(FavoriteDTO dto) {
		// TODO Auto-generated method stub
		return favRepo.insert(dto);
	}

	@Override
	public int delete(FavoriteDTO dto) {
		// TODO Auto-generated method stub
		return favRepo.delete(dto);
	}

	@Override
	public List<FavoriteDTO> selectMyList(FavoriteDTO dto) {
		// TODO Auto-generated method stub
		return favRepo.selectMyList(dto);
	}

	@Override
	public List<FavoriteDTO> selectPhotoList(FavoriteDTO dto) {
		// TODO Auto-generated method stub
		return favRepo.selectPhotoList(dto);
	}

	@Override
	public List<FavoriteDTO> selectBestList(FavoriteDTO dto) {
		// TODO Auto-generated method stub
		return favRepo.selectBestList(dto);
	}

}
