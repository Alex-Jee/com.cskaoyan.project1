package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bo.ChangeOrderBO;
import com.cskaoyan.mall.bo.OrderBO;
import com.cskaoyan.mall.bo.PageOrderBO;
import com.cskaoyan.mall.vo.PageOrderVO;

public interface OrderService {
    PageOrderVO ordersByPage(PageOrderBO pageOrderBO);

    OrderBO order(Integer id);

    void changeOrder(ChangeOrderBO changeOrderBO);

    void deleteOrder(int orderId);
}
