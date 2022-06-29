package com.tangchenyipinye.project.controller;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.tangchenyipinye.project.pojo.Admin;
import com.tangchenyipinye.project.pojo.Product;
import com.tangchenyipinye.project.service.AdminService;
import com.tangchenyipinye.project.service.ProductService;
import com.tangchenyipinye.project.until.MD5until;
import com.tangchenyipinye.project.until.R;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cassandra.CassandraProperties;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Api(tags = "templates测试")
@Controller
@RequestMapping("temapi")
public class templatesTest {
    @Autowired
    AdminService adminService;
    @Autowired
    ProductService productService;

    @Secured({"ROLE_admins"})
    @RequestMapping("/logout")
    public String logout(){
        return "success";
    }

    //    管理员登录接口
    @Secured({"ROLE_admins"})
    @RequestMapping(value = "/admin/login",method = RequestMethod.POST)
    public String adminRegister(@RequestParam("username") String username,
                           @RequestParam("password") String password) {
        System.out.println(username+"----"+password);
        Admin admin = adminService.getUserByNameAndPass(username, password);
//        解密
        String s = MD5until.string2MD5(password);
        if(!s.equals(admin.getAdmin_password())){
            return "success";
        }
        return "admin-homepage";
    }

    @Secured({"ROLE_admins"})
    @RequestMapping("/admindb")
    public String admindbcontrol(){
        return "admin-db-control";
    }
    @Secured({"ROLE_admins"})
    @RequestMapping("/admincategory")
    public String admincategory(){
        return "admin-category-control";
    }

    @Secured({"ROLE_admins"})
    @RequestMapping("/adminadmin")
    public String admin(){
        return "admin-admin-control";
    }

    @Secured({"ROLE_admins"})
    @RequestMapping("/adminorders")
    public String adminorders(){
        return "admin-orders-control";
    }

    @Secured({"ROLE_admins"})
    @RequestMapping("/admingoods")
    public String admingoods(){
        return "admin-goods-control";
    }
    @Secured({"ROLE_admins"})
    @RequestMapping("/adminuser")
    public String adminuser(){
        return "admin-user-control";
    }

    @Secured({"ROLE_admins"})
    @GetMapping("/tangchenyipinye")
    public String tangchenyipinye(HttpServletRequest request){
        List<Product> productsList = productService.list();
        request.setAttribute("productsList",productsList);
        return "tangchenyipinye";
    }




    /*
    商品信息查询，
    标题，价格，类别，内容
    */
//    @Secured({"ROLE_admins"})
//    @RequestMapping(value = "/user/register")

}

