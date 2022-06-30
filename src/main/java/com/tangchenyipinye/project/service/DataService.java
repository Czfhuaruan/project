package com.tangchenyipinye.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tangchenyipinye.project.pojo.Data_status;

public interface DataService extends IService<Data_status> {
    public Data_status selectData_status(int id);
}
