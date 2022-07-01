package com.tangchenyipinye.project.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tangchenyipinye.project.mapper.ProductMapper;
import com.tangchenyipinye.project.mapper.ShopCarMapper;
import com.tangchenyipinye.project.pojo.Product;
import com.tangchenyipinye.project.pojo.shopcar;
import com.tangchenyipinye.project.service.ShopCarService;
import com.tangchenyipinye.project.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ShopCarServiceImpl extends ServiceImpl<ShopCarMapper, shopcar> implements ShopCarService {
    @Autowired
    ShopCarMapper shopCarMapper;
    @Autowired
    ProductMapper productMapper;
    @Autowired
    UsersService usersService;
    @Override
    public int selectshopcarByprod(int prod,String username) {
        int shopcar =  shopCarMapper.selectByprod(prod,username);
        return shopcar;
    }

    @Override
    public int addproducttoshopcar(int prod) {
        Product product=productMapper.selectById(prod);
        shopcar shopcar=new shopcar();
        String username= usersService.getNameBySecurity();
        shopcar.setUsername(username);
        shopcar.setProd(Integer.parseInt(product.getId()));
        shopcar.setPname(product.getTitle());
        shopcar.setCount(1);
        shopcar.setPrice(product.getPrice());
        shopcar.setTotal(product.getPrice());
        int res=shopCarMapper.insert(shopcar);
        return res;
    }

    @Override
    public int updateproductshoper(int prod) {
        String username= usersService.getNameBySecurity();
        int i=shopCarMapper.updateproductshoper(prod,username);
        return i;
    }

    @Override
    public List<shopcar> selectshopcarByusername() {
        Map shopcarusername = new HashMap();
        String username= usersService.getNameBySecurity();
        shopcarusername.put("username",username);
        List<shopcar> shopcars=shopCarMapper.selectByMap(shopcarusername);
        return shopcars;
    }

    @Override
    public int deleteproductfromusershopcar() {
        Map shopcarusername = new HashMap();
        String username= usersService.getNameBySecurity();
        shopcarusername.put("username",username);
        baseMapper.deleteByMap(shopcarusername);
        return 0;
    }
}
