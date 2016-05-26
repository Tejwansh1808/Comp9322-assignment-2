package au.edu.unsw.soacourse.ass2;

import java.util.ArrayList;

public class UserProfileResponseDTO {
	int status;
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	ArrayList<String> jobSeeker=new ArrayList<String>();
	public ArrayList<String> getJobSeeker() {
		return jobSeeker;
	}
	public void setJobSeeker(ArrayList<String> jobSeeker) {
		this.jobSeeker = jobSeeker;
	}
	ArrayList<ArrayList> managerProfile=new ArrayList<ArrayList>();
	String userID;
	
	public ArrayList<ArrayList> getManagerProfile() {
		return managerProfile;
	}
	public void setManagerProfile(ArrayList<ArrayList> managerProfile) {
		this.managerProfile = managerProfile;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	
}
