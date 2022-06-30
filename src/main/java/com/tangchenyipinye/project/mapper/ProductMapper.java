package com.tangchenyipinye.project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tangchenyipinye.project.pojo.Product;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.context.annotation.Configuration;

@Configuration
public interface ProductMapper extends BaseMapper<Product> {
    /**
     *更新商品信息
     * @param product
     */
    @Update({"update t_product set title = #{product.title}, price = #{product.price},category=#{product.category},content=#{product.content} where id = #{product.id}"})
    void update(@Param("product") Product product);
}
