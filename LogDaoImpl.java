package com.finalproject.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.finalproject.dto.LogDto;

@Repository
public class LogDaoImpl implements LogDao{

	@Autowired private SqlSession session;

	@Override
	public int insertStart(LogDto dto) {
		// TODO Auto-generated method stub
		return session.insert("log.insertStart", dto);
	}

	@Override
	public int updateFinish(LogDto dto) {
		// TODO Auto-generated method stub
		return session.update("log.updateFinish", dto);
	}

	@Override
	public int update(LogDto dto) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<LogDto> getList(int empno) {
		// TODO Auto-generated method stub
		return session.selectList("log.getLogList", empno);
	}

	@Override
	public int getCountByMonth(int empno, String month) {
		// TODO Auto-generated method stub
		return session.selectOne("log.getCount", empno);
	}

	@Override
	public List<LogDto> getListByMonth(LogDto dto) {
		//parameterType 으로 지정할 Map 객체에 필요한 정보를 담는다.
		//Map<String, Object> map = Map.of("empno", empno, "month", month);
		//Map 객체를 parameterType 으로 전달해서 sql 문을 실행한다.
		return session.selectList("log.getList", dto);
	}
	
}
