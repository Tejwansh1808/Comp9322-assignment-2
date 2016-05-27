package au.edu.unsw.soacourse.ass2;

import java.util.ArrayList;
@SuppressWarnings("rawtypes")
public class JobSearchReponseDTO {

ArrayList<ArrayList> jobResults=new ArrayList<ArrayList>();
ArrayList job=new ArrayList();
public ArrayList getJob() {
	return job;
}

public void setJob(ArrayList job) {
	this.job = job;
}

int status;
public int getStatus() {
	return status;
}

public void setStatus(int status) {
	this.status = status;
}

public ArrayList<ArrayList> getJobResults() {
	return jobResults;
}

public void setJobResults(ArrayList<ArrayList> jobResults) {
	this.jobResults = jobResults;
}
}
