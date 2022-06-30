package com.tangchenyipinye.project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tangchenyipinye.project.pojo.shopcar;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface ShopCarMapper extends BaseMapper<shopcar> {
    @Select("select count(*) from shopcar where prod =#{prod} and username =#{username}")
    int selectByprod(int prod,String username);

    @Update("update shopcar set count =count+1,total =total+price where prod=#{prod} and username=#{username}")
    int updateproductshoper(int prod,String username);
}
