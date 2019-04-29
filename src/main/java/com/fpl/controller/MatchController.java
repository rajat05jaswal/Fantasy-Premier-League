package com.fpl.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fpl.dao.MatchDao;
import com.fpl.dao.PLTeamDao;
import com.fpl.dao.WeekDao;
import com.fpl.exception.MatchException;
import com.fpl.exception.PlayerException;
import com.fpl.exception.TeamException;
import com.fpl.exception.WeekException;
import com.fpl.pojo.Match;
import com.fpl.pojo.PLTeam;
import com.fpl.pojo.Player;
import com.fpl.pojo.User;
import com.fpl.pojo.Week;

@Controller
public class MatchController {
	@Autowired
	WeekDao weekDao;
	
	@Autowired
	MatchDao matchDao;
	
	@Autowired
	PLTeamDao plTeamDao;
	
	
	@RequestMapping(value = "/generateMatches", method=RequestMethod.GET)
	public String generateMatchesForm(ModelMap model, HttpSession session) throws WeekException {
		Week week=weekDao.getWeek(weekDao.currentWeek().getWeekNo());
		System.out.println();
		boolean generated=false;
		Set<Match> matchesPerWeek=week.getMatchesPerWeek();
		System.out.println(week.isMatchesGenerated());
		if(week.isMatchesGenerated()) {
//			matchesPerWeek=week.getMatchesPerWeek();
			generated=true;
		}
		System.out.println(matchesPerWeek);
		model.addAttribute("matches",matchesPerWeek);
		model.addAttribute("generatedAlready",generated);
		model.addAttribute("currentWeek",week.getWeekNo());
		return "AdminGenerateMatches";
	}
	
	
	@RequestMapping(value = "/generateMatches", method=RequestMethod.POST)
	public String generateMatches(ModelMap model) throws TeamException, MatchException, WeekException {
		Week week=weekDao.currentWeek();
		Set<Match> matches=new HashSet();
		Week newWeek = week;
		if(week.isMatchesGenerated()) {
			matches=week.getMatchesPerWeek();
		}else {
			List<PLTeam> teamList=plTeamDao.getTeams();
			Set<Integer> plTeamSet=new HashSet();
			Random r = new Random();
			int high = teamList.size();
			Set<Match> matchSet=new HashSet<Match>();
			while(plTeamSet.size()!=teamList.size()) {
				int randomNo = r.nextInt(high);
				PLTeam homeTeam=teamList.get(randomNo);
				if(!plTeamSet.contains(randomNo)) {
					int newrandomNo=r.nextInt(high);
					PLTeam awayTeam=teamList.get(newrandomNo);

					if(!plTeamSet.contains(newrandomNo) && newrandomNo!=randomNo) {
						Match m=new Match(homeTeam, awayTeam);
						week.getMatchesPerWeek().add(m);
						plTeamSet.add(randomNo);
						plTeamSet.add(newrandomNo);
					}
				}
			}
			System.out.println(matchSet);
			week.setMatchesGenerated(true);
			newWeek=weekDao.registerWeek(week);
		}
		model.addAttribute("matches",newWeek.getMatchesPerWeek());
		return "AdminGenerateMatches";
	}
	
	@RequestMapping(value = "/getMatch/{matchId}", method = RequestMethod.GET)
	public @ResponseBody String getMatch(@PathVariable String matchId) throws JsonProcessingException, MatchException {
		
		ObjectMapper mapper=new ObjectMapper();
		Match match=matchDao.getMatch(Integer.parseInt(matchId));
		String jsonString=mapper.writeValueAsString(match);
		return jsonString;
	}
}
