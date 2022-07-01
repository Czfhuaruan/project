package com.tangchenyipinye.project.controller;

import com.tangchenyipinye.project.pojo.OrderInfo;
import com.tangchenyipinye.project.service.AliPayService;
import com.tangchenyipinye.project.service.OrderInfoService;
import com.tangchenyipinye.project.service.UsersService;
import com.tangchenyipinye.project.until.R;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/ali-pay")
@Api("支付宝支付")
public class AliPayController {
    @Autowired
    private AliPayService aliPayService;

    @Autowired
    OrderInfoService orderInfoService;

    @Autowired
    UsersService usersService;
    //支付页面接口的调用
    @PostMapping("/trade/page/pay")
    public R tradePagePay(String Username){
        //支付宝开放平台接受request请求对象后
        //会为开发者生成一个html形式的form表单，包含自动提交的脚本
        Username=usersService.getNameBySecurity();
        String formStr=aliPayService.tradeCreate(Username);
        //将from表单字符串返回给前端程序，之后前端将会调用自动提交脚本，进行表单的提交
        //表单会自动提交到支付宝开放平台中，为用户展示一个支付页面
        return R.ok().data("formStr",formStr);
    }

//    @PostMapping("/test/createOrder")
//    public OrderInfo crrr(String username){
//      OrderInfo orderInfo= orderInfoService.createByusername(username);
//      return orderInfo;
//    }
}
