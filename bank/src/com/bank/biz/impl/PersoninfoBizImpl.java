package com.bank.biz.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bank.biz.PersoninfoBiz;
import com.bank.dao.*;
import com.bank.entity.Personinfo;
import com.bank.entity.Status;
@Transactional
@Service
public class PersoninfoBizImpl implements PersoninfoBiz{
	@Resource
	private PersonInfoDAO personinfoDao;		
	@Resource
	private UserDAO userDao;

	public boolean modifyPersoninfo(Personinfo personinfo) {
		// TODO Auto-generated method stub
		personinfoDao.modifyPersoninfo(personinfo);
		return true;
	}

	public List searchPersoninfo(Status status){//״̬0��ʾ��������û�
		List users=new ArrayList();
		if(status.getId()!=0){
			status=userDao.getStatus(status.getId());//��ϸ�о��о�
			users=personinfoDao.searchPersoninfo(status);

		}else{
			users=personinfoDao.getAllPersoninfo();			
		}
		return users;

	}
	public List searchPersoninfo(Personinfo personinfo){//����������ѯ
		return personinfoDao.searchPersoninfo(personinfo);
	}
	public boolean add(Personinfo personinfo) {
		// TODO Auto-generated method stub
		return personinfoDao.add(personinfo);
	}

}
