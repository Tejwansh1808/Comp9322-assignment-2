package au.edu.unsw.soacourse.ass2;

import java.sql.Connection;
import java.sql.PreparedStatement;

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
