package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bo.GoodsBO;
import com.cskaoyan.mall.dao.GoodsDao;
import com.cskaoyan.mall.dao.GoodsDaoImpl;
import com.cskaoyan.mall.model.Goods;
import com.cskaoyan.mall.model.GoodsInfo;
import com.cskaoyan.mall.model.Msg;
import com.cskaoyan.mall.model.Type;

import javax.servlet.ServletContext;
import java.util.List;

public class GoodsServiceImpl implements GoodsService {
    private GoodsDao goodsDao=new GoodsDaoImpl();

    @Override
    public List<Type> getType() {
        return goodsDao.getType();
    }

    @Override
    public int addType(Type type) {
        return goodsDao.addType(type);
    }

    @Override
    public List<Goods> getGoodsByType(int typeId) {
        return goodsDao.getGoodsByType(typeId);
    }

    @Override
    public void addGoods(GoodsBO goodsBO) {
        goodsDao.addGoods(goodsBO);
    }

    @Override
    public GoodsInfo getGoodsInfo(int id) {
        return goodsDao.getGoodsInfo(id);
    }

    @Override
    public void deleteGoods(int id, ServletContext context) {
        goodsDao.deleteGoods(id,context);
    }

    @Override
    public List<Msg> noReplyMsg() {
        return goodsDao.noReplyMsg();
    }

    @Override
    public List<Msg> repliedMsg() {
        return goodsDao.repliedMsg();
    }

    @Override
    public void reply(Msg msg) {
        goodsDao.reply(msg);
    }
}
