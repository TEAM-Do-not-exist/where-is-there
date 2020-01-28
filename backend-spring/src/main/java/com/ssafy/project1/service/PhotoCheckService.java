package com.ssafy.project1.service;

import java.util.List;

import com.ssafy.project1.dto.PhotoCheckDTO;

public interface PhotoCheckService {
	public int insert(PhotoCheckDTO dto);
	//추가
	public int delete(int pcode);
	//삭제
	public int update(PhotoCheckDTO dto);
	//수정
	public List<PhotoCheckDTO> selectList();
	//전체 사진정보
	public PhotoCheckDTO selectOne(PhotoCheckDTO dto);
	//pcode에 따른 검색했을때 전체정보
}
