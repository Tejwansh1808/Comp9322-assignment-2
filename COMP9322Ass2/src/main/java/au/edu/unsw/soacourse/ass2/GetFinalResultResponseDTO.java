package au.edu.unsw.soacourse.ass2;

import java.util.ArrayList;

public class GetFinalResultResponseDTO {
	int status;
	ArrayList<ArrayList<String>> finalApplicants;
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public ArrayList<ArrayList<String>> getFinalApplicants() {
		return finalApplicants;
	}
	public void setFinalApplicants(ArrayList<ArrayList<String>> finalApplicants) {
		this.finalApplicants = finalApplicants;
	}
}
