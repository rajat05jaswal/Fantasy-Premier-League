package com.fpl.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.util.ResourceUtils;

import com.fpl.dao.PLTeamDao;
import com.fpl.dao.PlayerDao;
import com.fpl.pojo.PLTeam;
import com.fpl.pojo.Player;

public class ConfigTeamsAndPlayers {
	public Map<String, Set<Player>> readAndSaveTeamsAndPlayers(String fileName, PLTeamDao teamDao, PlayerDao playerDao){
		String csvSplitBy=",";
		BufferedReader br;
		try
		{
			File file = ResourceUtils.getFile(fileName);
			br = new BufferedReader(new FileReader(file));
			Map<String, Set<Player>> map=new HashMap();
			while(br.readLine()!=null) {
				
				String[] player = br.readLine().split(csvSplitBy);
				String playerName=player[0];
				String teamName=player[1];
				String positionCat=player[3];
				String price=player[4];
				String position="";
				if(positionCat.equals("1")) {
					position="ATT";
				}else if(positionCat.equals("2")) {
					position="MID";
				}else if(positionCat.equals("3")) {
					position="DEF";
				}else if(positionCat.equals("4")) {
					position="GK";
				}

				Player p=new Player(playerName, Double.parseDouble(price), position);
				if(map.containsKey(teamName)) {
					Set<Player> players=map.get(teamName);
					players.add(p);
					map.put(teamName, players);
				}else {
					Set<Player> players=new HashSet<Player>();
					players.add(p);
					map.put(teamName, players);
				}
			}
			br.close();
			return map;
		}catch(IOException ex) {
			ex.printStackTrace();
			return null;
		}
	}
}
