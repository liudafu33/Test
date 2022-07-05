package com.xxxx.seckill.config;

import com.xxxx.seckill.pojo.User;
import com.xxxx.seckill.service.IUserService;
import com.xxxx.seckill.service.impl.UserServiceImpl;
import com.xxxx.seckill.utils.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component//实例化注入到Spring中，跟@Controller一样，名字不同而已
public class UserArgumentResolver implements HandlerMethodArgumentResolver {

    @Autowired
    private IUserService service;

    /**
     * 条件判断,如果符合条件才会执行下面的方法
     * @param parameter 获取到的参数
     * @return
     */
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        Class<?> parameterType = parameter.getParameterType();//parameter好像是参数,获取到类型

        return parameterType== User.class; //如果是TRUE就会走下面的方法
    }

    @Override
    public Object resolveArgument(
            MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest,
            WebDataBinderFactory binderFactory) throws Exception {

        HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
        HttpServletResponse response = webRequest.getNativeRequest(HttpServletResponse.class);

        String userTicket = CookieUtil.getCookieValue(request, "userTicket");
        if(StringUtils.isEmpty(userTicket)){
            return null;
        }
        return service.getUserByCookie(userTicket,request,response);
    }
}
