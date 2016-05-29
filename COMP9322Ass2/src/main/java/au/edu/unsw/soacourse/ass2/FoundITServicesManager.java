package au.edu.unsw.soacourse.ass2;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
	
	 
	 // Add a new job posting
	@POST
	@Consumes("application/json")
	@Produces("application/json")
	@Path("/addjob")
	public Response addJob(JobAddRequestDTO request)
	{	String jobID,jobName,companyProfileID,positionType,location,jobDescription,jobStatus,keyword,skills;
		int salaryRate;
		
		String sqlString="Insert INTO JOB VALUES(?,?,?,?,?,?,?,?,?,?)";
		String sqlString2="INSERT INTO JOBINTERNAL_STATUS VALUES(?,?)";
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
			
			
			status=201;
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("Error Occurred in  Manager service addJob");
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
	
	
	//To change status of the job from open to close 
	
	@PUT
	@Produces("json/application")
	@Path("closeJob/{jobID}")
	public Response closeJob(@PathParam("jobID")String jobID)
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
			try{
				
				ManagerUtil.closeJob(jobID, con);
				status=204;
				
			}
			catch(Exception e)
			{
				e.printStackTrace();
				status=500;
				System.out.println("Error Occurred in Managers service closeJob");
			}
		}
		else
		{
			status=403;
			System.out.println("Access Forbidden ");
		}
	return Response.status(status).build();
	}
	
	//Manager Profile
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

	//Add Company Details
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

//Add Reviewers 
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
   //Get the Company ID 
 	@GET
 	@Produces("application/json")
 	@Path("/CompanyID/{managerID}")
 	public GetCompanyIDResponseDTO getCompanyID(@PathParam("managerID")String managerID)
 	{	GetCompanyIDResponseDTO response=new  GetCompanyIDResponseDTO();
 		 String companyID="";
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
 				companyID=ManagerUtil.getConpanyID(managerID, con);
 				response.setCompanyID(companyID);
 				System.out.println();
 				status=200;
 			}
 			catch(Exception e)
 			{
 				e.printStackTrace();
 				status=500;
 				System.out.println("Error Occurred in Manager Services getCompnayID");
 			}
 		}
 		else
 		{
 			status=403;
 			System.out.println("Access Denied");
 		}
 		
 		return response;
 	}
 	
 	
 	//Get the Job List for the Manager to View and proceed
 	@GET
 	@Produces("application/json")
 	@Path("/jobList/{managerID}")
 	public ManagerJobListResponseDTO getJobList(@PathParam("managerID")String managerID)
 	{	ArrayList<ArrayList<String>> jobList;
 		ManagerJobListResponseDTO response=new ManagerJobListResponseDTO();
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
 				//System.out.println(managerID);
 				jobList=ManagerUtil.getJobList(managerID, con);
 				response.setJobList(jobList);
 				status=200;
 				
 			}
 			catch(Exception e)
 			{
 				e.printStackTrace();
 				status=500;
 				System.out.println("Error Occurred in Manager Services getJobList");
 			}
 		}
 		else
 		{
 			status=403;
 			System.out.println("Access Denied");
 		}
 		response.setStatus(status);
 		return response;
 	}
 	
 	//Get the Job Applicants for a particular job
 	@GET
 	@Produces("application/json")
 	@Path("applicants/{jobID}")
 	public GetApplicantResponseDTO getApplicants(@PathParam("jobID")String jobID)
 	{	GetApplicantResponseDTO response=new GetApplicantResponseDTO();
 		ArrayList<ArrayList<String>> applicantList=new ArrayList<ArrayList<String>>();
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
 				applicantList=ManagerUtil.getJobApplicants(jobID, con);
 				
 				response.setApplicantList(applicantList);
 				status=200;
 			}
 			catch(Exception e)
 			{
 				e.printStackTrace();
 				status=500;
 				System.out.println("Error Occurred in Manager Services getApplicants");
 			}
 		}
 		else
 		{
 			status=403;
 			System.out.println("Access Denied");
 		}
 		
 		response.setStatus(status);
 		return response;
 	}
 	
 	
 	//To update the Various Job Application Status
 	@PUT
 	@Produces("application/json")
 	@Path("updateJobApplication/{jobApplicationID}/{status}")
 	public Response updateJobApplication(@PathParam("jobApplicationID")String jobApplicationID,@PathParam("status")String update)
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
 			try
 			{
 				ManagerUtil.updateJobApplicationStatus(jobApplicationID, update, con);
 				status=204;
 			}
 			catch(Exception e)
 			{
 				System.out.println("Error Occurred in Manager Services updateJobApplication");
 				e.printStackTrace();
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
 	
 	//Get BPEL result Candidates ( unsuccessful /under review)
 	@GET
 	@Produces("application/json")
 	@Path("/applicants/{jobID}/{jobApplicationStatus}")
 	public GetCandidateResponseDTO getUnsuccessfulCandidates(@PathParam("jobID")String jobID,@PathParam("jobApplicationStatus")String jobApplicationStatus)
 	 	{
 		ArrayList<ArrayList<String>> candidates;
 		String jobName,internal;
 		GetCandidateResponseDTO response=new GetCandidateResponseDTO();
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
				candidates=ManagerUtil.getCandidate(jobID,jobApplicationStatus, con);
				jobName=ManagerUtil.getJobName(jobID,con);
				internal=ManagerUtil.getInternal(jobID, con);
				response.setCandidateList(candidates);
				response.setJobName(jobName);
				response.setInternal(internal);
				status=200;
			}
			catch(Exception e)
			{
				System.out.println("Error Occurred in Manager Services getunsuccessfulCandidates");
 				e.printStackTrace();
 				status=500;
			}
		}
		else
		{
			status=403;
 			System.out.println("Access Denied");
		}
		response.setStatus(status);
 		
		return response;
 	}
 	
 	@PUT
 	@Produces("application/json")
 	@Path("updateInternal/{jobID}/{internalValue}")
 	public Response updateInternal(@PathParam("jobID")String jobID,@PathParam("internalValue")String internalValue)
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
			try{
				ManagerUtil.updateInternal(jobID, internalValue, con);
				status=204;
			}
			catch(Exception e)
			{
				System.out.println("Error Occurred in Manager Services updateInternal");
 				e.printStackTrace();
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
 	
 	@GET
 	@Produces("application/json")
 	@Path("/reviewer/{managerID}")
 	public GetReviewerResponseDTO getReviewer(@PathParam("managerID")String managerID)
 	{	ArrayList<ArrayList<String>> reviewerList;
 		GetReviewerResponseDTO response=new GetReviewerResponseDTO();
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
				reviewerList=ManagerUtil.getReviewerList(managerID, con);
				status=200;
				response.setReviewerList(reviewerList);
			}
			catch(Exception e)
			{
				System.out.println("Error Occurred in Manager Services getReviewer");
 				e.printStackTrace();
 				status=500;
			}
		}
		else
		{
			status=403;
 			System.out.println("Access Denied");
		}
 		
		response.setStatus(status);
 		return response;
 	}
 
//Assign Reviewer to Job Application
 	@POST
 	@Produces("application/json")
 	@Consumes("application/json")
 	@Path("assignReviewer")
  public AssignReviewerResponseDTO assignReviever(AssignReviewerRequestDTO request)
  {	
 	AssignReviewerResponseDTO response=new AssignReviewerResponseDTO();
 	String flag,comment,errorRowID;
 	ArrayList<ArrayList<String>> assignedReviewers=new ArrayList<ArrayList<String>>();
 	ArrayList<String> returnValues=new ArrayList<String>();
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
				assignedReviewers=request.getAssignedReviewerList();
				returnValues=ManagerUtil.assignReviewer(assignedReviewers, con);
				
				flag=returnValues.get(0);
				comment=returnValues.get(1);
				errorRowID=returnValues.get(2);
				response.setFlag(flag);
				response.setComment(comment);
				response.setErrorRowID(errorRowID);
				status=201;
			}
			catch(Exception e)
			{
				System.out.println("Error Occurred in Manager Services assignReviewer");
 				e.printStackTrace();
 				status=500;
			}
		}
		else
		{
			status=403;
 			System.out.println("Access Denied");
		}
			
	  
	Response.status(status).build();
	
	response.setStatus(status);
	return response;
  }
 	
//Get the ShortListed Candidates after the Reviewers have reviewed the  job Applications 
@GET 
@Produces("application/json")
@Path("reviewedApplicants/{jobID}")
public ReviewedCandidateResponseDTO getReviewedApplicants(@PathParam("jobID")String jobID)
{	ReviewedCandidateResponseDTO response=new ReviewedCandidateResponseDTO();
	String jobName="";
	ArrayList<ArrayList<String>>  reviewedApplicants=new ArrayList<ArrayList<String>>();
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
				reviewedApplicants=ManagerUtil.getReviewedApplicants(jobID, con);
				jobName=ManagerUtil.getJobName(jobID, con);
				status=200;
				response.setReviewedApplicants(reviewedApplicants);
				response.setJobName(jobName);
				
			}
			catch(Exception e)
			{
				System.out.println("Error Occurred in Manager Services ReviewedApplicants");
 				e.printStackTrace();
 				status=500;
			}
		}
		else
		{
		status=403;
		System.out.println("Access Denied");
		}
		
		response.setStatus(status);
		
		return response;
		
}
 

//Short List the Applicants 
@POST
@Produces("application/json")
@Consumes("application/json")
@Path("/shortListApplicants")
public ShortListApplicantsResponseDTO shortListApplicants(ShortListApplicantsRequestDTO request)
{
	ShortListApplicantsResponseDTO response=new ShortListApplicantsResponseDTO();
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
				
				status=201;
			}
			catch(Exception e)
			{
				System.out.println("Error Occurred in Manager Services shortListApplicants");
 				e.printStackTrace();
 				status=500;
			}
		}
		else
		{
			status=403;
			System.out.println("Access Denied");
		}
	response.setStatus(status);
	return response;
}
 
 	
}