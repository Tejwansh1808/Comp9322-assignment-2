package au.edu.unsw.soacourse.ass2;

import java.sql.Connection;
import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;


@Path("/foundIT")
public class FoundITServicesReviewer {
	@Context
	HttpHeaders headers;
	
	Connection con;
	JDBC_Connection jd;
	int status;
	String securityKey,shortKey;	
	
	public FoundITServicesReviewer() {
		jd=new JDBC_Connection();
		con=jd.Connect();
	}	
	
	
	
@POST
@Produces("application/json")
@Consumes("application/json")
@Path("/loginReviewer")
public LoginReviewerResponseDTO loginReviewer(LoginReviewerRequestDTO request)
{	
	LoginReviewerResponseDTO response=new LoginReviewerResponseDTO();
	String username,password;
	boolean login;
	securityKey=headers.getRequestHeaders().getFirst("SecurityKey");
	shortKey=headers.getRequestHeaders().getFirst("ShortKey");
	if(securityKey==null)
	{
		securityKey="default";
	}
	if(shortKey==null)
	{
		shortKey="default";
	}
	if(securityKey.equalsIgnoreCase("i-am-foundit")&& shortKey.equalsIgnoreCase("app-reviewer"))
	{
		try{
			username=request.getUsername();
			password=request.getPassword();
			login=ReviewerUtil.loginReviewer(username, password, con);
			response.setLogin(login);
			status=201;
		}
		catch(Exception e)
		{
			System.out.println("Error Occured in Reviewer Services Login");
			status=500;
			e.printStackTrace();
		}
	}
	else
	{
		status=403;
		System.out.println("Access Forbidden");
	}
	
	response.setStatus(status);
	return response;
}

//To get the job Application which the Reviewer has to process. 
@GET 
@Produces("application/json")
@Path("/reviewApplications/{reviewerID}")
public  GetReviewerJobApplicationResponseDTO getReviewerJobApplications(@PathParam("reviewerID")String reviewerID)
{	ArrayList<ArrayList<String>> jobApplications=new ArrayList<ArrayList<String>>();
	GetReviewerJobApplicationResponseDTO response=new GetReviewerJobApplicationResponseDTO();
	securityKey=headers.getRequestHeaders().getFirst("SecurityKey");
	shortKey=headers.getRequestHeaders().getFirst("ShortKey");
	if(securityKey==null)
	{
		securityKey="default";
	}
	if(shortKey==null)
	{
		shortKey="default";
	}
	if(securityKey.equalsIgnoreCase("i-am-foundit")&& shortKey.equalsIgnoreCase("app-reviewer"))
	{
		try{
			
			jobApplications=ReviewerUtil.getJobApplications(reviewerID, con);
			response.setJobApplications(jobApplications);
			status=200;
		}
		catch(Exception e)
		{
			System.out.println("Error Occured in Reviewer getReviewerJobApplication");
			status=500;
			e.printStackTrace();

		}
	}
	else
	{
		status=403;
		System.out.println("Access Forbidden");
	}
	
	response.setStatus(status);
	
	return response;
}

//To the Get a particular Job Application for the Reviewer to review
@GET
@Produces("application/json")
@Path("reviewApplicant/{jobApplicationID}")
public ReviewerJobApplicationDetailsResponseDTO getJobApplication(@PathParam("jobApplicationID")String jobApplicationID)
{	
	ReviewerJobApplicationDetailsResponseDTO response=new ReviewerJobApplicationDetailsResponseDTO();
	ArrayList<ArrayList<String>> jobApplicationDetails=new ArrayList<ArrayList<String>>();
	securityKey=headers.getRequestHeaders().getFirst("SecurityKey");
	shortKey=headers.getRequestHeaders().getFirst("ShortKey");
	if(securityKey==null)
	{
		securityKey="default";
	}
	if(shortKey==null)
	{
		shortKey="default";
	}
	if(securityKey.equalsIgnoreCase("i-am-foundit")&& shortKey.equalsIgnoreCase("app-reviewer"))
	{
		try{
			jobApplicationDetails=ReviewerUtil.getJobApplicationDetails(jobApplicationID, con);
			response.setJobApplicationDetails(jobApplicationDetails);
			
			
			status=200;
		}
		catch(Exception e)
		{
			System.out.println("Error Occured in Reviewer getReviewerJobApplication");
			status=500;
			e.printStackTrace();
		}
	}
	else
	{
		status=403;
		System.out.println("Access Forbidden");
	}
	
	response.setStatus(status);
	return response;
}

//To Update the Result from the Reviewer for a particular job application
@PUT
@Produces("application/json")
@Path("/updateResult/{jobApplicationID}/{reviewerID}/{result}")
public Response updateReviewerResult(@PathParam("jobApplicationID")String jobApplicationID,@PathParam("reviewerID")String reviewerID,@PathParam("result")String result)
{
	securityKey=headers.getRequestHeaders().getFirst("SecurityKey");
	shortKey=headers.getRequestHeaders().getFirst("ShortKey");
	if(securityKey==null)
	{
		securityKey="default";
	}
	if(shortKey==null)
	{
		shortKey="default";
	}
	if(securityKey.equalsIgnoreCase("i-am-foundit")&& shortKey.equalsIgnoreCase("app-reviewer"))
	{
		try{
			
		}
		catch(Exception e)
		{
			System.out.println("Error Occured in Reviewer updateReviewerResult");
			status=500;
			e.printStackTrace();
		}
	}
	else
	{
		status=403;
		System.out.println("Access Forbidden");
	}
	
	return Response.status(status).build();
}
}
