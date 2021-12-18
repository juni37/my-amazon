package me.paulkim.shopPage.service;

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

}
