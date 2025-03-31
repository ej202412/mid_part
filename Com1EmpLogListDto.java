package com.example.finalproject.dto;

import java.util.List;

import lombok.Builder;

@Builder
public class Com1EmpLogListDto {
	private List<Com1EmpLogDto> list;
	private int startPageNum;
	private int endPageNum;
	private int totalPageCount;
	private int pageNum;
	private int totalRow;
	private String findQuery;
}
