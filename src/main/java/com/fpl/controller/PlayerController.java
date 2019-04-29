package com.fpl.controller;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fpl.dao.PLTeamDao;
import com.fpl.dao.PlayerDao;
import com.fpl.dao.UserDao;
import com.fpl.dao.WeekDao;
import com.fpl.exception.PlayerException;
import com.fpl.exception.TeamException;
import com.fpl.exception.UserException;
import com.fpl.exception.WeekException;
import com.fpl.pojo.PLTeam;
import com.fpl.pojo.Player;
import com.fpl.pojo.User;
import com.fpl.pojo.Week;

@Controller
public class PlayerController {
	
	@Autowired
	PLTeamDao plTeamDao;
	
	@Autowired
	PlayerDao playerDao;
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	WeekDao weekDao;
	
	@RequestMapping(value = "addPlayers", method = RequestMethod.POST)
	public String registerPlayer(@RequestParam("name") String name,
			@RequestParam("price") String price,
			@RequestParam("position") String position,
			@RequestParam("team") String team,Player player, Model model) throws TeamException, PlayerException, WeekException {
		player.setName(name);
		player.setPosition(position);
		player.setPrice(Double.parseDouble(price));
		PLTeam pl=plTeamDao.checkTeam(team);
		player.setPlTeam(pl);
		Player p=playerDao.registerPlayer(player);
		if(p.getPoints().size()==0) {
			Map<Week, Integer> points=new HashMap();
			for(Week w: weekDao.retrieveWeeks()) {
				points.put(w, 0);
			}
			p.setPoints(points);
			playerDao.registerPlayer(p);
		}
		model.addAttribute("message", "Player added succesfully -"+player.getName());
		List<PLTeam> teams=plTeamDao.getTeams();
		model.addAttribute("teams", teams);
		return "AdminAddPlayers";
	}
	
	@RequestMapping(value = "addPlayers", method = RequestMethod.GET)
	public String populateTeams(Model model) throws TeamException {
		List<PLTeam> teams=plTeamDao.getTeams();
		model.addAttribute("teams", teams);
		return "AdminAddPlayers";
	}
	
	@RequestMapping(value = "/getPlayersByPosition/{pos}", method = RequestMethod.GET)
	public @ResponseBody String populatePlayersByPosition(@PathVariable String pos) throws TeamException, PlayerException, JsonProcessingException {
		
		ObjectMapper mapper=new ObjectMapper();
		List<Player> players=playerDao.getPlayerByPosition(pos);
		for(Player x: players) {
			System.out.println(x.getName());
		}
		String jsonString=mapper.writeValueAsString(players);
		return jsonString;
	}
	
	@RequestMapping(value = "/getPlayersByTeam/{team}", method = RequestMethod.GET)
	public @ResponseBody String populatePlayersByTeam(@PathVariable String team) throws TeamException, PlayerException, JsonProcessingException {
		
		ObjectMapper mapper=new ObjectMapper();
		PLTeam plTeam=plTeamDao.checkTeam(team);
		
		Set<Player> players=plTeam.getListOfPlayers();
		String jsonString=mapper.writeValueAsString(players);
		return jsonString;
	}
	
	@RequestMapping(value = "/getUserTeam/{userId}", method = RequestMethod.GET)
	public @ResponseBody String populateTeamByuser(@PathVariable String userId) throws  JsonProcessingException, UserException {
		
		ObjectMapper mapper=new ObjectMapper();
		User u=userDao.getUserById(Integer.parseInt(userId));
		Set<Player> players=new HashSet();
		if(u.getUserTeam()!=null) {
			players=u.getUserTeam().getUserteam();
		}
		String jsonString=mapper.writeValueAsString(players);
		return jsonString;
	}
	
	@RequestMapping(value = "/getPlayerById/{id}", method = RequestMethod.GET)
	public @ResponseBody String populatePlayerById(@PathVariable String id) throws TeamException, PlayerException, JsonProcessingException {
		
		ObjectMapper mapper=new ObjectMapper();
		Player player=playerDao.getPlayerByID(Integer.parseInt(id));
		String jsonString=mapper.writeValueAsString(player);
		return jsonString;
	}
	
}
