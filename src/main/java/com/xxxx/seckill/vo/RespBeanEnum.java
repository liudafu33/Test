package com.xxxx.seckill.vo;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter//@getter注解：在JavaBean或类JavaBean中使用，使用此注解会生成对应的getter方法；
@ToString//无需启动调试器即可查看您的字段：只需让lombok为您生成一个toString！
@AllArgsConstructor//@AllArgsConstructor注解：在JavaBean或类JavaBean中使用，使用此注解会生成对应的有参构造方法；
public enum  RespBeanEnum {

    SUCCESS(200,"SUCCESS"),
    ERROR(500,"服务端异常"),

    LOGIN_ERROR(200210,"用户名或密码错误"),
    MOBILE_ERROR(500211,"手机号码格式有误"),
    BIND_ERROR(500212,"参数校验异常"),

    //秒杀5005xx
    EMPTY_STOCK(500500,"库存不足"),
    REPEATE_ERROR(500501,"该商品限购一件")

    ;

    private final Integer code;

    private final String message;
}
