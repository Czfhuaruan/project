package com.tangchenyipinye.project.controller;

import io.swagger.annotations.Api;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Api(tags = "templates测试")
@Controller
@RequestMapping("temapi")
public class templatesTest {

    @Secured({"ROLE_admins"})
    @RequestMapping("/logout")
    public String logout(){
        return "success";
    }

    @Secured({"ROLE_admins"})
    @RequestMapping("/adminpage")
    public String adminpagetest(){
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

}

