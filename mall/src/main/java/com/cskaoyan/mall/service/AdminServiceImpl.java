/**
 * User: zsquirrel
 * Date: 2020/4/28
 * Time: 4:45 下午
 */
package com.cskaoyan.mall.service;

import com.cskaoyan.mall.dao.AdminDao;
import com.cskaoyan.mall.dao.AdminDaoImpl;
import com.cskaoyan.mall.model.Admin;

import java.util.List;

public class AdminServiceImpl implements AdminService {

    private AdminDao adminDao = new AdminDaoImpl();

    @Override
    public int login(Admin admin) {

        return adminDao.login(admin);
    }

    @Override
    public List<Admin> allAdmins() {

        return adminDao.allAdmins();
    }
}
