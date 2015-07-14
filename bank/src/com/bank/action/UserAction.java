package com.bank.action;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.bank.entity.*;
import com.bank.biz.*;
//@ParentPackage("struts-default") 
//@Namespace("/user")  
//@Controller           
//@Scope("prototype") 
public class UserAction extends ActionSupport implements RequestAware ,SessionAware {
	//������Ȼ�õ���struts������������Ҫ�̳�ActionSupport����SpringMVC����@Colltroller����SpringMVCע��
	//����ʵ��ɨ�跽ʽע��UserAction�����Σ�����ʱ��Ҫ��XML����UserActionΪSpring��bean

	@Resource
	private UserBiz userBiz;//ע�ⷽʽʵ������ע��
	Map<String,Object> request;
	Map<String,Object> session;
	private Account account;
	private Personinfo personinfo;
	private Password pwd;
	
	/**
	 * ��¼��У�飬��ʵ�ָ����û�����ȡ�˻�����
	 */
	
	
	public void validateLogin(){
		Account a=userBiz.getAccount(account.getUsername());
		if(a==null){
			this.addFieldError("username", "�û���������");
		}else{
			if(!account.getPassword().equals(a.getPassword())){
				this.addFieldError("password", "���벻��ȷ");
			}
		}
		account=a;
	
	}
	/**
	 * ִ��ҳ��ͻ���¼����
	 * @return
	 */
	public String login(){
		personinfo=(Personinfo)account.getPersoninfos().iterator().next();
		session.put("user", account);
		session.put("personinfo", personinfo);
		return Action.SUCCESS;
	}
	
	public String logout(){
		session.remove("user");
		session.remove("personinfo");
		return "success";
	}
	
	
	/**
	 * ִ���޸���������
	 * @return
	 */
	public String changepwd(){
			account.setPassword(pwd.getNewpwd());
			if(userBiz.modifyAccount(account)){
				session.put("user", account);
				request.put("message", "�����޸ĳɹ���");
				return "message";
			}
			request.put("message", "�����޸�ʧ�ܣ�");
			return "message";
		
	}
	
	/**
	 * �޸�����ҳ����֤
	 */
	public void validateChangepwd(){
		account=(Account) session.get("user");
		if(!pwd.getOldpwd().equals(account.getPassword())){
			this.addFieldError("pwd.oldpwd", "���벻��ȷ");
		}
		if(!pwd.getNewpwd().equals(pwd.getConfirmpwd())){
			this.addFieldError("pwd.confirmpwd", "�������벻һ��");
		}
	}
	
	
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	public Personinfo getPersoninfo() {
		return personinfo;
	}
	public void setPersoninfo(Personinfo personinfo) {
		this.personinfo = personinfo;
	}
	public Password getPwd() {
		return pwd;
	}
	public void setPwd(Password pwd) {
		this.pwd = pwd;
	}
	public void setRequest(Map<String, Object> request) {
		// TODO Auto-generated method stub
		this.request=request;
	}
	public void setSession(Map<String, Object> session) {
		// TODO Auto-generated method stub
		this.session=session;
	}

}
