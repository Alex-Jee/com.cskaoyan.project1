package com.cskaoyan.mall.model;

public class Goods {
    private int id;
    private String name;
    private String img;
    private double price;
    private int typeId;
    private int stockNum;
    private String desc;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public int getStockNum() {
        return stockNum;
    }

    public void setStockNum(int stockNum) {
        this.stockNum = stockNum;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Goods() {
    }

    public Goods(String name, String img, double price, int typeId, int stockNum, String desc) {
        this.img = img;
        this.name = name;
        this.price = price;
        this.typeId = typeId;
        this.stockNum = stockNum;
        this.desc = desc;
    }
}
