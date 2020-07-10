package com.cskaoyan.mall.dao;

import com.cskaoyan.mall.bo.ChangeOrderBO;
import com.cskaoyan.mall.bo.OrderBO;
import com.cskaoyan.mall.bo.PageOrderBO;
import com.cskaoyan.mall.com.cskaoyan.mall.vo.PageOrderInfoUserVO;
import com.cskaoyan.mall.com.cskaoyan.mall.vo.PageOrderInfoVO;
import com.cskaoyan.mall.com.cskaoyan.mall.vo.PageOrderVO;
import com.cskaoyan.mall.model.Orders;
import com.cskaoyan.mall.utils.DruidUtils;
import com.cskaoyan.mall.vo.CountVO;
import com.cskaoyan.mall.vo.SpecVO;
import com.cskaoyan.mall.vo.StatesVO;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderDaoImpl implements OrderDao {
    QueryRunner queryRunner=new QueryRunner(DruidUtils.getDataSource());

    @Override
    public PageOrderVO orderByPage(PageOrderBO pageOrderBO) {
        PageOrderVO pageOrderVO = new PageOrderVO();
        Map<String,Object> map=getDynamicSQL(pageOrderBO);
        String selectTotal="select count(*) as count";
        try {
            CountVO total=queryRunner.query(selectTotal+map.get("sql"),
                    new BeanHandler<>(CountVO.class),
                    ((List<Object>)map.get("params")).toArray());
            pageOrderVO.setTotal(total.getCount());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String sql="select *"+map.get("sql");
        sql+="limit ? offset ?";
        List<Object> params=(List<Object>)map.get("params");
        params.add(pageOrderBO.getPagesize());
        params.add(pageOrderBO.getPagesize()*(pageOrderBO.getCurrentPage()-1));
        try {
            List<Orders> orders=queryRunner.query(sql,
                    new BeanListHandler<>(Orders.class),
                    params.toArray());
            List<PageOrderInfoVO> pageOrderInfoVO=new ArrayList<PageOrderInfoVO>();
            if(orders!=null){
                for (Orders order : orders) {
                    PageOrderInfoVO pageOrderInfoVO1 = new PageOrderInfoVO();
                    PageOrderInfoUserVO pageOrderInfoUserVO = new PageOrderInfoUserVO();

                    pageOrderInfoUserVO.setAddress(order.getAddress());
                    pageOrderInfoUserVO.setName(order.getName());
                    pageOrderInfoUserVO.setNickname(order.getNickname());
                    pageOrderInfoUserVO.setPhone(order.getPhone());

                    pageOrderInfoVO1.setUser(pageOrderInfoUserVO);
                    pageOrderInfoVO1.setId(order.getId());
                    pageOrderInfoVO1.setUserId(order.getUserId());
                    pageOrderInfoVO1.setGoodsDetailId(order.getGoodsDetailId());
                    pageOrderInfoVO1.setGoods(order.getGoods());
                    pageOrderInfoVO1.setSpec(order.getSpec());
                    pageOrderInfoVO1.setGoodsNum(order.getGoodsNum());
                    pageOrderInfoVO1.setAmount(order.getAmount());
                    pageOrderInfoVO1.setStateId(order.getStateId());

                    pageOrderInfoVO.add(pageOrderInfoVO1);
                }
            }
            pageOrderVO.setOrders(pageOrderInfoVO);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return pageOrderVO;
    }

    @Override
    public OrderBO order(Integer id) {
        OrderBO orderBO= null;
        try {
            orderBO = queryRunner.query("select id,amount,goodsNum as num,goodsDetailId,stateId as state,goods,goodsId from orders where id=?",
                    new BeanHandler<>(OrderBO.class),
                    id);
            orderBO.setCurState();
            orderBO.setCurSpec();
            orderBO.setStates(new StatesVO[4]);
            List<SpecVO> specVOList=queryRunner.query("select id,specName,unitPrice from spec where goodsId=?",
                    new BeanListHandler<>(SpecVO.class),
                    orderBO.getGoodsId());
            orderBO.setSpec(specVOList.toArray(new SpecVO[0]));
        } catch (SQLException e) {

            e.printStackTrace();
        }
        if(orderBO!=null){
            orderBO.setStatesArray();
        }
        return orderBO;
    }

    @Override
    public void changeOrder(ChangeOrderBO changeOrderBO) {
        int orderId=new Integer(changeOrderBO.getId());
        try {
            queryRunner.update("update orders set goodsNum=?,goodsDetailId=?,stateId=? where id=?",
                    changeOrderBO.getNum(),
                    changeOrderBO.getSpec(),
                    changeOrderBO.getState(),
                    orderId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteOrder(int orderId) {
        try {
            queryRunner.update("delete from orders where id=?",
                    orderId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Map<String, Object> getDynamicSQL(PageOrderBO pageOrderBO) {
        Map<String,Object> map=new HashMap<>();
        String sql=" from orders where 1=1";
        List<Object> params=new ArrayList<>();
        if(!"".equals(pageOrderBO.getName())){
            sql+=" and name like ?";
            params.add("%"+pageOrderBO.getName()+"%");
        }
        if(!"".equals(pageOrderBO.getAddress())){
            sql+=" and address like ?";
            params.add("%"+pageOrderBO.getAddress()+"%");
        }
        if(!"".equals(pageOrderBO.getGoods())){
            sql+=" and goods like ?";
            params.add("%"+pageOrderBO.getGoods()+"%");
        }
        if(!"".equals(pageOrderBO.getId())){
            sql+=" and id=?";
            params.add(new Integer(pageOrderBO.getId()));
        }
        if(!"".equals(pageOrderBO.getMoneyLimit1())){
            sql+=" and amount<=?";
            params.add(new Integer(pageOrderBO.getMoneyLimit1()));
        }
        if(!"".equals(pageOrderBO.getMoneyLimit2())){
            sql+=" and amount>=?";
            params.add(new Integer(pageOrderBO.getMoneyLimit2()));
        }
        if(!pageOrderBO.getState().equals(-1)){
            sql+=" and stateId=?";
            params.add(pageOrderBO.getState());
        }
        sql+=" order by id desc ";
        map.put("sql",sql);
        map.put("params",params);
        return map;
    }
}
