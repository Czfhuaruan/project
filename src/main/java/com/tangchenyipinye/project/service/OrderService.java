package com.tangchenyipinye.project.service;

import com.tangchenyipinye.project.pojo.Order;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;
public interface OrderService extends IService<Order>{

    public List<Order> selectById(String name);
    public void deleteOrderById(int id);
    public Order selectOrderById(int id);
    public List selectOrderByName(String title);
}
