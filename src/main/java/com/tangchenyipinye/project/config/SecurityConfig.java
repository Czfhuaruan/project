package com.tangchenyipinye.project.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetails;

    /*
     * 配置数据源
     * */
    @Autowired
    private DataSource dataSource;
    @Bean
    public PersistentTokenRepository persistentTokenRepository(){
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        //  jdbcTokenRepository.setCreateTableOnStartup(true);  //第一次启动项目时进行创表
        return jdbcTokenRepository;
    }
    /*
     * -------------------------》
     * */

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetails).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()  //自定义登录表单
                .loginPage("/login.html") //登录页面设置
                .loginProcessingUrl("/user/login") //登录访问路径
                .defaultSuccessUrl("/api/hello").permitAll()  //登录成功之后，跳转路径
                .and().authorizeRequests()
                .antMatchers("/user/login", "/static/ref/*","/ref/*/*","/ref/*/*/*","/*").permitAll() //设置哪些路径可以直接访问，不需要认证
                //当前登录用户，只有具有admins权限才可以访问
                //   .antMatchers("/test/index").hasAuthority("admins")
                //当前登录用户，有czzf和admins权限都可以访问
                // .antMatchers("/test/index").hasAnyAuthority("admins,czzf")
                //用户hasrole用法 ROLE_sale
                //.antMatchers("/test/index").hasRole("sale")
                .anyRequest().authenticated()

                //配置Token有效时长---自动登录
                .and().rememberMe().tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(120) //设置
                .userDetailsService(userDetails)


                .and().csrf().disable(); //关闭csrf防护
        http.exceptionHandling().accessDeniedPage("/UNLOGIN.html");//自定义访问被拒页面
        http.logout().logoutUrl("/logout").logoutSuccessUrl("/login.html").permitAll();//自定义退出页面
    }

    @Bean
    PasswordEncoder passwordEncoder(){return new BCryptPasswordEncoder();
    }
}
