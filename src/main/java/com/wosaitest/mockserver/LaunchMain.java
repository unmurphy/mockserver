package com.wosaitest.mockserver;

import org.jboss.resteasy.plugins.server.netty.NettyJaxrsServer;
import org.jboss.resteasy.spi.ResteasyDeployment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author yangzhixiang
 *
 */
public class LaunchMain {
	private static final Logger logger = LoggerFactory.getLogger(LaunchMain.class);

	public static void main(String[] args) {
		NettyJaxrsServer nettyJaxrsServer = new NettyJaxrsServer();
		nettyJaxrsServer.setRootResourcePath("/");
		nettyJaxrsServer.setPort(8000);
		ResteasyDeployment deployment = new ResteasyDeployment();
		deployment.getResources().add(new ServiceController());
		deployment.getProviders().add(new ExceptionHandler());
		nettyJaxrsServer.setDeployment(deployment);
		nettyJaxrsServer.setExecutorThreadCount(200);
		nettyJaxrsServer.start();
		logger.info("Mock Server Start!!!");
	}
}
