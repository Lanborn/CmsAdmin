package com.coderlan.controller;

import cn.hutool.core.map.MapUtil;
import com.coderlan.common.lang.Result;
import com.coderlan.service.CmsUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Autowired
    CmsUserService userService;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @PreAuthorize("hasRole('admin')")

    @GetMapping("/test")
    public Result test() {
        return Result.succ(userService.list());
    }


    @PreAuthorize("hasRole('system:users:list')")
    @GetMapping("/test/pass")
    public Result passEncode() {
        // 加密
        String pass = bCryptPasswordEncoder.encode("123123");
        System.out.println(pass);
        // 密码验证
        boolean matches = bCryptPasswordEncoder.matches("123213", pass);

        return Result.succ(MapUtil.builder()
                .put("pass",pass)
                .put("marches", matches)
                .build()
        );
    }
}
