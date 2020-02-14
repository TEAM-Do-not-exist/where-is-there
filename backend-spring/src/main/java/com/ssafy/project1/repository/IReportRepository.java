package com.ssafy.project1.repository;

import java.util.List;

import com.ssafy.project1.dto.ReportDTO;

public interface IReportRepository {

	public int insert(ReportDTO dto);

	public int delete(ReportDTO dto);

	public List<ReportDTO> selectReportList(ReportDTO dto);

	public ReportDTO selectReportOne(ReportDTO dto);

}
