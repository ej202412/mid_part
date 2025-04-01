package com.finalproject.dto;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Alias("logDto")	//mapper xml 에서 PostDto type 을 편하게 선언하기 위해
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class LogDto {
	private int logId; // 로그 ID
	private int empNo; // 직원 번호
	private String eName; // 직원 이름
	private String checkIn; // 출근 시간
	private String checkOut; // 퇴근 시간
	private Date workingDate; // 근무 날짜
	private double workingHours; // 근무 시간
	private double overtime; // 초과 근무 시간
	private String remarks; // 비고
	//페이징 처리할 때 필요한 필드
	private String month; //월
	private int startRowNum;
	private int endRowNum;
	private long prevNum; //이전글의 글번호
	private long nextNum; //다음글의 글번호
}
