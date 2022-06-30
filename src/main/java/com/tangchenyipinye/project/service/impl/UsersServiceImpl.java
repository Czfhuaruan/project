package com.tangchenyipinye.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tangchenyipinye.project.mapper.UsersMapper;
import com.tangchenyipinye.project.pojo.Product;
import com.tangchenyipinye.project.pojo.Users;
import com.tangchenyipinye.project.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
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

    /*
        添加用户
    */
    @Override
    public int insert(Users users) {
        int i = usersMapper.insert(users);
        return i;
    }

    /*
        通过用户查找用户
    */
    @Override
    public List selectByMap(String username) {
        Map map = new HashMap();
        map.put("username",username);
        List map1 = usersMapper.selectByMap(map);

        return map1;
    }

    /*
        获取用户信息通过Security
    */
    @Override
    public String getNameBySecurity() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user.getUsername();
    }

    /*
        通过id寻找用户商品
    */
    @Override
    public Users selectUserById(Integer id) {
        Users users = usersMapper.selectById(id);
        return users;
    }

    /*
        通过id修改用户信息
    */
    public void updateUser(Users users){
        usersMapper.update(users);
    }

    /*
        添加用户信息
    */
    public int addUser(Users users){
        int i = usersMapper.insert(users);
        return i;
    }

    /*
        根据id查找商品信息
    */
    public void deleteUserById(int id){
        usersMapper.deleteById(id);
    }

    /*
        用户模糊查询功能接口
    */
    public List selectUserByName(String username){
        QueryWrapper<Users> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(username!=null,"username",username);
        List<Users> users = usersMapper.selectList(queryWrapper);
        return users;
    }
}
