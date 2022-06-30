package com.tangchenyipinye.project.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tangchenyipinye.project.mapper.ShopCarMapper;
import com.tangchenyipinye.project.pojo.shopcar;
import com.tangchenyipinye.project.service.ShopCarService;
import org.springframework.stereotype.Service;

@Service
public class ShopCarServiceImpl extends ServiceImpl<ShopCarMapper, shopcar> implements ShopCarService {
}
