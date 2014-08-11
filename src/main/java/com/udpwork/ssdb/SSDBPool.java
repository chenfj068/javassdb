package com.udpwork.ssdb;

import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;


public class SSDBPool extends GenericPool<SSDB>{

	private final String host;
	private final int port;
	private final int timeout;
	private final SSDBPooledObjectFactory ssdbFactory = new SSDBPooledObjectFactory();
	public SSDBPool(String host,int port,GenericObjectPoolConfig poolConfig){
		this(host,port,5000,poolConfig);
	}

	public SSDBPool(String host,int port,int timeout,GenericObjectPoolConfig poolConfig){
		this.host=host;
		this.port=port;
		this.timeout=timeout;
		super.initPool(poolConfig, ssdbFactory);
	}

	@Override
	public SSDB getResource(){
		return super.getResource();
	}

	@Override
	public void returnResource(SSDB ssdb){
		super.returnResource(ssdb);
	}

	@Override
	public void returnBrokenResource(SSDB ssdb){
		super.returnBrokenResource(ssdb);
	}
	class SSDBPooledObjectFactory implements PooledObjectFactory<SSDB>{

		@Override
		public void activateObject(PooledObject<SSDB> poolObj) throws Exception {
			SSDB ssdb =	poolObj.getObject();
			ssdb.ping();
		}

		@Override
		public void destroyObject(PooledObject<SSDB> poolObj) throws Exception {
			poolObj.getObject().close();
		}

		@Override
		public PooledObject<SSDB> makeObject() throws Exception {
			SSDB ssdb = new SSDB(host,port,timeout);
			return new DefaultPooledObject(ssdb);
		}

		@Override
		public void passivateObject(PooledObject<SSDB> poolObj) throws Exception {


		}

		@Override
		public boolean validateObject(PooledObject<SSDB> poolObj) {
			SSDB ssdb =	poolObj.getObject();
			try {
				ssdb.ping();
			} catch (Exception e) {
				return false;
			}
			return true;
		}

	}
}
