package com.bank.dao;
import java.util.List;

import com.bank.entity.*;
public interface PersonInfoDAO {
	public void	modifyPersoninfo(Personinfo personinfo);

	
	public List getAllPersoninfo();//获取全部用户信息
	
	public List searchPersoninfo(Status status);//根据账户状态获取用户信息
	
	public boolean add(Personinfo personinfo);//添加个人信息

	
	public List searchPersoninfo(Personinfo personinfo);//根据条件查询个人信息
	
	
	
	
}
