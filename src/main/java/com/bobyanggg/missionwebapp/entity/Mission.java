package com.bobyanggg.missionwebapp.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="mission")
public class Mission {
	
	// define fields
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="category")
	private String category;
	
	@Column(name="content")
	private String content;
	
	@Column(name="location_city")
	private String locationCity;
	
	@Column(name="location_district")
	private String locationDistrict;
	
	@Column(name="location_address")
	private String locationAddress;
	
	@Column(name="salary")
	private String salary;
	
	@Column(name="time")
	private String time;
	
	@Column(name="detail")
	private String detail;
	
	@JsonIgnoreProperties({"order", "mission" , "user"})
	@ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE,
			 CascadeType.DETACH, CascadeType.REFRESH})
	@JoinColumn(name="user_id")
	private User user;
	
	@JsonIgnoreProperties({"order", "mission", "user"})
	@ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE,
			 CascadeType.DETACH, CascadeType.REFRESH})
	@JoinColumn(name="worker_id")
	private User worker;
	
	// define constructors
	
	public Mission() {
		
	}

	

	public Mission(int id, String category, String content, String locationCity, String locationDistrict,
			String locationAddress, String salary, String time, String detail, User user, User worker) {
		this.id = id;
		this.category = category;
		this.content = content;
		this.locationCity = locationCity;
		this.locationDistrict = locationDistrict;
		this.locationAddress = locationAddress;
		this.salary = salary;
		this.time = time;
		this.detail = detail;
		this.user = user;
		this.worker = worker;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getLocationCity() {
		return locationCity;
	}

	public void setLocationCity(String locationCity) {
		this.locationCity = locationCity;
	}

	public String getLocationDistrict() {
		return locationDistrict;
	}

	public void setLocationDistrict(String locationDistrict) {
		this.locationDistrict = locationDistrict;
	}

	public String getLocationAddress() {
		return locationAddress;
	}

	public void setLocationAddress(String locationAddress) {
		this.locationAddress = locationAddress;
	}

	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}

	

	public String getTime() {
		return time;
	}



	public void setTime(String time) {
		this.time = time;
	}



	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}



	public User getUser() {
		return user;
	}



	public void setUser(User user) {
		this.user = user;
	}



	public User getWorker() {
		return worker;
	}



	public void setWorker(User worker) {
		this.worker = worker;
	}



	@Override
	public String toString() {
		return "Mission [id=" + id + ", category=" + category + ", content=" + content + ", locationCity="
				+ locationCity + ", locationDistrict=" + locationDistrict + ", locationAddress=" + locationAddress
				+ ", salary=" + salary + ", time=" + time + ", detail=" + detail + ", user=" + user + ", worker="
				+ worker + "]";
	}


	
	

}

