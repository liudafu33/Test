package com.xxxx.seckill.vo;

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
    private Date startDate;

    //结束时间
    private Date endDate;


}
