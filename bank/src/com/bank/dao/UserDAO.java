package com.bank.dao;
import com.bank.entity.*;
import java.util.*;
public interface  UserDAO {
	
	public Account getAccount(int accountid);//�����˻�id��ȡ�˻�����		
	public Account getAccount(String username);//���ݿͻ�����ȡ�ͻ�����	
	public List getAllAcconts();	
	public Account searchAccounts(final Account account);

	public boolean updateAccount(Account account);	//�޸��˻�		
	public boolean addAccount(Account account);//����˻�	
	public boolean delAccount(Account account);//ɾ���˻�
	public void reflush(Account account);	
	public Status getStatus(String name);//�����˻�״̬���ƻ�ȡ�˻�״̬����	
	public Status getStatus(int id);	
	
	public Admin getAdmin(String username);//����username��ȡ����Ա	
	public boolean modifyAdmin(Admin admin);

}
