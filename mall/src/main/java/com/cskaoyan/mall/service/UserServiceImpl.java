package com.cskaoyan.mall.service;

import com.cskaoyan.mall.dao.UserDao;
import com.cskaoyan.mall.dao.UserDaoImpl;
import com.cskaoyan.mall.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDao userDao=new UserDaoImpl();
    @Override
    public List<User> allUser() {
        return userDao.allUser();
    }
    @Override
    public List<User> searchUser(String param) {
        return userDao.searchUser(param);
    }

    @Override
    public int deleteUser(int id) {
        return userDao.deleteUser(id);
    }
}
