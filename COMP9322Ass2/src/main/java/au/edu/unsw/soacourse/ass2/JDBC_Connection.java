package au.edu.unsw.soacourse.ass2;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;


public class JDBC_Connection {
	Connection con;

	public Connection connect() {
		try{
		System.out.println("Connecting!!!!");
		Class.forName("org.sqlite.JDBC");
		con = DriverManager.getConnection("jdbc:sqlite:test.db");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Connection Cannot be Established !!!");
		}
		System.out.println("Connection Established!!");
		return con;
	}
}
