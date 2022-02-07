package com.bobyanggg.missionwebapp.dao;

import com.bobyanggg.missionwebapp.entity.User;

public interface UserDao {

    public User findByUserName(String userName);
    
    public void save(User user);
    
}
