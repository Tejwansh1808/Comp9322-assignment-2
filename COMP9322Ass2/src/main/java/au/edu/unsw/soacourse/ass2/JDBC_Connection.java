package au.edu.unsw.soacourse.ass2;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;


public class JDBC_Connection {
	Connection con;

	public Connection connect() {
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
}
