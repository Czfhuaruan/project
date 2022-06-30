package com.tangchenyipinye.project.controller;

import com.tangchenyipinye.project.pojo.Admin;
import com.tangchenyipinye.project.pojo.Product;
import com.tangchenyipinye.project.pojo.Users;
import com.tangchenyipinye.project.service.AdminService;
import com.tangchenyipinye.project.service.ProductService;
import com.tangchenyipinye.project.service.UsersService;
import com.tangchenyipinye.project.until.MD5until;
import com.tangchenyipinye.project.until.R;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
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

    @Autowired
    UsersService usersService;
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

    @Secured({"ROLE_admins"})
    @GetMapping("/tangchenyipinye")
    public String tangchenyipinye(HttpServletRequest request){
        List<Product> productsList = productService.list();
        request.setAttribute("productsList",productsList);
        String username= usersService.getNameBySecurity();
       request.setAttribute("Username",username);
        return "tangchenyipinye";
    }

    @Secured({"ROLE_admins"})
    @GetMapping("/tangchenyipinye/shengcha")
    public String tangchenyipinyeshengcha(HttpServletRequest request){
        List<Product> productsList = productService.selectBycategory("生茶");
        request.setAttribute("productsList",productsList);
        String username= usersService.getNameBySecurity();
        request.setAttribute("Username",username);
        return "tangchenyipinye";
    }

    @Secured({"ROLE_admins"})
    @GetMapping("/tangchenyipinye/shucha")
    public String tangchenyipinyeshucha(HttpServletRequest request){
        List<Product> productsList = productService.selectBycategory("熟茶");
        request.setAttribute("productsList",productsList);
        String username= usersService.getNameBySecurity();
        request.setAttribute("Username",username);
        return "tangchenyipinye";
    }

    @Secured({"ROLE_admins"})
    @GetMapping("/tangchenyipinye/shengshu")
    public String tangchenyipinyeshengshu(HttpServletRequest request){
        List<Product> productsList = productService.selectBycategory("生熟套装");
        request.setAttribute("productsList",productsList);
        String username= usersService.getNameBySecurity();
        request.setAttribute("Username",username);
        return "tangchenyipinye";
    }


    //获取商品信息传到前台
    @Secured({"ROLE_admins"})
    @GetMapping("/allProducts")
    public String allProducts(Model model) {
        List<Product> productsList = productService.list();
        model.addAttribute("productsList", productsList);
        return "admin-goods-control";
    }

   /* //通过id删除商品
    @Secured({"ROLE_admins"})
    @RequestMapping("/deleteProductById")
    public String deleteProductById(int id) {
        productService.deleteProductById(id);
        return "redirect:/temapi/allProducts";
    }

    *//*
        点击修改商品，获取到商品id
        通过商品id查找商品后，跳转到商品页面修改商品
    *//*
    @Secured({"ROLE_admins"})
    @RequestMapping("/productToUpdate")
    public String toProductUpdate(Model model, int id) {
        Product product = productService.selectProductById(id);
        model.addAttribute("product", product);
        return "productUpdate";
    }


    //通过id修改商品
    @RequestMapping("/productUpdate")
    public String updateProductById() {

        return "redirect:productUpdate";
    }*/

    /*
    商品信息查询，
    标题，价格，类别，内容
    */
//    @Secured({"ROLE_admins"})
//    @RequestMapping(value = "/user/register")

}

