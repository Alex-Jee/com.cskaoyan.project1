package com.cskaoyan.mall.model;

public class Spec {
    private int id;
    private String specName;
    private int stockNum;
    private double unitPrice;
    private int goodsId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSpecName() {
        return specName;
    }

    public void setSpecName(String specName) {
        this.specName = specName;
    }

    public int getStockNum() {
        return stockNum;
    }

    public void setStockNum(int stockNum) {
        this.stockNum = stockNum;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    public Spec() {
    }

    public Spec(String specName, int stockNum, double unitPrice, int goodsId) {
        this.specName = specName;
        this.stockNum = stockNum;
        this.unitPrice = unitPrice;
        this.goodsId = goodsId;
    }
}
