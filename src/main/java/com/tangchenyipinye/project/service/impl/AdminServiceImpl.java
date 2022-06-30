package com.tangchenyipinye.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tangchenyipinye.project.mapper.AdminMapper;
import com.tangchenyipinye.project.pojo.Admin;
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

    @Override
    public int addAdmin(Admin admin) {
        int i = adminMapper.insert(admin);
        return i;
    }

    @Override
    public List selectAdminnameByMap(String admin_name) {
        Map map = new HashMap<>();
        map.put("admin_name",admin_name);
        List list = adminMapper.selectByMap(map);
        return list;
    }

    @Override
    public Admin getUserByAdminname(String admin_name) {
        QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("admin_name",admin_name);
        Admin admin = adminMapper.selectOne(queryWrapper);
        return admin;
    }
}
