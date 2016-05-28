package au.edu.unsw.soacourse.ass2;

import java.util.ArrayList;

public class GetApplicantResponseDTO {
	ArrayList<ArrayList<String>> applicantList=new ArrayList<ArrayList<String>>();
	int status;
	public ArrayList<ArrayList<String>> getApplicantList() {
		return applicantList;
	}
	public void setApplicantList(ArrayList<ArrayList<String>> applicantList) {
		this.applicantList = applicantList;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
}
