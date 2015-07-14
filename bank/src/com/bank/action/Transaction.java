package com.bank.action;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.annotation.Resources;

import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.bank.entity.*;
import com.bank.biz.*;
//@Component
public class Transaction  extends ActionSupport implements RequestAware,SessionAware{
	
	@Resource 
	private UserBiz userBiz;
	@Resource 
	private TransactionBiz transactionBiz;
	private Account account;
	private TransactionLog log;
	private Pager pager;
	
	private Map<String,Object> request;
	private Map<String,Object> session;
    /**public void setUserBiz(UserBiz userBiz) {
		this.userBiz = userBiz;
	}
	public void setTransactionBiz(TransactionBiz transactionBiz) {
		this.transactionBiz = transactionBiz;
	}
	**/
	public void setSession(Map<String, Object> session) {
		// TODO Auto-generated method stub
		this.session=session;
		account=(Account)session.get("user");
	}
	public void setRequest(Map<String, Object> request) {
		// TODO Auto-generated method stub
		this.request=request;
	}
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	public TransactionLog getLog() {
		return log;
	}
	public void setLog(TransactionLog log) {
		this.log = log;
	}
	public Pager getPager() {
		return pager;
	}
	public void setPager(Pager pager) {
		this.pager = pager;
	}
	
	/**
	 * 存款
	 * @return
	 */
	public String deposit(){		
		if(isEnable()){			
			log.setAccount(account);
			session.put("user", account);
			return isSuccess(transactionBiz.deposit(log));
		}
		return "message";
	}
	
	/**
	 * 取款
	 * @return
	 */
	public void validateWithdrawal(){
		if(log.getTrMoney()>account.getBalance()){
			this.addFieldError("log.trMoney", "您的帐户余额不足！");
		}
	}
	public String withdrawal(){
		if(isEnable()){
			log.setAccount(account);
			session.put("user", account);
			return isSuccess(transactionBiz.withdrawal(log));
		}
		return "message";
	}
	/**
	 * 转账
	 * @return
	 */
	public void validateTransfer(){
		if(log.getOtherid().intValue()==account.getAccountid().intValue()){
			this.addFieldError("log.otherid","您不能转账给自己！");
		}
		if(userBiz.getAccount(log.getOtherid())==null){
			this.addFieldError("log.otherid","该账户不存在！");
		}
		if(log.getTrMoney().compareTo(account.getBalance())>0){
			this.addFieldError("log.trMoney","您的账户余额不足！");
		}
	}
	public String transfer(){
		if(isEnable()){
			log.setAccount(account);
			session.put("user", account);
			return isSuccess(transactionBiz.transfer(log));
		}
		return "message";
	}
	/**
	 * 查询交易记录
	 * @return
	 */
	
	public String list(){
		
		int curPage=pager.getCurPage();
		
		List<TransactionLog> logs = transactionBiz.getLogs(account,curPage);		
		
		pager=transactionBiz.getPagerOfLogs(account);
		
		pager.setCurPage(curPage);
		request.put("logs", logs);
		return "success";
	}
	private boolean isEnable(){
		userBiz.reflush(account);
		if(account.getStatus().getName().equals("冻结")){
			request.put("message", "对不起该帐户被冻结，无法进行相关操作<br>");
			return false;
		}
		return true;
	}
	
	private String isSuccess(boolean flag){
		if(flag)
		{ 
			request.put("message","操作成功！");
			return "message";
		}
		request.put("message","操作失败！<a href='javascript:history.go(-1)'>返回</a>");
		return "message";
	}
}
