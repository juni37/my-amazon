package me.paulkim.shopPage.service;

import me.paulkim.shopPage.model.ProductDTO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MallMapper {

    @Autowired
    private SqlSession sqlSession;

    public List<ProductDTO> listPspec(String pspec) {
        return sqlSession.selectList("listPspec", pspec);
    }
}

