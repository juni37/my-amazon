package me.paulkim.shopPage.service;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.paulkim.shopPage.model.BoardDBBean;


@Service
public class BoardMapper {
	
	@Autowired
	private SqlSession sqlSession;
	
	public List<BoardDBBean> listBoard(Map<String, Integer> map){
		List<BoardDBBean> list = sqlSession.selectList("listBoard", map);
		return list;
	}
	
	public int getCount() {
		return sqlSession.selectOne("getCount");
	}
	public int plusRe_step(String sql) {
		Map<String, String>map = new Hashtable<String, String>();
		map.put("sql", sql);
		return sqlSession.update("plusRe_step", map);
	}
	
	public int insertBoard(BoardDBBean dto) {
		return sqlSession.insert("insertBoard", dto);
	}
	
	public int deleteBoard(Map<String, String> map) {
		BoardDBBean dto = sqlSession.selectOne("getBoard", Integer.parseInt(map.get("num")));
		if (dto.getPasswd().equals(map.get("passwd"))) {
			return sqlSession.delete("deleteBoard", Integer.parseInt(map.get("num")));
		}else {
			return -1;
		}
	}
	
	public BoardDBBean getBoard(int num, String mode) {
		if (mode.equals("content")) {
			sqlSession.update("plusReadcount", num);
		}
		return sqlSession.selectOne("getBoard", num);
	}
	
	public int updateBoard(BoardDBBean dto) {
		return sqlSession.update("updateBoard", dto);
	}
}










