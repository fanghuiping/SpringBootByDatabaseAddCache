package com.example.cuava_cache.sys.service;

import com.example.cuava_cache.sys.domain.User;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 方慧平
 * @since 2019-12-05
 */
public interface IUserService{
    List<User> getUsers();
    int addUser(User user);
    List<User> getUsersByName(String userName );
}
