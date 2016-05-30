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
//get the job internal status
static String internal;
public static String getInternal(String jobID,Connection con) throws Exception 
{
	String sqlString="Select INTERNAL from JOBINTERNAL_STATUS where JOB_ID=?";
	PreparedStatement pd=con.prepareStatement(sqlString);
	pd.setString(1, jobID);
	ResultSet rs=pd.executeQuery();
	while(rs.next())
	{
		internal=rs.getString(1);
	}
	return internal;
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
		
		reviewerList.add(temp);
	}

	
	return reviewerList;
}
//Check the values of Reviewers for being assigned 
static boolean flag=true;
public static boolean checkReviewers(String reviewerID1,String reviewerID2)
{
	if(reviewerID1=="")
	{
		flag=false;
	}
	if(reviewerID2=="")
	{
		flag=false;
	}
	if(reviewerID1=="" && reviewerID2=="")
	{
		flag=false;
	}
	if(reviewerID1.equalsIgnoreCase(reviewerID2))
	{
		flag=false;
	}
	return flag;
}

//Read and add the value of the Reviewers to respective job applications
static boolean error=false;
static String reviewerID1,reviewerID2,jobApplicationID;
static int i;
public static ArrayList<String> assignReviewer(ArrayList<ArrayList<String>> assignedReviewers,Connection con) throws Exception
{	int index=0;
	String comment,f;
	ArrayList<String> returnValues=new ArrayList<String>();
	ArrayList<String> temp;
	for(i=0;i<assignedReviewers.size();i++)
	{	temp=new ArrayList<String>();
		temp=assignedReviewers.get(i);
		 reviewerID1=temp.get(1);
		 reviewerID2=temp.get(2);
		 boolean flag;
		 flag=checkReviewers(reviewerID1, reviewerID2);
		 
		 if(flag==false)
		 {
			 error=true;
			 break;
		 }
		
		
	}
	
	if(error==true)
	{	
		index=i+1;
		//System.out.println("Error Index:"+index);
		comment="There is an error : The reviewers are the same or not provided to job application at index="+index;
		f="false";
		returnValues.add(f);
		returnValues.add(comment);
		returnValues.add(String.valueOf(index));
	}
	String jobID="";
	if(error==false)
	{
		String sqlString="INSERT INTO REVIEWER VALUES(?,?,?)";
		//System.out.println("No Error Was Found");
		for(i=0;i<assignedReviewers.size();i++)
		{
			temp=new ArrayList<String>();
			temp=assignedReviewers.get(i);
			jobApplicationID=temp.get(0);
			reviewerID1=temp.get(1);
			reviewerID2=temp.get(2);
			
			PreparedStatement pd1=con.prepareStatement(sqlString);
			pd1.setString(1, temp.get(0));
			pd1.setString(2,reviewerID1);
			pd1.setString(3, "NotDefined");
			pd1.executeUpdate();
			PreparedStatement pd2=con.prepareStatement(sqlString);
			pd2.setString(1, temp.get(0));
			pd2.setString(2,reviewerID2);
			pd2.setString(3, "NotDefined");
			pd2.executeUpdate();
			
			
			
			
		}
		
		String sqlString1="SELECT JOB_ID from JOBAPPLICATION where JOBAPPLICATIONID=?";
		PreparedStatement pd1=con.prepareStatement(sqlString1);
		pd1.setString(1, jobApplicationID);
		ResultSet rs=pd1.executeQuery();
		while(rs.next())
		{
			jobID=rs.getString(1);
		}
				
		System.out.println(jobID);
		String sqlString2="UPDATE JOBINTERNAL_STATUS SET INTERNAL=? where JOB_ID=?";
		PreparedStatement pd2=con.prepareStatement(sqlString2);
		pd2.setString(2, jobID);
		pd2.setString(1, "reviewer");
		pd2.executeUpdate();
		
		
		
		index=0;
		comment="There was no Error Update Successful!";
		f="true";
		returnValues.add(f);
		returnValues.add(comment);
		returnValues.add(String.valueOf(index));
	}
	
	return returnValues;

	
	
}

//Get Reviewed Applicants
static ArrayList<ArrayList<String>> reviewedApplicants;
public static ArrayList<ArrayList<String>> getReviewedApplicants(String jobID,Connection con) throws Exception
{	
	String jobApplicationStatus="under review";
	String sqlString="SELECT JOBAPPLICATION.JOBAPPLICATIONID,JOBAPPLICATION.USERID,JOBSEEKER.NAME,JOBSEEKER.EMAIL,JOBINTERNAL_STATUS.INTERNAL from JOBAPPLICATION,JOBSEEKER,JOBINTERNAL_STATUS where JOBSEEKER.USERID=JOBAPPLICATION.USERID and JOBINTERNAL_STATUS.JOB_ID=JOBAPPLICATION.JOB_ID and JOBAPPLICATION.JOB_ID=? and JOBAPPLICATION.STATUS=?";
	PreparedStatement pd=con.prepareStatement(sqlString);
	pd.setString(1, jobID);
	pd.setString(2, jobApplicationStatus);
	ResultSet rs=pd.executeQuery();
	reviewedApplicants=new ArrayList<ArrayList<String>>();
	String jobApplicationID="";
	ArrayList<String> temp;		
	while(rs.next())
	{
		temp=new ArrayList<String>();
		
		jobApplicationID=rs.getString(1);
		temp.add(jobApplicationID);
		temp.add(rs.getString(2));
		temp.add(rs.getString(3));
		temp.add(rs.getString(4));
		temp.add(rs.getString(5));
		temp.add(jobID);
		
		String sqlString2="Select RESULT from REVIEWER where JOBAPPLICATIONID=?";
		PreparedStatement pd2=con.prepareStatement(sqlString2);
		pd2.setString(1, jobApplicationID);
		ResultSet rs2=pd2.executeQuery();
		while(rs2.next())
		{
			temp.add(rs2.getString(1));
		}	
		reviewedApplicants.add(temp);
	}
	
	
	
	return reviewedApplicants;
}


//check for approval of Reviewers 
public static boolean checkApproval(String result1,String result2)
{
	boolean flag=false;
	if(result1.equalsIgnoreCase(result2))
	{
		if(result1.equalsIgnoreCase("yes"))
		{
		flag=true;
		}
		else
		{
		flag=false;
		}
	}
	
	else
	{
		flag=false;
	}
	
	return flag;
}

//short list the job applicants who have been reviewed and approved by the both the reviewers 
static boolean approved=true;
static String reviewerResult1,reviewerResult2;
static int k;
static String jobID;
public static ArrayList<String> shortListApplicants(ArrayList<ArrayList<String>>  shortListApplicantsList,String jobName,Connection con)throws Exception
{
	ArrayList<String> returnvalues=new ArrayList<String>();
	ArrayList<String> temp;
	
	String comment,f;
	boolean flag=false;
	String from,password,To,message,subject;
	for(k=0;k<shortListApplicantsList.size();k++)
	{
		temp=shortListApplicantsList.get(k);
		reviewerResult1=temp.get(4);
		reviewerResult2=temp.get(5);
		flag=checkApproval(reviewerResult1, reviewerResult2);
		jobID=temp.get(1);
		if(flag==true)
		{
			approved=true;
		}
		else
		{
			approved=false;
		}
		
		
		if(approved==true)
		{
			String URL="http://localhost:8080/COMP9322-Assign2-Client/interviewacceptance.jsp?jobApplicationID="+temp.get(0)+"&userID="+temp.get(2);
			String sqlString1="UPDATE JOBAPPLICATION SET STATUS=? where USERID=? and JOBAPPLICATIONID=?";
			PreparedStatement pd1=con.prepareStatement(sqlString1);
			pd1.setString(1, "short listed");
			pd1.setString(2, temp.get(2));
			pd1.setString(3, temp.get(0));
			pd1.executeUpdate();
			System.out.println("The Job application status was updated to ShortListed");
			
			from="founditservices@gmail.com";
			password="teju1808";
			To=temp.get(3);
			message="Hello, /n You have been Selected for Interview for the job "+jobName+"\n Please Click on the URL \n\n URL :"+URL+"\n Thank You ";
			subject="Short Listed for Interview for Job: "+jobName;
			Mail_Util.sendMail(from, password, To, message, subject);
			
			System.out.println("The Email Was sent!!!");
			
			String sqlString2="Insert INTO INTERVIEW VALUES(?,?,?)";
			PreparedStatement pd2=con.prepareStatement(sqlString2);
			pd2.setString(1,temp.get(0));
			pd2.setString(2,"NotDefined");
			pd2.setString(3,"NotDefined");
			pd2.executeUpdate();
			System.out.println("The Job Application was added to the Interview Table");
			
			
			
		}
		else
		{
			String sqlString1="UPDATE JOBAPPLICATION SET STATUS=? where USERID=? and JOBAPPLICATIONID=?";
			PreparedStatement pd1=con.prepareStatement(sqlString1);
			pd1.setString(1, "unsuccessful");
			pd1.setString(2, temp.get(2));
			pd1.setString(3, temp.get(0));
			pd1.executeUpdate();
			System.out.println("The Job application status was updated to unsuccessful");
			
			from="founditservices@gmail.com";
			password="teju1808";
			To=temp.get(3);
			message="Hello, \n We are Sorry to inform you that your job application for job  "+jobName+" has been unsuccessful \n\n Thank You ";
			subject="Unsuccessful  Job Application for job: "+jobName;
			Mail_Util.sendMail(from, password, To, message, subject);
			
			System.out.println("The Email Was sent!!!");
			
			
			
		}
		
	}
	
	
	
	String sqlString="UPDATE JOBINTERNAL_STATUS SET INTERNAL=? where JOB_ID=?";
	PreparedStatement pd1=con.prepareStatement(sqlString);
	pd1.setString(1, "interviewer");
	pd1.setString(2, jobID);
	pd1.executeUpdate();
	f="true";
	comment="There Was No error Process Completed";
	
	returnvalues.add(f);
	returnvalues.add(comment);
	
			
	
	return returnvalues;
}

//Background
static boolean a,b;
public static  boolean background(String name,String dl,String adr,Connection con) throws Exception
{
	String sqlString1="Select * from BACKGROUND where NAME=? and DL=?";
	String sqlString2="Select * from BACKGROUND where NAME=? and ADR=?";
	
	PreparedStatement pd1=con.prepareStatement(sqlString1);
	pd1.setString(1, name);
	pd1.setString(2, dl);
	ResultSet rs1=pd1.executeQuery();
	
	if(!rs1.isBeforeFirst())
	{
		a=false;
	}
	else
	{
		a=true;
	}
	
	PreparedStatement pd2=con.prepareStatement(sqlString2);
	pd2.setString(1, name);
	pd2.setString(2, adr);
	ResultSet rs2=pd2.executeQuery();
	
	if(!rs2.isBeforeFirst())
	{
		b=false;
	}
	else
	{
		b=true;
	}
	
	
	
	return (a||b);
}

//get the interview candidates
public static ArrayList<ArrayList<String>> getInterviewApplicants(String jobID,Connection con) throws Exception
{	ArrayList<ArrayList<String>> applicants;
	String sqlString="Select JOBAPPLICATION.JOB_ID,JOBAPPLICATION.JOBAPPLICATIONID,JOBAPPLICATION.USERID, JOBSEEKER.NAME,JOBSEEKER.EMAIL from JOBSEEKER,JOBAPPLICATION,INTERVIEW where JOBAPPLICATION.JOB_ID=? AND INTERVIEW.RESPONSE=? AND JOBAPPLICATION.STATUS=? AND JOBAPPLICATION.JOBAPPLICATIONID=INTERVIEW.JOBAPPLICATIONID AND JOBAPPLICATION.USERID=JOBSEEKER.USERID";
	PreparedStatement pd=con.prepareStatement(sqlString);
	pd.setString(1, jobID);
	pd.setString(2, "Accept");
	pd.setString(3, "short listed");
	ResultSet rs=pd.executeQuery();
	ArrayList<String> temp;
	applicants=new ArrayList<ArrayList<String>>();
	while(rs.next())
	{	
		temp=new ArrayList<String>();
		temp.add(rs.getString(1));
		temp.add(rs.getString(2));
		temp.add(rs.getString(3));
		temp.add(rs.getString(4));
		temp.add(rs.getString(5));
		applicants.add(temp);
	}
	
	return applicants;
}

//Add the Selected Applicants 
static String result;

public static ArrayList<String> addSelectedApplicants(ArrayList<ArrayList<String>> applicants,String jobID,Connection con) throws Exception
{	ArrayList<String> returnValues=new ArrayList<String>();
	String from,password,To,message,subject;
	ArrayList<String> temp;
	for(int i=0;i<applicants.size();i++)
	{
		temp=new ArrayList<String>();
		temp=applicants.get(i);
		result=temp.get(3);
		
		if(result.equalsIgnoreCase("yes"))
		{
			String sqlString1="UPDATE INTERVIEW SET RESULT=? where JOBAPPLICATIONID=? ";
			PreparedStatement pd1=con.prepareStatement(sqlString1);
			pd1.setString(1, result);
			pd1.setString(2,temp.get(0));
			pd1.executeUpdate();
			System.out.println("Interview Table Updated");
			
			String sqlString2="UPDATE JOBAPPLICATION SET STATUS=? where JOBAPPLICATIONID=? and USERID=?";
			PreparedStatement pd2=con.prepareStatement(sqlString2);
			pd2.setString(1, "selected");
			pd2.setString(2,temp.get(0));
			pd2.setString(3, temp.get(1));
			pd2.executeUpdate();
			System.out.println("Job application table updated");
			
			String jobName=getJobName(jobID, con);
			from="founditservices@gmail.com";
			password="teju1808";
			To=temp.get(2);
			message="Hello \n \n I am delited to inform you that you have been select for the job :"+jobName+"\n Please contact the concerned manager.\n"
					+ "Thank You For Applying with FoundITApp";
			subject="Interview Results for job :  "+jobName;
			Mail_Util.sendMail(from, password, To, message, subject);
			System.out.println("Email Was Sent");
			
		}
		else if(result.equalsIgnoreCase("no"))
		{
			String sqlString1="UPDATE INTERVIEW SET RESULT=? where JOBAPPLICATIONID=? ";
			PreparedStatement pd1=con.prepareStatement(sqlString1);
			pd1.setString(1, result);
			pd1.setString(2,temp.get(0));
			pd1.executeUpdate();
			System.out.println("Interview Table Updated");
			
			String sqlString2="UPDATE JOBAPPLICATION SET STATUS=? where JOBAPPLICATIONID=? and USERID=?";
			PreparedStatement pd2=con.prepareStatement(sqlString2);
			pd2.setString(1, "unsuccessful");
			pd2.setString(2,temp.get(0));
			pd2.setString(3, temp.get(1));
			pd2.executeUpdate();
			System.out.println("Job application table updated");
			
			String jobName=getJobName(jobID, con);
			from="founditservices@gmail.com";
			password="teju1808";
			To=temp.get(2);
			message="Hello \n \n I am Really Sorry to inform you that you have not been selected for the job : "+jobName
					+ "\n \n Thank You For Applying with FoundITApp";
			subject="Interview Results for job :  "+jobName;
			Mail_Util.sendMail(from, password, To, message, subject);
			System.out.println("Email Was Sent");
		}
		else
		{
			System.out.println("Wrong Response Value");
		}
			
		
		
		
		
	}
	String sqlString="UPDATE JOBINTERNAL_STATUS SET INTERNAL=? where JOB_ID=?";
	PreparedStatement pd=con.prepareStatement(sqlString);
	pd.setString(1, "end");
	pd.setString(2, jobID);
	pd.executeUpdate();
	
	System.out.println("The JobInternal_Status table has been updated");
	
	returnValues.add(getJobName(jobID, con));
	returnValues.add("true");
	returnValues.add("The Selected Applicants were added and informed!!!");
	
	return returnValues;
}

public static void main(String args[]) throws Exception
{
	ArrayList<String> temp=new ArrayList<String>();
	ArrayList<ArrayList<String>> temp1=new ArrayList<ArrayList<String>>();
	
	temp.add("ja3");
	temp.add("1223a");
	temp.add("tejwansh1808@gmail.com");
	temp.add("yes");
	temp1.add(temp);
	temp=new ArrayList<String>();
	temp.add("ja4");
	temp.add("1221a");
	temp.add("tejwansh1808@gmail.com");
	temp.add("no");
	temp1.add(temp);
	JDBC_Connection con1=new JDBC_Connection();
	Connection con;
	con=con1.Connect();
	
	addSelectedApplicants(temp1, "1000a", con);
	
}





}
