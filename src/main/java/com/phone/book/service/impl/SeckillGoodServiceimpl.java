package com.phone.book.service.impl;

import com.phone.book.dao.TbSeckillGoodsMapper;
import com.phone.book.dao.TbSeckillOrderMapper;
import com.phone.book.pojo.TbSeckillGoods;
import com.phone.book.pojo.TbSeckillOrder;
import com.phone.book.result.Result;
import com.phone.book.service.SeckillGoodService;
import com.phone.book.util.SnowFlake;
import com.phone.book.util.SystemConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * ClassName: SeckillGoodServiceimpl
 * Description: TODO
 * Author: Leo
 * Date: 2020/5/23-16:37
 * email 1437665365@qq.com
 */
@Slf4j
@Service
public class SeckillGoodServiceimpl implements SeckillGoodService {
    //雪花算法
    private SnowFlake snowFlake = new SnowFlake(2, 3);

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private TbSeckillOrderMapper tbSeckillOrderMapper;
    @Autowired
    private TbSeckillGoodsMapper tbSeckillGoodsMapper;

    @Override
    public List<TbSeckillGoods> findAll() {
        //查询缓存的数据
        List<TbSeckillGoods> list = redisTemplate.boundHashOps(TbSeckillGoods.class.getSimpleName()).values();
        //根据id排序
        List<TbSeckillGoods> sortList = new ArrayList<>(list);
        Collections.sort(sortList, (o1, o2) -> {
            return (int) (o1.getId() - o2.getId());
        });
        return sortList;
    }

    //根据id从缓存中查询
    @Override
    public TbSeckillGoods findOne(String id) {
        TbSeckillGoods tbSeckillGoods = null;
        try {
            tbSeckillGoods = (TbSeckillGoods) redisTemplate.boundHashOps(TbSeckillGoods.class.getSimpleName()).get(id);
        } catch (Exception e) {
            log.error("不存在此秒杀商品");
            e.fillInStackTrace();
        }
        return tbSeckillGoods;
    }

    //创建订单
    @Override
    public Result saveOrder(String id, String userId) {
        //从redis获取秒杀商品
        TbSeckillGoods tbSeckillGoods =
                (TbSeckillGoods) redisTemplate.boundHashOps(TbSeckillGoods.class.getSimpleName()).get(id);
        //2.判断商品是否存在，或库存是否<=0
        if(tbSeckillGoods == null|| tbSeckillGoods.getStockCount()<=0){
            //3.商品不存在或库存<=0,返回失败,提示卖完
            return new Result(false, "对不起,商品已售罄,请你选择其他商品");
        }
        //4.生成秒杀订单,将订单保存到redis缓存
        TbSeckillOrder seckillOrder = new TbSeckillOrder();
        seckillOrder.setUserId(userId);
        seckillOrder.setSellerId(tbSeckillGoods.getSellerId());
        seckillOrder.setSeckillId(tbSeckillGoods.getId());
        seckillOrder.setMoney(tbSeckillGoods.getCostPrice());
        seckillOrder.setCreateTime(new Date());
        seckillOrder.setId(snowFlake.nextId());
        seckillOrder.setStatus("0");//未支付
        log.info("seckillOrder={}", seckillOrder);
        //入库
        int insertCount = tbSeckillOrderMapper.insert(seckillOrder);
        if(insertCount == 0){
            log.info("订单入库失败");
        }
        redisTemplate.boundHashOps(TbSeckillOrder.class.getSimpleName()).put(userId, seckillOrder);
        //5.秒杀库存量-1
        tbSeckillGoods.setStockCount(tbSeckillGoods.getStockCount() - 1);
        //6.判断库存量是否<=0
        if (tbSeckillGoods.getStockCount() <= 0) {
            //7.是,将秒杀商品更新到数据库
           tbSeckillGoodsMapper.updateByPrimaryKeySelective(tbSeckillGoods);
            //删除秒杀商品缓存
            redisTemplate.boundHashOps(TbSeckillGoods.class.getSimpleName()).delete(id);
        } else {
            //8.否,将秒杀商品更新到缓存
            redisTemplate.boundHashOps(TbSeckillGoods.class.getSimpleName()).put(id, tbSeckillGoods);
        }
        return new Result(true, "秒杀成功,请你尽快支付");
    }
}
