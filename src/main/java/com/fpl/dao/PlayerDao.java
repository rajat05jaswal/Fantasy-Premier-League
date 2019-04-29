package com.fpl.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import com.fpl.exception.PlayerException;
import com.fpl.exception.TeamException;
import com.fpl.pojo.Player;

public class PlayerDao extends DAO{
	public List<Player> getAllPlayers() throws PlayerException{
		try {
			begin();
			Query q=getSession().createQuery("from Player");
			List<Player> players=q.list();
			commit();
			return players;
		} catch (HibernateException e) {
			// TODO: handle exception
			rollback();
			throw new PlayerException("Error to find Players : ", e);
		}finally {
			close();
		}
	}
	
	public List<Player> getPlayerByPosition(String pos) throws PlayerException{
		try {
			begin();
			Query q= getSession().createQuery("from Player where position =:pos");
			q.setString("pos", pos);
			List<Player> players=q.list();
			commit();
			return players;
		} catch (HibernateException e) {
			// TODO: handle exception
			rollback();
			throw new PlayerException("Error to find Players : ", e);
		}finally {
			close();
		}
	}
	
	public Player getPlayerByID(int id) throws PlayerException{
		try {
			begin();
			Query q= getSession().createQuery("from Player where id =:id");
			q.setInteger("id", id);
			Player player=(Player) q.uniqueResult();
			commit();
			return player;
		} catch (HibernateException e) {
			// TODO: handle exception
			rollback();
			throw new PlayerException("Error to find Players : ", e);
		}finally {
			close();
		}
	}
	
	public Player registerPlayer(Player player) throws PlayerException{
		try {
			begin();
			getSession().saveOrUpdate(player);
			commit();
			return player;
		} catch (HibernateException e) {
			// TODO: handle exception
			rollback();
			throw new PlayerException("Error to find Players : ", e);
		}finally {
			close();
		}
	}
}
