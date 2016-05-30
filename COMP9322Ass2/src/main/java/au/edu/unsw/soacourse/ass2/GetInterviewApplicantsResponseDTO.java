package au.edu.unsw.soacourse.ass2;

import java.util.ArrayList;

public class GetInterviewApplicantsResponseDTO {
ArrayList<ArrayList<String>> applicants=new ArrayList<ArrayList<String>>();
String jobName;
int status;
public ArrayList<ArrayList<String>> getApplicants() {
	return applicants;
}
public void setApplicants(ArrayList<ArrayList<String>> applicants) {
	this.applicants = applicants;
}
public String getJobName() {
	return jobName;
}
public void setJobName(String jobName) {
	this.jobName = jobName;
}
public int getStatus() {
	return status;
}
public void setStatus(int status) {
	this.status = status;
}
}
