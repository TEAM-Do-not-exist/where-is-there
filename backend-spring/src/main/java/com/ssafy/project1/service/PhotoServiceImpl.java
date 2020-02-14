package com.ssafy.project1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.project1.dto.PhotoDTO;
import com.ssafy.project1.repository.PhotoRepository;

@Service
public class PhotoServiceImpl implements PhotoService {

	@Autowired
	PhotoRepository photoRepo;

	@Override
	public int insert(PhotoDTO dto) {
		// TODO Auto-generated method stub
		return photoRepo.insert(dto);
	}

	@Override
	public int delete(int pcode) {
		// TODO Auto-generated method stub
		return photoRepo.delete(pcode);
	}

	@Override
	public int update(PhotoDTO dto) {
		// TODO Auto-generated method stub
		return photoRepo.update(dto);
	}

	@Override
	public List<PhotoDTO> selectList() {
		// TODO Auto-generated method stub
		return photoRepo.selectList();
	}

	@Override
	public PhotoDTO selectOne(PhotoDTO dto) {
		// TODO Auto-generated method stub
		return photoRepo.selectOne(dto);
	}

	@Override
	public List<PhotoDTO> selectList_favorite() {
		// TODO Auto-generated method stub
		return photoRepo.selectList_favorite();
	}

}
