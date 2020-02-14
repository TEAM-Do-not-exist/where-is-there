package com.ssafy.project1.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.project1.dto.ReportDTO;

@Repository
public class ReportRepositoryImpl implements IReportRepository {

	@Autowired
	SqlSession session;

	@Override
	public int insert(ReportDTO dto) {
		// TODO Auto-generated method stub
		return session.insert("ssafy.report.insert", dto);
	}

	@Override
	public int delete(ReportDTO dto) {
		// TODO Auto-generated method stub
		return session.delete("ssafy.report.delete", dto);
	}

	@Override
	public List<ReportDTO> selectReportList(ReportDTO dto) {
		// TODO Auto-generated method stub
		return session.selectList("ssafy.report.selectReportList", dto);
	}

	@Override
	public ReportDTO selectReportOne(ReportDTO dto) {
		// TODO Auto-generated method stub
		return session.selectOne("ssafy.report.selectReportOne", dto);
	}

}
