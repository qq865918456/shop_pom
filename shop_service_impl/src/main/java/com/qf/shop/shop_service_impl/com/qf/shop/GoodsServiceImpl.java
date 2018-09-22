package com.qf.shop.shop_service_impl.com.qf.shop;

import com.alibaba.dubbo.config.annotation.Service;
import com.qf.entity.Goods;
import com.qf.service.IGoodsService;
import com.qf.shop.dao.IGoodsDao;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class GoodsServiceImpl implements IGoodsService {
    @Autowired
    private IGoodsDao goodsDao;


    @Override
    public List<Goods> queryAll() {
        return goodsDao.queryAll();
    }

    @Override
    public Goods goodsadd(Goods goods) {
        int goodsadd = goodsDao.goodsadd(goods);
        return goods;
    }

    @Override
    public List<Goods> querynew() {
        return goodsDao.querynew();
    }
}
