package com.tangchenyipinye.project.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fasterxml.jackson.databind.ser.Serializers;
import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author devByHfj
 * @description com.tangchenyipinye.project.pojo
 * @create 2022-06-29 14:43
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("admin")
public class Admin extends BaseEntity{
    private String adminName;//管理员账号
    private String adminNickname;//昵称
    private String adminPassword;//密码
    private String adminImage;//头像
}
