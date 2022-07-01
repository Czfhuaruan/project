package com.tangchenyipinye.project.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tangchenyipinye.project.mapper.OrderInfoMapper;
import com.tangchenyipinye.project.pojo.OrderInfo;
import com.tangchenyipinye.project.pojo.shopcar;
import com.tangchenyipinye.project.service.OrderInfoService;
import com.tangchenyipinye.project.service.ShopCarService;
import com.tangchenyipinye.project.service.UsersService;
import com.tangchenyipinye.project.until.OrderUntils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderInfoServiceImpl  extends ServiceImpl<OrderInfoMapper, OrderInfo> implements OrderInfoService {

    @Autowired
    OrderInfoMapper orderInfoMapper;
    @Autowired
    ShopCarService shopCarService;

    public OrderInfo createByusername(String username){
       List<shopcar> shopcars=shopCarService.selectshopcarByusername();
       OrderInfo orderInfo=new OrderInfo();
       String title="";
       int total_fee=0;
       for(shopcar shopcar : shopcars){
         title += shopcar.getPname();
         total_fee +=shopcar.getTotal();
       }
       orderInfo.setTitle(title);
       orderInfo.setOrderNo(OrderUntils.getOrderNo());
       orderInfo.setTotalFee(total_fee);
        orderInfo.setOrderStatus("未支付");
        orderInfo.setPaymentType("支付宝");
        orderInfo.setUsername(username);
        baseMapper.insert(orderInfo);
        return orderInfo;
    }

    @Override
    public List<OrderInfo> selectByusername(String username) {
        Map m=new HashMap();
        m.put("username",username);
        List<OrderInfo> orderInfos=  orderInfoMapper.selectByMap(m);
        return orderInfos;
    }
}
