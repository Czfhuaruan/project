package com.tangchenyipinye.project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tangchenyipinye.project.pojo.Product;
import com.tangchenyipinye.project.pojo.Users;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

@Configuration
public interface UsersMapper extends BaseMapper<Users> {
    /**
     *更新商品信息
     * @param users
     */
    @Update({"update users set username = #{users.username}, nickname = #{users.nickname},mobile=#{users.mobile},address = #{users.address},email=#{users.email} where id = #{users.id}"})
    void update(@Param("users") Users users);
}
