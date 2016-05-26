package au.edu.unsw.soacourse.ass2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;


@Path("/foundIT")
public class FoundITServicesCandidate {
	@Context
	HttpHeaders headers;
	
	Connection con;
	JDBC_Connection jd;
	int status;
	String securityKey,shortKey;
	
	 public FoundITServicesCandidate() {
		jd=new JDBC_Connection();
		con=jd.Connect();
	}
	
	 
	 
	 
	
	@POST
	@Produces("application/json")
    @Consumes("application/json")
    @Path("/registration/{flag}")
	public Response registration ( RegistrationRequestDTO request,@PathParam("flag")String flag){
		
		String userID,dob,currentPosition,currentCompany,highestEducation,pastExperience,professionalSkills,resume,coverLetter;
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
		if(securityKey.equalsIgnoreCase("i-am-foundit")&& shortKey.equalsIgnoreCase("app-candidate"))
		{
		if(flag.equalsIgnoreCase("register"))
		{
		try{
			userID=request.getUserID();
			dob=request.getDob();
			currentPosition=request.getCurrentPosition();
			currentCompany=request.getCurrentCompany();
			highestEducation=request.getHighestEducation();
			pastExperience=request.getPastExperience();
			professionalSkills=request.getProfessionalSkills();
			resume=request.getResume();
			coverLetter=request.getCoverLetter();
			String sqlString="Insert into USERPROFILE (USERID,DOB,CURRENT_POSITION,CURRENT_COMPANY,HIGHEST_EDUCATION,PAST_EXPERIENCE,"
					+ "PROFESSIONAL_SKILLS,RESUME,CV) VALUES(?,?,?,?,?,?,?,?,?)";
			PreparedStatement pd=con.prepareStatement(sqlString);
			pd.setString(1, userID);
			pd.setString(2, dob);
			pd.setString(3, currentPosition);
			pd.setString(4,currentCompany);
			pd.setString(5, highestEducation);
			pd.setString(6, pastExperience);
			pd.setString(7, professionalSkills);
			pd.setString(8, resume);
			pd.setString(9, coverLetter);
			pd.executeUpdate();
			status=201;
		}
		catch(Exception e)
		{
			System.out.println("Error Occured in registration register FoundITService.java");
			status=500;
			e.printStackTrace();
		}
		}
		else if(flag.equalsIgnoreCase("update"))
			//Cannot Change email and userID but requires the userID to update the user.
		{
			try{
				userID=request.getUserID();
				dob=request.getDob();
				currentPosition=request.getCurrentPosition();
				currentCompany=request.getCurrentCompany();
				highestEducation=request.getHighestEducation();
				pastExperience=request.getPastExperience();
				professionalSkills=request.getProfessionalSkills();
				resume=request.getResume();
				coverLetter=request.getCoverLetter();
				String sqlString="UPDATE USERPROFILE SET DOB=?,CURRENT_POSITION=?,CURRENT_COMPANY=?,HIGHEST_EDUCATION=?,"
						+ "PAST_EXPERIENCE=?,PROFESSIONAL_SKILLS=?,RESUME=?,CV=? WHERE USERID=?";
				PreparedStatement pd=con.prepareStatement(sqlString);
				
				
				pd.setString(1, dob);
				pd.setString(2, currentPosition);
				pd.setString(3,currentCompany);
				pd.setString(4, highestEducation);
				pd.setString(5, pastExperience);
				pd.setString(6, professionalSkills);
				pd.setString(7, resume);
				pd.setString(8, coverLetter);
				pd.setString(9, userID);
				
				pd.executeUpdate();
				status=204;
				Response.ok().header("auth", "HelloSKK");
			}
			catch(Exception e)
			{
				System.out.println("Error Occured in registration update FoundITService.java");
				status=500;
				e.printStackTrace();
			}
		}
		else
		{
			status=404;
		}
		}
		else
		{
			status=403;
			System.out.println("Access Forbidden");
		}
		return Response.ok().status(status).build();
				 
	}
	@GET
	@Produces("application/json")
	@Path("/candidate/{userid}")
	public UserProfileResponseDTO getProfile(@PathParam("userid")String userID)
	{  
		UserProfileResponseDTO response=new UserProfileResponseDTO();
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
		if(securityKey.equalsIgnoreCase("i-am-foundit")&& shortKey.equalsIgnoreCase("app-candidate"))
		{
		try{
		
		ArrayList<String> jobSeekerProfile=new ArrayList<String>();
		jobSeekerProfile=ProfileSearch.getProfile(userID, con, "candidate");
		response.setJobSeeker(jobSeekerProfile);
		status=200;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("Error Occurred in getProfile Service");
			status=500;
		}
		}
		else
		{
			status=403;
			System.out.println("Security Breach!!!!");
			
			
		
		}
		
		response.setStatus(status);
		
		return response;
	}
	
	
	
	@POST
	@Produces("application/json")
    @Consumes("application/json")
    @Path("/signup")
	public Response addUsername(SignUpRegRequest request)
	
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
		if(securityKey.equalsIgnoreCase("i-am-foundit")&& shortKey.equalsIgnoreCase("app-candidate"))
		{
		try {
			String name=request.getName();
			String email=request.getEmail();
			String password=request.getPassword();
			String userID=request.getUserID();
			String sqlString="Insert INTO JOBSEEKER (USERID,EMAIL,PASSWORD,NAME) VALUES(?,?,?,?)";
			PreparedStatement pd=con.prepareStatement(sqlString);
			pd.setString(1, userID);
			pd.setString(2, email);
			pd.setString(3,password);
			pd.setString(4, name);
			pd.executeUpdate();
			status=201;
		} catch (SQLException e) {
			System.out.println("Error Occured in addUsername FoundITService.java");
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
	

	@POST
	@Produces("application/json")
    @Consumes("application/json")
    @Path("/email")
	public Response sendEmail(EmailRequestDTO request)
	
	{	
		securityKey=headers.getRequestHeaders().getFirst("SecurityKey");
		if(securityKey==null)
		{
			securityKey="default";
		}
		
	if(securityKey.equalsIgnoreCase("i-am-foundit"))
	{
		try{
			
		String email=request.getEmail();
		String subject=request.getSubject();
		String message=request.getMessage();
		Mail_Util.sendMail("founditservices@gmail.com", "teju1808", email, message, subject);
		status=201;
		}
		catch(Exception e)
		{	
			System.out.println("Error Occurred in service email");
			e.printStackTrace();
			status=500;
		}
	}
	else
	{
		status=403;
		System.out.println("Access Forbidden");
	}
		return Response.status(status).build();
	}
	
	
	
	@SuppressWarnings({ "rawtypes" })
	@GET
	@Path("/jobsearch/{keyword}/{status}/{skills}")
	@Produces("application/json")
	public JobSearchReponseDTO jobSearch(@PathParam("keyword")String keyword,@PathParam("skills")String skills,@PathParam("status")String jobStatus)
	{	
		JobSearchReponseDTO response=new JobSearchReponseDTO();
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
		if(keyword.equalsIgnoreCase(""))
		{
			keyword="default";
		}
		if(skills.equalsIgnoreCase(""))
		{
			skills="default";
		}
		if(jobStatus.equalsIgnoreCase(""))
		{
			jobStatus="default";
		}
		if(securityKey.equalsIgnoreCase("i-am-foundit")&& shortKey.equalsIgnoreCase("app-candidate"))
		{
			
		status=200;
		ArrayList<ArrayList> jobResults;
		try{
		
		jobResults=JobSearch.jobSearch(keyword, skills, jobStatus, con);
		response.setJobResults(jobResults);
		
		}
		catch(Exception e)
		{	e.printStackTrace();
			System.out.println("Error Occured in search job");
			status=500;
		}
		
		
		}
		else
		{	System.out.println("Security Breach!!!!");
			status=403;
		}
		
		//Response.status(status).build();
		response.setStatus(status);
		return response;
				
	}
	
	
	@SuppressWarnings({ "rawtypes" })
	@GET
	@Path("/job/{jobID}")
	@Produces("application/json")
	public JobSearchReponseDTO jobSearch(@PathParam("jobID")String jobID)
	{	
		JobSearchReponseDTO response=new JobSearchReponseDTO();
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
		if(securityKey.equalsIgnoreCase("i-am-foundit")&& shortKey.equalsIgnoreCase("app-candidate"))
		{
			
		
		ArrayList<ArrayList> jobResults;
		try{
		jobResults=JobSearch.jobSearch(jobID, con);
		response.setJobResults(jobResults);
		status=200;
		
		}
		catch(Exception e)
		{	e.printStackTrace();
			System.out.println("Error Occured in search job");
			status=500;
		}
		
		
		}
		else
		{	System.out.println("Security Breach!!!!");
			status=403;
		}
		
		response.setStatus(status);
		
		return response;
				
	}
	
	@POST
	@Produces("application/json")
    @Consumes("application/json")
    @Path("/addjobalert")	
	public Response addJobAlert(AddJobAlertRequestDTO request)
	{	String jobAlertID,userID,keyword,skills,positionType,subscribe;
		
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
		if(securityKey.equalsIgnoreCase("i-am-foundit")&& shortKey.equalsIgnoreCase("app-candidate"))
		{
			jobAlertID=request.getJobAlertID();
			userID=request.getUserID();
			keyword=request.getKeyword();
			skills=request.getSkills();
			positionType=request.getPositionType();
			subscribe=request.getSubscribe();
			ArrayList<String> jobAlert=new ArrayList<String>();
			jobAlert.add(jobAlertID);
			jobAlert.add(userID);
			jobAlert.add(keyword);
			jobAlert.add(skills);
			jobAlert.add(positionType);
			jobAlert.add(subscribe);
			
			
			try{
				CandidateUtil.insertJobAlert(jobAlert, con);
				status=201;	
			}
			catch(Exception e)
			{
				e.printStackTrace();
				System.out.println("Error Occured in Candidate Services addJobAlerts");
				status=500;
			}
			
		}
		else
		{
			status=403;
			System.out.println("Access Denied");
		}
		
		return Response.status(status).build();
	}
	
	@PUT
	@Produces("application/json")
	@Consumes("application/json")
	@Path("/updatejobalert/{flag}")
	public Response updateJobAlert(@PathParam("flag")String flag,UpdateJobAlertDTO request)
	{
	ArrayList<String> jobAlertID=new ArrayList<String>();
	String userID;
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
	if(securityKey.equalsIgnoreCase("i-am-foundit")&& shortKey.equalsIgnoreCase("app-candidate"))
	{
		if(flag.equalsIgnoreCase("subscribe"))
		{	
			userID=request.getUserID();
			jobAlertID=request.getJobALertID();
			try{
			CandidateUtil.updateJobAlert(jobAlertID, flag, userID, con);
			status=204;
			}
			catch(Exception e)
			{
				e.printStackTrace();
				status=500;
				System.out.println("Error Occurred in candidate Services updateJobAlert subscribe");
			}
		}
		else if(flag.equalsIgnoreCase("unsubscribe"))
		{	
			userID=request.getUserID();
			jobAlertID=request.getJobALertID();
			try{
				CandidateUtil.updateJobAlert(jobAlertID, flag, userID, con);
				status=204;
				}
				catch(Exception e)
				{
					e.printStackTrace();
					status=500;
					System.out.println("Error Occurred in candidate Services updateJobAlert subscribe");
				}
		}
		else
		{
			status=404;
			Response.serverError().build();
			System.out.println("Flag Incorrect!! Resource Not Found");
		}
	}
	else
	{
		status=403;
		System.out.println("Access Denied");
	}
		return Response.status(status).build();
	}
	
}