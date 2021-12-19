package me.paulkim.shopPage.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.paulkim.shopPage.model.ProductDTO;

@Service
public class ProductMapper {
	
	@Autowired
	private SqlSession sqlSession;
	
	public int insertProduct(ProductDTO dto) {
		return sqlSession.insert("insertProduct", dto); 
	}
	
	public List<ProductDTO> listProduct(){
		return sqlSession.selectList("listProduct");
	}
	
	public ProductDTO getProduct(int pnum) {
		return sqlSession.selectOne("getProduct", pnum);
	}
	
	public int deleteProduct(String pnum) {
		return sqlSession.delete("deleteProduct", Integer.parseInt(pnum));
	}
	
	public int updateProduct(ProductDTO dto) {
		return sqlSession.update("updateProduct", dto);
	}
	
	public List<ProductDTO> findCateProduct(Map<String, String> map){
		return sqlSession.selectList("findCateProduct", map);
	}
	public List<ProductDTO> findProdProduct(Map<String, String> map){
		return sqlSession.selectList("findProdProduct", map);
	}
}




