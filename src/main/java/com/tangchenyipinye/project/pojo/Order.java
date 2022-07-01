package com.tangchenyipinye.project.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_order_info")
public class Order extends BaseEntity{

    private String title;
    private String orderNo;
    private String paymentType;
    private Integer totalFee;
    private String orderStatus;
    private String username;

}
