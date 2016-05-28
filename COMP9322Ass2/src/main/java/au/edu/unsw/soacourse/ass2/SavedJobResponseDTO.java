package au.edu.unsw.soacourse.ass2;

import java.util.ArrayList;

public class SavedJobResponseDTO {
int status;
ArrayList<ArrayList> savedJobs=new ArrayList<ArrayList>();
public int getStatus() {
	return status;
}
public void setStatus(int status) {
	this.status = status;
}
public ArrayList<ArrayList> getSavedJobs() {
	return savedJobs;
}
public void setSavedJobs(ArrayList<ArrayList> savedJobs) {
	this.savedJobs = savedJobs;
}
}
