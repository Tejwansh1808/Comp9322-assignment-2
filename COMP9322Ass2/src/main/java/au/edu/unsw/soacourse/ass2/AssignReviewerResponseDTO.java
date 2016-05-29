package au.edu.unsw.soacourse.ass2;

public class AssignReviewerResponseDTO {
	String flag,comment;
	int status,ErrorRowID;
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
