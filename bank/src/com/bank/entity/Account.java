package com.bank.entity;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * Account entity. @author MyEclipse Persistence Tools
 */

public class Account implements java.io.Serializable {

	// Fields

	private Integer accountid;
	private Status status;
	private String username;
	private String password;
	private Double balance;
	private Set personinfos = new HashSet(0);
	private Set transactionLogs = new HashSet(0);

	// Constructors

	/** default constructor */
	public Account() {
	}

	/** minimal constructor */
	public Account(Status status, String username, String password,
			Double balance) {
		this.status = status;
		this.username = username;
		this.password = password;
		this.balance = balance;
	}

	/** full constructor */
	public Account(Status status, String username, String password,
			Double balance, Set personinfos, Set transactionLogs) {
		this.status = status;
		this.username = username;
		this.password = password;
		this.balance = balance;
		this.personinfos = personinfos;
		this.transactionLogs = transactionLogs;
	}

	// Property accessors

	public Integer getAccountid() {
		return this.accountid;
	}

	public void setAccountid(Integer accountid) {
		this.accountid = accountid;
	}

	public Status getStatus() {
		return this.status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Double getBalance() {
		return this.balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public Set getPersoninfos() {
		return this.personinfos;
	}

	public void setPersoninfos(Set personinfos) {
		this.personinfos = personinfos;
	}

	public Set getTransactionLogs() {
		return this.transactionLogs;
	}

	public void setTransactionLogs(Set transactionLogs) {
		this.transactionLogs = transactionLogs;
	}

}