package com.tangchenyipinye.project.pojo;

import lombok.Data;

//securuty信息
@Data
public class User {
    private String Username;
    private String Password;
    private Boolean Enabled;
    private Boolean AccountNonExpired;
    private Boolean credentialsNonExpired;
    private Boolean AccountNonLocked;
    private String GrantedAuthorities;
}
