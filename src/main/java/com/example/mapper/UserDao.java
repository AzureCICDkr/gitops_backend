package com.example.mapper;


import org.springframework.stereotype.Repository;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.dto.User;

@Mapper
public interface UserDao {

    User findByEmail(@Param("email") String email);

    void insertUser(User user);

    void updateUser(User user);
}

