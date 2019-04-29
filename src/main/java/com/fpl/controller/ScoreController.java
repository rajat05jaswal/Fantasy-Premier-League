package com.fpl.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fpl.dao.MatchDao;
import com.fpl.dao.PlayerDao;
import com.fpl.dao.WeekDao;
import com.fpl.exception.MatchException;
import com.fpl.exception.PlayerException;
import com.fpl.exception.TeamException;
import com.fpl.exception.WeekException;
import com.fpl.pojo.Match;
import com.fpl.pojo.PLTeam;
import com.fpl.pojo.Player;
import com.fpl.pojo.Week;

@Controller
public class ScoreController {
	
	@Autowired
	MatchDao matchDao;
	
	@Autowired
	PlayerDao playerDao;
	
	@Autowired
	WeekDao weekDao;
	
	
	@RequestMapping(value = "scoreViaMatch", method = RequestMethod.POST)
	public String scoreViaMatch(HttpServletRequest req,Model model) throws  MatchException, NumberFormatException, PlayerException, WeekException {
		Map<String, String[]> map=req.getParameterMap();
		
		String homeScore=map.get("home-score")[0];
		String awayScore=map.get("away-score")[0];
		String matchId=map.get("matchId")[0];
		String[] homeGoalScorer=map.get("home-goal-scorer");
		String[] awayGoalScorer=map.get("away-goal-scorer");
		String[] homeYellowCards=map.get("home-yellow-card");
		String[] awayYellowCards=map.get("away-yellow-card");
		String[] homeRedCards=map.get("home-red-card");
		String[] awayRedCards=map.get("away-red-card");
		
		
		Map<String,Integer> hm=new HashMap();
		if(!homeScore.equals("0")) {
			for(String x: homeGoalScorer) {
				String p=x;
				if(hm.containsKey(p)) {
					int score=hm.get(p);
					hm.put(p, score+5);
				}else {
					hm.put(p, 5);
				}
			}
		}
		
		
		if(!awayScore.equals("0")) {
			for(String x: awayGoalScorer) {
				String p=x;
				if(hm.containsKey(p)) {
					int score=hm.get(p);
					hm.put(p, score+5);
				}else {
					hm.put(p, 5);
				}
			}
		}
		
		
		if(!req.getParameter("home-yellow-card-count").equals("0")) {
			for(String x: homeYellowCards) {
				String p=x;
				if(hm.containsKey(p)) {
					int score=hm.get(p);
					hm.put(p, score-2);
				}else {
					hm.put(p, -2);
				}
			}
		}
		
		
		if(!req.getParameter("away-yellow-card-count").equals("0")) {
			for(String x: awayYellowCards) {
				String p=x;
				if(hm.containsKey(p)) {
					int score=hm.get(p);
					hm.put(p, score-2);
				}else {
					hm.put(p, -2);
				}
			}
		}
		
		
		
		Set<Player> homeRedCardSet=new HashSet<Player>();
		
		if(!req.getParameter("home-red-card-count").equals("0")) {
			for(String x: homeRedCards) {
				String p=x;
				if(hm.containsKey(p)) {
					int score=hm.get(p);
					hm.put(p, score-2);
				}else {
					hm.put(p, -2);
				}
			}
		}
		
		
		Set<Player> awayRedCardSet=new HashSet<Player>();
		
		if(!req.getParameter("away-red-card-count").equals("0")) {
			for(String x: awayRedCards) {
				String p=x;
				if(hm.containsKey(p)) {
					int score=hm.get(p);
					hm.put(p, score-2);
				}else {
					hm.put(p, -2);
				}
			}
		}
		
		
		Match match=matchDao.getMatch(Integer.parseInt(matchId));
		if(match.isAnalysisDone()) {
			return "AdminScoreRegister";
		}
		
		
		Week currentWeek=weekDao.currentWeek();
		for(Map.Entry<String, Integer> entry: hm.entrySet()) {
			String p=entry.getKey();
			Player x=playerDao.getPlayerByID(Integer.parseInt(p));
			Map<Week, Integer> pointsMap=x.getPoints();
			for(Week w: pointsMap.keySet()) {
				if(w.getWeekId()==currentWeek.getWeekId()) {
					pointsMap.put(w, entry.getValue());
					x.setPoints(pointsMap);
					playerDao.registerPlayer(x);
					break;
				}
			}
		}
		match.setScore(homeScore+"-"+awayScore);

		match.setAnalysisDone(true);
		matchDao.saveMatch(match);		
		
		
		return "AdminScoreRegister";
	}
}
