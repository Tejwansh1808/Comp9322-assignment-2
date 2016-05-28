package au.edu.unsw.soacourse.ass2;

import java.util.ArrayList;

public class ManagerJobListResponseDTO {
	ArrayList<ArrayList<String>> jobList=new ArrayList<ArrayList<String>>();
	int status;
	public ArrayList<ArrayList<String>> getJobList() {
		return jobList;
	}
	public void setJobList(ArrayList<ArrayList<String>> jobList) {
		this.jobList = jobList;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
}
