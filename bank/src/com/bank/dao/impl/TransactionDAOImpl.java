package com.bank.dao.impl;
import java.sql.SQLException;

import java.util.List;

import org.hibernate.Criteria;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.bank.dao.TransactionDAO;
import com.bank.entity.*;
@Repository
public class TransactionDAOImpl  implements TransactionDAO {
	
private SessionFactory sessionFactory;
	@Autowired
	public TransactionDAOImpl(SessionFactory sessionFactory){
		this.sessionFactory=sessionFactory;
	}
	
	private Session currentSession(){
		return sessionFactory.getCurrentSession();
	}
	public TransactionType getTransactionType(String name){
		String hql="from TransactionType t where t.name=:name";
		//Query query=currentSession().createQuery(hql);
		//query.setString("name", name);
		return (TransactionType)currentSession().createQuery(hql).setString("name", name).uniqueResult();
	}
	public boolean addLog(TransactionLog log){
		currentSession().save(log);
		return true;

	}
	public List getLogs(final Account account,final int page) {
		// TODO Auto-generated method stub
				Criteria c=currentSession().createCriteria(TransactionLog.class);
				c.add(Restrictions.or(Restrictions.eq("account", account),Restrictions.eq("otherid", account.getAccountid())));
				c.addOrder(Order.desc("id"));
				c.setFirstResult(10*(page-1));
				c.setMaxResults(10);
				return c.list();

			
	
	}
	public Integer getCountOfLogs(Account account) {
		// TODO Auto-generated method stub
		String sql="select count(*) from Transaction_Log where (accountid="+account.getAccountid()+" or otherid="+account.getAccountid()+")";
		
		Integer count= Integer.parseInt(currentSession().createSQLQuery(sql).uniqueResult().toString()) ;
		
		return count;


	}


}
