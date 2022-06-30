package com.tangchenyipinye.project.controller;

import com.tangchenyipinye.project.pojo.Admin;
import com.tangchenyipinye.project.pojo.Product;
import com.tangchenyipinye.project.service.AdminService;
import com.tangchenyipinye.project.service.ProductService;
import com.tangchenyipinye.project.until.MD5until;
import com.tangchenyipinye.project.until.R;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String logout() {
        return "success";
    }

    //    管理员登录接口
    @Secured({"ROLE_admins"})
    @RequestMapping(value = "/admin/login", method = RequestMethod.POST)
    public String adminRegister(@RequestParam("username") String username,
                                @RequestParam("password") String password) {
        System.out.println(username + "----" + password);
        Admin admin = adminService.getUserByNameAndPass(username, password);
//        解密
        String s = MD5until.string2MD5(password);
        if (!s.equals(admin.getAdmin_password())) {
            return "success";
        }
        return "admin-homepage";
    }

    @Secured({"ROLE_admins"})
    @RequestMapping("/admindb")
    public String admindbcontrol() {
        return "admin-db-control";
    }

    @Secured({"ROLE_admins"})
    @RequestMapping("/admincategory")
    public String admincategory() {
        return "admin-category-control";
    }

    @Secured({"ROLE_admins"})
    @RequestMapping("/adminadmin")
    public String admin() {
        return "admin-admin-control";
    }

    @Secured({"ROLE_admins"})
    @RequestMapping("/adminorders")
    public String adminorders() {
        return "admin-orders-control";
    }

    @Secured({"ROLE_admins"})
    @RequestMapping("/admingoods")
    public String admingoods() {
        return "admin-goods-control";
    }

    @Secured({"ROLE_admins"})
    @RequestMapping("/adminuser")
    public String adminuser() {
        return "admin-user-control";
    }
    //回到首页
    @Secured({"ROLE_admins"})
    @RequestMapping("/adminpage")
    public String adminpage() {
        return "admin-homepage";
    }
    /*
    将商品信息发送到首页
    */
    @Secured({"ROLE_admins"})
    @GetMapping("/tangchenyipinye")
    public String tangchenyipinye(HttpServletRequest request){
        List<Product> productsList = productService.list();
        request.setAttribute("productsList",productsList);
        return "tangchenyipinye";
    }

    /*
        获取商品信息发送到商品管理页面
        跳转到“商品管理页面”；
    */
    @Secured({"ROLE_admins"})
    @GetMapping("/allProducts")
    public String allProducts(Model model) {
        List<Product> productsList = productService.list();
        model.addAttribute("productsList", productsList);
        return "admin-goods-control";
    }

    /*
    通过id删除商品
    */
    @Secured({"ROLE_admins"})
    @RequestMapping("/deleteProductById")
    public String deleteProductById(int id) {
        productService.deleteProductById(id);
        return "redirect:/temapi/allProducts";
    }

    /*
        点击修改商品后，获取到商品id
        跳转到商品修改页面修改该商品
    */
    @Secured({"ROLE_admins"})
    @RequestMapping("/productToUpdate")
    public String toProductUpdate(Model model, int id) {
        Product product = productService.selectProductById(id);
        model.addAttribute("product", product);
        return "productUpdate";
    }

    /*
        取到商品id后，进入修改页面
        删除此id的商品
    */
    @Secured({"ROLE_admins"})
    @RequestMapping("/productUpdate")
    public String updateProduct(Product product) {
        productService.updateProduct(product);
        return "redirect:/temapi/allProducts";
    }

    /*
    商品信息查询，
    标题，价格，类别，内容
    */
//    @Secured({"ROLE_admins"})
//    @RequestMapping(value = "/user/register")

}

