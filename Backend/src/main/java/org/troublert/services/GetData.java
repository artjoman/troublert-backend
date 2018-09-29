package org.troublert.services;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/")
public interface GetData {
	
	@GET
	@Path(value="/getdata/{from}/{to}")
    @Produces({ "application/json" })
	public Response getData( @PathParam("from") String from, @PathParam("to") String to);
	//public Response getData();
}
