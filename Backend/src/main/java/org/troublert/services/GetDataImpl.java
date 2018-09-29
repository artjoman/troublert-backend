package org.troublert.services;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.stereotype.Service;

@Service
public class GetDataImpl implements GetData {

	@Override
	public Response getData(String from, String to) {
		return Response.status(Status.OK).build();
	}

//	@Override
//	public Response getData() {
//		// TODO Auto-generated method stub
//		return null;
//	}

}
