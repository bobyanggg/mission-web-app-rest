package com.bobyanggg.missionwebapp.service;

import java.util.List;

import com.bobyanggg.missionwebapp.entity.Mission;
import com.bobyanggg.missionwebapp.entity.User;

public interface MissionService {
	
	public List<Mission> findAll();
	
	public Mission findById(int theId);
	
	public void save(Mission theMission);
	
	public void deleteById(int theId);
	
	public void updateApply(User newWorker, int missionId);

	List<Mission> findByUserId(int userId);


}
