package au.edu.unsw.soacourse.ass2;

import java.util.ArrayList;

public class GetCandidateResponseDTO {

	int status;
	ArrayList<ArrayList<String>> unsuccesfulCandidateList=new ArrayList<ArrayList<String>>();
	String jobName;
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public ArrayList<ArrayList<String>> getUnsuccesfulCandidateList() {
		return unsuccesfulCandidateList;
	}
	public void setUnsuccesfulCandidateList(
			ArrayList<ArrayList<String>> unsuccesfulCandidateList) {
		this.unsuccesfulCandidateList = unsuccesfulCandidateList;
	}
	public String getJobName() {
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
}
