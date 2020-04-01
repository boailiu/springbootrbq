package com.boai.springbootrbq.Service.ServiceImpl;

import com.boai.springbootrbq.Model.User;
import com.boai.springbootrbq.Repository.RepositoryImpl.UserRepositoryImpl;
import com.boai.springbootrbq.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private UserRepositoryImpl repo;

    @Autowired
    public UserServiceImpl(UserRepositoryImpl repo) {
        this.repo = repo;
    }

    @Override
    public User getUserById(int userId) {
        return repo.getUserById(Long.parseLong(String.valueOf(userId)));
    }
}
