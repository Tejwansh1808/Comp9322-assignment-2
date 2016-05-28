package au.edu.unsw.soacourse.ass2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class CandidateUtil {
	
	//add Job Alert
	public static  void insertJobAlert(ArrayList<String> jobAlert,Connection con) throws Exception
	{
		String sqlString="Insert INTO JOBALERT VALUES(?,?,?,?,?,?)";
		
		PreparedStatement pd=con.prepareStatement(sqlString);
		int j=1;
		for(int i=0;i<jobAlert.size();i++)
		{
			pd.setString(j, jobAlert.get(i));
			j++;
		}
		pd.executeUpdate();
		
		
		
	}
	
	//update Job Alert
	public static void updateJobAlert(ArrayList<String> jobAlertID,String flag,String userID,Connection con) throws Exception
	{	String put="";
		String sqlString="UPDATE JOBALERT SET SUBSCRIBE=? where USERID=?";
		if(jobAlertID!=null){
		if(flag.equalsIgnoreCase("subscribe"))
		{
			PreparedStatement pd=con.prepareStatement(sqlString);
			
			for(int i=0;i<jobAlertID.size();i++)
			{
				pd.setString(1, "Yes");
				pd.setString(2, userID);
				pd.executeUpdate();
				
			}
			
			
			
		}
		else if(flag.equals("unsubscribe"))
		{
			PreparedStatement pd=con.prepareStatement(sqlString);
			
			for(int i=0;i<jobAlertID.size();i++)
			{
				pd.setString(1, "No");
				pd.setString(2, userID);
				pd.executeUpdate();
				
			}
		}
		else
		{
			System.out.println("Error Occurred in CandidateUtil.java update Job alert Flag Not Found");
		}
		}	
	}

	//Add Saved Jobs
	public static  void addSavedJobs(String jobID,String userID,String jobName,Connection con)throws Exception
	{
		String sqlString1="INSERT INTO SAVEDJOBS VALUES(?,?,?)";
		boolean empty=false;
		String sqlString2="Select * from SAVEDJOBS where USERID=? and JOB_ID=?";
		PreparedStatement pd2=con.prepareStatement(sqlString2);
		pd2.setString(1, userID);
		pd2.setString(2, jobID);
		ResultSet rs=pd2.executeQuery();
		
		if(!rs.isBeforeFirst())
		{
			empty=true;
		}
		
		if(empty==true)
		{
		PreparedStatement pd=con.prepareStatement(sqlString1);
		pd.setString(1, userID);
		pd.setString(2, jobID);
		pd.setString(3, jobName);
		pd.executeUpdate();
		}
		else
		{
			System.out.println("Already Existing!!!!");
		}
	}
	
	//get saved jobs
	static ArrayList<ArrayList> savedJobs;
	public static ArrayList<ArrayList> getSavedJobs(String userID, Connection con)throws Exception
	{
		String sqlString="Select * from SAVEDJOBS where USERID=?";
		
		PreparedStatement pd=con.prepareStatement(sqlString);
		pd.setString(1, userID);
		ResultSet rs=pd.executeQuery();
		ArrayList<String> temp;
		savedJobs=new ArrayList<ArrayList>();
		while(rs.next())
		{
			temp=new ArrayList<String>();
			temp.add(userID);
			temp.add(rs.getString("JOB_ID"));
			temp.add(rs.getString("JOB_NAME"));
			savedJobs.add(temp);
			
		}
		
		return savedJobs;
	}
	
	
	//Delete Saved Job

	public static void deleteSavedJobs(String userID,String jobID, Connection con) throws Exception
	{
		String sqlString="Delete from SAVEDJOBS where USERID=? and JOB_ID=?";
		PreparedStatement pd=con.prepareStatement(sqlString);
		pd.setString(1, userID);
		pd.setString(2, jobID);
		pd.executeUpdate();
	}
	
	//Apply Job Application
	public static void applyJob(ArrayList<String> job,Connection con) throws Exception
	{	String userID=job.get(1);
		String jobID=job.get(2);
		String sqlString1="Select * from JOBAPPLICATION where USERID=? and JOB_ID=?";
		String sqlString2="INSERT INTO JOBAPPLICATION VALUES(?,?,?,?,?,?,?,?)";
		boolean empty=false;
		PreparedStatement pd1=con.prepareStatement(sqlString1);
		pd1.setString(1, userID);
		pd1.setString(2,jobID);
		ResultSet rs=pd1.executeQuery();
		
		if(!rs.isBeforeFirst())
		{
			empty=true;
		}
		
		if(empty==true)
		{
			PreparedStatement pd2=con.prepareStatement(sqlString2);
			int j=1;
			for(int i=0;i<job.size();i++)
			{
				pd2.setString(j, job.get(i));
				j++;
			}
			
			pd2.executeUpdate();
			
			String sqlString3="Select * from JOBINTERNAL_STATUS where JOB_ID=?";
			String sqlString4="INSERT INTO JOBINTERNAL_STATUS VALUES(?,?)";
			PreparedStatement pd3=con.prepareStatement(sqlString3);
			pd3.setString(1, jobID);
			ResultSet rs3=pd3.executeQuery();
			if(!rs3.isBeforeFirst())
			{
				PreparedStatement pd4=con.prepareStatement(sqlString4);
				pd4.setString(1, jobID);
				pd4.setString(2, "start");
				pd4.executeUpdate();
			}
			else
			{
				System.out.println("Job ID Already Exists in JOBINTERVAL_STATUS");
			}
		}
		
		else
		{
			System.out.println("Job Already Applied !!!!");
		}
		
	}
	static boolean exists;
	//Check Job Application
	public static boolean checkJobApplication(String userID,String jobID, Connection con) throws Exception 
	{
		String sqlString1="Select * from JOBAPPLICATION where USERID=? and JOB_ID=?";
		PreparedStatement pd1=con.prepareStatement(sqlString1);
		pd1.setString(1, userID);
		pd1.setString(2,jobID);
		
		ResultSet rs1=pd1.executeQuery();
		
		if(!rs1.isBeforeFirst())
		{
			exists=false;
		}
		else
		{
			exists=true;
		}
		return exists;
	}
	
	//get the CV for the user
	static String cv;
	public static String getCV(String userID,Connection con)throws Exception
	{
		String sqlString="Select CV from USERPROFILE where USERID=?";
		PreparedStatement pd=con.prepareStatement(sqlString);
		pd.setString(1, userID);
		ResultSet rs=pd.executeQuery();
		while(rs.next())
		{
			cv=rs.getString("CV");
		}
		return cv;
	}
	
	//get the Resume for the user
	static String resume;
	public static String getResume(String userID,Connection con) throws Exception
	{
		String sqlString="Select RESUME from USERPROFILE where USERID=?";
		PreparedStatement pd=con.prepareStatement(sqlString);
		pd.setString(1, userID);
		ResultSet rs=pd.executeQuery();
		while(rs.next())
		{
			resume=rs.getString("RESUME");
		}
		return resume;
	}
	
	//get the Job Applications for a particular user
	static  ArrayList<ArrayList<String>> jobApplicationList;
	public static ArrayList<ArrayList<String>> getJobApplicationList(String userID,Connection con) throws Exception
	{
		String sqlString="Select JOBAPPLICATION.JOBAPPLICATIONID,JOBAPPLICATION.STATUS,JOB.JOB_ID,JOB.JOB_NAME,JOB.STATUS"
				+ " from JOB, JOBAPPLICATION where JOB.JOB_ID=JOBAPPLICATION.JOB_ID and JOBAPPLICATION.USERID=?";
		PreparedStatement pd=con.prepareStatement(sqlString);
		pd.setString(1, userID);
		ResultSet rs=pd.executeQuery();
		ArrayList<String> temp;
		jobApplicationList=new ArrayList<ArrayList<String>>();
		while(rs.next())
		{
			temp=new ArrayList<String>();
			temp.add(rs.getString(1));
			temp.add(rs.getString(2));
			temp.add(rs.getString(3));
			temp.add(rs.getString(4));
			temp.add(rs.getString(5));
			jobApplicationList.add(temp);
		}
		
		return jobApplicationList;
		
	}
	
	//Delete the Job Application if the Job Status=Open
	public static void deleteJobApplication(String jobApplicationID, Connection con) throws Exception
	{
		String sqlString="Delete from JOBAPPLICATION where JOBAPPLICATIONID=?";
		PreparedStatement pd=con.prepareStatement(sqlString);
		pd.setString(1, jobApplicationID);
		pd.executeUpdate();
	}
}
