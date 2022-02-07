package com.bobyanggg.missionwebapp.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.bobyanggg.missionwebapp.entity.User;
import com.bobyanggg.missionwebapp.user.CrmUser;



public interface UserService extends UserDetailsService {
	
	public List<User> findAll();
	
	public User findById(int theId);
	
	public void save(User theUser);
	
	public void deleteById(int theId);
	
	public User findByUserName(String theUserName);
	
	public void save(CrmUser crmUser);

}
