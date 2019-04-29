 package com.fpl.controller;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import com.fpl.dao.PLTeamDao;
import com.fpl.dao.UserDao;
import com.fpl.dao.WeekDao;
import com.fpl.exception.TeamException;
import com.fpl.exception.UserException;
import com.fpl.exception.WeekException;
import com.fpl.pojo.PLTeam;
import com.fpl.pojo.Player;
import com.fpl.pojo.User;
import com.fpl.pojo.Week;
import com.fpl.validator.UserValidator;

@Controller
public class LoginController {
	
	@Autowired
	UserDao userDao;
	
	@Autowired 
	UserValidator userValidator;
	
	@Autowired
	PLTeamDao plTeamDao;
	
	@Autowired
	WeekDao weekDao;
	
//	@InitBinder
//	protected void initBinder(WebDataBinder binder) {
//		binder.setValidator(userValidator);
//	}
	
	@RequestMapping(value = "/signIn",method=RequestMethod.GET)
	public String loadForm(ModelMap model, User user) {
		return "signIn";
	}
	
	@RequestMapping(value = "/signIn",method=RequestMethod.POST)
	public String handleLogin(Model model, @ModelAttribute("user") User user, HttpSession session) throws UserException, TeamException {
		String username=user.getUsername();
		String password=user.getPassword();
		session.setAttribute("username", username);
		String weekNo=(String) session.getAttribute("week");
		model.addAttribute("week",weekNo);
		if(username.equals("admin") && password.equals("admin")) {
			List<PLTeam> teams=plTeamDao.getTeams();
			model.addAttribute("teams", teams);
			return "adminView";
		}else {
			User u=userDao.checkUser(username, password);
			if(u==null) {
				return "signIn";
			}
			model.addAttribute("id", u.getUserId());
			model.addAttribute("username",username);
			model.addAttribute("userTeam",u.getUserTeam());
			if(u.getUserTeam()==null) return "userhomepage";
			Set<Player> goalKeepers=new HashSet<Player>();
			Set<Player> defenders=new HashSet<Player>();
			Set<Player> midfielders=new HashSet<Player>();
			Set<Player> attackers=new HashSet<Player>();
			for(Player p: u.getUserTeam().getUserteam()) {
				if(p.getPosition().equals("GK")) {
					goalKeepers.add(p);
				}else if(p.getPosition().equals("DEF")) {
					defenders.add(p);
				}else if(p.getPosition().equals("MID")) {
					midfielders.add(p);
				}else if(p.getPosition().equals("ATT")) {
					attackers.add(p);
				}
			}
			model.addAttribute("goalKeeper", goalKeepers);
			model.addAttribute("midfielders", midfielders);
			model.addAttribute("defenders", defenders);
			model.addAttribute("attackers", attackers);
			System.out.println(u.getUserTeam());
			return "userhomepage";
		}
	}
	

	@RequestMapping(value = "/signUp",method=RequestMethod.GET)
	public String signUpForm(ModelMap model, User user) {
		return "signUp";
	}
	
	@RequestMapping(value = "/signUp",method=RequestMethod.POST)
	public String signUpFormSubmit(@Validated @ModelAttribute("user") User user, BindingResult bindingResult) throws UserException, WeekException {
		if(bindingResult.hasErrors()) {
			return "signUp";
		}else {
			Map<Week, Integer> points=new HashMap();
			for(Week w: weekDao.retrieveWeeks()) {
				points.put(w, 0);
			}
			user.setPointsPerWeek(points);
			User u=userDao.saveUser(user);
			return "signIn";
		}
	}
	@RequestMapping(value = "/logout",method=RequestMethod.GET)
	public String logout(HttpSession session,HttpServletResponse response){
		session.invalidate();
		RequestMappingHandlerAdapter rmha = new RequestMappingHandlerAdapter();
		rmha.setCacheSeconds(0);
		return "signIn";
	}
}

