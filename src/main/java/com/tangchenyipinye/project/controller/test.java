package com.tangchenyipinye.project.controller;

import com.tangchenyipinye.project.pojo.*;
import com.tangchenyipinye.project.service.*;
import com.tangchenyipinye.project.until.MD5until;
import com.tangchenyipinye.project.until.R;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.parameters.P;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

@CrossOrigin(allowCredentials = "true") //开放跨域访问
@Api(tags = "商品管理")
@RestController
@RequestMapping("api")
public class test {
    @Autowired
    ProductService productService;
    @Autowired
    UsersService usersService;
    @Autowired
    AdminService adminService;
    @Autowired
    ShopCarService shopCarService;
    @Autowired
    OrderInfoService orderInfoService;

    @Secured({"ROLE_admins"})
    @GetMapping("/hello")
    public R hello() {
        List<Product> productsList = productService.list();
        return R.ok().data("productsList", productsList);
    }

    @Secured({"ROLE_admins"})
    @GetMapping("/HHHH")
    public String HHH() {
        return "HHHH";
    }

    @Secured({"ROLE_ts"})
    @GetMapping("/DDDD")
    public String DDDD() {
        return "DDDD";
    }

    @Secured({"ROLE_admins"})
    @GetMapping("/logout")
    public String logout() {
        return "success";
    }


    @Secured({"ROLE_admins"})
    @GetMapping("/register")
    public R register() {
        List<Users> usersList = usersService.list();
        System.out.println(usersList);
        return R.ok().data("usersList", usersList);
    }

    //商品加入购物车
    @Secured({"ROLE_admins"})
    @GetMapping("/shopcar/addProduct")
    public R shopcaraddProduct(@RequestParam("prod") int prod){
        String username=usersService.getNameBySecurity();
        int i=shopCarService.selectshopcarByprod(prod,username);
        if(i == 0){
            int n=shopCarService.addproducttoshopcar(prod);
            System.out.println("shopcar是空的");
        }else {
            int m=shopCarService.updateproductshoper(prod);
            System.out.println("shopcar不是空的");

        }
        System.out.println("HHHHHH");
        return R.ok();
    }
    //查看个人购物车
    @Secured({"ROLE_admins"})
    @GetMapping("/shopcar/selectallproudct")
    public R shopcarselectallproudct(HttpServletRequest request){
        List<shopcar> usershopcars=shopCarService.selectshopcarByusername();
        request.setAttribute("usershopcars",usershopcars);
        return R.ok().data("usershopcars",usershopcars);
    }
    //清空个人购物车
    @Secured({"ROLE_admins"})
    @GetMapping("/shopcar/deleteproductfromusershopcar")
    public R deleteproductfromusershopcar(){
        int i=shopCarService.deleteproductfromusershopcar();
        if(i==0){
            return R.ok().data("msg","购物车是空的了！");
        }
        return R.ok().data("msg","购物车已被清空！");
    }
    //查询个人订单
    @Secured({"ROLE_admins"})
    @GetMapping("/shopcar/selectdingdanByusername")
    public R selectdingdanByusername(){
        String username=usersService.getNameBySecurity();
        List<OrderInfo> orderInfos= orderInfoService.selectByusername(username);
        return R.ok().data("userorderInfos",orderInfos);
    }

    //    增加管理员接口,管理员用户名唯一
    @Secured({"ROLE_admins"})
    @RequestMapping(value = "/user/addAdmin", method = RequestMethod.POST)
    public R addAdmin(@RequestParam("username") String admin_name,
                      @RequestParam("nickname") String admin_nickname,
                      @RequestParam("password") String admin_password,
                      @RequestParam("imgsrc") String admin_image) {
        List list = adminService.selectAdminnameByMap(admin_name);
        if (list.size() >= 1) {
            return R.ok().data("msg", "管理员已存在");
        } else {
            Admin admin = new Admin(admin_name, admin_nickname, MD5until.string2MD5(admin_password), admin_image);
            int i = adminService.addAdmin(admin);
            if (i != 1) {
                R.ok().error();
            }
        }
        return R.ok().data("msg", "插入成功");
    }


    //    商品添加接口
    @Secured({"ROLE_admins"})
    @GetMapping("/store/addProduct")
    public R addProduct(@RequestParam("title") String title,
                        @RequestParam("price") Integer price,
                        @RequestParam("category") String category,
                        @RequestParam("content") String content) {
        Product product = new Product(title,price,category,"http://localhost:8888/ref/img/c.jpg",10010,content);
        int i = productService.addProduct(product);
        if(i!=1){
            return R.ok().error();
        }
        return R.ok().data("msg","添加成功");
    }

    /*
     *********用户信息管理模块*********

     *********用户注册*********
     */

    /*
        用户注册，用户名唯一，不然注册失败
        注册成功后跳转到登录页面
    */
//    @Secured({"ROLE_admins"})
    @RequestMapping(value = "/user/register")
    public R register2(@RequestParam("username") String username,
                       @RequestParam("password") String password,
                       @RequestParam String password1,
                       @RequestParam("nickname") String nickname,
                       @RequestParam("mobile") String mobile,
                       @RequestParam("email") String email) {
        //        mybatis-plus下的通过map方式进行条件查询
        //        判断用户名是否已存在
        List list = usersService.selectByMap(username);
        System.out.println("11111111111");
        if (list.size() >= 1) {
            System.out.println("222222222222222222");
            return R.error().data("msg","用户不唯一");
        } else {
            Users users = new Users(username, MD5until.string2MD5(password), nickname, mobile,  email);
            int i = usersService.insert(users);
            if (i != 1) {
                return R.error().data("msg","添加失败");
            }
        }
        return R.ok();
    }


    //    管理员登录接口
    @Secured({"ROLE_admins"})
    @RequestMapping(value = "/admin/login", method = RequestMethod.POST)
    public R adminLogin(@RequestParam("username") String username,
                        @RequestParam("password") String password) {
        System.out.println(username + "----" + password);
//        解密
        String s = MD5until.string2MD5(password);
        int i = adminService.getUserByAdminname(username, s);
        if(i==0){
            return R.error().data("msg",i);
        }else{
            return R.ok().data("msg",i);
        }
    }


    /*//商品加入购物车
    @Secured({"ROLE_admins"})
    @GetMapping("/shopcar/addProduct")
    public R shopcaraddProduct(@RequestParam("prod") int prod){

        return R.ok();
    }*/
//        List<Users> usersList = usersService.list();
        /*
        String regex = "^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9])|(16[6]))\\d{8}$";
        Pattern p = Pattern.compile(regex);

        if(list.size()>=1){
            return R.ok().data("msg","用户已存在");
        }else if(!password.equals(password1)){
            return R.ok().data("msg","两次密码输入不正确");
        }else if (!(p.matcher(mobile)).matches()){
            return R.ok().data("msg","手机号码格式错误");
        }else {
            Users users = new Users();
            users.setUsername(username);
            users.setPassword(MD5until.string2MD5(password));
//            MD5until.convertMD5(MD5until.convertMD5(password));解密
            users.setNickname(nickname);
            users.setMobile(mobile);
            users.setAddress(address);
            users.setEmail(email);
            int i = usersService.insert(users);
            if (i != 1) {
                return R.error();
            }
        }*/

}
