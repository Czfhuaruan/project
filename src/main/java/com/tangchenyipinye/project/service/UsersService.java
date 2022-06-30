package com.tangchenyipinye.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tangchenyipinye.project.mapper.UsersMapper;
import com.tangchenyipinye.project.pojo.Users;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author devByHfj
 * @description com.tangchenyipinye.project.service
 * @create 2022-06-28 19:47
 */
public interface UsersService extends IService<Users> {
    /*
        添加用户
    */
    public int insert(Users users);

    /*
        通过用户查找用户
    */
    public List selectByMap(String username);

    /*
        czzf写的东西
    */
    public String getNameBySecurity();

    /*
        通过id寻找用户商品
    */
    public Users selectUserById(Integer id);

    /*
        通过id修改用户信息
    */
    public void updateUser(Users users);

    /*
        添加用户信息
    */
    public int addUser(Users users);

    /*
        根据id查找用户信息
    */
    public void deleteUserById(int id);

    /*
        用户模糊查询功能接口
    */
    public List selectUserByName(String title);

}
