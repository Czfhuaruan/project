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

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

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



//    进入商品添加页面
    @Secured({"ROLE_admins"})
    @RequestMapping("addProduct")
    public String addProduct() {
        return "addProduct";
    }


    //回到首页
    @Secured({"ROLE_admins"})
    @RequestMapping("/adminpage")
    public String adminpage() {
        return "admin-homepage";
    }


    /*
                 *********商品信息管理模块*********

                 *********1.获取所有商品信息
                 *********2.通过id删除商品信息
                 *********3.获取修改商品id
                 *********4.修改商品
                 *********5.添加商品
                 *********6.商品模糊查询
    */
    /*
        将商品信息发送到首页
    */
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
        修改此id的商品
    */
    @Secured({"ROLE_admins"})
    @RequestMapping("/productUpdate")
    public String updateProduct(Product product) {
        productService.updateProduct(product);
        return "redirect:/temapi/allProducts";
    }

    /*
        点击商品添加，进入商品添加页面，进行商品添加
        访问页面路径：http://localhost:8888/temapi/addProduct
        成功后重定向：http://localhost:8888/temapi/allProducts
    */
    @Secured({"ROLE_admins"})
    @RequestMapping(value = "/admin/addProduct",method = RequestMethod.POST)
    public String addProduct(@RequestParam("title") String title,
                        @RequestParam("price") Integer price,
                        @RequestParam("category") String category,
                        @RequestParam("content") String content) {
        Product product = new Product(title,price,category,"http://localhost:8888/ref/img/c.jpg",10010,content);
        int i = productService.addProduct(product);
        if(i!=1){
            //添加商品失败 进入错误页面
        }
        return "redirect:/temapi/allProducts";
    }

    /*
        通过商品名称模糊查询商品信息
        将找到的结果返回到所有商品页面
        重定向：
    */
    @Secured("ROLE_admins")
    @RequestMapping(value = "/selectProductByName",method = RequestMethod.POST)
    public String selectProductByName(Model model,@RequestParam("title") String title){
        List list = productService.selectProductByName(title);
        model.addAttribute("productsList", list);
        return "admin-goods-control";
    }



    /*
             *********商品信息管理模块*********

             *********1.获取所有用户信息
             *********2.通过id删除用户信息
             *********3.获取修改用户id
             *********4.修改用户
             *********5.添加用户
             *********6.用户模糊查询
    */

    /*
        获取用户信息发送到用户管理页面
        跳转到“用户管理页面”；
    */
    @Secured({"ROLE_admins"})
    @GetMapping("/allUsers")
    public String allUsers(Model model) {
        List<Users> usersList = usersService.list();
        model.addAttribute("usersList", usersList);
        return "admin-user-control";
    }

    /*
        点击修改用户后，获取到用户id
        跳转到用户修改页面修改该用户信息
        用户修改页面：userUpdate.html
    */
    @Secured({"ROLE_admins"})
    @RequestMapping("/userToUpdate")
    public String toUserUpdate(Model model, int id) {
        Users users = usersService.selectUserById(id);
        model.addAttribute("users", users);
        return "userUpdate";
    }

    /*
        取到用户id后，进入修改页面
        修改此id的用户
        修改成功后回到商品管理页面
    */
    @Secured({"ROLE_admins"})
    @RequestMapping("/userUpdate")
    public String updateUser(Users users) {
        usersService.updateUser(users);
        return "redirect:/temapi/allUsers";
    }

    /*
        进入商品添加页面
    */
    @Secured({"ROLE_admins"})
    @RequestMapping("toAddUser")
    public String toUser() {
        return "addUser";
    }

    /*
        点击商品添加，进入商品添加页面，进行商品添加
        访问页面路径：http://localhost:8888/temapi/addProduct
        成功后重定向：http://localhost:8888/temapi/allProducts
    */
    @Secured({"ROLE_admins"})
    @RequestMapping(value = "/admin/addUser",method = RequestMethod.POST)
    public String addUser(@RequestParam("username") String username,
                             @RequestParam("nickname") String nickname,
                             @RequestParam("mobile") String mobile,
                             @RequestParam("address") String address,
                             @RequestParam("email") String email) {
        Users users = new Users();
        users.setUsername(username);
        users.setNickname(nickname);
        users.setMobile(mobile);
        users.setAddress(address);
        users.setEmail(email);
        int i = usersService.addUser(users);
        if(i!=1){
            //添加商品失败 进入错误页面
        }
        return "redirect:/temapi/allUsers";
    }

    /*
        通过id删除用户
    */
    @Secured({"ROLE_admins"})
    @RequestMapping("/deleteUserById")
    public String deleteUserById(int id) {
        usersService.deleteUserById(id);
        return "redirect:/temapi/allUsers";
    }

    /*
        通过用户名称模糊查询用户信息
        将找到的结果返回到所有用户页面
        重定向：
    */
    @Secured("ROLE_admins")
    @RequestMapping(value = "/selectUserByName",method = RequestMethod.POST)
    public String selectUserByName(Model model,@RequestParam("username") String username){
        List list = usersService.selectUserByName(username);
        model.addAttribute("usersList", list);
        return "admin-user-control";
    }



    /*
        用户注册，用户名唯一，不然注册失败
        注册成功后跳转到登录页面
    */
    /*@Secured({"ROLE_admins"})
    @RequestMapping(value = "/user/register", method = RequestMethod.POST)
    public String register(@RequestParam("username") String username,
                       @RequestParam("password") String password,
                       @RequestParam String password1,
                       @RequestParam("nickname") String nickname,
                       @RequestParam("mobile") String mobile,
                       @RequestParam("email") String email) {
        //        mybatis-plus下的通过map方式进行条件查询
        //        判断用户名是否已存在
        List list = usersService.selectByMap(username);
        if (list.size() >= 1) {

        } else {
            Users users = new Users(username, MD5until.string2MD5(password), nickname, mobile,  email);
            int i = usersService.insert(users);
            if (i != 1) {

            }
        }
        return "redirect:../../login.html";

    }*/

}

