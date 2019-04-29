package com.fpl.pojo;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity(name="UserTeam")
public class UserTeam {
	@Override
	public String toString() {
		return String.valueOf(this.userId);
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;
	private String userTeamName;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name="userteam_player_table",joinColumns = {@JoinColumn(name="userId")},inverseJoinColumns = {@JoinColumn(name="playerId")})
	private Set<Player> userteam;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="Player_Id")
	private Player captain;
	
	
	@OneToOne(fetch= FetchType.LAZY)
	@JoinColumn(name="User_Id")
	private User user;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="week_Id")
	private Week week;
	
	public UserTeam() {
		
	}
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserTeamName() {
		return userTeamName;
	}
	public void setUserTeamName(String userTeamName) {
		this.userTeamName = userTeamName;
	}
	public Set<Player> getUserteam() {
		return userteam;
	}
	public void setUserteam(Set<Player> userteam) {
		this.userteam = userteam;
	}
	public Player getCaptain() {
		return captain;
	}
	public void setCaptain(Player captain) {
		this.captain = captain;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Week getWeek() {
		return week;
	}
	public void setWeek(Week week) {
		this.week = week;
	}
}
