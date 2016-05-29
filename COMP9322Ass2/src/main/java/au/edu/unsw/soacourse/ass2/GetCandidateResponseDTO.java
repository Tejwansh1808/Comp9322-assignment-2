package au.edu.unsw.soacourse.ass2;

import java.util.ArrayList;

public class GetCandidateResponseDTO {

	int status;
	ArrayList<ArrayList<String>> candidateList=new ArrayList<ArrayList<String>>();
	String jobName,internal;
	public String getInternal() {
		return internal;
	}
	public void setInternal(String internal) {
		this.internal = internal;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public ArrayList<ArrayList<String>> getCandidateList() {
		return candidateList;
	}
	public void setCandidateList(
			ArrayList<ArrayList<String>> candidateList) {
		this.candidateList = candidateList;
	}
	public String getJobName() {
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
}
