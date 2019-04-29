package com.fpl.dao;


import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import com.fpl.exception.PlayerException;
import com.fpl.exception.TeamException;
import com.fpl.exception.UserException;
import com.fpl.pojo.PLTeam;
import com.fpl.pojo.User;


public class PLTeamDao extends DAO{
	public PLTeamDao() {
		
	}
	public PLTeam checkTeam(String name) throws TeamException{
		try {
			begin();
			Criteria c=getSession().createCriteria(PLTeam.class);
			c.add(Restrictions.eq("name", name));
			commit();
			return (PLTeam) c.uniqueResult();
		}catch(HibernateException he){
			rollback();
			throw new TeamException("Error to find Players : ", he);
		}finally {
			close();
		}
	}
	public PLTeam addTeam(PLTeam t) throws TeamException {
		try {
			begin();
			getSession().save(t);
			commit();
			return t;
		}catch (HibernateException e) {
			// TODO: handle exception
			rollback();
			throw new TeamException("Could not save Team : "+ t.getName(), e);
		}finally {
			close();
		}
	}
	
	public List<PLTeam> getTeams() throws TeamException {
		try {
			begin();
			Query q=getSession().createQuery("from PLTeam");
			List<PLTeam> l=q.list();
			commit();
			return l;
		}catch (HibernateException e) {
			// TODO: handle exception
			rollback();
			throw new TeamException("Could not find Teams : ", e);
		}finally {
			close();
		}
	}
}
