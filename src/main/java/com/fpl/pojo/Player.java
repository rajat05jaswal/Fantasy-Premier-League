package com.fpl.pojo;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MapKey;
import javax.persistence.MapKeyJoinColumn;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity(name="Player")
public class Player {
	@Override
	public String toString() {
		return this.name;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int playerId;
	private double price;
	private String name;
	private String position;
	
	public Player() {
		
	}
	public Player(String name, double price, String position) {
		this.name=name;
		this.price=price;
		this.position=position;
	}
	
	@ManyToOne
	@JoinColumn(name="PLTeam_Id")
	private PLTeam plTeam;
	
	
	@ElementCollection(fetch= FetchType.EAGER)
    @CollectionTable(name="WEEK_POINTS",
    joinColumns=@JoinColumn(name="playerId"))
    @Column(name="points")
    @MapKeyJoinColumn(name="weekID", referencedColumnName="weekId")
	private Map<Week, Integer> points = new HashMap<Week, Integer>();
	
	@ManyToMany(mappedBy = "userteam")
	@JsonIgnore
	private Set<UserTeam> userTeam;

	public int getPlayerId() {
		return playerId;
	}

	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}


	public Map<Week, Integer> getPoints() {
		return points;
	}

	public void setPoints(Map<Week, Integer> points) {
		this.points = points;
	}

	public Set<UserTeam> getUserTeam() {
		return userTeam;
	}

	public void setUserTeam(Set<UserTeam> userTeam) {
		this.userTeam = userTeam;
	}

	public PLTeam getPlTeam() {
		return plTeam;
	}

	public void setPlTeam(PLTeam plTeam) {
		this.plTeam = plTeam;
	}

}
