package com.cskaoyan.mall.dao;

import com.cskaoyan.mall.model.User;
import com.cskaoyan.mall.utils.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class UserDaoImpl implements UserDao{
    private QueryRunner queryRunner=new QueryRunner(DruidUtils.getDataSource());

    @Override
    public List<User> allUser() {
        List<User> result=null;
        try {
            result=queryRunner.query("select * from user order by id asc",
                    new BeanListHandler<>(User.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<User> searchUser(String param) {
        try {
            List<User> data=queryRunner.query("select * from user where nickname like ? order by id asc",
                    new BeanListHandler<>(User.class),"%"+param+"%");
            return data;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int deleteUser(int id) {
        int status=0;
        try {
            status=queryRunner.update("delete from user where id=?",id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }
}
