package com.tangchenyipinye.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tangchenyipinye.project.mapper.StoreMapper;
import com.tangchenyipinye.project.pojo.Store;
import com.tangchenyipinye.project.service.StoreService;
import org.apache.catalina.StoreManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
@Service
public class StoreServiceImpl extends ServiceImpl<StoreMapper,Store> implements StoreService {
    @Autowired
    StoreMapper storeMapper;
    //插入商家信息
    @Override
    public int insert(Store store) {
        int i = storeMapper.insert(store);
        return i;
    }
    //查询商家是否以及存在
    @Override
    public List selectByMap(String store_name) {
        Map map = new HashMap();
        map.put("store_name",store_name);
        List map1=storeMapper.selectByMap(map);

        return map1;
    }
    //修改商家信息
    @Override
    public int updaById(Store store) {
        int i = storeMapper.updateById(store);

        return i;
    }
    //删除商家信息
    @Override
    public int delete(String store_id) {
        int i = storeMapper.deleteById(store_id);
        return i;
    }

}
