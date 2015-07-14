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
	//这里依然用的是struts当控制器，你要继承ActionSupport，非SpringMVC所以@Colltroller这样SpringMVC注解
	//不能实现扫描方式注册UserAction，无奈！！暂时还要在XML声明UserAction为Spring的bean

	@Resource
	private UserBiz userBiz;//注解方式实现依赖注入
	Map<String,Object> request;
	Map<String,Object> session;
	private Account account;
	private Personinfo personinfo;
	private Password pwd;
	
	/**
	 * 登录表单校验，并实现根据用户名获取账户对象
	 */
	
	
	public void validateLogin(){
		Account a=userBiz.getAccount(account.getUsername());
		if(a==null){
			this.addFieldError("username", "用户名不存在");
		}else{
			if(!account.getPassword().equals(a.getPassword())){
				this.addFieldError("password", "密码不正确");
			}
		}
		account=a;
	
	}
	/**
	 * 执行页面客户登录请求
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
	 * 执行修改密码请求
	 * @return
	 */
	public String changepwd(){
			account.setPassword(pwd.getNewpwd());
			if(userBiz.modifyAccount(account)){
				session.put("user", account);
				request.put("message", "密码修改成功！");
				return "message";
			}
			request.put("message", "密码修改失败！");
			return "message";
		
	}
	
	/**
	 * 修改密码页面验证
	 */
	public void validateChangepwd(){
		account=(Account) session.get("user");
		if(!pwd.getOldpwd().equals(account.getPassword())){
			this.addFieldError("pwd.oldpwd", "密码不正确");
		}
		if(!pwd.getNewpwd().equals(pwd.getConfirmpwd())){
			this.addFieldError("pwd.confirmpwd", "两次密码不一致");
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
