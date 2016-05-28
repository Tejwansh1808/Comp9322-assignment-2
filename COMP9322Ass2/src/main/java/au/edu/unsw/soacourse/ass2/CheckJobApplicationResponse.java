package au.edu.unsw.soacourse.ass2;

public class CheckJobApplicationResponse {
	
	boolean exists;
	int status;
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public boolean isExists() {
		return exists;
	}

	public void setExists(boolean exists) {
		this.exists = exists;
	}

}
