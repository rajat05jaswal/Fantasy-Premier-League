package com.fpl.pojo;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name="PLTeam")
public class PLTeam {
	@Override
	public String toString() {
		return ""+name;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(unique = true)
	private String name;
	
	@OneToMany(fetch=FetchType.EAGER,cascade=CascadeType.ALL, mappedBy = "plTeam")
	@JsonIgnore
	private Set<Player> listOfPlayers;
	
	public PLTeam() {
		
	}
	
	public PLTeam(String name) {
		this.name=name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Set<Player> getListOfPlayers() {
		return listOfPlayers;
	}

	public void setListOfPlayers(Set<Player> listOfPlayers) {
		this.listOfPlayers = listOfPlayers;
	}

	
}
