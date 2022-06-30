package com.tangchenyipinye.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tangchenyipinye.project.mapper.ProductMapper;
import com.tangchenyipinye.project.pojo.Product;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface ProductService extends IService<Product> {
    public int addProduct(Product product);

    public List<Product> selectBycategory(String name);

}
