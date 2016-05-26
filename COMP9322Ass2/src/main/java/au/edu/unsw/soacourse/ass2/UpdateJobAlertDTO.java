package au.edu.unsw.soacourse.ass2;

import java.util.ArrayList;

public class UpdateJobAlertDTO {
ArrayList<String> jobALertID=new ArrayList<String>();
String userID;

public String getUserID() {
	return userID;
}

public void setUserID(String userID) {
	this.userID = userID;
}

public ArrayList<String> getJobALertID() {
	return jobALertID;
}

public void setJobALertID(ArrayList<String> jobALertID) {
	this.jobALertID = jobALertID;
}
}
