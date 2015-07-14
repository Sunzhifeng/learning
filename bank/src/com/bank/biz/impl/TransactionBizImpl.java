package com.bank.biz.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bank.biz.TransactionBiz;
import com.bank.dao.*;
import com.bank.entity.*;
@Transactional
@Service
public class TransactionBizImpl  implements TransactionBiz{

	@Resource 
	private TransactionDAO transactionDao; 	
	@Resource 
	private UserDAO userDao;	
	public boolean deposit(TransactionLog log){//存款
		Account self=log.getAccount();	
		self.setBalance(log.getAccount().getBalance()+(log.getTrMoney()));
		userDao.updateAccount(self);
		TransactionType type=transactionDao.getTransactionType("存款");
		log.setTransactionType(type);
		log.setOtherid(self.getAccountid());
		return transactionDao.addLog(log);
	}
	
	public boolean withdrawal(TransactionLog log) {
		// TODO Auto-generated method stub
		Account self=log.getAccount();
		self.setBalance(log.getAccount().getBalance()-(log.getTrMoney()));
		userDao.updateAccount(self);
		TransactionType type=transactionDao.getTransactionType("取款");
		log.setTransactionType(type);
		log.setOtherid(self.getAccountid());
		return transactionDao.addLog(log);
	}
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
	public boolean transfer(TransactionLog log) {
		// TODO Auto-generated method stub
		Account other=userDao.getAccount(log.getOtherid());
		Account self=log.getAccount();
		if(other!=null){
			self.setBalance(log.getAccount().getBalance()-(log.getTrMoney()));
			other.setBalance(other.getBalance()+(log.getTrMoney()));
			userDao.updateAccount(self);
			userDao.updateAccount(other);
			TransactionType type=transactionDao.getTransactionType("转账");
			log.setTransactionType(type);
			return transactionDao.addLog(log);
		}
		return false;
	}
	public List getLogs( Account account, int page){
		return transactionDao.getLogs(account, page);
	}
	public Pager getPagerOfLogs(Account account){
		int count=transactionDao.getCountOfLogs(account);
		Pager pager=new Pager();
		pager.setPerPageRows(10);
		pager.setRowCount(count);
		return pager;
	}
}
