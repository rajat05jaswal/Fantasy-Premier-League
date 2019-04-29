package com.fpl.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.fpl.exception.UserException;
import com.fpl.pojo.User;
import com.fpl.pojo.UserTeam;

public class UserDao extends DAO{
	public UserDao() {
		
	}
	public User checkUser(String username, String password) throws UserException {
		try {
			begin();
			System.out.println(getSession().toString());
			Query q= getSession().createQuery("from User where username = :username and password =:password"); 
			q.setString("username", username);
			q.setString("password", password);
			User u=(User) q.uniqueResult();
			commit();
			return u;
		}catch(HibernateException he) {
			rollback();
			throw new UserException("Could not get user : "+ username, he);
		}finally {
			close();
		}
	}
	
	public User saveUser(User u) throws UserException {
		try {
			begin();
			getSession().saveOrUpdate(u);
			commit();
			return u;
		}catch (HibernateException e) {
			// TODO: handle exception
			rollback();
			throw new UserException("Could not save user : "+ u.getUsername(), e);
		}finally {
			close();
		}
	}
	
	public User getUserById(int id) throws UserException {
		try {
			begin();
			System.out.println(getSession().toString());
			Query q= getSession().createQuery("from User where id = :id"); 
			q.setInteger("id", id);
			User u=(User) q.uniqueResult();
			commit();
			return u;
		}catch(HibernateException he) {
			rollback();
			throw new UserException("Could not get user : "+ id, he);
		}finally {
			close();
		}
	}
	
	public User getUserByUsername(String username) throws UserException {
		try {
			begin();
			System.out.println(getSession().toString());
			Query q= getSession().createQuery("from User where username = :username"); 
			q.setString("username", username);
			User u=(User) q.uniqueResult();
			commit();
			return u;
		}catch(HibernateException he) {
			rollback();
			throw new UserException("Could not get user : "+ username, he);
		}finally {
			close();
		}
	}
	
	public UserTeam registerUserTeam(UserTeam uTeam) throws UserException {
		try {
			begin();
			getSession().saveOrUpdate(uTeam);
			commit();
			return uTeam;
		}catch (HibernateException e) {
			// TODO: handle exception
			rollback();
			throw new UserException("Could not save user : ", e);
		}finally {
			close();
		}
	}
	
	public List<User> getUsers() throws UserException{
		try {
			begin();
			Query q=getSession().createQuery("from User"); 
			commit();
			return q.list();
		}catch (HibernateException e) {
			// TODO: handle exception
			rollback();
			throw new UserException("Could not save user : ", e);
		}finally {
			close();
		}
	}
	
	public int deleteUserTeam(User u) throws UserException {
		try {
			begin();
			Query q=getSession().createQuery("delete from UserTeam where userId = :userId");
			q.setInteger("userId", u.getUserId());
			int rows= q.executeUpdate();
			commit();
			return rows;
		} catch (HibernateException he) {
			// TODO: handle exception
			rollback();
			throw new UserException("Could not save user : ", he);
		}finally {
			close();
		}
	}
}
