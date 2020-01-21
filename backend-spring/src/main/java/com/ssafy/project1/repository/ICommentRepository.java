package com.ssafy.project1.repository;

import java.util.List;

import com.ssafy.project1.dto.CommentDTO;

public interface ICommentRepository {
	
	public int insert(CommentDTO dto);
	
	public List<CommentDTO> selectList();
	
	public int delete(int ccode);
	

}
