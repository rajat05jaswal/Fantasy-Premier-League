package com.fpl.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import com.fpl.dao.PlayerDao;
import com.fpl.dao.WeekDao;
import com.fpl.exception.PlayerException;
import com.fpl.exception.WeekException;
import com.fpl.pojo.Player;
import com.fpl.pojo.Week;



@Controller
public class PassPdfController extends PDFView {
	
	@Autowired
	WeekDao weekDao;
	
	@Autowired
	PlayerDao playerDao;
	
	@RequestMapping(value="/downloadPdf", method=RequestMethod.GET)
	ModelAndView downloadPdf(ModelMap model, 
            HttpServletRequest request) throws WeekException, PlayerException {
		
		Week w=weekDao.currentWeek();
		List<Player> list=playerDao.getAllPlayers();
		model.addAttribute("week",w);
		model.addAttribute("playerList",list);
		View v = new PDFView();
		return new ModelAndView(v);
	}
}
