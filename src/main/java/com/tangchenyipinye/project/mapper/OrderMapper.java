package com.tangchenyipinye.project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tangchenyipinye.project.pojo.Order;
import org.apache.ibatis.annotations.Select;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public interface OrderMapper extends BaseMapper<Order> {
    @Select("select * from t_order_info where id=#{name}")
    List<Order> selectById(String name);
}
