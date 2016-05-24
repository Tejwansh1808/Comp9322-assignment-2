package au.edu.unsw.soacourse.ass2;

import java.util.UUID;

public class SignUpRegRequest {
private String email,password,uuid,name;

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

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
