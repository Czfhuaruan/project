package com.tangchenyipinye.project.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tangchenyipinye.project.mapper.DataMapper;
import com.tangchenyipinye.project.pojo.Data_status;
import com.tangchenyipinye.project.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;

@Service
public class DataServiceImpl extends ServiceImpl<DataMapper,Data_status>implements DataService{
    @Autowired
    DataMapper dataMapper;
    @Override
    public Data_status selectData_status(int id) {
        Data_status data_status= dataMapper.selectById(id);
        return data_status;
    }
}
