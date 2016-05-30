package au.edu.unsw.soacourse.ass2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ReviewerUtil {


	//Login the reviewer
	static boolean login=true;
	public static boolean loginReviewer(String username,String password,Connection con)throws Exception
	{
		String sqlString="Select * from HIRING_TEAM where USERNAME=? and PASSWORD=?";
		PreparedStatement pd=con.prepareStatement(sqlString);
		pd.setString(1, username);
		pd.setString(2, password);
		ResultSet rs=pd.executeQuery();
		
		if(!rs.isBeforeFirst())
		{
			login=false;
		}
		else
		{
			login=true;
		}
		
		return login;
	}
	
	//Get Job Applications list for a reviewer
	static ArrayList<ArrayList<String>> jobApplications;
	public static ArrayList<ArrayList<String>> getJobApplications(String reviewerID,Connection con) throws Exception
	{	
		String sqlString="Select JOBAPPLICATION.JOBAPPLICATIONID,JOBSEEKER.NAME,JOBSEEKER.EMAIL from JOBAPPLICATION,JOBSEEKER, REVIEWER where JOBAPPLICATION.JOBAPPLICATIONID=REVIEWER.JOBAPPLICATIONID and JOBAPPLICATION.USERID=JOBSEEKER.USERID and REVIEWER.RESULT=? and REVIEWER.REVIEWERID=?";
		PreparedStatement pd=con.prepareStatement(sqlString);
		pd.setString(1, "NotDefined");
		pd.setString(2, reviewerID);
		ResultSet rs=pd.executeQuery();
		ArrayList<String> temp;
		jobApplications=new ArrayList<ArrayList<String>>();
		while(rs.next())
		{
			temp=new ArrayList<String>();
			temp.add(rs.getString(1));
			temp.add(rs.getString(2));
			temp.add(rs.getString(3));
			jobApplications.add(temp);
		}
		return jobApplications;
	}
	
	//Get job Application details 
	static ArrayList<ArrayList<String>> JobApplicationDetails;
	public static ArrayList<ArrayList<String>> getJobApplicationDetails(String jobApplicationID, Connection con) throws Exception
	{
		String sqlString="Select JOBAPPLICATION.JOBAPPLICATIONID, JOBAPPLICATION.JOB_ID,JOB.JOB_NAME,JOB.JOB_DESCRIPTION,JOBSEEKER.NAME,JOBSEEKER.EMAIL,USERPROFILE.DOB,USERPROFILE.PROFESSIONAL_SKILLS,JOBAPPLICATION.RESUME,JOBAPPLICATION.CV  from JOBAPPLICATION,JOB,JOBSEEKER,USERPROFILE where JOB.JOB_ID=JOBAPPLICATION.JOB_ID and JOBAPPLICATION.USERID=JOBSEEKER.USERID and JOBAPPLICATION.USERID=USERPROFILE.USERID and JOBAPPLICATION.JOBAPPLICATIONID=?";
		PreparedStatement pd=con.prepareStatement(sqlString);
		pd.setString(1, jobApplicationID);
		ResultSet rs=pd.executeQuery();
		ArrayList<String> temp;
		JobApplicationDetails=new ArrayList<ArrayList<String>>();
		while(rs.next())
		{
			temp=new ArrayList<String>();
			temp.add(rs.getString(1));
			temp.add(rs.getString(2));
			temp.add(rs.getString(3));
			temp.add(rs.getString(4));
			temp.add(rs.getString(5));
			temp.add(rs.getString(6));
			temp.add(rs.getString(7));
			temp.add(rs.getString(8));
			temp.add(rs.getString(9));
			temp.add(rs.getString(10));
			JobApplicationDetails.add(temp);
			
		}
		
		return JobApplicationDetails;
	}
	
//Update Results of the Reviewer to the Interview Table
 public static void updateReviewerJobApplication(String jobApplicationID,String reviewerID,String result,Connection con) throws Exception
 {
	 String sqlString="UPDATE REVIEWER SET RESULT=? where REVIEWERID=? and JOBAPPLICATIONID=?";
	 PreparedStatement pd=con.prepareStatement(sqlString);
	 pd.setString(1,result);
	 pd.setString(2, reviewerID);
	 pd.setString(3, jobApplicationID);
	 pd.executeUpdate();
	  
 }
}
