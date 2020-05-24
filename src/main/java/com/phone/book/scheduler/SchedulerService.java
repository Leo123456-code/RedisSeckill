package com.phone.book.scheduler;

import com.phone.book.dao.TbSeckillGoodsMapper;
import com.phone.book.pojo.TbSeckillGoods;
import com.phone.book.util.SystemConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * ClassName: SchedulerService
 * Description: 定时任务
 * Author: Leo
 * Date: 2020/5/15-17:33
 * email 1437665365@qq.com
 */
@Service
@Slf4j
public class SchedulerService {
    @Autowired
    private TbSeckillGoodsMapper tbSeckillGoodsMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Scheduled(cron = "0/30 * * * * ?  ")
    public void importToRedis() {
        //查询合法的秒杀商品数据:状态有效(status=1),库存量>0 ,秒杀开始时间<=当前时间
        List<TbSeckillGoods> seckillGoodsList = tbSeckillGoodsMapper.selectByStatusAndCountAndDate();

        //排序
        List<TbSeckillGoods> list = new ArrayList<>(seckillGoodsList);
        Collections.sort(list,(o1, o2) -> {
            return (int) (o1.getId()-o2.getId());
        });
        //将秒杀商品数据存入redis ,将数据存到Hash结构的redis
        for (TbSeckillGoods goods : list) {
            redisTemplate.boundHashOps(TbSeckillGoods.class.getSimpleName()).put(String.valueOf(goods.getId()),goods);
            //为每一个商品创建一个队列,队列中放和库存量相同数据量的商品id
//            createQueue(goods.getId(),goods.getStockCount());
        }
    }

    private void createQueue(Long id, Integer stockCount) {
        if(stockCount>0){
            for (int i=0;i<stockCount;i++){
                redisTemplate.boundListOps(SystemConst.CONST_SECKILLGOODS_ID_PREFIX+id).leftPush(id);
            }
        }
    }

}
