package com.xxxx.seckill.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xxxx.seckill.exception.GlobalException;
import com.xxxx.seckill.mapper.UserMapper;
import com.xxxx.seckill.pojo.User;
import com.xxxx.seckill.service.IUserService;
import com.xxxx.seckill.utils.CookieUtil;
import com.xxxx.seckill.utils.MD5Util;
import com.xxxx.seckill.utils.UUIDUtil;
import com.xxxx.seckill.vo.LoginVo;
import com.xxxx.seckill.vo.RespBean;
import com.xxxx.seckill.vo.RespBeanEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhoubin
 * @since 2022-06-08
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 登录
     * @param loginVo
     * @return
     */
    @Override
    public RespBean doLogin(LoginVo loginVo, HttpServletRequest request, HttpServletResponse response) {
        System.out.println("2");
        String mobile=loginVo.getMobile();
        String password=loginVo.getPassword();
      //是否为空
        if(StringUtils.isEmpty(mobile)||StringUtils.isEmpty(password)){
            return RespBean.error(RespBeanEnum.LOGIN_ERROR);
        }
 /*         //手机号码格式验证
        if(!ValidatorUtil.isMobile(password)){
            return RespBean.error(RespBeanEnum.MOBILE_ERROR);
        }
*/
        //根据手机号获取用户
        User user = userMapper.selectById(mobile);
        if(null==user){
            throw   new GlobalException(RespBeanEnum.LOGIN_ERROR);
        }

        //判断密码
        if(!MD5Util.formPassToDBPass(password,user.getSalt()).equals(user.getPassword())){
            System.out.println("MD5Util------"+RespBean.succes());
            throw   new GlobalException(RespBeanEnum.LOGIN_ERROR);
        }

        //生成Cookie
        String ticket = UUIDUtil.uuid();
        System.out.println("ticket----------"+ticket);
        //void getSession()方法相当于得到一个session对象，
        // 而void setAttribute()和String Attribute分别是对属性赋值和得到属性值的方法。
        request.getSession().setAttribute(ticket,user);
        CookieUtil.setCookie(request,response,"userTicket",ticket);
        return  RespBean.succes();
    }
}
