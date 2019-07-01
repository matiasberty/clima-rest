package com.galaxia.clima.ws;

import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.galaxia.clima.config.Config;

@Produces({ MediaType.APPLICATION_JSON })
@Consumes({ "*/*" })
@Path("/app")
public class AppService {

	private static final Logger LOG = LoggerFactory.getLogger(AppService.class);

	@GET
	@Path("/health")
	public String health() throws Exception {
		JsonObjectBuilder result = Json.createObjectBuilder();
		result.add("health", "success");
		return result.build().toString();
	}

	@GET
	@Path("/info")
	public String version() throws Exception {
		JsonObjectBuilder result = Json.createObjectBuilder();
		result.add("client.product", Config.getClientProduct());
		result.add("version", Config.getVersion());
		result.add("build.date", Config.getBuildDate());
		result.add("node.name", Config.getNodeName());
		return result.build().toString();

	}

}