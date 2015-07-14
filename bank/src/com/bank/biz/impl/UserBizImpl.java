package com.bank.biz.impl;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Isolation;
import com.bank.biz.UserBiz;
import com.bank.dao.UserDAO;
import com.bank.entity.Account;
import com.bank.entity.Admin;
import com.bank.entity.Status;
@Transactional
@Service
public class UserBizImpl implements UserBiz{
	@Resource
	private UserDAO userDao;
	
	
	public boolean addAccount(Account account) {
	 Status status=userDao.getStatus("∆Ù”√");
	 account.setStatus(status);
	 return userDao.addAccount(account);
	}

	public boolean delAccount(int id) {
		// TODO Auto-generated method stub
		Account account =userDao.getAccount(id);
		
		return userDao.delAccount(account);
	}
	
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
	public boolean modifyAccount(Account account) {
		// TODO Auto-generated method stub
		return userDao.updateAccount(account);
	}

	public Account searchAccounts(Account account) {
		// TODO Auto-generated method stub
		return null;
	}

	public Account getAccount(int accountid) {
		// TODO Auto-generated method stub
		return userDao.getAccount(accountid);
	}

	public Account getAccount(String username) {
		// TODO Auto-generated method stub
		return userDao.getAccount(username);
	}

	public List getAllAccounts() {
		// TODO Auto-generated method stub
		return null;
	}

	public void enabled(int id) {
		// TODO Auto-generated method stub
		Account account=userDao.getAccount(id);
		Status status=userDao.getStatus("∆Ù”√");
		account.setStatus(status);
		userDao.updateAccount(account);
	}

	public void locking(int id) {
		// TODO Auto-generated method stub
		Account account=userDao.getAccount(id);
		Status status=userDao.getStatus("∂≥Ω·");
		account.setStatus(status);
		userDao.updateAccount(account);
	}

	public Status getStatus(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	public Status getStatus(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean modifyAdmin(Admin admin) {
		return userDao.modifyAdmin(admin);
		
	}

	public Admin getAdmin(String username) {
		// TODO Auto-generated method stub
		return userDao.getAdmin(username);
	}

	public void reflush(Account account) {
		userDao.reflush(account);
		
	}

}
