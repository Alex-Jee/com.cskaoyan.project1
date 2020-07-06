/**
 * User: zsquirrel
 * Date: 2020/4/28
 * Time: 4:45 下午
 */
package com.cskaoyan.mall.service;

import com.cskaoyan.mall.model.Admin;

import java.util.List;

public interface AdminService {
    int login(Admin admin);

    List<Admin> allAdmins();

    List<Admin> getSearchAdmins(Admin admin);

    int addAdminss(Admin admin);

    void deleteAdmins(int id);

    Admin getAdminsInfoById(int id);

    int updateAdminss(Admin admin);
}
