package com.fpl.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fpl.dao.PLTeamDao;
import com.fpl.dao.PlayerDao;
import com.fpl.dao.WeekDao;
import com.fpl.exception.PlayerException;
import com.fpl.exception.TeamException;
import com.fpl.exception.WeekException;
import com.fpl.pojo.PLTeam;
import com.fpl.pojo.Player;
import com.fpl.pojo.Week;
import com.fpl.utils.ConfigTeamsAndPlayers;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	WeekDao weekDao;
	
	@Autowired
	PlayerDao playerDao; 
	
	@Autowired
	PLTeamDao plTeamDao;
	/**
	 * Simply selects the home view to render by returning its name.
	 * @throws WeekException 
	 * @throws PlayerException 
	 * @throws TeamException 
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model, HttpServletRequest request) throws WeekException, PlayerException, TeamException {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		if(weekDao.retrieveWeeks().size()==0) {
			for(int i=1;i<=5;i++) {
				Week w=new Week();
				w.setWeekNo("WEEK"+i);
				weekDao.registerWeek(w);
			}
		}		
		
		Week w=weekDao.currentWeek();
		
		if(plTeamDao.getTeams().size()==0) {

			ConfigTeamsAndPlayers ctp=new ConfigTeamsAndPlayers();
			Map<String, Set<Player>> map=ctp.readAndSaveTeamsAndPlayers("classpath:epldata.csv", plTeamDao, playerDao);
			for(String x: map.keySet()) {
				PLTeam plTeam=new PLTeam(x);
				plTeam.setListOfPlayers(map.get(x));
				for(Player p: map.get(x)) {
					Map<Week, Integer> points=new HashMap();
					for(Week week: weekDao.retrieveWeeks()) {
						points.put(week, 0);
					}
					p.setPoints(points);
					p.setPlTeam(plTeam);
				}
				plTeamDao.addTeam(plTeam);
			}
		}
		HttpSession session=request.getSession();
		session.setAttribute("week", w.getWeekNo());
		
		
		return "signIn";
	}
	
}
