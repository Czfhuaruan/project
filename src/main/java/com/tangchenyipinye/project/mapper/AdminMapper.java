package com.tangchenyipinye.project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tangchenyipinye.project.pojo.Admin;
import com.tangchenyipinye.project.pojo.Users;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.context.annotation.Configuration;

/**
 * @author devByHfj
 * @description com.tangchenyipinye.project.mapper
 * @create 2022-06-29 14:50
 */
@Configuration
public interface AdminMapper extends BaseMapper<Admin> {
    /**
     *更新商品信息
     * @param admin
     */
    @Update({"update admin set admin_name = #{admin.adminName}, admin_nickname = #{admin.adminNickname},admin_password=#{admin.adminPassword} where id = #{admin.id}"})
    void update(@Param("admin") Admin admin);
}
