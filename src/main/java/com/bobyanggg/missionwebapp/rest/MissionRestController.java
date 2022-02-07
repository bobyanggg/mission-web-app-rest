package com.bobyanggg.missionwebapp.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bobyanggg.missionwebapp.entity.Mission;
import com.bobyanggg.missionwebapp.entity.User;
import com.bobyanggg.missionwebapp.service.MissionService;
import com.bobyanggg.missionwebapp.service.UserService;

@RestController
@RequestMapping("/missions")
public class MissionRestController {
	
	private MissionService missionService;
	private UserService userService;
	
	@Autowired
	public MissionRestController(MissionService theMissionService,UserService theUserService) {
		missionService = theMissionService;
		userService=theUserService;
	}
	
	// expose "/employees" and return list of employees
	@GetMapping("/list")
	public List<Mission> findAll() {
		return missionService.findAll();
		
	}
	
	@GetMapping("/list/{missionId}")
	public Mission getMission(@PathVariable int missionId) {
		
		Mission theMission = missionService.findById(missionId);
		
		if (theMission == null) {
			throw new RuntimeException("Mission id not found - " + missionId);
		}
		
		return theMission;
	}

	
	@GetMapping("/list/mymissions/{userId}")
	public List<Mission> getMyMissions(@PathVariable int userId) {
		
		return missionService.findByUserId(userId);
	}

	

	

}
