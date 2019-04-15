package com.my.myshop.user.service;

import com.my.myshop.user.entity.User;

public interface UserService {
    User getUserById(long l);

    void sendVercode(String s);

    User login(User user);

    User selectUserByPhone(String phone);
}
