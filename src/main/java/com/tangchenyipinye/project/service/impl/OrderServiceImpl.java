package com.tangchenyipinye.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tangchenyipinye.project.mapper.OrderMapper;
import com.tangchenyipinye.project.service.OrderService;
import com.tangchenyipinye.project.pojo.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.Query;
import java.util.List;

@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper,Order> implements OrderService {
    @Autowired
    OrderMapper orderMapper;

    @Override
    public List<Order> selectById(String name) {
        List<Order> orders=orderMapper.selectById(name);
        return orders;
    }

    @Override
    public void deleteOrderById(int id) {

        orderMapper.deleteById(id);
    }

    @Override
    public Order selectOrderById(int id) {
        Order order = orderMapper.selectById(id);
        return order;
    }

    @Override
    public List selectOrderByName(String title) {
        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(title!=null,"title",title);
        List<Order> list = orderMapper.selectList(queryWrapper);
        return list;
    }
}
