package com.bank.dao;
import java.util.List;

import com.bank.entity.*;
public interface TransactionDAO {
	
	public boolean addLog(TransactionLog log);
	

	//��ȡ���׼�¼
	public List getLogs( Account account,int page);
	
	//���ݽ����������ƻ�ȡ�������Ͷ���
	public TransactionType getTransactionType(String name);
	
	//��ȡ���׼�¼��
	public Integer getCountOfLogs(Account account);

}
