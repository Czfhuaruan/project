package com.tangchenyipinye.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tangchenyipinye.project.pojo.shopcar;

import java.util.List;


public interface ShopCarService extends IService<shopcar> {
    //查找个人购物车是否有该产品
     int selectshopcarByprod(int prod,String username);

     //添加该产品到购物车
     int addproducttoshopcar(int prod);

     //更新购物车的该产品的数量和总价(增加)
    int updateproductshoper(int prod);

    //查找个人的购物车所有产品
    List<shopcar> selectshopcarByusername();
}
