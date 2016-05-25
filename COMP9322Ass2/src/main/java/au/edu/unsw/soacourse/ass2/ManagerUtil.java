package au.edu.unsw.soacourse.ass2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

public class ManagerUtil {

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
	
public static void insertReviewer(ArrayList<String> reviewer,Connection con) throws Exception
{
	String sqlString="INSERT INTO COMPANY_PROFILE VALUES(?,?,?,?,?)";
	PreparedStatement pd=con.prepareStatement(sqlString);
	int j=1;
	for(int i=0;i<reviewer.size();i++)
	{
		pd.setString(j, reviewer.get(i));
		j++;
	}
	pd.executeUpdate();
	
}
}
