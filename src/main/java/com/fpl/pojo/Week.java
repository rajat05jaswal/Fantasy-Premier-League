package com.fpl.pojo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity(name="Week")
public class Week {
	@Override
	public String toString() {
		return ""+weekNo;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int weekId;
	
	@Column(unique = true)
	private String weekNo;
	
	private boolean matchesGenerated;
	
	private boolean scoresGenerated;
	
	
	public boolean isMatchesGenerated() {
		return matchesGenerated;
	}

	public void setMatchesGenerated(boolean matchesGenerated) {
		this.matchesGenerated = matchesGenerated;
	}

	public boolean isScoresGenerated() {
		return scoresGenerated;
	}

	public void setScoresGenerated(boolean scoresGenerated) {
		this.scoresGenerated = scoresGenerated;
	}

	@OneToMany(fetch=FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name="WEEK_ID")
	private Set<Match> matchesPerWeek = new HashSet<Match>();
	
//	@ManyToOne
//	private Player player;
	
//	public Player getPlayer() {
//		return player;
//	}
//
//	public void setPlayer(Player player) {
//		this.player = player;
//	}

	public Week() {
		this.matchesGenerated=false;
		this.scoresGenerated=false;
	}
	
	public int getWeekId() {
		return weekId;
	}

	public void setWeekId(int weekId) {
		this.weekId = weekId;
	}

	public String getWeekNo() {
		return weekNo;
	}

	public void setWeekNo(String weekNo) {
		this.weekNo = weekNo;
	}

	public Set<Match> getMatchesPerWeek() {
		return matchesPerWeek;
	}

	public void setMatchesPerWeek(Set<Match> matchesPerWeek) {
		this.matchesPerWeek = matchesPerWeek;
	}
}
