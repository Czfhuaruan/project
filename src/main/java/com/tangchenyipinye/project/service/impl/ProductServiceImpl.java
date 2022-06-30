package com.tangchenyipinye.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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

    /*
    查询所有商品
    */


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

    /*
    商品模糊查询功能接口
    */
    @Override
    public List selectProductByName(String title) {
        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(title!=null,"title",title);
        List<Product> list = productMapper.selectList(queryWrapper);
        return list;
    }


}