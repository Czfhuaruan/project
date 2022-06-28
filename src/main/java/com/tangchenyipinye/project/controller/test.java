package com.tangchenyipinye.project.controller;

import com.tangchenyipinye.project.pojo.Product;
import com.tangchenyipinye.project.service.ProductService;
import com.tangchenyipinye.project.until.R;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "商品管理")
@RestController
@RequestMapping("/api")
public class test {
    @Autowired
    ProductService productService;

    @Secured({"ROLE_admins"})
    @GetMapping("/hello")
    public R hello(){
        List<Product> productsList=productService.list();
        return R.ok().data("productsList",productsList);
    }

    @Secured({"ROLE_admins"})
    @GetMapping("/HHHH")
    public String HHH(){
        return "HHHH";
    }

    @Secured({"ROLE_ts"})
    @GetMapping("/DDDD")
    public String DDDD(){
        return "DDDD";
    }

    @Secured({"ROLE_admins"})
    @GetMapping("/logout")
    public String logout(){
        return "success";
    }
}
