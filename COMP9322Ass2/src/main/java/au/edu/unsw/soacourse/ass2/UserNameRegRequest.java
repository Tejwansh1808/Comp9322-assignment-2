package au.edu.unsw.soacourse.ass2;

import java.util.UUID;

public class UserNameRegRequest {
private String email,password,uuid;

public String getUuid() {
	return uuid;
}

public void setUuid(String uuid) {
	this.uuid = uuid;
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
