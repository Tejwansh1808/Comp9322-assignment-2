package au.edu.unsw.soacourse.ass2;

import java.util.ArrayList;

public class ReviewedCandidateResponseDTO {
	ArrayList<ArrayList<String>> reviewedApplicants=new ArrayList<ArrayList<String>>();
	public ArrayList<ArrayList<String>> getReviewedApplicants() {
		return reviewedApplicants;
	}
	public void setReviewedApplicants(
			ArrayList<ArrayList<String>> reviewedApplicants) {
		this.reviewedApplicants = reviewedApplicants;
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
	String jobName;
	int status;
}
