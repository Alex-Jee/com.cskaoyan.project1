package com.cskaoyan.mall.model;

import com.cskaoyan.mall.bo.GoodsInfoBO;

import java.util.List;

public class GoodsInfo {
    private List<Spec> specs;
    private GoodsInfoBO goods;

    public List<Spec> getSpecs() {
        return specs;
    }

    public void setSpecs(List<Spec> specs) {
        this.specs = specs;
    }

    public GoodsInfoBO getGoods() {
        return goods;
    }

    public void setGoods(GoodsInfoBO goods) {
        this.goods = goods;
    }
}
