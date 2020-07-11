package com.cskaoyan.mall.dao;

import com.cskaoyan.mall.bo.GoodsBO;
import com.cskaoyan.mall.model.Goods;
import com.cskaoyan.mall.model.GoodsInfo;
import com.cskaoyan.mall.model.Msg;
import com.cskaoyan.mall.model.Type;

import javax.servlet.ServletContext;
import java.util.List;

public interface GoodsDao {
    List<Type> getType();

    int addType(Type type);

    List<Goods> getGoodsByType(int typeId);

    void addGoods(GoodsBO goodsBO);

    GoodsInfo getGoodsInfo(int id);

    void deleteGoods(int id, ServletContext context);

    List<Msg> noReplyMsg();

    List<Msg> repliedMsg();

    void reply(Msg msg);
}
