package com.tangchenyipinye.project.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tangchenyipinye.project.mapper.UsersMapper;
import com.tangchenyipinye.project.pojo.Users;
import com.tangchenyipinye.project.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author devByHfj
 * @description com.tangchenyipinye.project.service.impl
 * @create 2022-06-28 19:48
 */
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements UsersService {
    @Autowired
    UsersMapper usersMapper;

    @Override
    public int insert(Users users) {
        int i = usersMapper.insert(users);
        return i;
    }

//    查询用户名是否已存在
    @Override
    public List selectByMap(String username) {
        Map map = new HashMap();
        map.put("username",username);
        List map1 = usersMapper.selectByMap(map);

        return map1;
    }
}
