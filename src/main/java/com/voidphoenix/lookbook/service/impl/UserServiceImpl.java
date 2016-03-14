package com.voidphoenix.lookbook.service.impl;

import com.voidphoenix.lookbook.service.UserService;
import com.voidphoenix.lookbook.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<User> getAllUsers() {
        List<User> users = jdbcTemplate.query("SELECT username FROM users",
                (ResultSet rs, int rowNum) -> {
                    User user = new User();
                    user.setUsername(rs.getString("username"));
                    return user;
                });

        return users;
    }
}
