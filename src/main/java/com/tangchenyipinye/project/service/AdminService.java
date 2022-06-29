package com.tangchenyipinye.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tangchenyipinye.project.pojo.Admin;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author devByHfj
 * @description com.tangchenyipinye.project.service
 * @create 2022-06-29 14:48
 */
public interface AdminService extends IService<Admin> {
    public int addAdmin(Admin admin);

    public List selectAdminnameByMap(String admin_name);
}
