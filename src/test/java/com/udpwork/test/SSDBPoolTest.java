package com.udpwork.test;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.junit.Test;

import com.udpwork.ssdb.SSDB;
import com.udpwork.ssdb.SSDBPool;


public class SSDBPoolTest {

	@Test
	public void test() throws Exception {
		String host="jiecao-tucao";
		int port=48888;
		GenericObjectPoolConfig poolConf = new GenericObjectPoolConfig();
		poolConf.setMaxTotal(3);
		poolConf.setMaxIdle(2);
		poolConf.setTestWhileIdle(true);
		SSDBPool pool = new SSDBPool(host,port,poolConf);
		SSDB ssdb=pool.getResource();
		boolean broken=false;
		try{
			for(int i=0;i<100;i++){
				ssdb.set("key_"+i, "value_"+i);
			}
			for(int i=0;i<100;i++){
				byte[] value=ssdb.get("key_"+i);
				System.out.println(new String(value));
			}
			for(int i=0;i<100;i++){
				ssdb.del("key_"+i);
			}
		}catch(Exception e){
			broken=true;
			throw e;
		}finally{
			if(broken)
				pool.returnBrokenResource(ssdb);
			else
				pool.returnResource(ssdb);
		}
		pool.destroy();
	}
}
