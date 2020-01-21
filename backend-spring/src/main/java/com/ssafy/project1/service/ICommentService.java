package com.ssafy.project1.service;

import java.util.List;

import com.ssafy.project1.dto.CommentDTO;

public interface ICommentService {
	
	public int insert(CommentDTO dto);
	
	public int delete(int ccode);

	public List<CommentDTO> selectMyList(CommentDTO dto);
	
	public List<CommentDTO> selectPhotoList(CommentDTO dto);
	

}
