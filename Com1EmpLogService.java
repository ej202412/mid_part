package com.example.finalproject.service;

import com.example.finalproject.dto.Com1EmpLogDto;
import com.example.finalproject.dto.Com1EmpLogListDto;

public interface Com1EmpLogService {
	public boolean checkIn(Com1EmpLogDto dto);
	public boolean checkOut(Com1EmpLogDto dto);
	public int getMonthlyLogCount(int empNo, String Month);
	public Com1EmpLogListDto getMonthlyLogList(int empno, String Month, Com1EmpLogDto search);
}
