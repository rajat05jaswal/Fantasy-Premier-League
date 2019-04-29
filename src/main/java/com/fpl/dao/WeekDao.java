package com.fpl.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import com.fpl.exception.TeamException;
import com.fpl.exception.WeekException;
import com.fpl.pojo.Week;

public class WeekDao extends DAO{
	public Week registerWeek(Week w) throws WeekException {
		try {
			begin();
			getSession().saveOrUpdate(w);
			commit();
			return w;
		}catch (HibernateException e) {
			// TODO: handle exception
			rollback();
			throw new WeekException("Could not save Week : "+ w.getWeekNo(), e);
		}finally {
			close();
		}
	}
	public Week getWeek(String weekNo) throws WeekException {
		try {
			begin();

			Criteria c=getSession().createCriteria(Week.class);
			c.add(Restrictions.eq("weekNo", weekNo));
			Week w=(Week) c.uniqueResult();
			commit();
			return w;
		}catch (HibernateException e) {
			// TODO: handle exception
			rollback();
			throw new WeekException("Could not get Week : "+ weekNo, e);
		}finally {
			close();
		}
	}
	
	public List<Week> retrieveWeeks() throws WeekException {
		List<Week> list=new ArrayList<Week>();
		try {
			begin();
			Query q=getSession().createQuery("from Week");
			list=q.list();
			commit();
			return list;
		}catch (HibernateException e) {
			// TODO: handle exception
			rollback();
			throw new WeekException("Could not get Weeks : ", e);
		}finally {
			close();
		}
	}
	
	public Week currentWeekHelper() throws WeekException {
		try {
			begin();
			Criteria c=getSession().createCriteria(Week.class);
			c.add(Restrictions.eq("scoresGenerated",false));
			c.setMaxResults(1);
			c.setFirstResult(0);
			return (Week) c.uniqueResult();
		} catch (HibernateException e) {
			// TODO: handle exception
			rollback();
			throw new WeekException("Could not get Current Week :", e);
		} finally {
			close();
		}
	}
	public Week currentWeek() throws WeekException{
		return getWeek(currentWeekHelper().getWeekNo());		
	}
	
}
