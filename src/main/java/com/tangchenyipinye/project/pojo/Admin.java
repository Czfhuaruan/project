package com.tangchenyipinye.project.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author devByHfj
 * @description com.tangchenyipinye.project.pojo
 * @create 2022-06-29 14:43
 */
@Data
@AllArgsConstructor
@TableName("admin")
public class Admin {
    private String admin_name;//管理员账号
    private String admin_nickname;//昵称
    private String admin_password;//密码
    private String admin_image;//头像
}
