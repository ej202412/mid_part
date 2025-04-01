package com.finalproject.repository;

import java.util.List;

import com.finalproject.dto.LogDto;

public interface LogDao {
	public int insertStart(LogDto dto);
	public int updateFinish(LogDto dto);
	public int update(LogDto dto);
	public List<LogDto> getList(int empno);
	public int getCountByMonth(int empno, String month);
	public List<LogDto> getListByMonth(LogDto dto);

}
