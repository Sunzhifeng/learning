package com.bank.dao;
import java.util.List;

import com.bank.entity.*;
public interface TransactionDAO {
	
	public boolean addLog(TransactionLog log);
	

	//获取交易记录
	public List getLogs( Account account,int page);
	
	//根据交易类型名称获取交易类型对象
	public TransactionType getTransactionType(String name);
	
	//获取交易记录数
	public Integer getCountOfLogs(Account account);

}
