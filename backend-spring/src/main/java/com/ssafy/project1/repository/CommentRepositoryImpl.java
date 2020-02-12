package com.ssafy.project1.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.project1.dto.CommentDTO;
import com.ssafy.project1.dto.MemberDTO;

@Repository
public class CommentRepositoryImpl implements ICommentRepository {

	@Autowired
	SqlSession session;

	@Override
	public int insert(CommentDTO dto) {
		// TODO Auto-generated method stub
		return session.insert("ssafy.comment.insert", dto);
	}

	@Override
	public List<CommentDTO> selectMyList(CommentDTO dto) {
		// TODO Auto-generated method stub
		return session.selectList("ssafy.comment.selectMyList", dto);
	}

	@Override
	public List<CommentDTO> selectPhotoList(CommentDTO dto) {
		// TODO Auto-generated method stub
		return session.selectList("ssafy.comment.selectPhotoList", dto);
	}

	@Override
	public int delete(CommentDTO dto) {
		// TODO Auto-generated method stub
		return session.delete("ssafy.comment.delete", dto);
	}

	@Override
	public int update(CommentDTO dto) {
		// TODO Auto-generated method stub
		return session.update("ssafy.comment.update", dto);
	}

}
