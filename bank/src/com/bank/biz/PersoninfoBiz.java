package com.bank.biz;
import com.bank.entity.*;
import java.util.*;
public interface PersoninfoBiz {
	public boolean modifyPersoninfo(Personinfo personinfo);
	public List searchPersoninfo(Status status);//根据状态
	public List searchPersoninfo(Personinfo personinfo);//根据条件查询
	public boolean add(Personinfo personinfo);//添加个人信息
}
