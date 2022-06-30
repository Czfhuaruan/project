package com.tangchenyipinye.project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tangchenyipinye.project.pojo.Product;
import org.apache.ibatis.annotations.Select;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public interface ProductMapper extends BaseMapper<Product> {

    @Select("select * from t_product where category=#{name}")
    List<Product> selectBycategory(String name);
}
