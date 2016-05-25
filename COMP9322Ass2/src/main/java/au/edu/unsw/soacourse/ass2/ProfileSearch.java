package au.edu.unsw.soacourse.ass2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.sqlite.SQLite;

public class ProfileSearch {

    static	ArrayList<ArrayList> profileResult;
	static ArrayList<String> jobSeeker;
	static String sqlString;
	public static  ArrayList getProfile(String userID,Connection con,String type) throws Exception
	{
		
		
			
			if(type.equalsIgnoreCase("candidate"))
			{
				sqlString="Select * from JOBSEEKER, USERPROFILE where JOBSEEKER.USERID=USERPROFILE.USERID and JOBSEEKER.USERID=?";
				PreparedStatement pd=con.prepareStatement(sqlString);
				pd.setString(1, userID);
				ResultSet rs=pd.executeQuery();
				
				while(rs.next())
				{	jobSeeker=new ArrayList<String>();
					jobSeeker.add(userID);
					jobSeeker.add(rs.getString("NAME"));
					jobSeeker.add(rs.getString("EMAIL"));
					jobSeeker.add(rs.getString("DOB"));
					jobSeeker.add(rs.getString("CURRENT_POSITION"));
					jobSeeker.add(rs.getString("CURRENT_COMPANY"));
					jobSeeker.add(rs.getString("HIGHEST_EDUCATION"));
					jobSeeker.add(rs.getString("PAST_EXPERIENCE"));
					jobSeeker.add(rs.getString("PROFESSIONAL_SKILLS"));
					jobSeeker.add(rs.getString("RESUME"));
					jobSeeker.add(rs.getString("CV"));
					
				}
			}
			else if(type.equalsIgnoreCase("manager"))
			{
				
			}
			
			
		
		return jobSeeker;
	}
}
