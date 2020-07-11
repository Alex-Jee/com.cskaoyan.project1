package com.cskaoyan.mall.dao;

import com.cskaoyan.mall.bo.*;
import com.cskaoyan.mall.model.*;
import com.cskaoyan.mall.utils.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

public class GoodsDaoImpl implements GoodsDao {
    QueryRunner queryRunner=new QueryRunner(DruidUtils.getDataSource());

    @Override
    public List<Type> getType() {
        try {
            List<Type> result=queryRunner.query("select * from type order by id asc",
                    new BeanListHandler<>(Type.class));
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int addType(Type type) {
        int status=0;
        try {
            status=queryRunner.update("insert into type(name) values(?)",type.getName());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

    @Override
    public List<Goods> getGoodsByType(int typeId) {
        try {
            return queryRunner.query("select * from goods where typeId=? order by id desc",
                    new BeanListHandler<>(Goods.class),typeId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void addGoods(GoodsBO goodsBO) {
        List<SpecBO> list=goodsBO.getSpecList();
        double price=list.get(0).getUnitPrice();
        int stockNum=list.get(0).getStockNum();
        for (int i=1;i<list.size();++i) {
            if(price>list.get(i).getUnitPrice()){price=list.get(i).getUnitPrice();}
            stockNum+=list.get(i).getStockNum();
        }
        Goods goods=new Goods(goodsBO.getName(),goodsBO.getImg(),price,goodsBO.getTypeId(),stockNum,goodsBO.getDesc());
        addGoods(goods);
        int goodsId=lastInsertId();
        for (SpecBO specBO : list) {
            Spec spec=new Spec(specBO.getSpecName(),specBO.getStockNum(),specBO.getUnitPrice(),goodsId);
            addSpec(spec);
        }
    }

    @Override
    public GoodsInfo getGoodsInfo(int id) {
        GoodsInfo goodsInfo = new GoodsInfo();
        try {
            goodsInfo.setGoods(
                    queryRunner.query("select id,name,img,typeId,`desc` from goods where id=?",
                    new BeanHandler<>(GoodsInfoBO.class),id)
            );
            goodsInfo.setSpecs(
                    queryRunner.query("select * from spec where goodsId=?",
                            new BeanListHandler<>(Spec.class),id)
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return goodsInfo;
    }

    @Override
    public void deleteGoods(int id, ServletContext context) {
        try {
            String img=queryRunner.query("select img from goods where id=?",
                    new BeanHandler<>(Goods.class),
                    id).getImg();
            InputStream inputStream=this.getClass().getClassLoader().getResourceAsStream("application.properties");
            Properties properties = new Properties();
            properties.load(inputStream);
            String domain=properties.getProperty("domain");
            img=img.replaceFirst(domain,"");
            String realPath=context.getRealPath(img);
            System.out.println(realPath);
            File file=new File(realPath);
            if(file.exists()){
                file.delete();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            queryRunner.update("delete from goods where id=?",id);
            queryRunner.update("delete from spec where goodsId=?",id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Msg> noReplyMsg() {
        List<Msg> result=null;
        try {
            result=queryRunner.query("select * from msg where state=1 order by id",
                    new BeanListHandler<>(Msg.class));
            for (Msg msg : result) {
                MsgGoodsBO goods=queryRunner.query("select name from goods where id=?",
                        new BeanHandler<>(MsgGoodsBO.class),
                        msg.getGoodsId());
                msg.setGoods(goods);
                MsgUserBO user=queryRunner.query("select nickname as name from user where id=?",
                        new BeanHandler<>(MsgUserBO.class),
                        msg.getUserId());
                msg.setUser(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<Msg> repliedMsg() {
        List<Msg> result=null;
        try {
            result=queryRunner.query("select * from msg where state=0 order by id",
                    new BeanListHandler<>(Msg.class));
            for (Msg msg : result) {
                MsgGoodsBO goods=queryRunner.query("select name from goods where id=?",
                        new BeanHandler<>(MsgGoodsBO.class),
                        msg.getGoodsId());
                msg.setGoods(goods);
                MsgUserBO user=queryRunner.query("select nickname as name from user where id=?",
                        new BeanHandler<>(MsgUserBO.class),
                        msg.getUserId());
                msg.setUser(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void reply(Msg msg) {
        try {
            queryRunner.update("update msg set state=0,replyContent=? where id=?",
                    msg.getContent(),msg.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addSpec(Spec spec) {
        try {
            queryRunner.update("insert into spec(specName,stockNum,unitPrice,goodsId) values(?,?,?,?)",
                    spec.getSpecName(),
                    spec.getStockNum(),
                    spec.getUnitPrice(),
                    spec.getGoodsId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private int lastInsertId() {
        BigInteger goodsId= null;
        try {
            goodsId = queryRunner.query("select last_insert_id()",new ScalarHandler<>());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return goodsId.intValue();
    }

    private void addGoods(Goods goods) {
        try {
            queryRunner.update("insert into goods(name,img,price,typeId,stockNum,`desc`) values(?,?,?,?,?,?)",
                    goods.getName(),
                    goods.getImg(),
                    goods.getPrice(),
                    goods.getTypeId(),
                    goods.getStockNum(),
                    goods.getDesc());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
