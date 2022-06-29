package com.tangchenyipinye.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tangchenyipinye.project.pojo.Product;

import java.util.List;

public interface ProductService extends IService<Product> {
    public int addProduct(Product product);
}
