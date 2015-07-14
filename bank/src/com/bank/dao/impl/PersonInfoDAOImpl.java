package com.bank.dao.impl;

import java.util.List;

import org.hibernate.Criteria;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;

import com.bank.dao.PersonInfoDAO;
import com.bank.entity.Personinfo;
import com.bank.entity.Status;
/**
 * 
 * 构建不依赖与Spring的Hibernate代码
 *
 */
@Repository
public class PersonInfoDAOImpl  implements PersonInfoDAO {
	
	private SessionFactory sessionFactory;
	@Autowired
	public PersonInfoDAOImpl(SessionFactory sessionFactory){
		this.sessionFactory=sessionFactory;
	}
	
	private Session currentSession(){
		return sessionFactory.getCurrentSession();
	}
	public void	modifyPersoninfo(Personinfo personinfo){
		currentSession().update(personinfo);
	}

	public List getAllPersoninfo() {
		String hql="from Personinfo ";		
		return currentSession().createQuery(hql).list();
	}

	public boolean add(Personinfo personinfo) {
		// TODO Auto-generated method stub
		currentSession().save(personinfo);
		return true;
	}

	public List searchPersoninfo(final Personinfo personinfo) {					
		// TODO Auto-generated method stub
		Criteria c=currentSession().createCriteria(Personinfo.class);
		if(personinfo.getRealname()!=null&&!"".equals(personinfo.getRealname())){
			if(personinfo.getCardid()!=null){
				c.add(Restrictions.or(Restrictions.eq("realname", personinfo.getRealname()),Restrictions.eq("cardid", personinfo.getCardid())));
			}else{
				c.add(Restrictions.like("realname", personinfo.getRealname(),MatchMode.ANYWHERE));

			}
		}
		c.addOrder(Order.asc("id"));

		return c.list();
	
	
	}

	public List searchPersoninfo(Status status) {
		// TODO Auto-generated method stub
		String hql="from Personinfo p where p.account.status.id="+status.getId();
		//System.out.print(hql);		
		return currentSession().createQuery(hql).list();
	}
	
}
