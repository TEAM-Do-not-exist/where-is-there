package com.ssafy.project1.repository;

import java.util.List;

import com.ssafy.project1.dto.FavoriteDTO;

public interface IFavoriteRepository {

	public int insert(FavoriteDTO dto);

	public int delete(FavoriteDTO dto);

	public List<FavoriteDTO> selectMyList(FavoriteDTO dto);

	public List<FavoriteDTO> selectPhotoList(FavoriteDTO dto);

	public List<FavoriteDTO> selectBestList(FavoriteDTO dto);

}
