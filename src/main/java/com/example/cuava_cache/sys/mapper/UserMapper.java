package com.example.cuava_cache.sys.mapper;

import com.example.cuava_cache.sys.domain.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 方慧平
 * @since 2019-12-05
 */
@Mapper
public interface UserMapper{
    List<User> getUsers();
    int addUser(User user);
    List<User> getUsersByName(String userName );
}
