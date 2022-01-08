package me.paulkim.shopPage.service;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.paulkim.shopPage.model.MemberDTO;

@Service
public class MemberMapper {

	@Autowired
	private SqlSession sqlSession;
	
	public boolean checkMember(Map<String, String> params) {
		MemberDTO dto = sqlSession.selectOne("checkMember", params);
		if (dto == null) return false;
		else return true;
	}
	
	public int insertMember(MemberDTO dto) {
		return sqlSession.insert("insertMember", dto);
	}
}





