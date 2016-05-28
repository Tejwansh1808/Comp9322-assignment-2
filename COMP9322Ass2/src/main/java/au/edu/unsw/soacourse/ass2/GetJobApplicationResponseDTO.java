package au.edu.unsw.soacourse.ass2;

import java.util.ArrayList;

public class GetJobApplicationResponseDTO {
	int status;
	ArrayList<ArrayList<String>> jobApplicationList=new ArrayList<ArrayList<String>>();
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public ArrayList<ArrayList<String>> getJobApplicationList() {
		return jobApplicationList;
	}
	public void setJobApplicationList(
			ArrayList<ArrayList<String>> jobApplicationList) {
		this.jobApplicationList = jobApplicationList;
	}
}
