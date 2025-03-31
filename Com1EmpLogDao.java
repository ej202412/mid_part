package com.example.finalproject.repository;

import java.util.List;

import com.example.finalproject.dto.Com1EmpLogDto;

public interface Com1EmpLogDao {
	public int insertStart(Com1EmpLogDto dto);
	public int updateFinish(Com1EmpLogDto dto);
	public int update(Com1EmpLogDto dto);
	public List<Com1EmpLogDto> getList(int empno);
	public int getCountByMonth(int empno, String month);
	public List<Com1EmpLogDto> getListByMonth(int empno, String month);
}
