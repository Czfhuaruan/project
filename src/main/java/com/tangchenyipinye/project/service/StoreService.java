package com.tangchenyipinye.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tangchenyipinye.project.pojo.Store;

import java.util.List;

public interface StoreService extends IService<Store> {

    public int insert(Store store);
    public List selectByMap(String store_name);
    public int updaById(Store store);
    public int delete(String store_id);
}
