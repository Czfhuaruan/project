package com.tangchenyipinye.project.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("shopcar")
public class shopcar {
    private String username;
    private int prod;
    private String pname;
    private Integer price;
    private int count;
    private Integer total;
}
