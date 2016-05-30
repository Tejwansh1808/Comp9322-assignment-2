package au.edu.unsw.soacourse.ass2;

import java.util.ArrayList;

public class GetReviewerJobApplicationResponseDTO {
ArrayList<ArrayList<String>> jobApplications=new ArrayList<ArrayList<String>>();
int status;
public ArrayList<ArrayList<String>> getJobApplications() {
	return jobApplications;
}
public void setJobApplications(ArrayList<ArrayList<String>> jobApplications) {
	this.jobApplications = jobApplications;
}
public int getStatus() {
	return status;
}
public void setStatus(int status) {
	this.status = status;
}
}
