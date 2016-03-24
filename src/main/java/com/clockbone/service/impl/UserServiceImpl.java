package com.clockbone.service.impl;

import com.clockbone.service.UserService;
import com.clockpone.domain.UserNew;
import com.clockpone.dynamicdao.UserNewMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by qinjun on 2016/3/23.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserNewMapper userMapper;
    @Override
    public List<UserNew> getAll() {
        return userMapper.getAll();
    }

    @Override
    public UserNew getUserById(int id) {
       return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public UserNew getUserByName(String name) {

        return null;
    }

    @Override
    public int update(UserNew user) {
        return userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public int add(UserNew user) {
        return userMapper.insert(user);
    }

    @Override
    public int delete(UserNew user) {
        return userMapper.deleteByPrimaryKey(user.getUserId());
    }
}
