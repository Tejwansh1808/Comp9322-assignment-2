package au.edu.unsw.soacourse.ass2;

import java.util.ArrayList;

public class SelectedApplicantsRequestDTO {
	ArrayList<ArrayList<String>> applicants=new ArrayList<ArrayList<String>>();
	String jobID;
	public ArrayList<ArrayList<String>> getApplicants() {
		return applicants;
	}
	public void setApplicants(ArrayList<ArrayList<String>> applicants) {
		this.applicants = applicants;
	}
	public String getJobID() {
		return jobID;
	}
	public void setJobID(String jobID) {
		this.jobID = jobID;
	}
	
}
