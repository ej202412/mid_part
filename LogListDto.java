package com.finalproject.dto;

import java.util.List;

import lombok.Builder;

@Builder
public class LogListDto {
	private List<LogDto> list;
	private int startPageNum;
	private int endPageNum;
	private int totalPageCount;
	private int pageNum;
	private int totalRow;
	private String findQuery;
}
