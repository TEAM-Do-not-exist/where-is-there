package com.ssafy.project1.service;

import java.util.List;

import com.ssafy.project1.dto.PhotoDTO;

public interface PhotoService {
	public int insert(PhotoDTO dto);
	//추가
	public int delete(int pcode);
	//삭제
	public int update(PhotoDTO dto);
	//수정
	public List<PhotoDTO> selectList();
	//전체 사진정보
	public List<PhotoDTO> selectList_favorite();
	//좋아요 많은 순으로 정렬
	public PhotoDTO selectOne(PhotoDTO dto);
	//pcode에 따른 검색했을때 전체정보
}
