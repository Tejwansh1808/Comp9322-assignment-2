package au.edu.unsw.soacourse.ass2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

@Path("/foundIT")
public class FoundITServicesManager {
	
	@Context
	HttpHeaders headers;
	
	Connection con;
	JDBC_Connection jd;
	int status;
	String securityKey,shortKey;
	
	 public FoundITServicesManager() {
		jd=new JDBC_Connection();
		con=jd.Connect();
	}
	
	@POST
	@Consumes("application/json")
	@Produces("application/json")
	@Path("/addjob")
	public Response addJob(JobAddRequestDTO request)
	{	String jobID,jobName,companyProfileID,positionType,location,jobDescription,jobStatus,keyword,skills;
		int salaryRate;
		
		String sqlString="Insert INTO JOB VALUES(?,?,?,?,?,?,?,?,?,?)";
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
		if(securityKey.equalsIgnoreCase("i-am-foundit")&& shortKey.equalsIgnoreCase("app-manager"))
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
	
	@POST
	@Path("/addManager")
	@Produces("application/json")
	@Consumes("application/json")
	public Response addManager(MangaerLoginRequestDTO request)
	{	String managerID,name,email,password;
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
		if(securityKey.equalsIgnoreCase("i-am-foundit")&& shortKey.equalsIgnoreCase("app-manager"))
		{
			try{
			managerID=request.getManagerID();
			name=request.getName();
			email=request.getEmail();
			password=request.getPassword();
			ManagerUtil.insertManagerLogin(con, managerID, name, email, password);
			status=201;
			}
			catch(Exception e)
			{
				e.printStackTrace();
				System.out.println("Error Occurred in Manager Services add Manager");
				status=500;
			}
			
		}
		else
		{
			status=403;
			System.out.println("Access Forbidden ");
		}
		return Response.status(status).build();
	}

@POST
@Path("/addCompany")
@Produces("application/json")
@Consumes("application/json")
public Response addCompanyProfile(CompanyAddRequestDTO request)
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
	if(securityKey.equalsIgnoreCase("i-am-foundit")&& shortKey.equalsIgnoreCase("app-manager"))
	{
	String managerID,companyProfileID,description,website,industryType,companyLocation,headquaters,companyName;
	ArrayList<String> companyProfile=new ArrayList<String>();
	companyProfileID=request.getCompanyProfileID();
	managerID=request.getManagerID();
	companyName=request.getCompanyName();
	description=request.getDescription();
	website=request.getWebsite();
	industryType=request.getIndustryType();
	companyLocation=request.getCompanyLocation();
	headquaters=request.getHeadquaters();
	companyProfile.add(companyProfileID);
	companyProfile.add(managerID);
	companyProfile.add(companyName);
	companyProfile.add(description);
	companyProfile.add(website);
	companyProfile.add(industryType);
	companyProfile.add(companyLocation);
	companyProfile.add(headquaters);
	
	
	try {
		ManagerUtil.insertCompanyProfile(companyProfile, con);
		status=201;
	} catch (Exception e) {
		status=500;
		System.out.println("Error Occurred in the Manager Services add Company Profile");
		e.printStackTrace();
	}
	
	
	
	
	}
	else
	{
		status=403;
		System.out.println("Access Denied");
	}
	
	return Response.status(status).build();
			
}
@POST
@Path("/addReviewer")
@Produces("application/json")
@Consumes("application/json")
public Response addHiringTeam(HiringTeamAddRequestDTO request)
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
	if(securityKey.equalsIgnoreCase("i-am-foundit")&& shortKey.equalsIgnoreCase("app-manager"))
	{	ArrayList<String> reviewer=new ArrayList<String>();
		String reviewerID,managerID,name,username,password;
		reviewerID=request.getReviewerID();
		managerID=request.getManagerID();
		name=request.getName();
		username=request.getUsername();
		password=request.getPassword();
		reviewer.add(reviewerID);
		reviewer.add(managerID);
		reviewer.add(name);
		reviewer.add(username);
		reviewer.add(password);
		
		try {
			ManagerUtil.insertReviewer(reviewer, con);
			status=201;
		} catch (Exception e) {
			status=500;
			System.out.println("Error Occurred in Manager Services in add Hiring Team");
			e.printStackTrace();
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
