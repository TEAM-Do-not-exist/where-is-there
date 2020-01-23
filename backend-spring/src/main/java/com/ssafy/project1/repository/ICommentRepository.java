package com.ssafy.project1.repository;

import java.util.List;

import com.ssafy.project1.dto.CommentDTO;

public interface ICommentRepository {
	
	public int insert(CommentDTO dto);
	
	public List<CommentDTO> selectMyList(CommentDTO dto);
	
	public List<CommentDTO> selectPhotoList(CommentDTO dto);
	
	public int delete(CommentDTO dto);
	
	public int update(CommentDTO dto);
	
	

}
