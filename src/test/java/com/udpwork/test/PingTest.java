package com.udpwork.test;

import org.junit.Test;

import com.udpwork.ssdb.Response;
import com.udpwork.ssdb.SSDB;

public class PingTest {

	@Test
	public void testPint() throws Exception{
		SSDB ssdb = null;
		Response resp;
		byte[] b;
		ssdb = new SSDB("203.195.164.38", 48888);
		Response reponse=ssdb.request("caonima","");
		reponse.print();
	}
}
