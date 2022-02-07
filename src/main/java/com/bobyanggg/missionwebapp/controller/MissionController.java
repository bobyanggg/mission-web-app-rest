package com.bobyanggg.missionwebapp.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.Null;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bobyanggg.missionwebapp.entity.Mission;
import com.bobyanggg.missionwebapp.entity.User;
import com.bobyanggg.missionwebapp.service.MissionService;
import com.bobyanggg.missionwebapp.service.UserService;


@Controller
@RequestMapping("/missionsPage")
public class MissionController {

	private MissionService missionService;
	private UserService userService;
	
	public MissionController(MissionService theMissionService, UserService theUserService) {
		missionService = theMissionService;
		userService = theUserService;
	}
	
	// add mapping for "/list"

	@GetMapping("/listPage")
	public String listMission(Model theModel) {
		
		return "missions/list-missions";
	}
	
	@GetMapping("/myMissions")
	public String mylistMission(Model theModel,HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		
		int theUserId = (int) session.getAttribute("userId");
		theModel.addAttribute("userId", theUserId);	
		
		return "missions/my-missions";
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel,User theUser,HttpServletRequest request) {
		
		HttpSession session = request.getSession();

		int theUserId = (int) session.getAttribute("userId");
		
		// create model attribute to bind form data
		Mission theMission = new Mission();
		
		theModel.addAttribute("mission", theMission);
		theModel.addAttribute("userId", theUserId);
		return "missions/add-mission-form";
	}

	@GetMapping("/showForApply")
	public String showFormForUpdate(@RequestParam("id") int theId, Model theModel,User theUser,HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session = request.getSession();
		
		// get the mission from the service
		Mission theMission = missionService.findById(theId);

		int theUserId = (int) session.getAttribute("userId");
		
		if (theUserId==theMission.getUser().getId()){
			theModel.addAttribute("errorMessage","Can't apply your own page!");
			return "missions/error-page";
		}
		
		if (theMission.getWorker()!=null){
			theModel.addAttribute("errorMessage","Oops! Someone already applied!");
			return "missions/error-page";
		}
		
		theUser = userService.findById(theUserId);
		
		
		// set mission as a model attribute to pre-populate the form
		theModel.addAttribute("mission", theMission);
		theModel.addAttribute("newWorker", theUser);
		
		// send over to our form
		return "missions/mission-apply-form";			
	}
	
	@GetMapping("/showForDelete")
	public String showFormForDelete(@RequestParam("missionId") int theMissionId, Model theModel,HttpServletRequest request) {
		
		// get the mission from the service
		Mission theMission = missionService.findById(theMissionId);
		
		
		// set mission as a model attribute to pre-populate the form
		theModel.addAttribute("mission", theMission);
		
		// send over to delete form
		return "missions/mission-delete-form";			
	}
	
	
	@PostMapping("/save")
	public String saveMission(@ModelAttribute("mission") Mission theMission,HttpServletRequest request, HttpServletResponse response,Model theModel) {
		
		HttpSession session = request.getSession();
		int userId = (int) session.getAttribute("userId");
		User missionOwner = userService.findById(userId);
		theMission.setUser(missionOwner);
		// save the new mission
		missionService.save(theMission);
		
		// use a redirect to prevent duplicate submissions
		return "redirect:/missionsPage/listPage";
	}
	
	
	@GetMapping("/delete")
	public String delete(@RequestParam("id") int missionId) {  //Param id refers to the id of mission(can't be other name)
		
		// delete the employee
		missionService.deleteById(missionId);
		
		// redirect to /employees/list
		return "redirect:/missionsPage/myMissions";
		
	}
	
	@GetMapping("/updateApply")
	public String updateApply(HttpServletRequest request, HttpServletResponse response,@RequestParam("id") int missionId,Model theModel) {

		HttpSession session = request.getSession();
		int newWorkerId = (int) session.getAttribute("userId");
		User newWorker = userService.findById(newWorkerId);
		missionService.updateApply(newWorker,missionId);
		
		return "redirect:/missionsPage/listPage";
	
	}
}


















