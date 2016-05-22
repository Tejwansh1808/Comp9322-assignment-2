package au.edu.unsw.soacourse.ass2;
import java.net.URI;

import javax.jws.WebService;
import javax.net.ssl.SSLEngineResult.Status;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/hello")

public class HelloWorld {

    @GET
    @Path("/echo/{input}")
    @Produces("text/plain")
    public String ping(@PathParam("input") String input) {
        return input;
    }

    @POST
    @Produces("application/json")
    @Consumes("application/json")
    @Path("/jsonBean")
    public Response modifyJson(JsonBean input) {
    	//System.out.println(input.getVal1());
        return Response.ok().entity(input).build();
    }
    @POST	
    @Path("/write")
    public Response write(@FormParam("name")String name)
    {	
    	System.out.println(name);
    	return Response.status(201).build();
    }
    
}

