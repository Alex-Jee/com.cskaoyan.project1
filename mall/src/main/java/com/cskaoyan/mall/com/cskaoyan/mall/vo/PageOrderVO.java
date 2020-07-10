package com.cskaoyan.mall.com.cskaoyan.mall.vo;

import java.util.List;

public class PageOrderVO {
    private Integer total;
    private List<PageOrderInfoVO> orders;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<PageOrderInfoVO> getOrders() {
        return orders;
    }

    public void setOrders(List<PageOrderInfoVO> orders) {
        this.orders = orders;
    }
}
