package au.edu.unsw.soacourse.ass2;

public class AddJobAlertRequestDTO {
String jobAlertID,userID,keyword,skills,positionType,subscribe;

public String getJobAlertID() {
	return jobAlertID;
}

public void setJobAlertID(String jobAlertID) {
	this.jobAlertID = jobAlertID;
}

public String getUserID() {
	return userID;
}

public void setUserID(String userID) {
	this.userID = userID;
}

public String getKeyword() {
	return keyword;
}

public void setKeyword(String keyword) {
	this.keyword = keyword;
}

public String getSkills() {
	return skills;
}

public void setSkills(String skills) {
	this.skills = skills;
}

public String getPositionType() {
	return positionType;
}

public void setPositionType(String positionType) {
	this.positionType = positionType;
}

public String getSubscribe() {
	return subscribe;
}

public void setSubscribe(String subscribe) {
	this.subscribe = subscribe;
}

}
