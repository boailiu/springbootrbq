package com.boai.springbootrbq.Mapper;

import com.boai.springbootrbq.Model.User;

import java.util.List;
import java.util.Map;

public interface UserMapper {
    void saveOrUpateUser(User user);

    User getUserById(Long id);

    List<User> listUser();

    Map<String, Object> getUserMap(long id);

    Map<String, Object> getUserMapByName(String name);
}
