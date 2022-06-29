package com.tangchenyipinye.project.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@TableName("t_product")
public class Product extends BaseEntity{

    private String title; //商品名称
    private Integer price; //价格（分）
    private String category;//类别
//    private Integer review_count;//评论数量
    private Integer product_store_id;//商家id
    private String content;//内容

    public Product(String title, Integer price, String category, String content) {
        this.title = title;
        this.price = price;
        this.category = category;
        this.content = content;
    }

    public Product() {
    }
}
