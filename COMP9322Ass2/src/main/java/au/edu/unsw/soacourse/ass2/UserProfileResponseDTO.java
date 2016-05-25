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
	ArrayList<ArrayList> userProfile=new ArrayList<ArrayList>();
	String userID;
	public ArrayList<ArrayList> getUserProfile() {
		return userProfile;
	}
	public void setUserProfile(ArrayList<ArrayList> userProfile) {
		this.userProfile = userProfile;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	
}
