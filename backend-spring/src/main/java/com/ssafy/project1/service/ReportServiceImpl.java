package com.ssafy.project1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.project1.dto.ReportDTO;
import com.ssafy.project1.repository.IReportRepository;

@Service
public class ReportServiceImpl implements IReportService {

	@Autowired
	IReportRepository reRepo;

	@Override
	public int insert(ReportDTO dto) {
		// TODO Auto-generated method stub
		return reRepo.insert(dto);
	}

	@Override
	public int delete(ReportDTO dto) {
		// TODO Auto-generated method stub
		return reRepo.delete(dto);
	}

	@Override
	public ReportDTO selectReportOne(ReportDTO dto) {
		// TODO Auto-generated method stub
		return reRepo.selectReportOne(dto);
	}

	@Override
	public List<ReportDTO> selectReportList(ReportDTO dto) {
		// TODO Auto-generated method stub
		return reRepo.selectReportList(dto);
	}

}
