package com.fpl.pojo;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyJoinColumn;
import javax.persistence.OneToOne;

@Entity(name="User")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;
	private String name;
	
	@Column(unique = true)
	private String username;
	
	private String password;
	
	@OneToOne(fetch=FetchType.EAGER, mappedBy = "user")
	private UserTeam userTeam;
	
	private int rank;
	
	@ElementCollection(fetch= FetchType.EAGER)
    @CollectionTable(name="USER_WEEK_POINTS",
    joinColumns=@JoinColumn(name="userId"))
    @Column(name="points")
    @MapKeyJoinColumn(name="weekID", referencedColumnName="weekId")
	private Map<Week, Integer> pointsPerWeek=new HashMap<Week, Integer>();
	
	public Map<Week, Integer> getPointsPerWeek() {
		return pointsPerWeek;
	}


	public void setPointsPerWeek(Map<Week, Integer> pointsPerWeek) {
		this.pointsPerWeek = pointsPerWeek;
	}


	public User() {
		
	}
	
	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public UserTeam getUserTeam() {
		return userTeam;
	}
	public void setUserTeam(UserTeam userTeam) {
		this.userTeam = userTeam;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	
}
