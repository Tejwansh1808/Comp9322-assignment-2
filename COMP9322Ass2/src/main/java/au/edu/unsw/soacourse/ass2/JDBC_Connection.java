
package au.edu.unsw.soacourse.ass2;


import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;






import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

/**
 *
 * @author Tejwansh Singh
 */
public class JDBC_Connection {
    public Connection con;
    
    public Connection Connect()
    { 
    
        try{
        	System.out.println("Starting Connection Attempt");
            Class.forName("oracle.jdbc.OracleDriver");
            con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XE","SOA","soa");
            System.out.println("Attempt Successfull!! Connection Established!!!!");
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Connection Cannot be Established !!!");
            e.printStackTrace();
        }
            return con;
        
    }
    public static void main(String args[]) throws SQLException
    {
    	JDBC_Connection conn=new JDBC_Connection();
    	Connection con1=conn.Connect();
    	PreparedStatement st=con1.prepareStatement("Select * From EMP");
    	
    	
    }
  
}
