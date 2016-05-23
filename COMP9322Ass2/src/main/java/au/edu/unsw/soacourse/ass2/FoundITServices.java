package au.edu.unsw.soacourse.ass2;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;



@Path("/foundIT")
public class FoundITServices {
	
	
	@POST
	@Produces("application/json")
    @Consumes("application/json")
    @Path("/registration")
	public Response registration ( RegistrationRequestDTO registrationRequestDTO){
		System.out.println(registrationRequestDTO.getName());
		
		
		// do registration 
		return Response.ok().entity("").build();
	}

}
