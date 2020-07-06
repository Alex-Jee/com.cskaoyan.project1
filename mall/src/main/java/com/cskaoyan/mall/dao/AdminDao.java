/**
 * User: zsquirrel
 * Date: 2020/4/28
 * Time: 4:46 下午
 */
package com.cskaoyan.mall.dao;

import com.cskaoyan.mall.model.Admin;

import java.util.List;

public interface AdminDao {
    int login(Admin admin);

    List<Admin> allAdmins();

    List<Admin> getSerachAdmins(Admin admin);
}
