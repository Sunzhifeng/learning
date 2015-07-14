package com.bank.action;
import java.util.Map;
import java.util.regex.Pattern;
import javax.annotation.Resource;
import org.apache.struts2.interceptor.*;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;
import com.bank.biz.*;
import com.bank.entity.*;
//@Component
public class PersoninfoAction  extends ActionSupport implements RequestAware , SessionAware{

	@Resource 
	private PersoninfoBiz personinfoBiz;
	Map<String, Object> request;
	Map<String, Object> session;
	private Personinfo personinfo;
	public String modify(){
		Personinfo per=(Personinfo) session.get("personinfo");
		per.setAddress(personinfo.getAddress());
		per.setAge(personinfo.getAge());
		per.setCardid(personinfo.getCardid());
		per.setRealname(personinfo.getRealname());
		per.setSex(personinfo.getSex());
		per.setTelephone(personinfo.getTelephone());
		if(personinfoBiz.modifyPersoninfo(per)){
			session.put("personinfo",per);
			request.put("message", "�޸ĳɹ�");
			return "message";
		}
		request.put("message", "�޸�ʧ��");
		return "message";
	}
	public void validateModify(){
		if("".equals(personinfo.getTelephone().trim())){
			personinfo.setTelephone("�绰����");
		}
		if(!(personinfo.getAge()>18&&personinfo.getAge()<100)){
			addFieldError("personinfo.age", "���䲻��");
		}
		if(!Pattern.compile("^\\d{17}(\\d|x)$").matcher(personinfo.getCardid().toString()).matches()){
			addFieldError("personinfo.cardId", "���֤��ʽ����ȷ");
		}
		if(!"�绰����".equals(personinfo.getTelephone().trim())&&!Pattern.compile("^(?:1[358]\\d{9}|\\d{3,4}-\\d{8,9})$").matcher(personinfo.getTelephone()).matches()){
			addFieldError("personinfo.telephone", "�绰��ʽ����ȷ");
		}
	}
	
	public Personinfo getPersoninfo() {
		return personinfo;
	}
	public void setPersoninfo(Personinfo personinfo) {
		this.personinfo = personinfo;
	}
	public void setSession(Map<String, Object> session) {
		// TODO Auto-generated method stub
		this.session=session;
		
	}

	public void setRequest(Map<String, Object> request) {
		// TODO Auto-generated method stub
		this.request=request;
	}
	

}
