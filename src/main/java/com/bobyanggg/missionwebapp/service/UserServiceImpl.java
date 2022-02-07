package com.bobyanggg.missionwebapp.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bobyanggg.missionwebapp.dao.RoleDao;
import com.bobyanggg.missionwebapp.dao.UserDao;
import com.bobyanggg.missionwebapp.dao.UserRepository;
import com.bobyanggg.missionwebapp.entity.Role;
import com.bobyanggg.missionwebapp.entity.User;
import com.bobyanggg.missionwebapp.user.CrmUser;



@Service
public class UserServiceImpl implements UserService {
	
	private UserRepository userRepository;
	
	@Autowired
	public UserServiceImpl(UserRepository theUserRepository) {
		userRepository = theUserRepository;
	}

	@Override
	public List<User> findAll() {
		
		return userRepository.findAllByOrderByLastNameAsc();
	}

	@Override
	public User findById(int theId) {
		Optional<User> result = userRepository.findById(theId);
		
		User theUser = null;
		
		if (result.isPresent()) {
			theUser = result.get();
		}
		else {
			// we didn't find the employee
			throw new RuntimeException("Did not find user id - " + theId);
		}
		
		return theUser;
	}

	@Override
	public void save(User theUser) {
		userRepository.save(theUser);

	}

	// need to inject user dao
	@Autowired
	private UserDao userDao;

	@Autowired
	private RoleDao roleDao;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	@Transactional
	public User findByUserName(String userName) {
		// check the database if the user already exists
		return userDao.findByUserName(userName);
	}

	@Override
	@Transactional
	public void save(CrmUser crmUser) {
		User user = new User();
		 // assign user details to the user object
		user.setUserName(crmUser.getUserName());
		user.setPassword(passwordEncoder.encode(crmUser.getPassword()));
		user.setFirstName(crmUser.getFirstName());
		user.setLastName(crmUser.getLastName());
		user.setEmail(crmUser.getEmail());
		user.setPhone(crmUser.getPhone());
		// give user default role of "employee"
		user.setRoles(Arrays.asList(roleDao.findRoleByName("ROLE_USER")));

		 // save user in the database
		userDao.save(user);
	}

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		User user = userDao.findByUserName(userName);
		if (user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
				mapRolesToAuthorities(user.getRoles()));
	}

	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}
	
	@Override
	public void deleteById(int theId) {
		
		userRepository.deleteById(theId);

	}


//	@Override
//	public User findByUserName(String theUserName) {
//		theUserName ="David";//test
//		Optional<User> result = userRepository.findByUserName(theUserName);
//		
//		User theUser = null;
//		
//		if (result.isPresent()) {
//			theUser = result.get();
//		}
//		else {
//			// we didn't find the employee
//			throw new RuntimeException("Did not find user id - " + theUserName);
//		}
//		return theUser;
//	}



}
