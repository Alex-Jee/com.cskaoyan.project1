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
    private QueryRunner queryRunner=new QueryRunner(DruidUtils.getDataSource());

    @Override
    public int login(Admin admin) {
        try {
            Admin ad = queryRunner.query("select * from admin where email = ? and pwd = ?",
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
        List<Admin> admins = null;
        try {
            admins = queryRunner.query("select * from admin", new BeanListHandler<Admin>(Admin.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return admins;
    }

    @Override
    public List<Admin> getSerachAdmins(Admin admin) {
        List<Admin> admins=null;
        Map<String,Object> result=getDynamicSQL(admin);
        try{
            admins=queryRunner.query((String) result.get("sql"),
                    new BeanListHandler<>(Admin.class),
                    ((List<String>)result.get("params")).toArray());
        }catch (Exception e){
            e.printStackTrace();
        }
        return admins;
    }

    @Override
    public int addAdminss(Admin admin) {
        Admin result=isEmailExists(admin);
        if(result!=null) return 403;
        try {
            int code=queryRunner.update("insert into admin(email,nickname,pwd) values(?,?,?)",
                    admin.getEmail(),admin.getNickname(),admin.getPwd());
            if(code>0) return 200;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 500;
    }

    @Override
    public void deleteAdmins(int id) {
        try {
            queryRunner.update("delete from admin where id=?",id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Admin getAdminsInfoById(int id) {
        Admin result= null;
        try {
            result = queryRunner.query("select * from admin where id=?", new BeanHandler<>(Admin.class), id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int updateAdminss(Admin admin) {
        int code=500;
        try {
            int result=queryRunner.update("update admin set email=?,nickname=?,pwd=? where id=?",
                    admin.getEmail(),admin.getNickname(),admin.getPwd(),admin.getId());
            if(result>0) code=200;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return code;
    }

    /**
     * 获取动态SQL查询结果
     * @param admin
     * @return
     */
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

    /**
     * 判断给定admin账户是否存在，存在返回这个admin对象，不存在返回null
     * @param admin
     * @return
     */
    private Admin isEmailExists(Admin admin){
        try {
            return queryRunner.query("select * from admin where email=? limit 1",
                    new BeanHandler<>(Admin.class),
                    admin.getEmail());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
