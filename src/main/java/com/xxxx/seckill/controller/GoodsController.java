package com.xxxx.seckill.controller;

import com.xxxx.seckill.pojo.User;
import com.xxxx.seckill.service.IGoodsService;
import com.xxxx.seckill.service.IUserService;
import com.xxxx.seckill.service.impl.UserServiceImpl;
import com.xxxx.seckill.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IGoodsService goodsService;

    /**
     * 跳转商品列表页
     * @param
     * @param
     * @param
     * @return
     */
    @RequestMapping("/toList")
    public String toLogin(Model model,User user){

        System.out.println("--------------------跳转商品列表页");

       /* //判断cookie是否为空,空去登录
        if(StringUtils.isEmpty(ticket)){
            return "login";
        }
        *//* //通过session获取用户
        //session.setAttribute(“user”,userName)； 》》是session设置值的方法
        //可以使用session.getAttribute(“key”);来取值，以为着你能得到userName的值。
        User user = (User) session.getAttribute(ticket);
        System.out.println("user.nicakname="+user.getNickname());*//*

        User user = userService. getUserByCookie(ticket, request, response);


        if(null==user){
            return "login";
        }*/

        /**
         * 往前台传数据，可以传对象，可以传List，通过el表达式 ${}可以获取到，
         * 类似于request.setAttribute("sts",sts)效果一样。
         */
        model.addAttribute("user",user);
        model.addAttribute("goodsList",goodsService.findGoodsVo());
        return "goodsList";
    }


    public void a(){
    }

}
