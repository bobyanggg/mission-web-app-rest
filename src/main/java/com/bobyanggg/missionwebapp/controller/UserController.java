package com.bobyanggg.missionwebapp.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bobyanggg.missionwebapp.entity.Mission;
import com.bobyanggg.missionwebapp.entity.User;
import com.bobyanggg.missionwebapp.service.UserService;


@Controller
@RequestMapping("/users")
public class UserController {

	private UserService userService;
	
	public UserController(UserService theUserService) {
		userService = theUserService;
	}
	
	// add mapping for "/list"

	@GetMapping("/list")
	public String listUser(Model theModel) {
		
		// get employees from db
		List<User> theUsers = userService.findAll();
		
		// add to the spring model
		theModel.addAttribute("users", theUsers);
		
		return "users/list-users";
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		
		// create model attribute to bind form data
		User theUser = new User();
		
		theModel.addAttribute("user", theUser);
		
		return "users/user-form";
	}

	@PostMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("userId") int theId, //check request mapping later
									Model theModel) {
		
		// get the employee from the service
		User theUser = userService.findById(theId);
		
		// set employee as a model attribute to pre-populate the form
		theModel.addAttribute("user", theUser);
		
		// send over to our form
		return "users/user-form";			
	}
	
	
	@PostMapping("/save")
	public String saveUser(@ModelAttribute("user") User theUser) {
		
		// save the employee
		userService.save(theUser);
		
		// use a redirect to prevent duplicate submissions
		return "redirect:/users/list";
	}
	
	
	@PostMapping("/delete")
	public String delete(@RequestParam("userId") int theId) {
		
		// delete the employee
		userService.deleteById(theId);
		
		// redirect to /employees/list
		return "redirect:/users/list";
		
	}
	
	//only for demo
	@GetMapping("/mission")
	public String getMission(HttpServletRequest request, HttpServletResponse response, User theUser,Model theModel)
			throws IOException, ServletException{
		
		HttpSession session = request.getSession();

		int theUserId = (int) session.getAttribute("userId");
		
		theUser = userService.findById(theUserId);
		
		// create model attribute to bind form data

		List<Mission> theMissions=theUser.getMission();

		theModel.addAttribute("missions", theMissions);
		
		return "users/list-users-mission";
	}
}


















