package com.wosaitest.mockserver;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author yangzhixiang
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
        logger.info("status get: " + multivaluedMap.toString());
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
    @Produces("application/json; charset=utf-8")
    @Consumes(value = {"application/json"})
    public User createGetuiMsg(@Context UriInfo uriInfo, String body) {
        User user = null;
        ObjectMapper mapper = new ObjectMapper();
        try {
            user = mapper.readValue(body, User.class);
        } catch (IOException e) {
            logger.error(e.getMessage());
            System.out.println(e.getMessage());
        }
        this.ai.incrementAndGet();
        return new User(user.getName(), user.getAge());
    }
}
