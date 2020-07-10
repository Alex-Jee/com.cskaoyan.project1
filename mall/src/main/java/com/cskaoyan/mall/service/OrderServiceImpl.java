package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bo.ChangeOrderBO;
import com.cskaoyan.mall.bo.OrderBO;
import com.cskaoyan.mall.bo.PageOrderBO;
import com.cskaoyan.mall.com.cskaoyan.mall.vo.PageOrderVO;
import com.cskaoyan.mall.dao.OrderDao;
import com.cskaoyan.mall.dao.OrderDaoImpl;

public class OrderServiceImpl implements OrderService {
    private OrderDao orderDao=new OrderDaoImpl();
    @Override
    public PageOrderVO ordersByPage(PageOrderBO pageOrderBO) {
        return orderDao.orderByPage(pageOrderBO);
    }

    @Override
    public OrderBO order(Integer id) {
        return orderDao.order(id);
    }

    @Override
    public void changeOrder(ChangeOrderBO changeOrderBO) {
        orderDao.changeOrder(changeOrderBO);
    }

    @Override
    public void deleteOrder(int orderId) {
        orderDao.deleteOrder(orderId);
    }
}
