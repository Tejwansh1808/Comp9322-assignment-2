package au.edu.unsw.soacourse.ass2;

public class LoginReviewerResponseDTO {

	boolean login;
	int status;
	String reviewerID;
	public String getReviewerID() {
		return reviewerID;
	}
	public void setReviewerID(String reviewerID) {
		this.reviewerID = reviewerID;
	}
	public boolean getLogin() {
		return login;
	}
	public void setLogin(boolean login) {
		this.login = login;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
}
