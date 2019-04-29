package com.fpl.controller;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fpl.dao.PlayerDao;
import com.fpl.dao.UserDao;
import com.fpl.dao.WeekDao;
import com.fpl.exception.UserException;
import com.fpl.exception.WeekException;
import com.fpl.pojo.Match;
import com.fpl.pojo.Player;
import com.fpl.pojo.User;
import com.fpl.pojo.UserTeam;
import com.fpl.pojo.Week;

@Controller
public class AdminPointsController {
	
	@Autowired
	WeekDao weekDao;
	
	@Autowired
	UserDao userDao;
	
	
	@RequestMapping(value = "/registerPoints",method=RequestMethod.GET)
	public String handlePoint(Model model, HttpSession session) throws WeekException {
		Week w=weekDao.currentWeek();
		Set<Match> matches=w.getMatchesPerWeek();
		System.out.println(matches);
		model.addAttribute("matches",matches);
		model.addAttribute("week",w.getWeekNo());
		boolean weekAnalysisDone=true;
		for(Match m: matches) {
			if(!m.isAnalysisDone()) {
				weekAnalysisDone=false;
				break;
			}
		}
		System.out.println(weekAnalysisDone);
		model.addAttribute("weekAnalysisDone",weekAnalysisDone);
		return "AdminScoreRegister";
	}
	
	@RequestMapping(value = "/registerPoints",method=RequestMethod.POST)
	public String setPoints(Model model) throws WeekException, UserException {
		Week w=weekDao.currentWeek();
		System.out.println(w);
		List<User> users=userDao.getUsers();
		
		for(User u: users) {
			System.out.println(u.getUserTeam().getCaptain());
			int points=0;
			for(Player p:u.getUserTeam().getUserteam()) {
				for(Week currWeek: p.getPoints().keySet()) {
					if(currWeek.getWeekId()==w.getWeekId()) {
						System.out.println(p.getPlayerId()+","+p.getPoints().get(currWeek));
						points+=p.getPoints().get(currWeek);
						if(u.getUserTeam().getCaptain().getPlayerId()==p.getPlayerId()) {
							points+=p.getPoints().get(currWeek);
						}
					}
				}
			}
			for(Week curr: u.getPointsPerWeek().keySet()) {
				if(curr.getWeekId()==w.getWeekId()) {
					u.getPointsPerWeek().put(curr, points);
					break;
				}
			}
			userDao.deleteUserTeam(u);
			userDao.saveUser(u);
			System.out.println(points);
		}
		w.setScoresGenerated(true);
		weekDao.registerWeek(w);
		return "AdminScoreRegister";
	}
}
