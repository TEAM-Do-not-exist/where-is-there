package com.ssafy.project1.service;

import java.util.List;

import com.ssafy.project1.dto.CommentDTO;
import com.ssafy.project1.dto.FavoriteDTO;

public interface IFavoriteService {
	
	public int insert(FavoriteDTO dto);
	
	public int delete(FavoriteDTO dto);
	
	public List<FavoriteDTO> selectMyList(FavoriteDTO dto);
	
	public List<FavoriteDTO> selectPhotoList(FavoriteDTO dto);
	
	public List<FavoriteDTO> selectBestList(FavoriteDTO dto);

}
