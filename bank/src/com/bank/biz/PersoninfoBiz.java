package com.bank.biz;
import com.bank.entity.*;
import java.util.*;
public interface PersoninfoBiz {
	public boolean modifyPersoninfo(Personinfo personinfo);
	public List searchPersoninfo(Status status);//����״̬
	public List searchPersoninfo(Personinfo personinfo);//����������ѯ
	public boolean add(Personinfo personinfo);//��Ӹ�����Ϣ
}
