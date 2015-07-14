package com.bank.biz;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Isolation;
import com.bank.entity.*;

@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
public interface UserBiz {
	/**
	 * ����˻�
	 * @param account
	 * @return
	 */
	public boolean addAccount(Account account);
	
	/**
	 * ɾ���˻�
	 */
	public boolean delAccount(int id);

	/**
	 * �޸��˻�
	 * @param account
	 * @return
	 */
	public abstract boolean modifyAccount(Account account);
	
	/**
	 * ��ѯ�˻�
	 */
	public Account searchAccounts(final Account account);
	
	/**
	 * �����˻�id��ȡ�˻�����
	 * @param accountid
	 * @return
	 */
	@Transactional(readOnly = true)
	public abstract Account getAccount(int accountid);
	
	//�����˻����ƻ�ȡ�˻�
	public abstract Account getAccount(String username);
	
	public List getAllAccounts();
	/**
	 * �����˻�
	 */
	public void enabled(int id);
	/**
	 * �����˻�
	 */
	public void locking(int id);
	
	//�������ƻ�ȡ�˻�״̬����
	public Status getStatus(String name);
	
	public Status getStatus(int id);
	

	//�޸Ĺ���Ա
	public boolean modifyAdmin(Admin admin);
	
	//����username��ȡ����Ա
	public abstract Admin getAdmin(String username);

	public abstract void reflush(Account account);

}

