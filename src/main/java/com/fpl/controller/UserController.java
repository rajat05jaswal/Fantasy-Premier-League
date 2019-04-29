package com.fpl.controller;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fpl.dao.PlayerDao;
import com.fpl.dao.UserDao;
import com.fpl.dao.WeekDao;
import com.fpl.exception.PlayerException;
import com.fpl.exception.UserException;
import com.fpl.exception.WeekException;
import com.fpl.pojo.Player;
import com.fpl.pojo.User;
import com.fpl.pojo.UserTeam;
import com.fpl.pojo.Week;

@Controller
public class UserController {
	
	@Autowired
	PlayerDao playerDao;
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	WeekDao weekDao;
	
	@RequestMapping(value="userTeam", method=RequestMethod.GET)
	public String userTeamPopulate(ModelMap model,HttpSession session) throws UserException {
		String username=(String) session.getAttribute("username");
		User u=userDao.getUserByUsername(username);
		model.addAttribute("id",u.getUserId());
		return "userhomepage";
	}
	@RequestMapping(value="userTeam",method = RequestMethod.POST)
	public String userTeamSubmit(ModelMap model,HttpSession session,@RequestParam("team-player") String[] playerIdteam, @RequestParam("captain") String captain) throws NumberFormatException, PlayerException, UserException, WeekException {
		Set<Player> team=new HashSet();
		for(String x: playerIdteam) {
			Player p=playerDao.getPlayerByID(Integer.parseInt(x));
			team.add(p);
		}
		String username=(String) session.getAttribute("username");
		User u=userDao.getUserByUsername(username);
		UserTeam uTeam;
		if(u.getUserTeam()!=null) {
			uTeam=u.getUserTeam();
			System.out.println(uTeam.getUserteam());
		}else {
			uTeam=new UserTeam();
		}
		uTeam.setUserteam(team);
		Week week=weekDao.currentWeek();
		uTeam.setWeek(week);
		uTeam.setUser(u);
		u.setUserTeam(uTeam);
		int pointsPerWeek=0;
		
		Player captainPlayer=playerDao.getPlayerByID(Integer.parseInt(captain));
		uTeam.setCaptain(captainPlayer);
		for(Player p: uTeam.getUserteam()) {
			for(Week w: p.getPoints().keySet()) {
				if(w.getWeekId()==week.getWeekId()) {
					pointsPerWeek+=p.getPoints().get(w);
					System.out.println(w+" - "+p.getPoints().get(w));
					if(p.equals(captainPlayer)) {
						pointsPerWeek+=p.getPoints().get(w);
					}
					break;
				}
			}
		}
		System.out.println("Points ARE -"+pointsPerWeek);
		Map<Week, Integer> userPointsMap=u.getPointsPerWeek();
		for(Week w: userPointsMap.keySet()) {
			if(w.getWeekId()==week.getWeekId()) {
				
				userPointsMap.put(w, pointsPerWeek);
				break;
			}
		}
		
		
		u.setPointsPerWeek(userPointsMap);

		System.out.println(u.getPointsPerWeek());
		
		userDao.saveUser(u);
		userDao.registerUserTeam(uTeam);
		
		Set<Player> goalKeeper=new HashSet<Player>();
		Set<Player> defenders=new HashSet<Player>();
		Set<Player> midfielders=new HashSet<Player>();
		Set<Player> attackers=new HashSet<Player>();
		for(Player p: uTeam.getUserteam()) {
			if(p.getPosition().equals("GK")) {
				goalKeeper.add(p);
			}else if(p.getPosition().equals("DEF")) {
				defenders.add(p);
			}else if(p.getPosition().equals("MID")) {
				midfielders.add(p);
			}else if(p.getPosition().equals("ATT")) {
				attackers.add(p);
			}
		}
		model.addAttribute("userTeam", uTeam);
		model.addAttribute("goalKeeper", goalKeeper);
		model.addAttribute("midfielders", midfielders);
		model.addAttribute("defenders", defenders);
		model.addAttribute("attackers", attackers);
		model.addAttribute("id",u.getUserId());
		return "userhomepage";
	}
	
}
