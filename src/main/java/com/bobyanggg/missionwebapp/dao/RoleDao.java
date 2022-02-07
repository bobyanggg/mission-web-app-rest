package com.bobyanggg.missionwebapp.dao;

import com.bobyanggg.missionwebapp.entity.Role;

public interface RoleDao {

	public Role findRoleByName(String theRoleName);
	
}
