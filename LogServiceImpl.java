package com.finalproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finalproject.dto.LogDto;
import com.finalproject.dto.LogListDto;
import com.finalproject.repository.LogDao;


@Service
public class LogServiceImpl implements LogService{
	
	//한 페이지에 몇개씩 표시할 것인지
	final int PAGE_ROW_COUNT=10;
	//하단 페이지를 몇개씩 표시할 것인지
	final int PAGE_DISPLAY_COUNT=3;
	
	//보여줄 페이지의 번호를 일단 1이라고 초기값 지정
	int pageNum=1;
	
	//보여줄 월의 초기값 01 이라고 지정
	String month="01";
	
	@Autowired 
	private LogDao logDao;
	
	@Override
	public boolean checkIn(LogDto dto) {
		int result=logDao.insertStart(dto);
		if(result==0) {
			return false;
		}else {
			return true;
		}
	}
	
	@Override
	public boolean checkOut(LogDto dto) {
		int result=logDao.updateFinish(dto);
		if(result==0) {
			return false;
		}else {
			return true;
		}
	}
	
	public List<LogDto> getLogList(int empNo){
		return logDao.getList(empNo);
	}
	
	@Override
	public int getMonthlyLogCount(int empNo, String month) {
		return logDao.getCountByMonth(empNo, month);
	}
	
	@Override
	public LogListDto getMonthlyLogList(int pageNum, String month, LogDto search){
		//보여줄 페이지의 시작 ROWNUM
		int startRowNum=1+(pageNum-1)*PAGE_ROW_COUNT;
		//보여줄 페이지의 끝 ROWNUM
		int endRowNum=pageNum*PAGE_ROW_COUNT;
		
		//하단 시작 페이지 번호 
		int startPageNum = 1 + ((pageNum-1)/PAGE_DISPLAY_COUNT)*PAGE_DISPLAY_COUNT;
		//하단 끝 페이지 번호
		int endPageNum=startPageNum+PAGE_DISPLAY_COUNT-1;
		//전체 글의 갯수
		int empNo=search.getEmpNo();
		int totalRow=logDao.getCountByMonth(empNo, month);
		//전체 페이지의 갯수 구하기
		int totalPageCount=(int)Math.ceil(totalRow/(double)PAGE_ROW_COUNT);
		//끝 페이지 번호가 이미 전체 페이지 갯수보다 크게 계산되었다면 잘못된 값이다.
		if(endPageNum > totalPageCount){
			endPageNum=totalPageCount; //보정해 준다. 
		}	
		
		// startRowNum 과 endRowNum 을 dto 객체에 담아서
		search.setStartRowNum(startRowNum);
		search.setEndRowNum(endRowNum);
		
		//로그 목록 얻어오기
		List<LogDto> list=logDao.getListByMonth(search);
		
		//페이지 이동에 필요한 쿼리문
		String findQuery="";
		//페이지 번호가 파라미터로 전달되는지 읽어와 본다.
		//String strPageNum=request.getParams("pageNum");
		//선택한 페이지 번호와 월로 이동
		if(pageNum != 0 && month != null){
			//숫자로 바꿔서 보여줄 페이지 번호로 지정한다.
			findQuery="&month="+month+"&pageNum="+pageNum;
		}
		//로그 목록 페이지에서 필요한 정보를 모두 LogListDto 에 담는다.
		LogListDto dto=LogListDto.builder()
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
	
