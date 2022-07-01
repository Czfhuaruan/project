package com.tangchenyipinye.project.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("Users")
public class Users extends BaseEntity{
    private String username;
    private String password;
    private String nickname;
    private String mobile;
    private String address;
    private String email;

    private Integer mind;

    public Users(String username, String password, String nickname, String mobile, String email) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.mobile = mobile;
        this.email = email;
    }




}
