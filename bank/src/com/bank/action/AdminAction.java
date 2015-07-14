package com.bank.action;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.stereotype.Component;

import com.bank.biz.*;
import com.bank.entity.*;
import com.opensymphony.xwork2.ActionSupport;
//@Component

public class AdminAction extends ActionSupport implements RequestAware,SessionAware {
	
	@Resource private UserBiz userBiz;	
	@Resource private PersoninfoBiz personinfoBiz;
	Map<String, Object> request;
	Map<String, Object> session;	
	private Admin admin;	
	private Account account;	
	private Personinfo personinfo;
	private Password pwd;
	private int id;
	private Status status;
	 public  int getId(){
	    	return this.id;
	    }
	public void setId(int id) {
		this.id = id;
	}
   
	/**
	 * 对登录页面进行验证，检查用户名和密码是否正确
	 */
	public void validateLogin(){
		//调用业务方法，根据username获取管理员
		Admin a=userBiz.getAdmin(admin.getUsername());
		System.out.println("进入Validateionlogin");
		if(a==null){
			this.addFieldError("username", "用户名不存在");
		}else{
			if(!(admin.getPassword().equals(a.getPassword()))){
				this.addFieldError("password", "密码不正确");
			
			
			}
			admin=a;

			
		}
	}	
	/**
	 * 登录
	 * @return
	 */
	public String login(){
       
		if(admin!=null){			
			session.put("admin",admin);
			return "success";
		}
		return "input";
	}

	/**
	 * 查询账户
	 * @return
	 */
	public String listUsers(){
		//调用业务方法，根据账户状态获取个人信息，状态为0表示获取所有客户
		//System.out.println(status.getId());
		//this.status=new Status();
		//this.status.setId(id);
		List users=personinfoBiz.searchPersoninfo(status);		
		request.put("users",users);
		return "users";

	}

	//注销
	public String logout(){
		session.remove("admin");
		return "login";
	}

	/**
	 * 对开户页面进行校验，验证用户名是否已存在、一张身份证只能拥

有一个账户
	 */
	public void validateKaihu(){
		if(userBiz.getAccount(account.getUsername())!=null){
			request.put("message", "用户名已存在");
		}
		//获取满足条件的个人信息，这里的条件为开户页面中填写的身份证号
		List list = personinfoBiz.searchPersoninfo

		(personinfo);
		//如果所填写的身份证号在个人信息中已存在，则提示错误信		息
		if(list.size()>0){
			this.addFieldError("personinfo.cardid", "一张身份证只能拥有一个账户");
		}		
	}	
	//开户
	public String kaihu(){
		//调用业务方法，向账户表Account中添加账户
		userBiz.addAccount(account);
		//调用业务方法，向个人信息表personinfo添加个人信息
		account = userBiz.getAccount(account.getUsername());
		personinfo.setAccount(account);
		personinfoBiz.add(personinfo);
		request.put("message", "添加成功");
		return "message";		
	}

	/**
	 * 对修改密码页面进行验证
	 */
	public void validateChangepwd(){
		admin=(Admin)session.get("admin");
		if(!pwd.getOldpwd().equals(admin.getPassword())){
			this.addFieldError("pwd.oldpwd", "密码不正确");
		}
		if(!pwd.getNewpwd().equals(pwd.getConfirmpwd())){this.addFieldError("pwd.confirmpwd", "两次密码不一致");
		}
	}	
	//修改密码
	public String changepwd(){
		admin.setPassword(pwd.getNewpwd());
		if(userBiz.modifyAdmin(admin)){
			session.put("admin",admin);
			request.put("message", "密码修改成功！");
			return "message";
		}
		request.put("message", "密码修改失败！");
		return "message";
	}

	/**
	 * 删除账户
	 */	
	public String del(){
		//调用业务方法，删除账户，同时进行级联删除
		userBiz.delAccount(id);
		return "list";
	}
	/**
	 * 查询账户
	 */	
	public String search(){
		List users=personinfoBiz.searchPersoninfo(personinfo);
		request.put("users",users);
		return "users";
	}
	/**
	 * 启用账户
	 * @return
	 */
	public String enabled(){
		userBiz.enabled(id);
		return "list";
	}
	/**
	 * 冻结账户
	 * @return
	 */
	public String locking(){
		userBiz.locking(id);
		return "list";
	}	



	public Admin getAdmin() {
		return admin;
	}
	public void setAdmin(Admin admin) {
		this.admin = admin;
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
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public void setRequest(Map<String, Object> request) {
		this.request=request;
	}

	public void setSession(Map<String, Object> session) {
		this.session=session;
	}

}
