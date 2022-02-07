package com.bobyanggg.missionwebapp.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bobyanggg.missionwebapp.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	public List<User> findAllByOrderByLastNameAsc();
	
    @Query("select u from User u where u.userName = ?1")
    public Optional<User> findByUserName(String theUserName);

}
