package com.bank.dao;
import java.util.List;

import com.bank.entity.*;
public interface PersonInfoDAO {
	public void	modifyPersoninfo(Personinfo personinfo);

	
	public List getAllPersoninfo();//��ȡȫ���û���Ϣ
	
	public List searchPersoninfo(Status status);//�����˻�״̬��ȡ�û���Ϣ
	
	public boolean add(Personinfo personinfo);//��Ӹ�����Ϣ

	
	public List searchPersoninfo(Personinfo personinfo);//����������ѯ������Ϣ
	
	
	
	
}
