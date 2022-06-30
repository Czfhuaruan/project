package com.tangchenyipinye.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tangchenyipinye.project.mapper.ProductMapper;
import com.tangchenyipinye.project.pojo.Product;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface ProductService extends IService<Product> {

    /*
        添加商品
    */
    public int addProduct(Product product);

    public List<Product> selectBycategory(String name);


    /*
        根据id查找商品信息
    */
    public void deleteProductById(int id);

    /*
        更新商品信息
    */
    public void updateProduct(Product product);

    /*
        根据id查找商品信息
    */
    public Product selectProductById(int id);

    /*
        商品模糊查询功能接口
    */
    public List selectProductByName(String title);
}
