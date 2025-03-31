package com.example.finalproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.finalproject.dto.Com1EmpLogDto;
import com.example.finalproject.dto.Com1EmpLogListDto;
import com.example.finalproject.service.Com1EmpLogService;

@RestController
public class Com1StaffController {

	@Autowired
	private Com1EmpLogService empLogService;
	
	@PostMapping("/checkin")
	public ResponseEntity<String> checkIn(@RequestBody Com1EmpLogDto dto){
		boolean result = empLogService.checkIn(dto);
		if(result) {
			return ResponseEntity.ok("출근 등록 성공"); 
		}else {
			return ResponseEntity.badRequest().body("출근 등록 실패");
		}
	}
	
	@PostMapping("/checkout")
	public ResponseEntity<String> checkOut(@RequestBody Com1EmpLogDto dto){
		boolean result = empLogService.checkOut(dto);
		if(result) {
			return ResponseEntity.ok("퇴근 등록 성공");
		}else {
			return ResponseEntity.badRequest().body("퇴근 등록 실패");
		}
	}
	
	@GetMapping("/log/{empno}/month/{month}")
	public Com1EmpLogListDto getList(@RequestBody Com1EmpLogDto dto){
		int empno=dto.getEmpNo();
		String month=dto.getMonth();
		return empLogService.getMonthlyLogList(empno, month, dto);
	}
}
