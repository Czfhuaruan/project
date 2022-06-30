package com.tangchenyipinye.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tangchenyipinye.project.pojo.Product;

import java.util.List;

public interface ProductService extends IService<Product> {
    public int addProduct(Product product);

    public void deleteProductById(int id);

//    修改商品
    public void updateProduct(Product product);

//    通过id查找商品
    public Product selectProductById(int id);
}
