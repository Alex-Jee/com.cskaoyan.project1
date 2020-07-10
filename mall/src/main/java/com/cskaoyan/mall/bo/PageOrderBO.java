package com.cskaoyan.mall.bo;

/**
 * 用于后台管理系统获取请求参数BO
 */
public class PageOrderBO {
    private Integer state;
    private Integer currentPage;
    private Integer pagesize;
    private String moneyLimit1;//用String的原因是：有可能传入的是""，这时只能用String接收，下同
    private String moneyLimit2;
    private String goods;
    private String address;
    private String name;
    private String id;//订单号


    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPagesize() {
        return pagesize;
    }

    public void setPagesize(Integer pagesize) {
        this.pagesize = pagesize;
    }

    public String getMoneyLimit1() {
        return moneyLimit1;
    }

    public void setMoneyLimit1(String moneyLimit1) {
        this.moneyLimit1 = moneyLimit1;
    }

    public String getMoneyLimit2() {
        return moneyLimit2;
    }

    public void setMoneyLimit2(String moneyLimit2) {
        this.moneyLimit2 = moneyLimit2;
    }

    public String getGoods() {
        return goods;
    }

    public void setGoods(String goods) {
        this.goods = goods;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
