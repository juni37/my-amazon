package me.paulkim.shopPage.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.paulkim.shopPage.model.ProductDTO;

@Service
public class MallMapper {

	@Autowired
	private SqlSession sqlSession;
	
	public List<ProductDTO> listPspec(String pspec){
		return sqlSession.selectList("listPspec", pspec);
	}
	
	public List<ProductDTO> listPcode(String pcode){
		return sqlSession.selectList("listPcode", pcode);
	}
}
