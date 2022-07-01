package com.tangchenyipinye.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tangchenyipinye.project.pojo.Admin;
import com.tangchenyipinye.project.pojo.Users;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author devByHfj
 * @description com.tangchenyipinye.project.service
 * @create 2022-06-29 14:48
 */
public interface AdminService extends IService<Admin> {

    /*
        管理员添加页面，保证添加数据唯一
    */
    public List selectAdminnameByMap(String admin_name);

    /*
        登录页面，通过用户名查找密码
    */
    public int getUserByAdminname(String admin_name,String admin_password);

    /*
        通过id寻找管理员
    */
    public Admin selectAdminById(Integer id);

    /*
        通过id修改管理员信息
    */
    public void updateAdmin(Admin admin);

    /*
        添加管理员信息
    */
    public int addAdmin(Admin admin);

    /*
        根据id删除用户信息
    */
    public void deleteAdminById(int id);

    /*
        用户模糊查询功能接口
    */
    public List selectAdminByName(String adminName);
}
