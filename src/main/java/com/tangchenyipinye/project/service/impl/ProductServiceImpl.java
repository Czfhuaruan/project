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

    /*
    查询所有商品
    */
    @Override
    public int addProduct(Product product) {
        int i = productMapper.insert(product);
        return i;
    }

    /*
    根据id查找商品信息
    */
    @Override
    public void deleteProductById(int id) {
        productMapper.deleteById(id);
    }

    /*
    更新商品信息
    */
    @Override
    public void updateProduct(Product product) {
        productMapper.update(product);
    }

    /*
    根据id查找商品信息
    */
    @Override
    public Product selectProductById(int id) {
        Product product = productMapper.selectById(id);
        return product;
    }
}