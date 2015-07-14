package com.bank.biz;
import java.util.List;

import com.bank.entity.*;
public interface TransactionBiz {
	public boolean deposit(TransactionLog log);
	public boolean withdrawal(TransactionLog log);
	public boolean transfer(TransactionLog log);
	public List getLogs( Account account, int page);
	public Pager getPagerOfLogs(Account account);
}
