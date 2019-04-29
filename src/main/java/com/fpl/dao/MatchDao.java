package com.fpl.dao;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;

import com.fpl.exception.MatchException;
import com.fpl.exception.PlayerException;
import com.fpl.exception.TeamException;
import com.fpl.exception.WeekException;
import com.fpl.pojo.Match;
import com.fpl.pojo.PLTeam;
import com.fpl.pojo.Player;
import com.fpl.pojo.Week;

public class MatchDao extends DAO{
	
	
	public Match saveMatch(Match m) throws MatchException {
		try {
			begin();
			getSession().saveOrUpdate(m);
			commit();
			return m;
		} catch (HibernateException e) {
			// TODO: handle exception
			rollback();
			throw new MatchException("Error to save Match : ", e);
		}finally {
			close();
		}
	}
	
	public Match updateMatch(Match m) throws MatchException {
		try {
			begin();
			getSession().update(m);
			commit();
			return m;
		} catch (HibernateException e) {
			// TODO: handle exception
			rollback();
			throw new MatchException("Error to save Match : ", e);
		}finally {
			close();
		}
	}
	
	public Match getMatch(int matchId) throws MatchException {
		try {
			begin();
			System.out.println(matchId);
			Query q= getSession().createQuery("from Match_Table where id =:matchId");
			q.setInteger("matchId", matchId);
			Match m=(Match) q.uniqueResult();
			commit();
			return m;
		} catch (HibernateException e) {
			// TODO: handle exception
			rollback();
			throw new MatchException("Error to find match : ", e);
		}finally {
			close();
		}
	}
	
//	public List<Match> getMatchesPerWeek(Week w) throws MatchException{
//		try {
//			begin();
//		} catch (HibernateException he) {
//			// TODO: handle exception
//		}finally {
//			close();
//		}
//	}
	
}
