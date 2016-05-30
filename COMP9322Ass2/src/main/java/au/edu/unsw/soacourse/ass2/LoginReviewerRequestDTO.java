package au.edu.unsw.soacourse.ass2;

public class LoginReviewerRequestDTO {
	String email;
	String password;
	public String getEmail() {
		return email;
	}
	public void setEmail(String username) {
		this.email = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
