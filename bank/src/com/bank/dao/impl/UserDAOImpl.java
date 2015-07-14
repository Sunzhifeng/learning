package com.bank.dao.impl;

import java.util.List;
import com.bank.dao.UserDAO;
import com.bank.entity.Account;
import com.bank.entity.Admin;
import com.bank.entity.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.springframework.stereotype.Repository;
@Repository
public class UserDAOImpl implements UserDAO {
	
   private SessionFactory sessionFactory;
   @Autowired
	public UserDAOImpl(SessionFactory sessionFactory){
		this.sessionFactory=sessionFactory;
	}
	
	private Session currentSession(){
		return sessionFactory.getCurrentSession();
	}
	public  Account getAccount(int accountid){
		return (Account)currentSession().get(Account.class, accountid);
		
	
	}
	public Account getAccount(String username){
		String hql="from Account as a where a.username=:username";
	//	Session session=this.getSessionFactory().getCurrentSession();
	//	Query query=session.createQuery(hql);
	//	query.setString("username",username);		
		return (Account)currentSession().createQuery(hql).setString("username",username).uniqueResult();
	}	
	public List getAllAcconts(){
		String hql="from Account ";
		//Session session = this.getSessionFactory().getCurrentSession();
		//Query query = session.createQuery(hql);
		return currentSession().createQuery(hql).list();
	}
	public Account searchAccounts(final Account account){
		return account;
		
	}

	public boolean updateAccount(Account account){
		currentSession().update(account);
		return true;
	}		
	public boolean addAccount(Account account){
		currentSession().save(account);
		return true;
	}	
	public boolean delAccount(Account account){
		currentSession().delete(account);
		return true;
	}
	//从session中重新获取对象account
	public void reflush(Account account){
		currentSession().refresh(account);
	}
	public Status getStatus(String name){
		String hql="from Status as s where s.name=:name";
		//Session session = this.getSessionFactory().getCurrentSession();
		//Query query = session.createQuery(hql);
		//query.setString("name", name);
		return (Status) currentSession().createQuery(hql).setString("name", name).uniqueResult();
	
	}	
	public Status getStatus(int id){//通过主键Id获得状态
		return (Status)currentSession().get(Status.class, id);
	}	
	
	public Admin getAdmin(String username){
		String hql="from Admin as a where a.username=:username";		
		Query query=currentSession().createQuery(hql);
		query.setString("username",username);
		Admin a=(Admin)query.uniqueResult();
		//System.out.println("UserDaoImpl:getAdmin:username+passwd"+a.getUsername()+a.getPassword());
		return a;
		
	}	
	public boolean modifyAdmin(Admin admin){
		currentSession().update(admin);
		return true;
	}

}
