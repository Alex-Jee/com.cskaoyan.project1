/**
 * User: zsquirrel
 * Date: 2020/4/28
 * Time: 4:47 下午
 */
package com.cskaoyan.mall.dao;

import com.cskaoyan.mall.model.Admin;
import com.cskaoyan.mall.utils.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdminDaoImpl implements AdminDao {
    @Override
    public int login(Admin admin) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        try {
            Admin ad = runner.query("select * from admin where email = ? and pwd = ?",
                    new BeanHandler<>(Admin.class),
                    admin.getEmail(),
                    admin.getPwd());
            if(ad != null){
                return 200;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 500;
    }

    @Override
    public List<Admin> allAdmins() {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        List<Admin> admins = null;
        try {
            admins = runner.query("select * from admin", new BeanListHandler<Admin>(Admin.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return admins;
    }

    @Override
    public List<Admin> getSerachAdmins(Admin admin) {
        QueryRunner runner=new QueryRunner(DruidUtils.getDataSource());
        List<Admin> admins=null;
        Map<String,Object> result=getDynamicSQL(admin);
        try{
            admins=runner.query((String) result.get("sql"),
                    new BeanListHandler<>(Admin.class),
                    ((List<String>)result.get("params")).toArray());
        }catch (Exception e){
            e.printStackTrace();
        }
        return admins;
    }

    @Override
    public int addAdminss(Admin admin) {
        QueryRunner queryRunner=new QueryRunner(DruidUtils.getDataSource());
        try {
            Admin result=queryRunner.query("select * from admin where email=? limit 1",
                    new BeanHandler<>(Admin.class),
                    admin.getEmail());
            if(result!=null) return 403;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            int result=queryRunner.update("insert into admin(email,nickname,pwd) values(?,?,?)",
                    admin.getEmail(),admin.getNickname(),admin.getPwd());
            if(result>0) return 200;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 500;
    }

    private Map<String, Object> getDynamicSQL(Admin admin) {
        Map<String,Object> map=new HashMap<>();
        String sql="Select * from admin where 1=1";
        List<String> params=new ArrayList<>();
        if(!"".equals(admin.getEmail())){
            sql+=" and email like ?";
            params.add("%"+admin.getEmail()+"%");
        }
        if(!"".equals(admin.getNickname())){
            sql+=" and nickname like ?";
            params.add("%"+admin.getNickname()+"%");
        }
        sql+=" order by id asc;";
        map.put("sql",sql);
        map.put("params",params);
        return map;
    }
}
