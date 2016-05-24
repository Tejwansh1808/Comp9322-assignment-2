package au.edu.unsw.soacourse.ass2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;



@Path("/foundIT")
public class FoundITServices {
	Connection con;
	JDBC_Connection jd;
	int status;
	
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
		
		String userID,name,email,dob,currentPosition,currentCompany,highestEducation,pastExperience,professionalSkills,resume,coverLetter;
		if(flag.equalsIgnoreCase("register"))
		{
		try{
			userID=request.getUserID();
			name=request.getName();
			email=request.getEmail();
			dob=request.getDob();
			currentPosition=request.getCurrentPosition();
			currentCompany=request.getCurrentCompany();
			highestEducation=request.getHighestEducation();
			pastExperience=request.getPastExperience();
			professionalSkills=request.getProfessionalSkills();
			resume=request.getResume();
			coverLetter=request.getCoverLetter();
			String sqlString="Insert into USERPROFILE (USERID,NAME,EMAIL,DOB,CURRENT_POSITION,CURRENT_COMPANY,HIGHEST_EDUCATION,PAST_EXPERIENCE,"
					+ "PROFESSIONAL_SKILLS,RESUME,CV) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement pd=con.prepareStatement(sqlString);
			pd.setString(1, userID);
			pd.setString(2, name);
			pd.setString(3, email);
			pd.setString(4, dob);
			pd.setString(5, currentPosition);
			pd.setString(6,currentCompany);
			pd.setString(7, highestEducation);
			pd.setString(8, pastExperience);
			pd.setString(9, professionalSkills);
			pd.setString(10, resume);
			pd.setString(11, coverLetter);
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
				name=request.getName();				
				dob=request.getDob();
				currentPosition=request.getCurrentPosition();
				currentCompany=request.getCurrentCompany();
				highestEducation=request.getHighestEducation();
				pastExperience=request.getPastExperience();
				professionalSkills=request.getProfessionalSkills();
				resume=request.getResume();
				coverLetter=request.getCoverLetter();
				String sqlString="UPDATE USERPROFILE SET NAME=?,DOB=?,CURRENT_POSITION=?,CURRENT_COMPANY=?,HIGHEST_EDUCATION=?,"
						+ "PAST_EXPERIENCE=?,PROFESSIONAL_SKILLS=?,RESUME=?,CV=? WHERE USERID=?";
				PreparedStatement pd=con.prepareStatement(sqlString);
				
				pd.setString(1, name);
				pd.setString(2, dob);
				pd.setString(3, currentPosition);
				pd.setString(4,currentCompany);
				pd.setString(5, highestEducation);
				pd.setString(6, pastExperience);
				pd.setString(7, professionalSkills);
				pd.setString(8, resume);
				pd.setString(9, coverLetter);
				pd.setString(10, userID);
				
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
    @Path("/username")
	public Response addUsername(UserNameRegRequest request)
	
	{	
		
		
		try {
			String email=request.getEmail();
			String password=request.getPassword();
			String userID=request.getUuid();
			String sqlString="Insert INTO JOBSEEKER (USERID,EMAIL,PASSWORD) VALUES(?,?,?)";
			PreparedStatement pd=con.prepareStatement(sqlString);
			pd.setString(1, userID);
			pd.setString(2, email);
			pd.setString(3,password);
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

}
