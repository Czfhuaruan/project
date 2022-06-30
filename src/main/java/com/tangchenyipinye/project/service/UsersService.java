package com.tangchenyipinye.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tangchenyipinye.project.mapper.UsersMapper;
import com.tangchenyipinye.project.pojo.Users;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author devByHfj
 * @description com.tangchenyipinye.project.service
 * @create 2022-06-28 19:47
 */
public interface UsersService extends IService<Users> {
//    @Autowired
//    UsersMapper usersMapper;
    public int insert(Users users);

    public List selectByMap(String username);

    public String getNameBySecurity();
}
