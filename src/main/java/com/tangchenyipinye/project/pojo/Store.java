package com.tangchenyipinye.project.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Date;


@Data
@AllArgsConstructor
@TableName("store")
public class Store {
    private String store_name;
    private String store_nickname;
    private String store_password;
    private String store_image;


}
