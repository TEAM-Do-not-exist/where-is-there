package com.ssafy.project1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.project1.dto.CommentDTO;
import com.ssafy.project1.repository.ICommentRepository;

@Service
public class CommentServiceImpl implements ICommentService{

	@Autowired
	ICommentRepository comRepo;
	
	@Override
	public int insert(CommentDTO dto) {
		// TODO Auto-generated method stub
		return comRepo.insert(dto);
	}

	@Override
	public List<CommentDTO> selectMyList(CommentDTO dto) {
		// TODO Auto-generated method stub
		return comRepo.selectMyList(dto);
	}

	@Override
	public List<CommentDTO> selectPhotoList(CommentDTO dto) {
		// TODO Auto-generated method stub
		return comRepo.selectPhotoList(dto);
	}
	
	@Override
	public int delete(CommentDTO dto) {
		// TODO Auto-generated method stub
		return comRepo.delete(dto);
	}
	
	@Override
	public int update(CommentDTO dto) {
		// TODO Auto-generated method stub
		return comRepo.update(dto);
	}
	

}
