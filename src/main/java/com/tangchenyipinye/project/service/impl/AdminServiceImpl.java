package com.tangchenyipinye.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tangchenyipinye.project.mapper.AdminMapper;
import com.tangchenyipinye.project.pojo.Admin;
import com.tangchenyipinye.project.pojo.Users;
import com.tangchenyipinye.project.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author devByHfj
 * @description com.tangchenyipinye.project.service.impl
 * @create 2022-06-29 14:49
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {
    @Autowired
    AdminMapper adminMapper;

    /*
        管理员添加页面，保证添加数据唯一
    */
    @Override
    public List selectAdminnameByMap(String admin_name) {
        Map map = new HashMap<>();
        map.put("admin_name",admin_name);
        List list = adminMapper.selectByMap(map);
        return list;
    }

    /*
        登录页面，通过用户名查找密码
    */
    @Override
    public int getUserByAdminname(String admin_name,String admin_password) {
        QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("admin_name",admin_name);
        Integer i = adminMapper.selectCount(queryWrapper);
        return i;
    }

    /*
        通过id寻找管理员
    */
    public Admin selectAdminById(Integer id){
        Admin admin = adminMapper.selectById(id);
        return admin;
    }

    /*
        通过id修改管理员信息
    */
    public void updateAdmin(Admin admin){
        adminMapper.update(admin);
    }

    /*
        添加管理员信息
    */
    @Override
    public int addAdmin(Admin admin) {
        int i = adminMapper.insert(admin);
        return i;
    }

    /*
        根据id删除用户信息
    */
    public void deleteAdminById(int id){
        adminMapper.deleteById(id);
    }

    /*
        用户模糊查询功能接口
    */
    public List selectAdminByName(String adminName){
        QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(adminName!=null,"admin_name",adminName);
        List<Admin> admins = adminMapper.selectList(queryWrapper);
        return admins;
    }


}
