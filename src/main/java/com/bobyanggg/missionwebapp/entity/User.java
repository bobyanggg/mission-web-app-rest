package com.bobyanggg.missionwebapp.entity;

import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name="user")
public class User {
	// define fields
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="username")
	private String userName;
	
	@Column(name="password")
	private String password;
	
	@Column(name="first_name")
	private String firstName;

	@Column(name="last_name")
	private String lastName;
	
	@Column(name="email")
	private String email;
	
	@Column(name="phone")
	private String phone;
	
	@OneToMany(fetch=FetchType.LAZY,
			mappedBy = "user",
			cascade= { CascadeType.PERSIST, CascadeType.MERGE,
			 CascadeType.DETACH, CascadeType.REFRESH})
	private List<Mission> order;
	
	@OneToMany(fetch=FetchType.LAZY,
			mappedBy = "user",
			cascade= { CascadeType.PERSIST, CascadeType.MERGE,
			 CascadeType.DETACH, CascadeType.REFRESH})
	private List<Mission> mission;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "users_roles", 
	joinColumns = @JoinColumn(name = "user_id"), 
	inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Collection<Role> roles;
	
	@Column(name="is_delete")
	private boolean isDelete;

		
	// define constructors
	
	public User() {
		
	}
	



	public Collection<Role> getRoles() {
		return roles;
	}




	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}




	public User(int id, String userName, String password, String firstName, String lastName, String email, String phone,
			List<Mission> order, List<Mission> mission, Collection<com.bobyanggg.missionwebapp.entity.Role> roles,
			boolean isDelete) {
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
		this.order = order;
		this.mission = mission;
		this.roles = roles;
		this.isDelete = isDelete;
	}




	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	public String getUserName() {
		return userName;
	}



	public void setUserName(String userName) {
		this.userName = userName;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}


	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}




	public List<Mission> getOrder() {
		return order;
	}



	public void setOrder(List<Mission> order) {
		this.order = order;
	}



	public List<Mission> getMission() {
		return mission;
	}



	public void setMission(List<Mission> mission) {
		this.mission = mission;
	}



	public boolean isDelete() {
		return isDelete;
	}

	public void setDelete(boolean isDelete) {
		this.isDelete = isDelete;
	}




	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", password=" + password + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", email=" + email + ", phone=" + phone + ", order=" + order + ", mission="
				+ mission + ", roles=" + roles + ", isDelete=" + isDelete + "]";
	}






}