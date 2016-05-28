package au.edu.unsw.soacourse.ass2;

public class ApplyJobRequestDTO {
String JobApplicationID,userID,jobID,status,resume,cv,dl,adr;

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

public String getResume() {
	return resume;
}

public void setResume(String resume) {
	this.resume = resume;
}

public String getCv() {
	return cv;
}

public void setCv(String cv) {
	this.cv = cv;
}

public String getDl() {
	return dl;
}

public void setDl(String dl) {
	this.dl = dl;
}

public String getAdr() {
	return adr;
}

public void setAdr(String adr) {
	this.adr = adr;
}


}
