package com.tangchenyipinye.project.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tangchenyipinye.project.mapper.ProductMapper;
import com.tangchenyipinye.project.pojo.Product;
import com.tangchenyipinye.project.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {
    @Autowired
    ProductMapper productMapper;

    @Override
    public int addProduct(Product product) {
        int i = productMapper.insert(product);
        return i;
    }

    @Override
    public List<Product> selectBycategory(String name) {
        List<Product> products=productMapper.selectBycategory(name);
        return products;
    }

    @Override
    public void deleteProductById(int id) {

    }

    @Override
    public void updateProduct(Product product) {

    }

    @Override
    public Product selectProductById(int id) {
        return null;
    }

    @Override
    public List selectProductByName(String title) {
        return null;
    }


}