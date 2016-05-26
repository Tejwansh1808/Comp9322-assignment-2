package au.edu.unsw.soacourse.ass2;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
		String sqlString="INSERT INTO SAVEDJOBS VALUES(?,?,?)";
		PreparedStatement pd=con.prepareStatement(sqlString);
		pd.setString(1, userID);
		pd.setString(2, jobID);
		pd.setString(3, jobName);
		pd.executeUpdate();
	}
}
