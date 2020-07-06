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

    @Override
    public List<Admin> getSearchAdmins(Admin admin) {
        return adminDao.getSerachAdmins(admin);
    }

    @Override
    public int addAdminss(Admin admin) {
        return adminDao.addAdminss(admin);
    }

    @Override
    public void deleteAdmins(int id) {
        adminDao.deleteAdmins(id);
    }

    @Override
    public Admin getAdminsInfoById(int id) {
        return adminDao.getAdminsInfoById(id);
    }

    @Override
    public int updateAdminss(Admin admin) {
        return adminDao.updateAdminss(admin);
    }
}
