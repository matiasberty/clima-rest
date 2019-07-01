package com.galaxia.clima.ws;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.galaxia.clima.ejb.ClimaWebEjb;

@Produces({ MediaType.APPLICATION_JSON })
@Consumes({ "*/*" })
@Path("/rest")
public class ClimaWebServiceRest {
	private static final Logger LOG = LoggerFactory.getLogger(ClimaWebServiceRest.class);
	@Inject
	ClimaWebEjb ejb;

	@GET
	@Path("/ws_get_tex")
	public String ws_get_tex() throws Exception {
		return ejb.ws_get_tex();
	}

}