package com.bank.dao;
import com.bank.entity.*;
import java.util.*;
public interface  UserDAO {
	
	public Account getAccount(int accountid);//根据账户id获取账户对象		
	public Account getAccount(String username);//根据客户名获取客户对象	
	public List getAllAcconts();	
	public Account searchAccounts(final Account account);

	public boolean updateAccount(Account account);	//修改账户		
	public boolean addAccount(Account account);//添加账户	
	public boolean delAccount(Account account);//删除账户
	public void reflush(Account account);	
	public Status getStatus(String name);//根据账户状态名称获取账户状态对象	
	public Status getStatus(int id);	
	
	public Admin getAdmin(String username);//根据username获取管理员	
	public boolean modifyAdmin(Admin admin);

}
