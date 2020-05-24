package com.phone.book.controller;

import com.phone.book.pojo.TbSeckillGoods;
import com.phone.book.result.Result;
import com.phone.book.service.SeckillGoodService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * ClassName: SeckillGoodsController
 * Description: TODO
 * Author: Leo
 * Date: 2020/5/23-16:33
 * email 1437665365@qq.com
 */
@RestController
@Slf4j
@RequestMapping("/goods")
public class SeckillGoodsController {

    @Autowired
    private SeckillGoodService seckillGoodService;

    //从缓存中查询秒杀商品
    @GetMapping("/findAll")
    public List<TbSeckillGoods> redisCache(){

        return seckillGoodService.findAll();
    }

    //根据id从缓存中查询秒杀商品
    @GetMapping("/findOne/{id}")
    public TbSeckillGoods findOne(@PathVariable("id") String id){
        return seckillGoodService.findOne(id);
    }

    //创建订单
    @GetMapping("/saveOrder/{id}")
    public Result saveOrder(@PathVariable("id") String id){
        //模拟用户登录
        String userId = "1";
        //商品id,用户id
        return seckillGoodService.saveOrder(id,userId);
    }


}
