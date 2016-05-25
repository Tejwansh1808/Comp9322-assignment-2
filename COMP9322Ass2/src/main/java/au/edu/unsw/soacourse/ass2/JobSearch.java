package au.edu.unsw.soacourse.ass2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
@SuppressWarnings("rawtypes")
public class JobSearch {

	
	 static ArrayList<ArrayList> jobResults;
	
	public static ArrayList<ArrayList> jobSearch(String keyword,String skills,String status,Connection con)
	{
		String sqlString="Select * from JOB where KEYWORD=? OR SKILLS=? OR STATUS=?";
		try{
			
			
			PreparedStatement pd=con.prepareStatement(sqlString);
			pd.setString(1, keyword);
			pd.setString(2,skills);
			pd.setString(3, status);
			ResultSet rs=pd.executeQuery();
			jobResults=new ArrayList<ArrayList>();
			ArrayList temp;
			while(rs.next())
			{
				temp=new ArrayList<String>();
				temp.add(rs.getString("JOB_ID"));
				temp.add(rs.getString("JOB_NAME"));
				temp.add(rs.getString("COMPANYPROFILE_ID"));
				temp.add(rs.getString("SALARY_RATE"));
				temp.add(rs.getString("POSITION_TYPE"));
				temp.add(rs.getString("LOCATION"));
				temp.add(rs.getString("JOB_DESCRIPTION"));
				temp.add(rs.getString("STATUS"));
				temp.add(rs.getString("KEYWORD"));
				temp.add(rs.getString("SKILLS"));
				jobResults.add(temp);
			}
			
			
			System.err.println("Job Result Created");
				
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			
			System.out.println("Error Occurred in service jobSearch.java");
		}
		return jobResults;
	}
	
	public static ArrayList<ArrayList> jobSearch(String jobID, Connection con)
	{
		String sqlString="Select * from JOB where JOB_ID=?";
		try{
			
			
			PreparedStatement pd=con.prepareStatement(sqlString);
			pd.setString(1, jobID);
			ResultSet rs=pd.executeQuery();
			jobResults=new ArrayList<ArrayList>();
			ArrayList temp;
			while(rs.next())
			{
				temp=new ArrayList<String>();
				temp.add(rs.getString("JOB_ID"));
				temp.add(rs.getString("JOB_NAME"));
				temp.add(rs.getString("COMPANYPROFILE_ID"));
				temp.add(rs.getString("SALARY_RATE"));
				temp.add(rs.getString("POSITION_TYPE"));
				temp.add(rs.getString("LOCATION"));
				temp.add(rs.getString("JOB_DESCRIPTION"));
				temp.add(rs.getString("STATUS"));
				temp.add(rs.getString("KEYWORD"));
				temp.add(rs.getString("SKILLS"));
				jobResults.add(temp);
			}
			
			
			System.err.println("Job Result Created");
				
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			
			System.out.println("Error Occurred in service jobSearch.java");
		}
		return jobResults;
	}

}
