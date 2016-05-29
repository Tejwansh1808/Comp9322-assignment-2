package au.edu.unsw.soacourse.ass2;

public class AssignReviewerResponseDTO {
	String flag,comment,errorRowID;
	public String getErrorRowID() {
		return errorRowID;
	}
	public void setErrorRowID(String errorRowID) {
		this.errorRowID = errorRowID;
	}
	int status;
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
}
