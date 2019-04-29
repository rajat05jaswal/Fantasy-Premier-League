package com.fpl.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fpl.dao.PLTeamDao;
import com.fpl.exception.TeamException;
import com.fpl.pojo.PLTeam;

@Controller
public class TeamController {
	
	@Autowired
	PLTeamDao plTeamDao;
	
	@RequestMapping(value = "addTeams", method = RequestMethod.GET)
	public String populate(Model model) throws TeamException {
		
		return "AdminRegisterTeam";
	}
	
	@RequestMapping(value = "addTeams", method = RequestMethod.POST)
	public String registerTeam(@RequestParam("name") String teamName, PLTeam plTeam, Model model) throws TeamException {
		if(plTeamDao.checkTeam(teamName)!=null) {
			model.addAttribute("message","Team already Exists");
			return "AdminRegisterTeam";
		}
		plTeam.setName(teamName);
		plTeamDao.addTeam(plTeam);
		model.addAttribute("message","Team "+plTeam.getName()+" Added");
		return "AdminRegisterTeam";
	}
}
