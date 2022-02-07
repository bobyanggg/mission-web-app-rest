package com.bobyanggg.missionwebapp.dao;


import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.bobyanggg.missionwebapp.entity.Mission;
import com.bobyanggg.missionwebapp.entity.User;


public interface MissionRepository extends JpaRepository<Mission, Integer> {
	
	@Transactional
	@Modifying
	@Query("update Mission m set m.worker = ?1 where m.id = ?2")
	void updateApply(User newWorker, int missionId);
		
	public List<Mission> findByUserId(int userId);
}
