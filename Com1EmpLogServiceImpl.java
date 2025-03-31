package com.example.finalproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.finalproject.dto.Com1EmpLogDto;
import com.example.finalproject.dto.Com1EmpLogListDto;
import com.example.finalproject.repository.Com1EmpLogDao;

@Service
public class Com1EmpLogServiceImpl implements Com1EmpLogService{
	
	//한 페이지에 몇개씩 표시할 것인지
	final int PAGE_ROW_COUNT=10;
	//하단 페이지를 몇개씩 표시할 것인지
	final int PAGE_DISPLAY_COUNT=3;
	
	//보여줄 페이지의 번호를 일단 1이라고 초기값 지정
	int pageNum=1;
	
	@Autowired 
	private Com1EmpLogDao empLogDao;
	
	public boolean checkIn(Com1EmpLogDto dto) {
		int result=empLogDao.insertStart(dto);
		if(result==0) {
			return false;
		}else {
			return true;
		}
	}
	
	public boolean checkOut(Com1EmpLogDto dto) {
		int result=empLogDao.updateFinish(dto);
		if(result==0) {
			return false;
		}else {
			return true;
		}
	}
	
	public List<Com1EmpLogDto> getLogList(int empNo){
		return empLogDao.getList(empNo);
	}
	
	public int getMonthlyLogCount(int empNo, String month) {
		return empLogDao.getCountByMonth(empNo, month);
	}
	
	public Com1EmpLogListDto getMonthlyLogList(int empNo, String month, Com1EmpLogDto search){
		//보여줄 페이지의 시작 ROWNUM
		int startRowNum=1+(pageNum-1)*PAGE_ROW_COUNT;
		//보여줄 페이지의 끝 ROWNUM
		int endRowNum=pageNum*PAGE_ROW_COUNT;
		
		//하단 시작 페이지 번호 
		int startPageNum = 1 + ((pageNum-1)/PAGE_DISPLAY_COUNT)*PAGE_DISPLAY_COUNT;
		//하단 끝 페이지 번호
		int endPageNum=startPageNum+PAGE_DISPLAY_COUNT-1;
		//전체 글의 갯수
		int totalRow=empLogDao.getCountByMonth(empNo, month);
		//전체 페이지의 갯수 구하기
		int totalPageCount=(int)Math.ceil(totalRow/(double)PAGE_ROW_COUNT);
		//끝 페이지 번호가 이미 전체 페이지 갯수보다 크게 계산되었다면 잘못된 값이다.
		if(endPageNum > totalPageCount){
			endPageNum=totalPageCount; //보정해 준다. 
		}	
		
		// startRowNum 과 endRowNum 을 Dto 객체에 담아서
		search.setStartRowNum(startRowNum);
		search.setEndRowNum(endRowNum);
		
		//로그 목록 얻어오기
		List<Com1EmpLogDto> list=empLogDao.getListByMonth(empNo, month);
		
		String findQuery="";
		if(search.getMonth() != null) {
			findQuery="&month="+search.getMonth();
		}
		
		//로그 목록 페이지에서 필요한 정보를 모두 PostListDto 에 담는다.
		Com1EmpLogListDto dto=Com1EmpLogListDto.builder()
				.list(list)
				.startPageNum(startPageNum)
				.endPageNum(endPageNum)
				.totalPageCount(totalPageCount)
				.pageNum(pageNum)
				.totalRow(totalRow)
				.findQuery(findQuery)
				.build();
				
		return dto;
	}

}
