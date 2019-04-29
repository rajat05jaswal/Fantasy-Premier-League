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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

@Entity(name="Match_Table")
public class Match {
	@Override
	public String toString() {
		return this.hometeam +" VS " + this.awayTeam;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String matchId;
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="homeTeam_id")
	private PLTeam hometeam;
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="awayTeam_id")
	private PLTeam awayTeam;
	
	private String score;
	
	@Transient
	private Set<Player> goalScorers = new HashSet();
	
	@Transient
	private Set<Player> yellowCards = new HashSet();
	
	@Transient
	private Set<Player> redCards = new HashSet();
	
	private boolean analysisDone;
	
	public boolean isAnalysisDone() {
		return analysisDone;
	}

	public void setAnalysisDone(boolean analysisDone) {
		this.analysisDone = analysisDone;
	}

	public Match() {
	}
	
	public Match(PLTeam homeTeam, PLTeam awayTeam) {
		this.hometeam=homeTeam;
		this.awayTeam=awayTeam;
		this.analysisDone=false;
		this.matchId=this.hometeam +" VS " + this.awayTeam;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMatchId() {
		return matchId;
	}
	public void setMatchId(String matchId) {
		this.matchId = matchId;
	}
	public PLTeam getHometeam() {
		return hometeam;
	}
	public void setHometeam(PLTeam hometeam) {
		this.hometeam = hometeam;
	}
	public PLTeam getAwayTeam() {
		return awayTeam;
	}
	public void setAwayTeam(PLTeam awayTeam) {
		this.awayTeam = awayTeam;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public Set<Player> getGoalScorers() {
		return goalScorers;
	}

	public void setGoalScorers(Set<Player> goalScorers) {
		this.goalScorers = goalScorers;
	}

	public Set<Player> getYellowCards() {
		return yellowCards;
	}

	public void setYellowCards(Set<Player> yellowCards) {
		this.yellowCards = yellowCards;
	}

	public Set<Player> getRedCards() {
		return redCards;
	}

	public void setRedCards(Set<Player> redCards) {
		this.redCards = redCards;
	}
	
}
