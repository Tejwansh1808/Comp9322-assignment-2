package au.edu.unsw.soacourse.ass2;

public class ApplyJobRequestDTO {
String JobApplicationID,userID,jobID,status,jobStatus;

public String getJobApplicationID() {
	return JobApplicationID;
}

public void setJobApplicationID(String jobApplicationID) {
	JobApplicationID = jobApplicationID;
}

public String getUserID() {
	return userID;
}

public void setUserID(String userID) {
	this.userID = userID;
}

public String getJobID() {
	return jobID;
}

public void setJobID(String jobID) {
	this.jobID = jobID;
}

public String getStatus() {
	return status;
}

public void setStatus(String status) {
	this.status = status;
}

public String getJobStatus() {
	return jobStatus;
}

public void setJobStatus(String jobStatus) {
	this.jobStatus = jobStatus;
}
}
