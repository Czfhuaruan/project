package com.tangchenyipinye.project.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import com.tangchenyipinye.project.pojo.Admin;
import com.tangchenyipinye.project.pojo.Data_status;
import com.tangchenyipinye.project.pojo.Product;
import com.tangchenyipinye.project.pojo.Users;
import com.tangchenyipinye.project.service.AdminService;
import com.tangchenyipinye.project.service.DataService;
import com.tangchenyipinye.project.service.ProductService;
import com.tangchenyipinye.project.service.UsersService;
import com.tangchenyipinye.project.until.MD5until;
import com.tangchenyipinye.project.until.R;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.xml.crypto.Data;
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
    @Autowired
    DataService dataService;
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
        String s = MD5until.string2MD5(password);
        Admin admin = adminService.getUserByNameAndPass(username, password);
//        解密

        if (!s.equals(admin.getAdmin_password())) {

            return "redirect:../admin-login";
        }
        return "redirect:../allProducts";
    }

    @Secured({"ROLE_admins"})
    @RequestMapping("/admindb")
    public String admindbcontrol(Model model) {
            int i = 1;
        Data_status returndata=dataService.selectData_status(i);
        model.addAttribute("data",returndata);
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
    将商品信息发送到首页
    */
    @Secured({"ROLE_admins"})
    @GetMapping("/tangchenyipinye")
    public String tangchenyipinye(HttpServletRequest request){
        List<Product> productsList = productService.list();
        String username= usersService.getNameBySecurity();
        request.setAttribute("Username",username);
        request.setAttribute("productsList",productsList);
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
        return "redirect:../temapi/allProducts";
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

}

