package au.edu.unsw.soacourse.ass2;

import java.util.ArrayList;

public class ReviewerJobApplicationDetailsResponseDTO {
	ArrayList<ArrayList<String>> jobApplicationDetails=new ArrayList<ArrayList<String>>();
	int status;
	public ArrayList<ArrayList<String>> getJobApplicationDetails() {
		return jobApplicationDetails;
	}
	public void setJobApplicationDetails(
			ArrayList<ArrayList<String>> jobApplicationDetails) {
		this.jobApplicationDetails = jobApplicationDetails;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
}
