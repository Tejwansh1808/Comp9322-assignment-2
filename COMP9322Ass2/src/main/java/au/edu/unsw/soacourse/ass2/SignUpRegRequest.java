package au.edu.unsw.soacourse.ass2;

import java.util.UUID;

public class SignUpRegRequest {
private String email,password,userID,name;

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public String getUserID() {
	return userID;
}

public void setUserID(String userID) {
	this.userID = userID;
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
}
}
