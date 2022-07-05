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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.alibaba.fastjson.JSON;

import java.util.Date;

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

     /**
     * 商品详情
     * @param goodsId
     */
    @RequestMapping("toDetail/{goodsId}")
    public String toDetail(Model model,User user,@PathVariable Long goodsId){
        System.out.println(JSON.toJSONString(user));
        //@PathVariable 映射 URL 绑定的占位符
        // 一般与@RequestMapping(method = RequestMethod.GET)一起使用
        model.addAttribute("user",user);
        //获取商品详情
        GoodsVo goodsVo = goodsService.findGoodsVoByGoodsId(goodsId);
        //时间设置
        Date startDate = goodsVo.getStartDate();
        Date endDate = goodsVo.getEndDate();
        Date nowDate = new Date();
        //秒杀状态
        int secKillStatus=0;
        //秒杀倒计时
        int remainSeconds=0;

        if(nowDate.before(startDate)){
            //活动未开始
            remainSeconds=((int)(startDate.getTime()-nowDate.getTime())/1000);

        }else if(nowDate.after(endDate)){
            //活动已结束
            secKillStatus=2;
            remainSeconds=-1;
        }else {
            //秒杀中
            secKillStatus=1;
            remainSeconds=0;
        }
        model.addAttribute("remainSeconds",remainSeconds);
        model.addAttribute("secKillStatus",secKillStatus);
        model.addAttribute("goods",goodsVo);
        return "goodsDetail";
    }


}
