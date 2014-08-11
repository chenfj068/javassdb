package com.udpwork.ssdb;

public class SSDBException extends RuntimeException{
	private static final long serialVersionUID = 849276316160517039L;

	public SSDBException(String msg,Throwable e){
		super(msg,e);
	}

	public SSDBException(){}
}
