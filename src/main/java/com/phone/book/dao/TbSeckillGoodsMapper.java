package com.phone.book.dao;

import com.phone.book.pojo.TbSeckillGoods;

import java.util.List;

public interface TbSeckillGoodsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TbSeckillGoods record);

    int insertSelective(TbSeckillGoods record);

    TbSeckillGoods selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TbSeckillGoods record);

    int updateByPrimaryKey(TbSeckillGoods record);
    //查询状态status=1,库存量>0,秒杀时间<=当前时间
    List<TbSeckillGoods> selectByStatusAndCountAndDate();
}