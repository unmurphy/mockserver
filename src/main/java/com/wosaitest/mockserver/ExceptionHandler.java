package com.wosaitest.mockserver;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class ExceptionHandler implements ExceptionMapper<Throwable> {

	public Response toResponse(Throwable exception) {
		// TODO Auto-generated method stub
		Response response;
		if (exception instanceof NotFoundException) {
			response = Response.status(Response.Status.NOT_FOUND).build();
		} else {
			response = Response.serverError().build();
		}
		return response;
	}
}
