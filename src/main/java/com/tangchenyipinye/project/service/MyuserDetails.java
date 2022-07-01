package com.tangchenyipinye.project.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tangchenyipinye.project.mapper.UsersMapper;
import com.tangchenyipinye.project.pojo.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userDetails")
public class MyuserDetails  implements UserDetailsService {
    @Autowired
    private UsersMapper baseMapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        /*
        * 调用Basemapper方法，根据用户名查询数据库
        * */
        QueryWrapper<Users> objectQueryWrapper = new QueryWrapper();
        objectQueryWrapper.eq("username",username);
        Users users= baseMapper.selectOne(objectQueryWrapper);
        if(users==null){
            throw new UsernameNotFoundException("用户名不存在!");
        }

        //添加权限(或/和)用户
        List<GrantedAuthority> auths= AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_admins,admins");

        return new User(users.getUsername(),new BCryptPasswordEncoder().encode(users.getPassword()),auths);
    }
}
