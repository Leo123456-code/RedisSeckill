package com.phone.book.service;

import com.phone.book.pojo.TbSeckillGoods;
import com.phone.book.result.Result;

import java.util.List;

/**
 * @program: sell
 * @ClassName: SeckillGoodService
 * @Description: TODO
 * @Author: Leo
 * @Date: 2020/5/23-16:35
 */
public interface SeckillGoodService {
    //查询合格的秒杀商品
    List<TbSeckillGoods> findAll();
    //根据id查询秒杀商品
    //从缓存中查询
    TbSeckillGoods findOne(String id);
    //创建订单
    Result saveOrder(String id, String userId);
}
