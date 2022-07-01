package com.tangchenyipinye.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tangchenyipinye.project.pojo.OrderInfo;

import java.util.List;


public interface OrderInfoService extends IService<OrderInfo> {

    OrderInfo createByusername(String username);

    List<OrderInfo> selectByusername(String username);
}
