package com.tangchenyipinye.project.controller;

import com.tangchenyipinye.project.pojo.Product;
import com.tangchenyipinye.project.pojo.Users;
import com.tangchenyipinye.project.service.ProductService;
import com.tangchenyipinye.project.service.UsersService;
import com.tangchenyipinye.project.until.MD5until;
import com.tangchenyipinye.project.until.R;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.regex.Pattern;

@Api(tags = "商品管理")
@RestController
@RequestMapping("/api")
public class test {
    @Autowired
    ProductService productService;
    @Autowired
    UsersService usersService;

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


    @Secured({"ROLE_admins"})
    @GetMapping("/register")
    public R register(){
        List<Users> usersList = usersService.list();
        System.out.println(usersList);
        return R.ok().data("usersList",usersList);
    }

    @Secured({"ROLE_admins"})
    @RequestMapping(value = "/user/register",method = RequestMethod.POST)
    public R register2(@RequestParam("username") String username,@RequestParam String password,@RequestParam String password1,@RequestParam String nickname,@RequestParam String mobile,@RequestParam String address){

//        mybatis-plus下的通过map方式进行条件查询
//        判断用户名是否已存在
//        List<Users> usersList = usersService.list();
        String regex = "^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9])|(16[6]))\\d{8}$";
        Pattern p = Pattern.compile(regex);
        List list = usersService.selectByMap(username);
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
            int i = usersService.insert(users);
            if (i != 1) {
                return R.error();
            }
        }
        return R.ok().data("msg","添加成功");
    }
}
