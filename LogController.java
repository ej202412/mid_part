package com.finalproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.finalproject.dto.LogDto;
import com.finalproject.dto.LogListDto;
import com.finalproject.service.LogService;

@RestController
public class LogController {

	@Autowired
	private LogService logService;
	
	@PostMapping("/checkin")
	public ResponseEntity<String> checkIn(@RequestBody LogDto dto){
		boolean result = logService.checkIn(dto);
		if(result) {
			return ResponseEntity.ok("출근 등록 성공"); 
		}else {
			return ResponseEntity.badRequest().body("출근 등록 실패");
		}
	}
	
	@PatchMapping("/checkout")
	public ResponseEntity<String> checkOut(@RequestBody LogDto dto){
		boolean result = logService.checkOut(dto);
		if(result) {
			return ResponseEntity.ok("퇴근 등록 성공");
		}else {
			return ResponseEntity.badRequest().body("퇴근 등록 실패");
		}
	}
	
	@GetMapping("/log/{empno}/month/{month}")
	public LogListDto getList(@PathVariable LogDto dto){
		int empno=dto.getEmpNo();
		String month=dto.getMonth();
		return logService.getMonthlyLogList(empno, month, dto);
	}
}
