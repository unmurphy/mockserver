package com.wosaitest.mockserver;

import java.util.concurrent.atomic.AtomicInteger;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author yangzhixiang
 *
 */
@Path("/mockserver")
public class ServiceController {

	private static final Logger logger = LoggerFactory.getLogger(ServiceController.class);

	private AtomicInteger ai = new AtomicInteger(0);

	@GET
	@Path("/status")
	@Produces("application/json; charset=GBK")
	public String getMNS(@Context UriInfo uriInfo, String body) {
		MultivaluedMap<String, String> multivaluedMap = uriInfo.getQueryParameters();
		logger.info("status get: " + multivaluedMap.toString() + "-----" + body);
		return String.valueOf(this.ai.get());
	}

	@GET
	@Path("/reset")
	@Produces("application/json; charset=GBK")
	public String resetMNS(@Context UriInfo uriInfo, String body) {
		this.ai.set(0);
		logger.info("reset ok");
		return "{\"msg\":\"reset ok\"}";
	}

	@POST
	@Path("/createmsg")
	@Produces("application/json")
	@Consumes(value = { "text/xml", "application/json" })
	public String createGetuiMsg(@Context String body) {
		logger.info("get create msg " + body);
		this.ai.incrementAndGet();
		return "{\"result\":\"ok\"}";
	}
}
