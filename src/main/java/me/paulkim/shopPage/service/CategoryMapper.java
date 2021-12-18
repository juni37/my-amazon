package me.paulkim.shopPage.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.paulkim.shopPage.model.CategoryDTO;

@Service
public class CategoryMapper {
	  
	@Autowired
	private SqlSession sqlSession;
	
	public int insertCategory(CategoryDTO dto) {
		return sqlSession.insert("insertCategory", dto); 
	}
	
	public int deleteCategory(int cnum) {
		return sqlSession.delete("deleteCategory", cnum);
	}
	
	public int updateCategory(CategoryDTO dto) {
		return sqlSession.update("updateCategory", dto);
	}
	
	public CategoryDTO getCategory(Map<String, String> map) {
		return sqlSession.selectOne("getCategory", map);
	}
	
	public List<CategoryDTO> listCategory(){
		return sqlSession.selectList("listCategory");
	}
}










