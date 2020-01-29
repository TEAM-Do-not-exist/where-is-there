package com.ssafy.project1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.project1.dto.PhotoCheckDTO;
import com.ssafy.project1.repository.PhotoCheckRepository;

@Service
public class PhotoCheckServiceImpl implements PhotoCheckService {
	
	@Autowired
	PhotoCheckRepository photoCheckRepo;
	
	@Override
	public int insert(PhotoCheckDTO dto) {
		// TODO Auto-generated method stub
		return photoCheckRepo.insert(dto);
	}

	@Override
	public int delete(int pcode) {
		// TODO Auto-generated method stub
		return photoCheckRepo.delete(pcode);
	}

	@Override
	public int update(PhotoCheckDTO dto) {
		// TODO Auto-generated method stub
		return photoCheckRepo.update(dto);
	}

	@Override
	public List<PhotoCheckDTO> selectList() {
		// TODO Auto-generated method stub
		return photoCheckRepo.selectList();
	}

	@Override
	public PhotoCheckDTO selectOne(PhotoCheckDTO dto) {
		// TODO Auto-generated method stub
		return photoCheckRepo.selectOne(dto);
	}
}
