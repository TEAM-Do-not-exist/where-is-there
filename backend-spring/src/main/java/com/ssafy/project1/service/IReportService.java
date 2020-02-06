package com.ssafy.project1.service;

import java.util.List;

import com.ssafy.project1.dto.ReportDTO;

public interface IReportService {
	public int insert(ReportDTO dto);
	
	public int delete(ReportDTO dto);
	
	public ReportDTO selectReportOne(ReportDTO dto);
	
	public List<ReportDTO> selectReportList(ReportDTO dto);
}
