package com.cskaoyan.mall.vo;

public class SpecVO {
    private int id;
    private String specName;
    private Double unitPrice;

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

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Override
    public String toString() {
        return "SpecVO{" +
                "id=" + id +
                ", specName='" + specName + '\'' +
                ", unitPrice=" + unitPrice +
                '}';
    }
}
