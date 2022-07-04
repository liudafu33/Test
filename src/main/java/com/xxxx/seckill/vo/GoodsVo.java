package com.xxxx.seckill.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.xxxx.seckill.pojo.Goods;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GoodsVo extends Goods {

    //秒杀价格
    private BigDecimal seckillPrice;

    //库存
    private Integer stockCount;

    //开始时间
    //@JsonFormat  //返回json数据的时候会编程yyyy-MM-dd HH:mm:ss
    private Date startDate;

    //结束时间
    private Date endDate;


}
