package com.xxxx.seckill.controller;

import com.xxxx.seckill.service.IUserService;
import com.xxxx.seckill.vo.LoginVo;
import com.xxxx.seckill.vo.RespBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/login")
@Slf4j
public class LoginController {

    @Autowired
    private IUserService userService;


    /**
     * 登录页面跳转方法
     * @return
     */
    @RequestMapping("/toLogin")
    public String toLogin(){
        System.out.println("登录页面跳转方法");
        return "login";
    }


    /**
     * 登录
     * @param loginVo
     * @return
     */
    @RequestMapping("doLogin")
    @ResponseBody
    public RespBean doLogin(LoginVo loginVo){
        log.info("{}",loginVo);

        return userService.doLogin(loginVo);
    }

}
