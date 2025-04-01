package com.finalproject.service;

import com.finalproject.dto.LogDto;
import com.finalproject.dto.LogListDto;

public interface LogService {
	public boolean checkIn(LogDto dto);
	public boolean checkOut(LogDto dto);
	public int getMonthlyLogCount(int empNo, String Month);
	public LogListDto getMonthlyLogList(int pageNum, String Month, LogDto search);
}
