package com.tangchenyipinye.project.controller;

import io.swagger.annotations.Api;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Api(tags = "templates测试")
@Controller
@RequestMapping("temapi")
public class templatesTest {

    @Secured({"ROLE_admins"})
    @RequestMapping("/logout")
    public String logout(){
        return "success";
    }
}
