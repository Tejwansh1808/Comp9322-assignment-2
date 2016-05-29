package au.edu.unsw.soacourse.ass2;

import java.util.ArrayList;

public class ShortListApplicantsRequestDTO {

	ArrayList<ArrayList<String>> shortListApplicantsList=new ArrayList<ArrayList<String>>();
	String jobName;

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public ArrayList<ArrayList<String>> getShortListApplicantsList() {
		return shortListApplicantsList;
	}

	public void setShortListApplicantsList(
			ArrayList<ArrayList<String>> shortListApplicantsList) {
		this.shortListApplicantsList = shortListApplicantsList;
	}
}
