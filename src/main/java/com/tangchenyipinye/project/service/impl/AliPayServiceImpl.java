package com.tangchenyipinye.project.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.response.AlipayTradePagePayResponse;
import com.tangchenyipinye.project.pojo.OrderInfo;
import com.tangchenyipinye.project.service.AliPayService;
import com.tangchenyipinye.project.service.OrderInfoService;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;

@Service
public class AliPayServiceImpl implements AliPayService {

    @Resource
    private OrderInfoService orderInfoService;

    @Resource
    private AlipayClient alipayClient;

    @Resource
    private Environment config;
    @Transactional
    @Override
    public String tradeCreate(String Username) {
        //购物车生成订单
        OrderInfo orderInfo=orderInfoService.createByusername(Username);

        try {
            //官方的开发文档
            //     AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do","app_id","your private_key","json","GBK","alipay_public_key","RSA2");
            AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
            //支付完成后，支付宝向用户发起异步通知的地址
//        request.setNotifyUrl("");
            //支付完成后，我们想让页面跳转回的页面，配置returnUrl
            request.setReturnUrl(config.getProperty("alipay.return-url"));
            JSONObject bizContent = new JSONObject();
            bizContent.put("out_trade_no", orderInfo.getOrderNo()); //订单号
            BigDecimal totalprice = new BigDecimal(orderInfo.getTotalFee().toString()).divide(new BigDecimal("100")); //int类型转换为decimal类型
            bizContent.put("total_amount", totalprice); //总价(元)
            bizContent.put("subject", orderInfo.getTitle()); //主题
            bizContent.put("product_code", "FAST_INSTANT_TRADE_PAY"); //响应码-电脑支付的响应码
            request.setBizContent(bizContent.toString());

            //调用支付宝接口
            AlipayTradePagePayResponse response = alipayClient.pageExecute(request);
            //判断是否调用成功，并获取返回结果
            if(response.isSuccess()){
                System.out.println("调用成功");
                orderInfo.setOrderStatus("已支付");
                System.out.println("结果==》"+response.getBody());
            } else {
                System.out.println("调用失败");
                System.out.println("结果码==》"+response.getCode()+"结果信息==》"+response.getMsg());
                throw new RuntimeException("支付接口调用失败");
            }
            return response.getBody();
        } catch (AlipayApiException e) {
            e.printStackTrace();
            throw new RuntimeException("创建支付失败");
        }
    }
}
