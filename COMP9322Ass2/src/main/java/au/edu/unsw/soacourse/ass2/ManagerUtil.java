package au.edu.unsw.soacourse.ass2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.spi.DirStateFactory.Result;

public class ManagerUtil {
	static String companyID="";

	
//manager Login
public static void insertManagerLogin(Connection con,String managerID,String name,String email,String password) throws Exception
{
	String sqlString="INSERT INTO MANAGER VALUES(?,?,?,?)";
	PreparedStatement pd=con.prepareStatement(sqlString);
	pd.setString(1, managerID);
	pd.setString(2, name);
	pd.setString(3, email);
	pd.setString(4, password);
	pd.executeUpdate();
}

//Insert Company Profile
public static void insertCompanyProfile(ArrayList<String> profile,Connection con) throws Exception
{
	String sqlString="INSERT INTO COMPANY_PROFILE VALUES(?,?,?,?,?,?,?,?)";
	PreparedStatement pd=con.prepareStatement(sqlString);
	int j=1;
	for(int i=0;i<profile.size();i++)
	{
		pd.setString(j, profile.get(i));
		j++;
	}
	pd.executeUpdate();
	
}

//Add the Reviewers 
public static void insertReviewer(ArrayList<String> reviewer,Connection con) throws Exception
{
	String sqlString="INSERT INTO HIRING_TEAM VALUES(?,?,?,?,?)";
	PreparedStatement pd=con.prepareStatement(sqlString);
	int j=1;
	for(int i=0;i<reviewer.size();i++)
	{
		pd.setString(j, reviewer.get(i));
		j++;
	}
	pd.executeUpdate();
	
	
}


//Get the company ID for the manager
public static String getConpanyID(String manageID,Connection con)throws Exception
{
	

	String sqlString="Select * from COMPANY_PROFILE where MANAGERID=?";
	
	PreparedStatement pd=con.prepareStatement(sqlString);
	pd.setString(1, manageID);
	ResultSet rs=pd.executeQuery();
	while(rs.next())
	{
		companyID=(String)rs.getString(1);
		
	}
	
	return companyID;
			
			
}

//get Job List
static ArrayList<ArrayList<String>> jobList;
public static ArrayList<ArrayList<String>> getJobList(String managerID,Connection con) throws Exception
{	ArrayList<String> temp;

	String sqlString3="Select JOB.JOB_ID,JOB.JOB_NAME,JOB.STATUS,JOBINTERNAL_STATUS.INTERNAL,COMPANY_PROFILE.COMPANY_NAME from COMPANY_PROFILE,JOB,JOBINTERNAL_STATUS,MANAGER where COMPANY_PROFILE.MANAGERID=MANAGER.MANAGERID and JOB.JOB_ID=JOBINTERNAL_STATUS.JOB_ID and JOB.COMPANYPROFILE_ID=COMPANY_PROFILE.COMPANYPROFILE_ID AND MANAGER.MANAGERID=?";
	PreparedStatement pd2=con.prepareStatement(sqlString3);
	pd2.setString(1, managerID);

	
	ResultSet rs2=pd2.executeQuery();
	
	jobList=new ArrayList<ArrayList<String>>();
	while(rs2.next())
	{
		temp=new ArrayList<String>();
		temp.add(rs2.getString(1));
		temp.add(rs2.getString(2));
		temp.add(rs2.getString(3));
		temp.add(rs2.getString(4));
		temp.add(rs2.getString(5));
		jobList.add(temp);
		
	}
	return jobList;
}


//close a job
public static void closeJob(String jobID, Connection con)throws Exception
{
	String sqlString="UPDATE JOB SET STATUS=? where JOB_ID=?";
	PreparedStatement pd=con.prepareStatement(sqlString);
	pd.setString(1, "Close");
	pd.setString(2,jobID);
	pd.executeUpdate();
}


//get Job Applicants 
static ArrayList<ArrayList<String>> applicantList;
public static ArrayList<ArrayList<String>> getJobApplicants(String jobID,Connection con)throws Exception
{
	String sqlString="Select JOBAPPLICATION.JOBAPPLICATIONID, JOBSEEKER.USERID,JOBSEEKER.NAME,JOBAPPLICATION.DL,JOBAPPLICATION.ADR from JOBAPPLICATION,JOBSEEKER WHERE JOBAPPLICATION.USERID=JOBSEEKER.USERID AND JOBAPPLICATION.JOB_ID=?";
	
	PreparedStatement pd=con.prepareStatement(sqlString);
	pd.setString(1, jobID);
	ResultSet rs=pd.executeQuery();
	ArrayList<String> temp;
	applicantList=new ArrayList<ArrayList<String>>();
	while(rs.next())
	{
		temp=new ArrayList<String>();
		temp.add(rs.getString(1));
		temp.add(rs.getString(2));
		temp.add(rs.getString(3));
		temp.add(rs.getString(4));
		temp.add(rs.getString(5));
		applicantList.add(temp);
	}
	
	return applicantList;

}

//Update the status of the job application
public static void updateJobApplicationStatus(String jobApplicationID,String update,Connection con) throws Exception
{
	String sqlString="UPDATE JOBAPPLICATION SET STATUS=? where JOBAPPLICATIONID=? ";
	PreparedStatement pd=con.prepareStatement(sqlString);
	pd.setString(1, update);
	pd.setString(2,jobApplicationID);
	pd.executeUpdate();
	
	
}

//Get the Unsuccessful Job Applicants
static ArrayList<ArrayList<String>> candidateList;
public static ArrayList<ArrayList<String>> getCandidate(String jobID,String jobApplicationStatus,Connection con)throws Exception
{	
	
	String sqlString="Select JOBAPPLICATION.JOBAPPLICATIONID,JOBAPPLICATION.USERID,JOBSEEKER.NAME,JOBSEEKER.EMAIL from JOBAPPLICATION,JOBSEEKER where JOBAPPLICATION.USERID=JOBSEEKER.USERID and JOBAPPLICATION.JOB_ID=? and JOBAPPLICATION.STATUS=?";
	PreparedStatement pd=con.prepareStatement(sqlString);
	pd.setString(1, jobID);
	pd.setString(2, jobApplicationStatus);
	ResultSet rs=pd.executeQuery();
	ArrayList<String> temp;
	candidateList=new ArrayList<ArrayList<String>>();
	while(rs.next())
	{	temp=new ArrayList<String>();
		temp.add(rs.getString(1));
		temp.add(rs.getString(2));
		temp.add(rs.getString(3));
		temp.add(rs.getString(4));
		temp.add(jobID);
		candidateList.add(temp);
	}
	
	return candidateList;
}

//get the job name
static String jobName;
public static String getJobName(String jobID,Connection con) throws Exception
{
	
	String sqlString="Select JOB_NAME from JOB where JOB_ID=?";
	PreparedStatement pd=con.prepareStatement(sqlString);
	pd.setString(1, jobID);
	ResultSet rs=pd.executeQuery();
	while(rs.next())
	{
		jobName=rs.getString(1);
	}
	
	return jobName;
}

//Update Internal Value
public static void updateInternal(String jobID,String internalValue,Connection con)throws Exception
{
	String sqlString="UPDATE JOBINTERNAL_STATUS SET INTERNAL=? where JOB_ID=?";
	PreparedStatement pd=con.prepareStatement(sqlString);
	pd.setString(1, internalValue);
	pd.setString(2, jobID);
	pd.executeUpdate();
	
}

//Get Reviewer List for a particular Manager
static ArrayList<ArrayList<String>> reviewerList;
public static ArrayList<ArrayList<String>> getReviewerList(String managerID,Connection con) throws Exception
{	
	String sqlString="Select * from HIRING_TEAM where MANAGERID=?";
	
	PreparedStatement pd=con.prepareStatement(sqlString);
	pd.setString(1, managerID);
	ResultSet rs=pd.executeQuery();
	ArrayList<String> temp;
	reviewerList=new ArrayList<ArrayList<String>>();
	while(rs.next())
	{
		temp=new ArrayList<String>();
		temp.add(rs.getString(1));
		temp.add(rs.getString(2));
		temp.add(rs.getString(3));
		temp.add(rs.getString(4));
		temp.add(rs.getString(5));
		reviewerList.add(temp);
	}

	
	return reviewerList;
}
}
