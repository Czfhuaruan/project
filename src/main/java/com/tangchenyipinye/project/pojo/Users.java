package com.tangchenyipinye.project.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("Users")
public class Users extends BaseEntity{
    private String username;
    private String password;
    private String nickname;
    private String mobile;
    private String address;
}
