package com.bobyanggg.missionwebapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.bobyanggg.missionwebapp.dao.MissionRepository;
import com.bobyanggg.missionwebapp.entity.Mission;
import com.bobyanggg.missionwebapp.entity.User;


@Service
public class MissionServiceImpl implements MissionService {
	
	private MissionRepository missionRepository;

	@Autowired
	public MissionServiceImpl(MissionRepository theMissionRepository) {
		missionRepository = theMissionRepository;
	}

	@Override
	public List<Mission> findAll() {
		
		return missionRepository.findAll();
	}

	@Override
	public Mission findById(int theId) {
		Optional<Mission> result = missionRepository.findById(theId);
		
		Mission theMission = null;
		
		if (result.isPresent()) {
			theMission = result.get();
		}
		else {
			// we didn't find the employee
			throw new RuntimeException("Did not find user id - " + theId);
		}
		
		return theMission;
	}

	@Override
	public void save(Mission theMission) {
		missionRepository.save(theMission);

	}

	@Override
	public void deleteById(int theId) {
		
		missionRepository.deleteById(theId);

	}
	
	@Override
	public void updateApply(User newWorker, int missionId) {
		missionRepository.updateApply(newWorker, missionId);
	}
	
	@Override
	public List<Mission> findByUserId(int userId){
		return missionRepository.findByUserId(userId);
	}

}
