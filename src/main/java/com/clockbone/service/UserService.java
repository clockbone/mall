package com.clockbone.service;

import com.clockpone.domain.UserNew;

import java.util.List;

/**
 * Created by qinjun on 2016/3/23.
 */
public interface UserService {
    List<UserNew> getAll();
    UserNew getUserById(int id);
    UserNew getUserByName(String name);
    int update(UserNew user);
    int add(UserNew user);
    int delete(UserNew user);
}
