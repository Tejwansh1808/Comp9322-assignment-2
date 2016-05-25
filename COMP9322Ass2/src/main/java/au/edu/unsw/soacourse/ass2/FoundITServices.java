package au.edu.unsw.soacourse.ass2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;


@Path("/foundIT")
public class FoundITServices {
	@Context
	HttpHeaders headers;
	
	Connection con;
	JDBC_Connection jd;
	int status;
	String securityKey,shortKey;
	
	 public FoundITServices() {
		jd=new JDBC_Connection();
		con=jd.Connect();
	}
	
	Response s;
	@POST
	@Produces("application/json")
    @Consumes("application/json")
    @Path("/registration/{flag}")
	public Response registration ( RegistrationRequestDTO request,@PathParam("flag")String flag){
		
		String userID,dob,currentPosition,currentCompany,highestEducation,pastExperience,professionalSkills,resume,coverLetter;
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
				status=201;
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
		
		return Response.ok().status(status).build();
				 
	}
	
	@POST
	@Produces("application/json")
    @Consumes("application/json")
    @Path("/signup")
	public Response addUsername(SignUpRegRequest request)
	
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
		return Response.status(status).build();
	}
	

	@POST
	@Produces("application/json")
    @Consumes("application/json")
    @Path("/email")
	public Response sendEmail(EmailRequestDTO request)
	
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
		if(securityKey.equalsIgnoreCase("i-am-foundit")&& shortKey.equalsIgnoreCase("app-candidate"))
		{
			
		
		ArrayList<ArrayList> jobResults;
		try{
		jobResults=JobSearch.jobSearch(keyword, skills, jobStatus, con);
		response.setJobResults(jobResults);
		status=200;
		}
		catch(Exception e)
		{	e.printStackTrace();
			System.out.println("Error Occured in search job");
			status=500;
		}
		
		Response.status(status).build();
		}
		else
		{	System.out.println("Security Breach!!!!");
			status=800;
		}
		response.setStatus(status);
		return response;
				
	}
	
	@POST
	@Consumes("application/json")
	@Produces("application/json")
	@Path("/addjob")
	public Response addJob(JobAddRequestDTO request)
	{	String jobID,jobName,companyProfileID,positionType,location,jobDescription,jobStatus,keyword,skills;
		int salaryRate;
		
		String sqlString="Insert INTO JOB VALUES(?,?,?,?,?,?,?,?,?,?)";
		try{
			jobID=request.getJobID();
			jobName=request.getJobName();
			companyProfileID=request.getCompanyProfileID();
			salaryRate=request.getSalaryRate();
			positionType=request.getPositionType();
			location=request.getLocation();
			jobDescription=request.getJobDescription();
			jobStatus=request.getStatus();
			keyword=request.getKeyword();
			skills=request.getSkills();
			PreparedStatement pd=con.prepareStatement(sqlString);
			pd.setString(1, jobID);
			pd.setString(2, jobName);
			pd.setString(3, companyProfileID);
			pd.setInt(4,salaryRate);
			pd.setString(5, positionType);
			pd.setString(6, location);
			pd.setString(7,jobDescription);
			pd.setString(8,jobStatus);
			pd.setString(9, keyword);
			pd.setString(10, skills);
			pd.executeUpdate();
			status=200;
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("Erro Occurred in service addJob");
			status=500;
		}
		
		return Response.status(status).build();
	}

}
