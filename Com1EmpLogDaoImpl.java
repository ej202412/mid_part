package com.example.finalproject.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.finalproject.dto.Com1EmpLogDto;

@Repository
public class Com1EmpLogDaoImpl implements Com1EmpLogDao {

	@Autowired private SqlSession session;

	@Override
	public int insertStart(Com1EmpLogDto dto) {
		// TODO Auto-generated method stub
		return session.insert("log.insertStart", dto);
	}

	@Override
	public int updateFinish(Com1EmpLogDto dto) {
		// TODO Auto-generated method stub
		return session.update("log.updateFinish", dto);
	}

	@Override
	public int update(Com1EmpLogDto dto) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Com1EmpLogDto> getList(int empno) {
		// TODO Auto-generated method stub
		return session.selectList("log.getLogList", empno);
	}

	@Override
	public int getCountByMonth(int empno, String month) {
		// TODO Auto-generated method stub
		return session.selectOne("log.getCount", empno);
	}

	@Override
	public List<Com1EmpLogDto> getListByMonth(int empno, String month) {
		//parameterType 으로 지정할 Map 객체에 필요한 정보를 담는다.
		Map<String, Object> map = Map.of("empno", empno, "month", month);
		//Map 객체를 parameterType 으로 전달해서 sql 문을 실행한다.
		return session.selectList("log.getList", map);
	}
	

}
