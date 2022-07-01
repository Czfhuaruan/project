package com.tangchenyipinye.project.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_product")
public class Product extends BaseEntity{

    private String title; //商品名称

    private Integer price; //价格（分）

    private String category; //商品分类

    private String image; //图片

    private int prostored; //商家id

    private String content; //描述
}
